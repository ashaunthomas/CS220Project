
public class Ball implements BallInterface{
	private Game main;
	private int x, y;
	private int vectorI, vectorJ;
	private int size;
	private int lastPlayerHit;
	
	public Ball(int size, Game main){
		setX((main.getWidth()-size)/2);
		setY((main.getHeight()-size)/2);
		setSize(size);
		this.main = main;
	}
	
	//for extra balls later on
	public Ball(int x, int y, int size, Game main){
		setX(x);
		setY(y);
		setSize(size);
		this.main = main;
	}
	
	public void move(){
		setX(getX() + getVectorI());
		setY(getY() + getVectorJ());
	}
	
	public void bounce(char surface){
		switch(surface){
		case 'h':
			setVectorJ(-getVectorJ());
			//forget theta. it is getting in the way of progress
			break;
		case 'v':
			int plusQuantity = 0;
			if(getVectorI()<main.paddle1.getWidth()){
				plusQuantity = 1;
			}
			setVectorI(-getVectorI() + (-getVectorI()>0?plusQuantity:-plusQuantity));
			break;
		}
		
		//this is what happends when the ball "scores"
		//Parameter is the ball that has Scored
	}
	public void hasScored(Paddle paddle){
		setX((main.getWidth()-size)/2);
		setY((main.getHeight()-size)/2);
		setVectorI(0);
		setVectorJ(0);
		paddle.Scores();
		
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getLastPlayerHit() {
		return lastPlayerHit;
	}
	
	public void setLastPlayerHit(int lastPlayerHit) {
		this.lastPlayerHit = lastPlayerHit;
	}

	public int getVectorI() {
		return vectorI;
	}

	public void setVectorI(int vectorI) {
		this.vectorI = vectorI;
	}

	public int getVectorJ() {
		return vectorJ;
	}

	public void setVectorJ(int vectorJ) {
		this.vectorJ = vectorJ;
	}
	

}
