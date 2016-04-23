import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;

public class Animator {
	
	private GameCanvas panel;
	private Game main;
	private Graphics g;
	
	private BufferedImage[] rain;
	private int rainTicker = 0;
	private int rainTimer = 3;
	private BufferedImage currentRainFrame;
	private BufferedImage[] clouds;
	private Cloud[] foreClouds, aftClouds;
	private BufferedImage[] pongavis;
	
	private int[] pongavi_position1 = {Game.WIDTH_MIDPOINT - 48, Game.HEIGHT_MIDPOINT - 32};
	private int[] pongavi_position2 = {Game.WIDTH_MIDPOINT - 16, Game.HEIGHT_MIDPOINT};
	private int[] pongavi_position3 = {Game.WIDTH_MIDPOINT + 16, Game.HEIGHT_MIDPOINT};
	private int[] pongavi_position4 = {Game.WIDTH_MIDPOINT - 32, Game.HEIGHT_MIDPOINT + 32};
	private int[] pongavi_position5 = {Game.WIDTH_MIDPOINT, Game.HEIGHT_MIDPOINT + 32};
	
	public Animator(GameCanvas panel, Game main, Graphics g2d)
	{
		this.panel = panel;
		this.main = main;
		this.g = g2d;
		loadTitleScreenAssets();
		loadSelectScreenAssets();
	}
	
	public void titleScreen()
	{
		g.setColor(Color.BLACK);
		g.fillRect(0,0,main.getWidth(),main.getHeight());
		
		if(rainTicker%rainTimer == 0)
			currentRainFrame = rain[rainTicker/rainTimer];
		g.drawImage(currentRainFrame, 0, 0, null);
		rainTicker++;
		if(rainTicker >= (7*rainTimer)+(rainTimer-1))
			rainTicker = 0;
		
		for(Cloud cloud: aftClouds){
			g.drawImage(cloud.getImage(), cloud.getX(), cloud.getY(), null);
			cloud.move();
		}
		for(Cloud cloud: foreClouds){
			g.drawImage(cloud.getImage(), cloud.getX(), cloud.getY(), null);
			cloud.move();
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Pong", main.WIDTH_MIDPOINT - 10, main.HEIGHT_MIDPOINT);
		
		
	}
	
	public void selectPaddle()
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
		g.setColor(Color.WHITE);
		
		//work in progress. See
		g.drawImage(pongavis[0], pongavi_position1[0], pongavi_position1[1], null); //normal
		g.drawImage(pongavis[1], pongavi_position2[0], pongavi_position2[1], null); //long
		g.drawImage(pongavis[2], pongavi_position3[0], pongavi_position3[1], null); //short
		g.drawImage(pongavis[3], pongavi_position4[0], pongavi_position4[1], null); //brick
		g.drawImage(pongavis[4], pongavi_position5[0], pongavi_position5[1], null); //lucille
		
		
	}
	
	public void mainGame()
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
        g.setColor(Color.WHITE);

        g.fillRect(main.paddle1.getX(), main.paddle1.getY(), main.paddle1.getWidth(), main.paddle1.getLength());
        g.fillRect(main.paddle2.getX(), main.paddle2.getY(), main.paddle2.getWidth(), main.paddle2.getLength());
        
        g.fillOval(main.ball.getX(), main.ball.getY(), main.ball.getSize(), main.ball.getSize());
	}
	
	private void loadTitleScreenAssets(){
		rain = new BufferedImage[8];
		for(int i = 0; i < 8; i++){
			try {
				rain[i] = ImageIO.read(new File("RainFrames\\RainFrame" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		currentRainFrame = rain[0];
		
		clouds = new BufferedImage[5];
		for(int i = 0; i < 5; i++){
			try {
				clouds[i] = ImageIO.read(new File("RainClouds\\RainCloud" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int numOfClouds = 6;
		foreClouds = new Cloud[numOfClouds];
		int cloudSpaceCounter = 0;
		for(Cloud cloud: foreClouds){
			cloud = new Cloud(main.getWidth());
			int intermediary = (int) Math.floor((Math.random())*5);
			cloud.setImage(clouds[intermediary == 5?4:intermediary]);
			cloud.setX((((main.getWidth()+cloud.getWidth())/numOfClouds)*cloudSpaceCounter)-(cloud.getWidth()/2));
			cloud.setY(-90);
			cloud.setSpeed(2);
			foreClouds[cloudSpaceCounter] = cloud;
			cloudSpaceCounter++;
		}

		aftClouds = new Cloud[numOfClouds];
		cloudSpaceCounter = 0;
		for(Cloud cloud: aftClouds){
			cloud = new Cloud(main.getWidth());
			int intermediary = (int) Math.floor((Math.random())*5);
			cloud.setImage(clouds[intermediary == 5?4:intermediary]);
			cloud.setX((((main.getWidth()+cloud.getWidth())/numOfClouds)*cloudSpaceCounter)-(cloud.getWidth()/2));
			cloud.setY(-60);
			aftClouds[cloudSpaceCounter] = cloud;
			cloudSpaceCounter++;
		}
	}
	
	private void loadSelectScreenAssets()
	{
		pongavis = new BufferedImage[5];
		
		for(int i = 1; i <= 5; i++)
			try {
				pongavis[i - 1] = ImageIO.read(new File("Pongavis\\pongavi" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

