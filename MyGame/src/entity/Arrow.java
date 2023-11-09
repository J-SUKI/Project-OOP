package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.util.List;

public class Arrow extends Entity {
    GamePanel gp;
    boolean visible;
    public BufferedImage arrowImage;

    public Arrow(int startX, int startY, GamePanel gp) {
        this.x = startX;
        this.y = startY;
        this.speed = 35;
        this.visible = true;
        this.gp = gp; 
        
        try {
            arrowImage = ImageIO.read(getClass().getResourceAsStream("/player/Arrow2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (visible) {
            y += speed;
            if (y > gp.getHeight()) {
                visible = false;
            } else {
                for (Fish fish : gp.getFishes()) {
                    if (fish.isAlive() && Math.abs(x - fish.x) <= 50 && Math.abs(y - fish.y) <= 50) {
                        fish.setAlive(false); //ไม่มีชีวิต
                        fish.setHit(true); //ยิงโดน
                        visible = false; //ลบธนู
                        if (fish.gotHit()) {
                            gp.increaseScore(50);
                        }
                        break;
                    }
                  
                }
                for (Trash trash : gp.getTrashes()) {
                    if (Math.abs(x - trash.x) <= 50 && Math.abs(y - trash.y) <= 50) {
                        trash.visible = false;
                        gp.increaseScore(-50);
                        visible = false;
                        break;
                    }
                }
            }
        } else {
            gp.getFishes().removeIf(fish -> !fish.isAlive());
        }
    }

    public boolean isVisible() {  
        return visible;
    }
    
    public void draw(Graphics2D g2) {
        if (visible) {
            g2.drawImage(arrowImage, x+100, y+120 ,170,170, null);
        }
    }
}
