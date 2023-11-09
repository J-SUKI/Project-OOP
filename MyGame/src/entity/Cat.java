package entity;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class  Cat extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	
	public Cat(GamePanel gp,KeyHandler keyH) {
		this.gp =gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getCatImage();
	}
	public void setDefaultValues() {
		x=160;
		y=85;
		speed=8;
		
		direction="left";
		direction="right";
	}
	
	public void getCatImage() {
		try{
			left=ImageIO.read(getClass().getResourceAsStream("/player/cat02.png"));
			right=ImageIO.read(getClass().getResourceAsStream("/player/cat01.png"));
			

		}catch(IOException e) {
			e.printStackTrace();
			
		}
	
	}
	
	
	
	
	public void update() {
		if(keyH.leftPressed == true){
			direction="left";
            x -= speed;
        }
        else if(keyH.rightPressed == true ){
        	direction="right";
            x += speed;
        }
		
	}
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		switch(direction) {
		case "left":
			image = left;
			break;
		case "right":
			image = right;
			break;	
		}
		g2.drawImage(image, x, y,350,350,null);
	}
	

}
