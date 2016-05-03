import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Roulette {
	
	//start node definition
	private class Node{
		private PowerUp data;
		private Node nextNode;
		public Node(PowerUp data){
			this.data = data;
			
			if(nodeCount == 0){
				this.setNext(this);
				startNode = this;
				currentNode = this;
			}
			else{
				currentNode.setNext(this);
				this.setNext(startNode);
				currentNode = this;
			}
			nodeCount++;
		}
		
		public Node next(){
			return nextNode;
		}
		
		public void setNext(Node nextNode){
			this.nextNode = nextNode;
		}
		
		public PowerUp getData(){
			return data;
		}
	}
	//end node definition
	
	private Game main;
	private Node currentNode, startNode;	
	private int nodeCount = 0;
	private int speed = 3, rollState = 0;
	private boolean rolling, stopped;
	private BufferedImage frame;
	
	public Roulette(Game main){
		this.main = main;
		
		try {
			frame = ImageIO.read(new File("PowerUps\\RouletteFrame.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadPowerUps();
	}
	
	public void roll(){
		stopped = false;
		rolling = true;
	}
	
	public void advance(){
		if(rollState == 0 && !stopped){
			currentNode = currentNode.next();
		}
		if(rollState == 0 && stopped){
			rolling = false;
			return;
		}
		rollState = (rollState>=29)?0:rollState+speed;
	}
	
	public PowerUp stop(){
		stopped = true;
		return currentNode.next().getData();
	}
	
	public BufferedImage getFrame(){
		return frame;
	}
	
	public BufferedImage getSpinner(){
		BufferedImage spinner = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spinner.createGraphics();
		if(rollState != 0){
			g2.drawImage(currentNode.getData().getIcon().getSubimage(0, rollState>29?1:rollState, 30, rollState>29?1:30-rollState), 0, 0, null);
			g2.drawImage(currentNode.next().getData().getIcon().getSubimage(0, 0, 30, rollState), 0, 30-rollState, null);
		}
		else{
			g2.drawImage(currentNode.next().getData().getIcon(), 0, 0, null);
		}
		g2.dispose();
		return spinner;
	}
	
	public BufferedImage getImage(){
		BufferedImage image = new BufferedImage(36, 49, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.drawImage(getFrame(), 0, 0, null);
		g2.drawImage(getSpinner(), 3, 16, null);
		g2.dispose();
		return image;
	}
	
	public int getRollState(){
		return rollState;
	}
	
	public boolean isRolling(){
		return rolling;
	}
	
	public void setIsStopped(){
		stopped = true;
	}
	
	private void loadPowerUps(){
		//initialize powerup objects here!
		for(int i = 0; i<5; i++){
			new Node(new PowerUp(i));
		}
	}
}
