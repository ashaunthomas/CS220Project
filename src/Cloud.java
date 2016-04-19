import java.awt.image.BufferedImage;

public class Cloud {
	private BufferedImage image;
	private int y, x, width, height, speed, frameWidth;
	
	public Cloud(int frameWidth){
		setWidth(285);
		setHeight(140);
		setSpeed(1);
		this.frameWidth = frameWidth;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void move(){
		setX(getX()-getSpeed());
		if(getX() + getWidth() < 0){
			setX(frameWidth);
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
