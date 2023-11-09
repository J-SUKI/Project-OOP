package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Trash extends Entity {
    GamePanel gp;
    boolean movingRight;
    public BufferedImage TrashImage;
    Random random;
	public boolean visible;

	public Trash(GamePanel gp) {
	    this.gp = gp;
	    random = new Random();
	    loadTrashImage(); 
	    setDefaultValues();
	}

	void setDefaultValues() {
	    y = random.nextInt(350) + 330; 
	    speed = 2;
	    movingRight = true;
	    visible = true; 
	}


    void loadTrashImage() {
        try {
            TrashImage = ImageIO.read(getClass().getResourceAsStream("/player/trash.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (movingRight) {
            x += speed;
            if (x >= gp.getWidth()) {
                
            }
        }
        
    }

    public void draw(Graphics2D g2) {
    	if (visible) {
            g2.drawImage(TrashImage, x, y, 250, 250, null);
        }
    }
    
}
