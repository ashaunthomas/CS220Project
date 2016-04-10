import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial") //Gave weird warning, I'll check that out later
public class Game extends JFrame 
{
	private int gameState;
	/*
	 * States:
	 * 0 - Title Screen
	 * 1 - Paddle Select
	 * 2 - Main Game
	 */
	
	
	
	
	protected String title = "Pong: This Time It's Personal";
	
	//16:9 dimensions for jframe
	
	protected final static int HEIGHT= 500;
	protected final static int WIDTH = 281;
	
	Game(){
		initUI();
		
		//set state to 2 so i could test pong game engine
		setGameState(2);
		
		EventQueue.invokeLater(new Runnable() 
				{
					@Override
					public void run()
					{
						Game thisGame = new Game();
						thisGame.setVisible(true);
					}
				});
	}
	
	private void initUI()
	{
		setTitle(title);
		
		setSize(HEIGHT,WIDTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
}
