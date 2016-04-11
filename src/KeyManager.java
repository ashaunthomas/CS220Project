import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private Game main;
	private boolean[] keyPressed = new boolean[256];
	
	public KeyManager(Game main)
	{
		this.main = main;
		for(boolean key: keyPressed)
		{
			key = false;
		}
	}
	
	public void recieveKey(int key)
	{
		switch(main.getGameState()){
		case 2:
			switch(key){
			case 32:
				main.ball.setVelocity(7);
				main.ball.setTheta(Math.random() * 2 * Math.PI);
				main.ball.setVectorI((int)(main.ball.getVelocity()*Math.cos(main.ball.getTheta())));
				main.ball.setVectorJ((int)(main.ball.getVelocity()*Math.sin(main.ball.getTheta())));
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
