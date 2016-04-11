
public class Paddle implements PaddleInterface{
	private int x,y;
	private int id, thickness;
	private int length, width;
	private int velocity;
	Game main;
	
	public Paddle(int x, int length, int width, int id, Game main){
		this.main = main;
		setX(x);
		setLength(length);
		setWidth(width);
		setY((main.getHeight()/2)-(getLength()/2));
		setID(id);
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
	
	public void setRotation() {
		// TODO Auto-generated method stub
		
	}
	
	public void rotateRight() {
		// TODO Auto-generated method stub
		
	}
	
	public void rotateLeft() {
		// TODO Auto-generated method stub
		
	}
	
	public int getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public int getWidth() {
		return width;
	}
	private void setWidth(int width) {
		this.width = width;
	}
		
	
}
