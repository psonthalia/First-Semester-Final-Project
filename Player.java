import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {
	private String imageName;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean hidden = false;

	private static final long serialVersionUID = 1209236;

	public Player(String imageName, int x, int y, int width, int height) {
		this.imageName = imageName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void drawMe(Graphics g) {
		if(!hidden) {
			try {                
	        	BufferedImage image = ImageIO.read(new File(imageName));
	        	g.drawImage(image, x, y, null);
	       	} catch (IOException ex) {
	            System.out.println(ex);
       		}
		}
	}

	public void setHidden(boolean hid) {
		hidden = hid;
	}
	public void setX(int value) {
		x = value;
	}
	public void setY(int value) {
		y = value;
	}

	public void moveX(int interval) {
		x += interval;
	}

	public void moveY(int interval) {
		y += interval;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}