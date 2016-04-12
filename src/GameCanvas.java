import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class GameCanvas extends JPanel{
	private Game main;
	public Animator animate;
	private BufferedImage screen;
	private boolean isFullScreen;
	private Graphics2D g2d;

	
	public GameCanvas()
	{
		
	}
	
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension((int) this.getParent().getWidth(), this.getParent().getHeight());
	}
	
	
	@Override
	public void paint(Graphics g) 
	{

		switch(main.getGameState())
		{
		case 0: animate.titleScreen();break;
		case 1: animate.selectPaddle(); break;
		case 2: animate.mainGame(); break;
		}
		
		g.drawImage(screen.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
		
	}
	
	public void toggleIsFullScreen()
	{
		isFullScreen = !isFullScreen;
	}
	
	public boolean getIsFullScreen(){
		return isFullScreen;
	}
	
	public void setAnimator(Animator animate)
	{
		this.animate = animate;
	}
		
	public void setScreen()
	{
		screen = new BufferedImage(main.getWidth(), main.getHeight(), BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) screen.getGraphics();
		animate = new Animator(this, main, g2d);
	}
	
	public void setGame(Game main)
	{
		this.main = main;
	}

}