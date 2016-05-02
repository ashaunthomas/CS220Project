import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class KeyManager implements KeyListener {
	private Game main;
	private boolean[] keyPressed = new boolean[256];
	boolean player1HasChosen = false, player2HasChosen = false;
	public Selector p1 = new Selector(main, 1);
	public Selector p2 = new Selector(main, 2);
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
				p1.setChosen(true);
				break;
			case 87: //w pressed
				if((p1.getSelectorState() - 1) == p2.getSelectorState() || (p1.getSelectorState() == 1 && p2.getSelectorState() == 5 ))
				{
					p1.moveUp();
					p1.moveUp();
				}
				else
				{
					p1.moveUp();
				}
				break;
			case 83: //d pressed
				if((p1.getSelectorState() + 1) == p2.getSelectorState() || (p1.getSelectorState() == 5 && p2.getSelectorState() == 1))
				{
					p1.moveDown();
					p1.moveDown();
				}
				else
				{
					p1.moveDown();
				}
				break;
				
			case 104: //numpad 8 pressed
				if(p2.getSelectorState() - 1 == p1.getSelectorState() || (p2.getSelectorState() == 1 && p1.getSelectorState() == 5))
				{
					p2.moveUp();
					p2.moveUp();
				}
				else
				{
					p2.moveUp();
				}
				break;
			case 101: //numpad 5 pressed
				if(p2.getSelectorState() + 1 == p1.getSelectorState() || (p2.getSelectorState() == 5 && p1.getSelectorState() == 1))
				{
					p2.moveDown();
					p2.moveDown();
				}
				else
				{
					p2.moveDown();
				}
				break;
			case 77:
				main.music.mute();
				break;
			case 78:
				main.music.next();
				break;
			case 96:
				player2HasChosen = true;
				p2.setChosen(true);
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
				case 69:
					if(!main.paddle1.getRoulette().isRolling()){
						main.paddle1.getRoulette().roll();
					}
					else{
						main.paddle1.getRoulette().stop();
					}
					break;
				case 103:
					if(!main.paddle2.getRoulette().isRolling()){
						main.paddle2.getRoulette().roll();
					}
					else{
						main.paddle2.getRoulette().stop();
					}
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
