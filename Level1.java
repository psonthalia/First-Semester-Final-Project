import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Level1 {
	private final int store1X = 200;
	private final int store1Y = 700;
	private final int store2X = 620;
	private final int store2Y = 500;
	private final int store3X = 620;
	private final int store3Y = 100;

	private final int store1Width = 103;
	private final int store1Height = 103;
	private final int store2Width = 150;
	private final int store2Height = 150;
	private final int store3Width = 200;
	private final int store3Height = 160;

	private final int blueButtonX = 50;
	private final int blueButtonY = 50;
	private final int yellowButtonX = 300;
	private final int yellowButtonY = 600;
	private final int cyanButtonX = 600;
	private final int cyanButtonY = 100;
	private final int greenButtonX = 700;
	private final int greenButtonY = 400;
	private final int redButtonX = 900;
	private final int redButtonY = 800;
	private final int buttonWidth = 50;
	private final int buttonHeight = 50;

	private final int doorX = 10;
	private final int doorY = 350;
	private final int doorWidth = 100;
	private final int doorHeight = 213;

	private final int redDoorX = 435;
	private final int redDoorY = 30;
	private final int redDoorWidth = 120;
	private final int redDoorHeight = 160;

	private int scene = 0;
	private int buttonClicked = 0;

	private boolean collectedItems = false;

	private boolean level1done = false;

	public Level1() {

	}

	public void drawMe(Graphics g, Item[] items) {
		if(scene == 0) {
			try {                
	        	BufferedImage road = ImageIO.read(new File("road.jpg"));
	        	BufferedImage concrete = ImageIO.read(new File("concrete.jpg"));
	        	BufferedImage store1 = ImageIO.read(new File("store1.png"));
	        	BufferedImage store2 = ImageIO.read(new File("store2.png"));
	        	BufferedImage store3 = ImageIO.read(new File("store3.png"));
	        	g.drawImage(concrete, 0, 0, null);
	        	g.drawImage(road, 370, 0, null);
	        	g.drawImage(store1, store1X, store1Y, null);
	        	g.drawImage(store2, store2X, store2Y, null);
	        	g.drawImage(store3, store3X, store3Y, null);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

	       	if(collectedItems) {
	       		try {                
		        	BufferedImage image = ImageIO.read(new File("redDoor.png"));
		        	g.drawImage(image, redDoorX, redDoorY, null);
		       	} catch (IOException ex) {
		            System.out.println(ex);
		       	}
	       	}
		} else {
			try {  
				BufferedImage tile = ImageIO.read(new File("tile.jpg"));              
	        	BufferedImage blueButton = ImageIO.read(new File("blueButton.png"));
	        	BufferedImage yellowButton = ImageIO.read(new File("yellowButton.png"));
	        	BufferedImage cyanButton = ImageIO.read(new File("cyanButton.png"));
	        	BufferedImage greenButton = ImageIO.read(new File("greenButton.png"));
	        	BufferedImage redButton = ImageIO.read(new File("redButton.png"));
	        	BufferedImage door = ImageIO.read(new File("door.png"));
	        	g.drawImage(tile, 0, 0, null);
	        	g.drawImage(blueButton, blueButtonX, blueButtonY, null);
	        	g.drawImage(yellowButton, yellowButtonX, yellowButtonY, null);
	        	g.drawImage(cyanButton, cyanButtonX, cyanButtonY, null);
	        	g.drawImage(greenButton, greenButtonX, greenButtonY, null);
	        	g.drawImage(redButton, redButtonX, redButtonY, null);
	        	g.drawImage(door, doorX, doorY, null);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}
		}
		if(scene == 1) {
			try {  
				BufferedImage tesla3 = ImageIO.read(new File("tesla3.png"));
				BufferedImage tesla = ImageIO.read(new File("tesla.png"));
	        	g.drawImage(tesla3, 350, 500, null);
	        	g.drawImage(tesla, 500, 200, null);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

	       	if(buttonClicked == 1) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       		if(items[0] != null && items[0].getName().equals("Chest 1")) {
	       			items[0].setHidden(false);
	       		}
	       	} else if(buttonClicked == 2) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	}  
	       	else if(buttonClicked == 3) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	}  
	       	else if(buttonClicked == 4) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	} 
	       	else if(buttonClicked == 5) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	}
		}
		else if(scene == 2) {
			try {  
				BufferedImage couch = ImageIO.read(new File("couch.png"));
				BufferedImage bed = ImageIO.read(new File("bed.png"));
	        	g.drawImage(couch, 550, 100, null);
	        	g.drawImage(bed, 300, 500, null);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

	       	if(buttonClicked == 1) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	} else if(buttonClicked == 2) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	}  
	       	else if(buttonClicked == 3) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       		if(items[1] != null && items[1].getName().equals("Key 1")) {
	       			items[1].setHidden(false);
	       		}
	       	}  
	       	else if(buttonClicked == 4) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       		
	       	} 
	       	else if(buttonClicked == 5) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	}
		}
		else if(scene == 3) {
			try {  
				BufferedImage aventador = ImageIO.read(new File("aventador.png"));
				BufferedImage sesto = ImageIO.read(new File("sesto.png"));
	        	g.drawImage(aventador, 200, 100, null);
	        	g.drawImage(sesto, 600, 500, null);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

	       	if(buttonClicked == 1) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	} else if(buttonClicked == 2) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       		if(items[2] != null && items[2].getName().equals("Money 1")) {
	       			items[2].setHidden(false);
	       		}
	       	}  
	       	else if(buttonClicked == 3) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	}  
	       	else if(buttonClicked == 4) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       		
	       	} 
	       	else if(buttonClicked == 5) {
	       		for(int i = 0; i < items.length; i++) {
	       			items[i].setHidden(true);
	       		}
	       	}
		}
	}

	public int getScene() {
		return scene;
	}

	public boolean inShop() {
		if(scene != 0) {
			return true;
		}
		return false;
	}

	public int getShop() {
		return scene;
	}

	public void setCollectedItems(boolean collected) {
		collectedItems = collected;
	}

	public void hitEnemy() {
		scene = 0;
	}

	public boolean getLevel1Done() {
		return level1done;
	}

	public void checkCollison(int playerX, int playerY, int playerWidth, int playerHeight) {
		Rectangle player = new Rectangle(playerX, playerY, playerWidth, playerHeight);
		if(scene == 0) {
    		Rectangle store1Rect = new Rectangle(store1X, store1Y, store1Width, store1Height);
    		Rectangle store2Rect = new Rectangle(store2X, store2Y, store2Width, store2Height);
    		Rectangle store3Rect = new Rectangle(store3X, store3Y, store3Width, store3Height);

    		if(player.intersects(store1Rect)) {
    			scene = 1;
    		}
			else if(player.intersects(store2Rect)) {
				scene = 2;
			}
			else if(player.intersects(store3Rect)) {
				scene = 3;
			} else {
				scene = 0;
			}

			if(collectedItems) {
				Rectangle redDoorRect = new Rectangle(redDoorX, redDoorY, redDoorWidth, redDoorHeight);
				if(player.intersects(redDoorRect)) {
					level1done = true;
				}
			}
		} else {
			Rectangle blueButtonRect = new Rectangle(blueButtonX, blueButtonY, buttonWidth, buttonHeight);
    		Rectangle yellowButtonRect = new Rectangle(yellowButtonX, yellowButtonY, buttonWidth, buttonHeight);
    		Rectangle cyanButtonRect = new Rectangle(cyanButtonX, cyanButtonY, buttonWidth, buttonHeight);
    		Rectangle greenButtonRect = new Rectangle(greenButtonX, greenButtonY, buttonWidth, buttonHeight);
    		Rectangle redButtonRect = new Rectangle(redButtonX, redButtonY, buttonWidth, buttonHeight);
    		Rectangle doorRect = new Rectangle(doorX, doorY, doorWidth, doorHeight);

    		if(player.intersects(blueButtonRect)) {
    			buttonClicked = 1;
    		}
    		else if(player.intersects(yellowButtonRect)) {
    			buttonClicked = 2;
    		}
    		else if(player.intersects(cyanButtonRect)) {
    			buttonClicked = 3;
    		}
    		else if(player.intersects(greenButtonRect)) {
    			buttonClicked = 4;
    		}
    		else if(player.intersects(redButtonRect)) {
    			buttonClicked = 5;
    		}
    		if(player.intersects(doorRect)) {
    			scene = 0;
    		}
		}
	}
}