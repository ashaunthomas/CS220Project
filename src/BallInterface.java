
public interface BallInterface {
	
	public void move();
	public void bounce(char surface);
	
	public int getX();
	public void setX(int x);
	
	public int getY();
	public void setY(int y);
	
	public int getVectorI();
	public void setVectorI(int i);
	
	public int getVectorJ();
	public void setVectorJ(int i);
	
	public int getLastPlayerHit();
	public void setLastPlayerHit(int lastPlayerHit);
}
