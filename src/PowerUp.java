import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class PowerUp {
	//I made icon and rand seperate but they might work well as the same variable, this is possible changing rand.png to PU6.png
	BufferedImage icon;
	BufferedImage rand;
	int id;
	int x,y;
	
	
	private Random r= new Random();
	private	Graphics2D g2 ;
	
	
	
	PowerUp(int iconNum){
		try {
			icon = ImageIO.read(new File("PowerUps\\PU" + iconNum + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			rand = ImageIO.read(new File("PowerUps\\Rand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		id = iconNum;

	}

	public BufferedImage getIcon(){
		return icon;
	}
	public BufferedImage getRand(){
		return rand;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y=y;
	
	}
	public int getY(){
		return y;
	}
	public void Spawn() {

		setX(r.nextInt(100)+150);
		setY(r.nextInt(180)+50);
		
		
	}
	
}
