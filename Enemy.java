import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import java.io.Serializable;

public class Enemy implements Serializable {
	private String imageName;
	private int x;
	private int y;
	private int width;
	private int height;
	private int moveDirection;	//1-left,right	2-up,down
	private int topBound;
	private int bottomBound;
	private boolean hidden = false;
	private boolean movingUp = true;
	private boolean enabled = true;

	private static final long serialVersionUID = 1209238;

	public Enemy(String imageName, int x, int y, int width, int height, int moveDirection, int topBound, int bottomBound) {
		this.imageName = imageName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.moveDirection = moveDirection;
		this.topBound = topBound;
		this.bottomBound = bottomBound;
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

	public void move() {
		if(moveDirection == 1) {
			if(movingUp) {
				x--;
				if(x < bottomBound) {
					movingUp = false;
				}
			}
			else {
				x++;
				if(x > topBound) {
					movingUp = true;
				}
			}
		} else {
			if(movingUp) {
				y--;
				if(y < bottomBound) {
					movingUp = false;
				}
			}
			else {
				y++;
				if(y > topBound) {
					movingUp = true;
				}
			}
		}
		
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

	public String toString() {
		return imageName.substring(0, imageName.indexOf('.'));
	}

	public void setHidden(boolean hid) {
		hidden = hid;
	}

	public boolean getEnabled() {
		return enabled;
	}
}