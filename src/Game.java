import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
//Track 0 citation:
//Toby Fox, "Unite Synchronization", Homestuck Volume 8


@SuppressWarnings("serial") //Gave weird warning, I'll check that out later
public class Game extends JFrame 
{
	private GameCanvas panel;
	KeyManager manager;
	MusicManager music;
	
	Random r = new Random();
	
	Paddle paddle1, paddle2;
	Ball ball;
	PowerUp powUp;
	private int gameState;
	/*
	 * States:
	 * 0 - Title Screen
	 * 1 - Paddle Select
	 * 2 - Main Game
	 */
	int screen_title = 0;
	int screen_paddle_select = 1;
	int screen_main = 2;	
	int introSong = 0;
	int p1_moveUpKey = 87;
	int p1_moveDownKey = 83;
	int p2_moveUpKey = 104;
	int p2_moveDownKey = 101;
	int time,sleepTime;
	protected String title = "Pong: This Time It's Personal";
	protected final int HEIGHT= 281; //16:9 dimensions for jframe
	protected final int WIDTH = 500; //16:9 dimensions for jframe
	public static final int HEIGHT_MIDPOINT = 140;
	public static final int WIDTH_MIDPOINT = 250;
	public boolean objectsWereLoaded = false;
	
	Game(){
		initUI();
		runGameLoop();
		setGameState(screen_title);
		setTime(0);
		
	}

	private void update(){
		if(getGameState() == 2){
			if(!objectsWereLoaded)
			{
				loadObjects();
				objectsWereLoaded = true;
			}
			collision();
			score();
			movement();
			count();
			powerSleeper();
		}
		panel.repaint();
	}
	
	private void initUI()
	{
		this.setTitle(title);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		manager = new KeyManager(this);
		music = new MusicManager();
		
		panel = new GameCanvas();
		panel.addKeyListener(manager);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		panel.setGame(this);
		panel.setScreen();
	    this.getContentPane().add(panel, BorderLayout.CENTER);
	    
	    this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - this.getWidth())/2, (screenSize.height - this.getHeight())/2);
		this.setVisible(true);
		panel.repaint();
		
		this.setVisible(true);
		music.play(introSong);
	}
	
	private void loadObjects() {
		int distanceFromEdge = 20;
		int paddleWidth = 10;
		
		//p1 selection decision
		switch(manager.p1.getSelectorState())
		{
			case 1: //Normal Paddle
				paddle1 = new Paddle(distanceFromEdge, this);
				break;
			case 2: //Long Paddle
				paddle1 = new LongPaddle(distanceFromEdge, this);
				break;
			case 3: //Short Paddle
				paddle1 = new ShortPaddle(distanceFromEdge, this);
				break;
			case 4: //Brick Paddle
				paddle1 = new BrickPaddle(distanceFromEdge, this); 
				break;
			case 5: //Lucille Paddle
				paddle1 = new Paddle(distanceFromEdge, this);
				break;
			default:
				paddle1 = new Paddle(distanceFromEdge, this);
				break;
			
		}
		
		
		//p2 selection decision
		switch(manager.p2.getSelectorState())
		{
			case 1: //Normal Paddle
				paddle2 = new Paddle(WIDTH - (distanceFromEdge + paddleWidth), this);
				break;
			case 2: //Long Paddle
				paddle2 = new LongPaddle(WIDTH - (distanceFromEdge + paddleWidth), this);
				break;
			case 3: //Short Paddle
				paddle2 = new ShortPaddle(WIDTH - (distanceFromEdge + paddleWidth), this);
				break;
			case 4: //Brick Paddle
				paddle2 = new BrickPaddle(WIDTH - (distanceFromEdge + paddleWidth), this);;
				System.out.println("selection 4 was selected"); 
				break;
			case 5: //Lucille Paddle
				paddle2 = new Paddle(WIDTH - (distanceFromEdge + paddleWidth), this);
				System.out.println("Selection 5 was selected");
				break;
			default:
				paddle2 = new Paddle(WIDTH - (distanceFromEdge + paddleWidth), this);
				System.out.println("Paddle 2 load error");
				break;
			
		}
		
		
		int ballSize = 20;
		
		ball = new Ball(ballSize, this);
		powUp = new PowerUp(0);
		
	}
	
	private void movement(){
		//paddle movement
		if(manager.getIsPressing(p1_moveUpKey) && paddle1.getY() > 0)
			paddle1.moveUp();
		if(manager.getIsPressing(p1_moveDownKey) && (paddle1.getY() + paddle1.getLength()) < this.getHeight())
			paddle1.moveDown();
		if(manager.getIsPressing(p2_moveUpKey) && paddle2.getY() > 0)
			paddle2.moveUp();
		if(manager.getIsPressing(p2_moveDownKey) && (paddle2.getY() + paddle2.getLength()) < this.getHeight())
			paddle2.moveDown();
		
		
		//ball movement
		ball.move();
		
		//Roulette spin
		if(paddle1.getRoulette().isRolling())
			paddle1.getRoulette().advance();
		if(paddle2.getRoulette().isRolling())
			paddle2.getRoulette().advance();
		
		
	}
	
	private void collision(){
		
		if((ball.getY() <= 0) || ((ball.getY() + ball.getSize()) >= this.getHeight())){
			ball.bounce('h');
		}
		
		if((ball.getX() >= paddle1.getX() && ball.getX() <= paddle1.getX() + paddle1.getWidth()) && (ball.getY() + ball.getSize() >= paddle1.getY() && ball.getY() <= paddle1.getY() + paddle1.getLength())){
			ball.bounce('v');
			ball.setLastPlayerHit(0);
		}
		
		if((ball.getX() + ball.getSize() >= paddle2.getX() && ball.getX() + ball.getSize() <= paddle2.getX() + paddle2.getWidth()) && (ball.getY() + ball.getSize() >= paddle2.getY() && ball.getY() <= paddle2.getY() + paddle2.getLength())){
			ball.bounce('v');
			ball.setLastPlayerHit(1);
		}
		if((ball.getY()-powUp.getY()>-10 && ball.getY()-powUp.getY()<10) && (ball.getX()-powUp.getX()>-10 && ball.getX()-powUp.getX()<10 )){
			
			if(ball.getLastPlayerHit() ==0 ){
				paddle1.getRoulette().roll();

			}
			if(ball.getLastPlayerHit() == 1){
				paddle2.getRoulette().roll();
			}
			powUp.Spawn();
			
		}
		
	}
		//scoring and returning
	private void score(){
		if(ball.getX() > 500){
			paddle1.score();
			ball.hasScored();
		}
		if (ball.getX() < 0){
			paddle2.score();
			ball.hasScored();
		}
	}
	//blah
	
	public int getHeight(){
		return HEIGHT;
	}
	
	
	public int getWidth(){
		return WIDTH;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	
	//resets the time count generates a new random number
	public void reset(){
		sleepTime=r.nextInt(500)+100;
		setTime(0);

	}
	//counts every frame
	public void count(){
		time++;
	}
	public int getTime(){
		return time;
	}
	public void setTime(int time){
		this.time=time;
	}
	//checks if the power up is ready to spawn
	public void powerSleeper(){

		if (time>sleepTime){
			powUp.Spawn();
			reset();
		}
	}
	
	
	public void runGameLoop()
	{
		while(true)
		{
					
			update();
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
