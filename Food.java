import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import java.io.Serializable;

public class Food implements Serializable {
	private String imageName;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean hidden = false;
	private boolean enabled = true;

	private static final long serialVersionUID = 1209241;

	public Food(String imageName, int x, int y, int width, int height) {
		this.imageName = imageName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean checkCollison(int playerX, int playerY, int playerWidth, int playerHeight) {
		if(!hidden && enabled) {
			Rectangle player = new Rectangle(playerX, playerY, playerWidth, playerHeight);
			Rectangle itemRect = new Rectangle(x, y, width, height);
			if(player.intersects(itemRect)) {
				return true;
			}
		}
		return false;
	}

	public void drawMe(Graphics g) {
		if(!hidden && enabled) {
			try {                
	        	BufferedImage image = ImageIO.read(new File(imageName));
	        	g.drawImage(image, x, y, null);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}
		}
	}

	public void setEnabled(boolean en) {
		enabled = en;
	}

	public String getImageName() {
		return imageName;
	}

	public void setHidden(boolean hid) {
		hidden = hid;
	}	

	public boolean getEnabled() {
		return enabled;
	}
}