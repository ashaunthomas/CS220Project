import java.awt.image.BufferedImage;

public class Selector {
	Game game;
	private final int WIDTH = 32;
	private final int HEIGHT = 32;
	private int x;
	private int y;
	private int selectorState;
	private BufferedImage image;
	boolean hasChosen = false;
	String description = "N/a";
	public Selector(Game game, int initialState)
	{
		this.game = game;
		selectorState = initialState;
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
				description = "Norman";
				break;
			case 2://player is highlighting small paddle
				x = Game.WIDTH_MIDPOINT - 16;
				y = Game.HEIGHT_MIDPOINT - 32;
				description = "Slim";
				break;
			case 3: //player is highlighting long paddle
				x = Game.WIDTH_MIDPOINT + 16;
				y = Game.HEIGHT_MIDPOINT - 32;
				description = "Tyrion";
				break;
			case 4: //player is highlighting brick paddle
				x = Game.WIDTH_MIDPOINT - 32;
				y = Game.HEIGHT_MIDPOINT;
				description = "TheBrick";
				break;
			case 5: //player is highlighting lucille paddle
				x = Game.WIDTH_MIDPOINT;
				y = Game.HEIGHT_MIDPOINT;
				description = "Lucille";
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
	
	public void moveDown()
	{
		if(!hasChosen)
		{
			if(selectorState != 5)
				selectorState++;
			else
				selectorState = 1;
			setSelectorState(selectorState);
		}
	}
	
	public void moveUp()
	{
		if(!hasChosen)
		{
			if (selectorState != 1)
				selectorState--;
			else
				selectorState = 5;
			setSelectorState(selectorState);
		}
	}
	
	public void setChosen(boolean hasChosen)
	{
		this.hasChosen = hasChosen;
	}
	
	public int getSelectorState()
	{
		return selectorState;
	}
	
	public String getDesc()
	{
		return description;
	}
}
