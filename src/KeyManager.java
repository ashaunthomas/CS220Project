import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */ 
	public void recieveKey(int key) throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		switch(main.getGameState())
		{
		case 0:
			switch(key)
			{
				case 32:
					main.setGameState(1);
					break;
				case 77:
					main.music.mute();
					break;
				case 78:
					main.music.next();
					break;
				default:
					break;
 			}
			break;
		case 1:
			switch(key)
			{
			case 32:
				player1HasChosen = true;
				break;
				
			case 77:
				main.music.mute();
				break;
			case 78:
				main.music.next();
				break;
			case 87: //w
				
				break;
			case 83: //d
				break;
			case 96:
				player2HasChosen = true;
				break;
			}
			if (player1HasChosen && player2HasChosen)
			{
				main.setGameState(2);
			}
			
			break;
		case 2:
			switch(key)
			{
				case 32:
					main.ball.setVectorI(5*(Math.random()>0.5?1:-1));
					main.ball.setVectorJ(5*(Math.random()>0.5?1:-1));
					break;
					
				case 77:
					main.music.mute();
					break;
				case 78:
					main.music.next();
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
				try {
					recieveKey(key);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
