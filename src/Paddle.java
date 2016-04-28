
public class Paddle implements PaddleInterface{
	protected int x,y;
	protected int length, width;
	private int velocity;
	Game main;
	
	
	public Paddle(int x, Game main){
		this.main = main;
		setX(x);
		setLength(80);
		setWidth(10);
		setY((main.getHeight()/2)-(getLength()/2)); 
		setVelocity(4);
	}
	
	
	public void moveUp() {
		setY(getY()-getVelocity());
	}
	
	public void moveDown() {
		setY(getY()+getVelocity());
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	public void bump() {
		// TODO Auto-generated method stub
	}
	
	public boolean hasPowerUp() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getWidth() {
		return width;
	}
	private void setWidth(int width) {
		this.width = width;
	}
		
	
}
