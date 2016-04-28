
public class ShortPaddle extends Paddle {

	public ShortPaddle(int x, Game main) {
		super(x, main);
		setVelocity(9);
		setLength(40);
		setY((main.getHeight()/2)-(getLength()/2));
	}

}
