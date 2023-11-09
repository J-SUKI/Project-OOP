package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[]tile;
	
	public TileManager(GamePanel gp) {
		
		this.gp=gp;
		tile = new Tile[10];
		getTileImage();
	}
	public void getTileImage() {
		try{
			tile[0] = new Tile();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/bg/sand.png"));
			
			
			tile[1] = new Tile();
			tile[1].image=ImageIO.read(getClass().getResourceAsStream("/bg/sand3.png"));
			
			tile[2] = new Tile();
			tile[2].image=ImageIO.read(getClass().getResourceAsStream("/bg/Water.png"));
			
			tile[3] = new Tile();
			tile[3].image=ImageIO.read(getClass().getResourceAsStream("/bg/Water2.png"));
			
			tile[4] = new Tile();
			tile[4].image=ImageIO.read(getClass().getResourceAsStream("/bg/dirt_grass.png"));
			
			tile[5] = new Tile();
			tile[5].image=ImageIO.read(getClass().getResourceAsStream("/bg/sky.png"));
			
			tile[6] = new Tile();
			tile[6].image=ImageIO.read(getClass().getResourceAsStream("/bg/skybox_top.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	public void draw(Graphics2D g2, int x, int y, int screenWidth) {
        int tileWidth = screenWidth / 10; 
        for (int i = 0; i < 11; i++) {
            g2.drawImage(tile[0].image, x + i * tileWidth, y, tileWidth, tileWidth, null);
        }
        
        
        for (int i = 0; i < 11; i++) {
            g2.drawImage(tile[2].image, x + i * tileWidth, y-150, tileWidth, tileWidth, null);
            g2.drawImage(tile[1].image, x + i * tileWidth, y-40, tileWidth, tileWidth, null);
            g2.drawImage(tile[2].image, x + i * tileWidth, y-240, tileWidth, tileWidth, null);
            g2.drawImage(tile[2].image, x + i * tileWidth, y-340, tileWidth, tileWidth, null);
            g2.drawImage(tile[4].image, x + i * tileWidth, y-520, tileWidth, tileWidth, null); 
            g2.drawImage(tile[3].image, x + i * tileWidth, y-450, tileWidth, tileWidth, null);            
            g2.drawImage(tile[5].image, x + i * tileWidth, y-630, tileWidth, tileWidth, null); 
            g2.drawImage(tile[6].image, x + i * tileWidth, y-740, tileWidth, tileWidth, null);
            g2.drawImage(tile[6].image, x + i * tileWidth, y-760, tileWidth, tileWidth, null);

        }
        
     

        
        
        
    }
}






