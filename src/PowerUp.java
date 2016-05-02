import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUp {
	BufferedImage icon;
	int id;
	
	PowerUp(int iconNum){
		try {
			icon = ImageIO.read(new File("PowerUps\\PU" + iconNum + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		id = iconNum;
	}
	
	public BufferedImage getIcon(){
		return icon;
	}
}
