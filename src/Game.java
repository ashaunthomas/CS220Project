import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial") //Gave weird warning, I'll check that out later
public class Game extends JFrame 
{
	protected String title = "Pong: This Time It's Personal";
	
	//16:9 dimensions for jframe
	
	protected final static int HEIGHT= 500;
	protected final static int WIDTH = 281;
	
	public Game() {initUI();};
	
	private void initUI()
	{
		setTitle(title);
		
		setSize(HEIGHT,WIDTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//blah
	public static void main(String[]args)
	{
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
}
