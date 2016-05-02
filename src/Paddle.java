
public class Paddle implements PaddleInterface{
	protected int x,y;
	protected int length, width;
	private int velocity;
	private Roulette roulette;
	private boolean hasPowerUp;
	Game main;
	
	
	public Paddle(int x, Game main){
		this.main = main;
		setX(x);
		setLength(80);
		setWidth(10);
		setY((main.getHeight()/2)-(getLength()/2)); 
		setVelocity(4);
		roulette = new Roulette(main);
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
		return hasPowerUp;
	}
	
	public void toggleHasPowerUp(){
		hasPowerUp = !hasPowerUp;
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
	protected void setWidth(int width) {
		this.width = width;
	}
	
	public Roulette getRoulette(){
		return roulette;
	}
		
	
}
