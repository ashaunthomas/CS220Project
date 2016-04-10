
public class Paddle implements PaddleInterface{
	int x,y,id,length;
	Game main;
	
	public Paddle(int x, int id, Game main){
		this.main = main;
		setX(x);
		setY((main.getHeight()/2)-(length/2));
		setID(id);
		setLength(length);
	}
	public void MoveUp() {
		// TODO Auto-generated method stub
		
	}
	public void MoveDown() {
		// TODO Auto-generated method stub
		
	}
	public boolean setX(int x) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean setY(int y) {
		// TODO Auto-generated method stub
		return false;
	}
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int setY() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void Bump() {
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
		// TODO Auto-generated method stub
		return 0;
	}
	public void setRotation() {
		// TODO Auto-generated method stub
		
	}
	public void RotateRight() {
		// TODO Auto-generated method stub
		
	}
	public void RotateLeft() {
		// TODO Auto-generated method stub
		
	}
	public int getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setLength() {
		// TODO Auto-generated method stub
		
	}
		
	
}
