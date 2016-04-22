import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	private Game main;
	private boolean[] keyPressed = new boolean[256];
	boolean player1HasChosen = false, player2HasChosen = false;
	
	public KeyManager(Game main)
	{
		this.main = main;
		for(boolean key: keyPressed)
		{
			key = false;
		}
		
	}
	
	/**
	 * The receive key method receives a specified key. The switch dictates which "screen" has the
	 * specified key actions. If you need to add a key, use the  codes listed here: http://monkeyfighter.com/images/games/keycodes.gif
	 * game state legend:
	 * 0 is title screen
	 * 1 is paddle select
	 * 2 is actual game
	 * @param key The key pressed
	 */ 
	public void recieveKey(int key)
	{
		switch(main.getGameState())
		{
		case 0:
			switch(key)
			{
				case 32:
					main.setGameState(1);
					break;
				default:
					break;
 			}
		case 1:
			switch(key)
			{
			case 32:
				player1HasChosen = true;
				break;
			
			case 96:
				player2HasChosen = true;
				break;
			}
			if (player1HasChosen && player2HasChosen)
			{
				main.setGameState(2);
			}
		case 2:
			switch(key)
			{
				case 32:
					main.ball.setVelocity(7);
					main.ball.setTheta(Math.random() * 2 * Math.PI);
					main.ball.setVectorI((int)(main.ball.getVelocity()*Math.cos(main.ball.getTheta())));
					main.ball.setVectorJ((int)(main.ball.getVelocity()*Math.sin(main.ball.getTheta())));
					break;
			}
		}
	}
	
	public void recieveKeyRelease(int key)
	{
		
	}
	
	public void setIsPressing(boolean isPressing, int key)
	{
		this.keyPressed[key] = isPressing;		
	}
	
	public boolean getIsPressing(int key)
	{
		return keyPressed[key];
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
			if(!getIsPressing(key)){
				recieveKey(key);
				setIsPressing(true, key);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		recieveKeyRelease(key);
		setIsPressing(false, key);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
