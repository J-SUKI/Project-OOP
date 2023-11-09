package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Fish extends Entity {
     GamePanel gp;
     boolean movingRight;
     public BufferedImage fishImage;
     Random random;
     boolean visible = true;
     boolean isAlive;
     boolean gotHit;

     public Fish(GamePanel gp) {
         this.gp = gp;
         random = new Random();
         loadFishImage(); 
         setDefaultValues();
         isAlive = true;
     }

     public boolean isAlive() {
         return isAlive;
     }
     public boolean gotHit() {
         return gotHit;
     }
     public void setHit(boolean gotHit) {
         this.gotHit = gotHit;
     }

     void setDefaultValues() {
        // สุ่มตำแหน่งเริ่มต้นในแกน y         
    	y = random.nextInt(350) + 350; 
        speed = 3;
        movingRight = true;
    }

    //random ภาพปลาที่เกิด
    void loadFishImage() {
         try {
             int imageNumber = random.nextInt(4) ;
             String imagePath = "/player/fish" + imageNumber + ".png";
             fishImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    //การเคลื่อนที่
    public void update() {
        if (movingRight) {
            x += speed;
            if (x >= gp.getWidth()) {
                isAlive = false; 
            }
        } else {
            x -= speed;
            if (x <= -120) {
                isAlive = false; 
            }
        }

    
    }


    public void draw(Graphics2D g2) {
        if (visible) {
            g2.drawImage(fishImage, x, y, 120, 120, null);
        }
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
}
