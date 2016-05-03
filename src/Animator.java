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
	
	//keys we care about
	int p1_moveUpKey = 87;
	int p1_moveDownKey = 83;
	int p2_moveUpKey = 38; 
	int p2_moveDownKey = 40;
	
	private int desc_y =  0;
	private int descWidth = 200;
	
	private int p1_x = 0;
	
	private BufferedImage title;
	private BufferedImage[] rain;
	private BufferedImage[] field;
	private int rainTicker = 0;
	private int rainTimer = 3;
	private int fieldTicker = 0;
	private int fieldTimer = 5;
	private int fieldLightningTimer = 0;
	private BufferedImage currentRainFrame;
	private BufferedImage currentFieldFrame;
	private BufferedImage scoreNumbers[];
	private BufferedImage[] clouds;
	private Cloud[] foreClouds, aftClouds;
	private BufferedImage[] pongavis;
	private BufferedImage[] characters, names;
	
	
	private int[] pongavi_position1 = {Game.WIDTH_MIDPOINT - 48, Game.HEIGHT_MIDPOINT - 32};
	private int[] pongavi_position2 = {Game.WIDTH_MIDPOINT - 16, Game.HEIGHT_MIDPOINT - 32};
	private int[] pongavi_position3 = {Game.WIDTH_MIDPOINT + 16, Game.HEIGHT_MIDPOINT - 32};
	private int[] pongavi_position4 = {Game.WIDTH_MIDPOINT - 32, Game.HEIGHT_MIDPOINT};
	private int[] pongavi_position5 = {Game.WIDTH_MIDPOINT, Game.HEIGHT_MIDPOINT};
	
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
		g.drawImage(title, Game.WIDTH_MIDPOINT - 57, Game.HEIGHT_MIDPOINT-28, null);
		g.drawString("-PRESS SPACE-", Game.WIDTH_MIDPOINT-36, Game.HEIGHT_MIDPOINT+20);
		g.drawString("Music: Unite Synchronization by Toby Fox", main.getWidth()-227, main.getHeight()-5);
		
		
	}
	
	public void selectPaddle()
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
		g.setColor(Color.WHITE);
		
		if(fieldTicker%fieldTimer == 0){
			if(fieldTicker/fieldTimer == 5 && fieldLightningTimer != 0){
				fieldTicker = 0;
				fieldLightningTimer--;
			}
			currentFieldFrame = field[fieldTicker/fieldTimer];
		}
		g.drawImage(currentFieldFrame, 0, 0, null);
		fieldTicker++;
		if(fieldTicker >= (19*fieldTimer)+(fieldTimer-1)){
			fieldTicker = 0;
			fieldLightningTimer = 15;
		}
			
		g.drawImage(pongavis[0], pongavi_position1[0], pongavi_position1[1], null); //normal
		g.drawImage(pongavis[1], pongavi_position2[0], pongavi_position2[1], null); //long
		g.drawImage(pongavis[2], pongavi_position3[0], pongavi_position3[1], null); //short
		g.drawImage(pongavis[3], pongavi_position4[0], pongavi_position4[1], null); //brick
		g.drawImage(pongavis[4], pongavi_position5[0], pongavi_position5[1], null); //lucille
		
		g.drawImage(main.manager.p1.getImage(), main.manager.p1.getX(), main.manager.p1.getY(), null);
		g.setColor(Color.BLUE);
		
		g.drawImage(getName(main.manager.p1.getDesc()), 0, 0, null);
		g.drawImage(getName(main.manager.p2.getDesc()), main.getWidth()-(getName(main.manager.p2.getDesc()).getWidth()), 0, null);
		g.drawImage(getHeadshot(main.manager.p1.getDesc()), 0, 64, null);
		g.drawImage(getHeadshot(main.manager.p2.getDesc()), main.getWidth(), 64, -getHeadshot(main.manager.p2.getDesc()).getWidth(), getHeadshot(main.manager.p2.getDesc()).getHeight(), null);
		
		
		//g.drawRect(p1_x, desc_y, descWidth, main.getHeight());
		g.setColor(Color.WHITE);
		//g.drawString(main.manager.p1.getDesc(), p1_x, desc_y+10);
		g.drawImage(main.manager.p2.getImage(), main.manager.p2.getX(), main.manager.p2.getY(), null);
		//g.drawRect(main.getWidth() - descWidth,desc_y,descWidth,main.getHeight());
		//g.drawString(main.manager.p2.getDesc(),main.getWidth() - descWidth,desc_y+10);
	}
	
	public void mainGame()
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
        g.setColor(Color.WHITE);

        g.fillRect(main.paddle1.getX(), main.paddle1.getY(), main.paddle1.getWidth(), main.paddle1.getLength());
        g.fillRect(main.paddle2.getX(), main.paddle2.getY(), main.paddle2.getWidth(), main.paddle2.getLength());
        
        g.fillOval(main.ball.getX(), main.ball.getY(), main.ball.getSize(), main.ball.getSize());
        

        g.drawImage(main.paddle1.getRoulette().getImage(), 90, 5, null);
        g.drawImage(main.paddle2.getRoulette().getImage(), main.getWidth()-(90+36), 5, null);
        
        g.drawImage(scoreNumbers[main.paddle1.getScore()], 175, 10, null);
        g.drawImage(scoreNumbers[main.paddle2.getScore()], main.getWidth() - (175+32), 10, null);

	}
	
	private void loadTitleScreenAssets(){
		try {
			title = ImageIO.read(new File("Strings\\Title.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scoreNumbers = new BufferedImage[10];
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
		
		for(int i = 0; i<10; i++){
			try {
				scoreNumbers[i] = ImageIO.read(new File("Numbers\\" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void loadSelectScreenAssets()
	{
		pongavis = new BufferedImage[5];
		field = new BufferedImage[20];
		//load pong assets
		for(int i = 1; i <= 5; i++)
		{
			try {
				pongavis[i - 1] = ImageIO.read(new File("Pongavis\\pongavi" + i + ".png"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		//Load Paddle Screen Select background
		for(int i = 0; i < 20; i++)
		{
			try {
				field[i] = ImageIO.read(new File("FieldFrames\\frame" + i + ".png")); 
			} catch (IOException e) { e.printStackTrace();}
		}
		
		//p1 border selector
		try {
			main.manager.p1.setImage(ImageIO.read(new File("Pongavis\\border_p1.png")));
		} catch(IOException e) { e.printStackTrace();} 
		
		//p2 border selector
		try {
			main.manager.p2.setImage(ImageIO.read(new File("Pongavis\\border_p2.png")));
		} catch(IOException e) { e.printStackTrace();} 
		
		
		//initialize selector states
		main.manager.p1.setSelectorState(1);
		main.manager.p2.setSelectorState(2);
		
		//character Profiles
		characters = new BufferedImage[5];
		try {
			characters[0] = ImageIO.read(new File("Headshots\\Norman.png"));
			characters[1] = ImageIO.read(new File("Headshots\\Slim.png"));
			characters[2] = ImageIO.read(new File("Headshots\\Tyrion.png"));
			characters[3] = ImageIO.read(new File("Headshots\\TheBrick.png"));
			characters[4] = ImageIO.read(new File("Headshots\\Lucille.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//character name strings
		names = new BufferedImage[5];
		try {
			names[0] = ImageIO.read(new File("Strings\\Norman.png"));
			names[1] = ImageIO.read(new File("Strings\\Slim.png"));
			names[2] = ImageIO.read(new File("Strings\\Tyrion.png"));
			names[3] = ImageIO.read(new File("Strings\\TheBrick.png"));
			names[4] = ImageIO.read(new File("Strings\\Lucille.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private BufferedImage getHeadshot(String name){
		switch(name){
		case "Norman":
			return characters[0];
		case "Slim":
			return characters[1];
		case "Tyrion":
			return characters[2];
		case "TheBrick":
			return characters[3];
		case "Lucille":
			return characters[4];
		default:
			return characters[1];
		}
	}
	
	private BufferedImage getName(String name){
		switch(name){
		case "Norman":
			return names[0];
		case "Slim":
			return names[1];
		case "Tyrion":
			return names[2];
		case "TheBrick":
			return names[3];
		case "Lucille":
			return names[4];
		default:
			return names[1];
		}
	}

	

}

