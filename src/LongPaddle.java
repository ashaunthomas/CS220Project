
public class LongPaddle extends Paddle {

	public LongPaddle(int x, Game main) {
		super(x, main);
		setVelocity(3);
		setLength(100);
		setY((main.getHeight()/2)-(getLength()/2));
	}

}
