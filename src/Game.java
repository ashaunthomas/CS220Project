import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial") //Gave weird warning, I'll check that out later
public class Game extends JFrame 
{
	String title = "Pong: This Time It's Personal";
	
	public Game() {initUI();};
	
	private void initUI()
	{
		setTitle(title);
		
		setSize(500,281);
		
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
