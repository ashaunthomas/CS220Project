import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial") //Gave weird warning, I'll check that out later
public class Game extends JFrame 
{
	private GameCanvas panel;
	KeyManager manager;
	Paddle paddle1, paddle2;
	Ball ball;
	private int gameState;
	/*
	 * States:
	 * 0 - Title Screen
	 * 1 - Paddle Select
	 * 2 - Main Game
	 */
	
	
	
	
	protected String title = "Pong: This Time It's Personal";
	
	//16:9 dimensions for jframe
	
	protected final int HEIGHT= 281;
	protected final int WIDTH = 500;
	protected final int HEIGHT_MIDPOINT = 140;
	protected final int WIDTH_MIDPOINT = 250;
	Game(){
		initUI();
		loadObjects();
		runGameLoop();
		setGameState(0);
		
	}

	private void update(){
		collision();
		score();
		movement();
		panel.repaint();
	}
	
	private void initUI()
	{
		this.setTitle(title);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		manager = new KeyManager(this);
		
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
	}
	
	private void loadObjects() {
		int distanceFromEdge = 20;
		int paddleLength = 80;
		int paddleWidth = 10;
		
		paddle1 = new Paddle(distanceFromEdge, paddleLength, paddleWidth, 1, this);
		paddle2 = new Paddle(WIDTH - (distanceFromEdge + paddleWidth), paddleLength, paddleWidth, 2, this);
		
		int ballSize = 20;
		
		ball = new Ball(ballSize, this);
		
	}
	
	private void movement(){
		//paddle movement
		if(manager.getIsPressing(87) && paddle1.getY() > 0)
			paddle1.moveUp();
		if(manager.getIsPressing(83) && (paddle1.getY() + paddle1.getLength()) < this.getHeight())
			paddle1.moveDown();
		if(manager.getIsPressing(38) && paddle2.getY() > 0)
			paddle2.moveUp();
		if(manager.getIsPressing(40) && (paddle2.getY() + paddle2.getLength()) < this.getHeight())
			paddle2.moveDown();
		
		ball.move();
		
		//ball movement
	}
	
	private void collision(){
		
		if((ball.getY() <= 0) || ((ball.getY() + ball.getSize()) >= this.getHeight())){
			ball.bounce('h');
		}
		
		if((ball.getX() >= paddle1.getX() && ball.getX() <= paddle1.getX() + paddle1.getWidth()) && (ball.getY() + ball.getSize() >= paddle1.getY() && ball.getY() <= paddle1.getY() + paddle1.getLength())){
			ball.bounce('v');
		}
		
		if((ball.getX() + ball.getSize() >= paddle2.getX() && ball.getX() + ball.getSize() <= paddle2.getX() + paddle2.getWidth()) && (ball.getY() + ball.getSize() >= paddle2.getY() && ball.getY() <= paddle2.getY() + paddle2.getLength())){
			ball.bounce('v');
		}
		
	}
		//scoring and returning
	private void score(){
		if(ball.getX() > 500){
			ball.hasScored(paddle1);
		}
		if (ball.getX() < 0){
			ball.hasScored(paddle2);
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
