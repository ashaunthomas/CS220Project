import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private boolean[] keyPressed = new boolean[256];
	
	public KeyManager()
	{
		for(boolean key: keyPressed)
		{
			key = false;
		}
	}
	
	public void recieveKey(int key)
	{
		
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
		}
		setIsPressing(true, key);
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
