
public class BrickPaddle extends Paddle {

	public BrickPaddle(int x, Game main) {
		super(x, main);
		setVelocity(4);
		setWidth(30);
		setLength(60);
		setY((main.getHeight()/2)-(getLength()/2));
	}

}
