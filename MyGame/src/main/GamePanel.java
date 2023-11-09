package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import entity.*;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    int width = 400;
    int height = 500;
    int scale = 3;
    int FPS = 60;
    
    public Arrow arrow;
    public List<Fish> fishes;
    public KeyHandler keyH = new KeyHandler();
    public Thread gameThread;
    public Cat cat = new Cat(this, keyH);
    public Fish fish = new Fish(this);
    public TileManager tileM = new TileManager(this);
    public Trash trash= new Trash(this);
    public List<Trash> trashes;
    
    
    
    int score; 
    private int countdown = 60;
    
    
    
    
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(width * scale, height * scale));
        setDoubleBuffered(true);
        addKeyListener(keyH);
        setFocusable(true);
        
        fishes = new ArrayList<>(); // สร้าง ArrayList สำหรับ Fish
        startFishSpawner(); 
        
        trashes = new ArrayList<>();
        startTrashSpawner();
   
        arrow = new Arrow(cat.x, cat.y, this);
        startCountdownTimer(); 
    }
    

    public void startCountdownTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countdown > 0) {
                    countdown--;
                } else {
                    ((Timer) e.getSource()).stop(); 
                }
            }
        });
        timer.start(); 
    }


    
    public List<Fish> getFishes() {
        return fishes;
    }
    
    public List<Trash> getTrashes() {
        return trashes;
    }
    
    // เพิ่มคะแนน
    public void increaseScore(int points) {
        score += points;
        repaint();
    }
    

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    // ความเร็วในการวาดภาพ 
    @Override
    public void run() {
        double drawInterval = 600000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
            update();
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 100000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    // Spawner Fish
    public void startFishSpawner() {
        Thread fishSpawner = new Thread(() -> {
            while (true) {
                Fish newFish = new Fish(this);
                fishes.add(newFish);
                try {
                    Thread.sleep(2000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fishSpawner.start();
    }
    
    // Spawner Trash
    public void startTrashSpawner() {
        Thread trashSpawner = new Thread(() -> {
            while (true) {
                Trash newTrash = new Trash(this);
                trashes.add(newTrash);
                
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        trashSpawner.start();
    }
    
    
    public void update() {

        cat.update();
        fish.update();
        
        // ปลา
        List<Fish> fishesToRemove = new ArrayList<>(); // ลิสต์เก็บปลา
        for (Fish f : fishes) { 
            f.update();
            if (!f.isAlive()) {
                fishesToRemove.add(f); //ปลาที่ลบ
            }
        }
        fishes.removeAll(fishesToRemove); // ลบปลาที่ต้องการลบออกจาก ArrayList

        //ธนู
        if (keyH.spacePressed) {
            if (!arrow.isVisible()) {
                arrow = new Arrow(cat.x, cat.y, this); // สร้างรูป Arrow ที่ตำแหน่งของ Cat
            }
        }
        
        //ขยะ
        for (Trash trash : trashes) {
            trash.update();
        }
        
        arrow.update();
        
        
        
        
    }


    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        int bgX = 0;
        int bgY = getHeight() - 100;
        tileM.draw(g2, bgX, bgY, getWidth());
        
        for (Fish f : fishes) { 
            f.draw(g2);
        }
        
        for (Trash trash : trashes) {
            trash.draw(g2);
        }
        cat.draw(g2);
        arrow.draw(g2);
        
        //Score+Time
        Font font = new Font("Arial", Font.ITALIC, 30);
        g2.setFont(font);
        g2.setColor(Color.BLACK);
        g2.drawString("Score: " + score, 900, 70);  
        g2.drawString("Time: " + countdown, 900, 110); 
        
        if ( score < 0) { 
            g2.setColor(Color.RED);
            Font font1 = new Font("Arial", Font.ITALIC, 60);
            g2.setFont(font1);
            g2.drawString("Game Over", (int) (getWidth() / 2.5 - 50), getHeight() / 2); 
            gameThread = null;
        }
        if (countdown == 0 ) { 
            g2.setColor(Color.BLACK);
            Font font2 = new Font("Arial", Font.ITALIC, 50);
            g2.setFont(font2);
            g2.drawString("Time's up!!", (int) (getWidth() / 2.5 - 50), getHeight() / 2); 
            g2.drawString("Score = "+score, (int) (getWidth() / 2.7 - 50), (getHeight() / 2)+ 70); 
            gameThread = null;
        }
         
        g2.dispose();
    }

}
