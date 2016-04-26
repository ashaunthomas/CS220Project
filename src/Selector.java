import java.awt.image.BufferedImage;

public class Selector {
	Game game;
	private final int WIDTH = 32;
	private final int HEIGHT = 32;
	private int x;
	private int y;
	private int selectorState;
	private BufferedImage image;
	
	public Selector(Game game)
	{
		this.game = game;
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	public void setSelectorState(int selectorState)
	{
		switch(selectorState)
		{
			case 1: //player is highlighting normal paddle
				x = Game.WIDTH_MIDPOINT - 48;
				y = Game.HEIGHT_MIDPOINT - 32;
				break;
			case 2://player is highlighting 
				x = Game.WIDTH_MIDPOINT - 16;
				y = Game.HEIGHT_MIDPOINT - 32;
				break;
			case 3:
				x = Game.WIDTH_MIDPOINT + 16;
				y = Game.HEIGHT_MIDPOINT - 32;
				break;
			case 4:
				x = Game.WIDTH_MIDPOINT - 32;
				y = Game.HEIGHT_MIDPOINT;
				break;
			case 5:
				x = Game.WIDTH_MIDPOINT;
				y = Game.HEIGHT_MIDPOINT;
				break;
			default:
				System.out.println("Error in setSelectorState method in Selector!");
				break;
		}
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	
}
