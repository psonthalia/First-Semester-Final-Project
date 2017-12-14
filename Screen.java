import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Stack;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Stack;
import java.io.*;
import java.net.*;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Screen extends JPanel implements ActionListener, KeyListener {

	private JButton playGame;
	private JButton instructions;
	private JButton mainMenu;

	private int x = 70;
	private int y = 100;

	Item[] itemsLevel1 = new Item[3];
	Enemy[] enemiesLevel1 = new Enemy[6];
	Obstacle[] obstaclesLevel1 = new Obstacle[6];
	CarEnemy[] carEnemiesLevel1 = new CarEnemy[2];
	Food[] foodLevel1 = new Food[2];

	Item[] itemsLevel2 = new Item[4];
	Enemy[] enemiesLevel2 = new Enemy[9];
	Obstacle[] obstaclesLevel2 = new Obstacle[9];
	CarEnemy[] carEnemiesLevel2 = new CarEnemy[4];
	Food[] foodLevel2 = new Food[2];

	TreeMap<Item, Integer> inventory = new TreeMap<Item, Integer>();

	private boolean playerMoveUp = false;
	private boolean playerMoveDown = false;
	private boolean playerMoveLeft = false;
	private boolean playerMoveRight = false;

	Level1 level1 = new Level1();
	Level2 level2 = new Level2();
	private int currentLevel = 0;

	private final int playerWidth = 73;
	private final int playerHeight = 89;
	CarEnemy police1 = new CarEnemy("police.png", 60, 400, 100, 100);
	CarEnemy police2 = new CarEnemy("police.png", 780, 400, 100, 100);

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

	private Stack<Integer> health = new Stack<Integer>();

	Player player;
	Player otherPlayer;

	private boolean checkInventory = true;

	private static final long serialVersionUID = 1209235;

	public Dimension getPreferredSize() {
		return new Dimension(1000, 900);
	}
	public void resetGame() {
		player = new Player("player.png", x, y, playerWidth, playerHeight);

		itemsLevel1[0] = new Item("chest.png", 200, 200, 100, 79, "Chest 1");
		itemsLevel1[1] = new Item("key.png", 400, 400, 100, 79, "Key 1");
		itemsLevel1[2] = new Item("money.png", 800, 700, 100, 79, "Money 1");

		inventory.put(new Item("chest.png", 0, 0, 0, 0, "chest"), 0);
		inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), 0);
		inventory.put(new Item("money.png", 0, 0, 0, 0, "money"), 0);

		enemiesLevel1[0] = new Enemy("shopkeeper.png", 100, 300, 100, 129, 2, 500, 200);
		enemiesLevel1[1] = new Enemy("shopkeeper2.png", 200, 100, 100, 129, 1, 500, 300);
		enemiesLevel1[2] = new Enemy("shopkeeper.png", 100, 300, 100, 129, 2, 500, 200);
		enemiesLevel1[3] = new Enemy("shopkeeper2.png", 200, 100, 100, 129, 1, 500, 300);
		enemiesLevel1[4] = new Enemy("shopkeeper.png", 100, 300, 100, 129, 2, 500, 200);
		enemiesLevel1[5] = new Enemy("shopkeeper2.png", 200, 100, 100, 129, 1, 500, 300);

		obstaclesLevel1[0] = new Obstacle("obstacle.png", 100, 80, 150, 132);
		obstaclesLevel1[1] = new Obstacle("obstacle.png", 560, 650, 150, 132);
		obstaclesLevel1[2] = new Obstacle("obstacle.png", 300, 150, 150, 132);
		obstaclesLevel1[3] = new Obstacle("obstacle.png", 600, 850, 150, 132);
		obstaclesLevel1[4] = new Obstacle("obstacle.png", 100, 750, 150, 132);
		obstaclesLevel1[5] = new Obstacle("obstacle.png", 500, 350, 150, 132);

		carEnemiesLevel1[0] = new CarEnemy("jaguar.png", 390, 700, 86, 175);
		carEnemiesLevel1[1] = new CarEnemy("mclaren.png", 505, 200, 100, 191);

		foodLevel1[0] = new Food("food.png", 400, 800, 80, 80);
		foodLevel1[1] = new Food("food.png", 520, 20, 80, 80);



		itemsLevel2[0] = new Item("engine.png", 200, 200, 100, 79, "Engine 1");
		itemsLevel2[1] = new Item("iphone.png", 300, 300, 100, 100, "iPhone 1");
		itemsLevel2[2] = new Item("card.png", 400, 400, 80, 59, "Card 1");
		itemsLevel2[3] = new Item("battery.png", 800, 700, 100, 100, "Battery 1");

		enemiesLevel2[0] = new Enemy("shopkeeper.png", 100, 300, 100, 129, 2, 500, 200);
		enemiesLevel2[1] = new Enemy("shopkeeper3.png", 200, 100, 100, 172, 1, 500, 300);
		enemiesLevel2[2] = new Enemy("shopkeeper3.png", 800, 100, 100, 172, 2, 600, 300);
		enemiesLevel2[3] = new Enemy("shopkeeper.png", 100, 300, 100, 129, 2, 500, 200);
		enemiesLevel2[4] = new Enemy("shopkeeper3.png", 200, 100, 100, 172, 1, 500, 300);
		enemiesLevel2[5] = new Enemy("shopkeeper3.png", 800, 100, 100, 172, 2, 600, 300);
		enemiesLevel2[6] = new Enemy("shopkeeper.png", 100, 300, 100, 129, 2, 500, 200);
		enemiesLevel2[7] = new Enemy("shopkeeper3.png", 200, 100, 100, 172, 1, 500, 300);
		enemiesLevel2[8] = new Enemy("shopkeeper3.png", 800, 100, 100, 172, 2, 600, 300);

		obstaclesLevel2[0] = new Obstacle("obstacle.png", 100, 80, 150, 132);
		obstaclesLevel2[1] = new Obstacle("obstacle.png", 560, 650, 150, 132);
		obstaclesLevel2[2] = new Obstacle("obstacle.png", 300, 150, 150, 132);
		obstaclesLevel2[3] = new Obstacle("obstacle.png", 600, 850, 150, 132);
		obstaclesLevel2[4] = new Obstacle("obstacle.png", 100, 750, 150, 132);
		obstaclesLevel2[5] = new Obstacle("obstacle.png", 500, 350, 150, 132);
		obstaclesLevel2[6] = new Obstacle("obstacle.png", 500, 350, 150, 132);
		obstaclesLevel2[7] = new Obstacle("obstacle.png", 500, 350, 150, 132);
		obstaclesLevel2[8] = new Obstacle("obstacle.png", 500, 350, 150, 132);

		carEnemiesLevel2[0] = new CarEnemy("koingsegg.png", 165, 300, 141, 200);
		carEnemiesLevel2[1] = new CarEnemy("ferrari.png", 305, 800, 100, 184);
		carEnemiesLevel2[2] = new CarEnemy("bugatti.png", 605, 100, 120, 242);
		carEnemiesLevel2[3] = new CarEnemy("acura.png", 735, 600, 106, 200);

		foodLevel2[0] = new Food("food.png", 300, 800, 80, 80);
		foodLevel2[1] = new Food("food.png", 620, 20, 80, 80);

		inventory.put(new Item("battery.png", 0, 0, 0, 0, "battery"), 0);
		inventory.put(new Item("iphone.png", 0, 0, 0, 0, "iphone"), 0);
		inventory.put(new Item("card.png", 0, 0, 0, 0, "card"), 0);
		inventory.put(new Item("engine.png", 0, 0, 0, 0, "engine"), 0);

		for(int i = 0; i < 20; i++) {
			health.push(1);
		} 
	}
	public Screen() {
		this.setLayout(null);
		this.addKeyListener(this);
		this.setFocusable(true);

		playGame = new JButton("Play Game!");
		playGame.setFont(new Font("Arial", Font.BOLD, 27));
		playGame.setBounds(400, 350, 200, 80);
		playGame.addActionListener(this);
		this.add(playGame);

		instructions = new JButton("Instructions");
		instructions.setFont(new Font("Arial", Font.BOLD, 27));
		instructions.setBounds(400, 450, 200, 80);
		instructions.addActionListener(this);
		this.add(instructions);

		mainMenu = new JButton("Return to Main Menu");
		mainMenu.setFont(new Font("Arial", Font.BOLD, 23));
		mainMenu.setBounds(320, 450, 300, 80);
		mainMenu.addActionListener(this);
		mainMenu.setVisible(false);
		this.add(mainMenu);


		resetGame();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(currentLevel == 1) {
			level1.drawMe(g, itemsLevel1);
			for(int i = 0; i < itemsLevel1.length; i++) {
				if(itemsLevel1[i] != null) {
					itemsLevel1[i].drawMe(g);
				}
			}
			for(int i = 0; i < enemiesLevel1.length; i++) {
				if(enemiesLevel1[i] != null) {
					enemiesLevel1[i].drawMe(g);
				}
			}
			for(int i = 0; i < obstaclesLevel1.length; i++) {
				if(obstaclesLevel1[i] != null) {
					obstaclesLevel1[i].drawMe(g);
				}
			}
			for(int i = 0; i < carEnemiesLevel1.length; i++) {
				if(carEnemiesLevel1[i] != null) {
					carEnemiesLevel1[i].drawMe(g);
				}
			}
			for(int i = 0; i < foodLevel1.length; i++) {
				if(foodLevel1[i] != null) {
					foodLevel1[i].drawMe(g);
				}
			}
		} else if(currentLevel == 2) {
			level2.drawMe(g, itemsLevel2);
			for(int i = 0; i < itemsLevel2.length; i++) {
				if(itemsLevel2[i] != null) {
					itemsLevel2[i].drawMe(g);
				}
			}
			for(int i = 0; i < enemiesLevel2.length; i++) {
				if(enemiesLevel2[i] != null) {
					enemiesLevel2[i].drawMe(g);
				}
			}
			for(int i = 0; i < obstaclesLevel2.length; i++) {
				if(obstaclesLevel2[i] != null) {
					obstaclesLevel2[i].drawMe(g);
				}
			}
			for(int i = 0; i < carEnemiesLevel2.length; i++) {
				if(carEnemiesLevel2[i] != null) {
					carEnemiesLevel2[i].drawMe(g);
				}
			}
			for(int i = 0; i < foodLevel2.length; i++) {
				if(foodLevel2[i] != null) {
					foodLevel2[i].drawMe(g);
				}
			}
		}
		else if(currentLevel == 3) {
			g.setColor(Color.green);
			g.setFont(new Font("Arial", Font.BOLD, 80));
	       	g.drawString("You Win!", 300, 300);
	       	g.setColor(Color.black);
	       	g.setFont(new Font("Arial", Font.BOLD, 25));
	       	g.drawString("[You may commence the winning dance]", 230, 400);
	       	mainMenu.setVisible(true);
	       	this.add(mainMenu);
		}
		else if(currentLevel == 4) {
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 80));
	       	g.drawString("You Lose!", 300, 300);
	       	g.setColor(Color.black);
	       	g.setFont(new Font("Arial", Font.BOLD, 25));
	       	g.drawString("[You may commence the losing dance]", 230, 400);
	       	mainMenu.setVisible(true);
	       	this.add(mainMenu);
		}
		
		if(currentLevel != 0 && currentLevel != 5) {
			player.drawMe(g);
			if(otherPlayer != null) {
				otherPlayer.drawMe(g);
			}

	       	g.setColor(Color.gray);
	       	g.fillRect(80, 20, 600, 15);
	       	g.setColor(Color.red);
	       	g.fillRect(80, 20, health.size() * 30, 15);
	       	g.setFont(new Font("Arial", Font.BOLD, 18));
	       	g.drawString("Health: ", 15, 35);

	       	g.setColor(Color.gray);
	       	g.fillRect(790, 10, 200, 240);

	       	g.setColor(Color.white);
	       	g.setFont(new Font("Arial", Font.BOLD, 25));
	       	g.drawString("Inventory", 830, 35);
	       	g.setColor(Color.black);
	       	g.setFont(new Font("Arial", Font.PLAIN, 20));
	       	int y = 0;
	       	for(Item key : inventory.keySet()) {
				g.drawString(key + ": " + inventory.get(key), 800, 60 + (y * 30));
				y++;
	       	}

	       	if(inventory.get(new Item("chest.png", 0, 0, 0, 0, "chest")) == 1 && inventory.get(new Item("key.png", 0, 0, 0, 0, "key")) == 1 && inventory.get(new Item("money.png", 0, 0, 0, 0, "money")) == 1) {
	       		level1.setCollectedItems(true);
	       		if(inventory.get(new Item("battery.png", 0, 0, 0, 0, "battery")) == 1 && inventory.get(new Item("engine.png", 0, 0, 0, 0, "engine")) == 1 && inventory.get(new Item("iphone.png", 0, 0, 0, 0, "iphone")) == 1 && inventory.get(new Item("card.png", 0, 0, 0, 0, "card")) == 1) {
		       		level2.setCollectedItems(true);
		       	}
	       	}
		}

		if(currentLevel == 0) {
			this.add(playGame);
			this.add(instructions);
			try {                
				BufferedImage concrete = ImageIO.read(new File("concrete.jpg"));
	        	g.drawImage(concrete, 0, 0, this);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

	       	police1.drawMe(g);
	       	police2.drawMe(g);

			g.setFont(new Font("Arial", Font.BOLD, 50));
	       	g.drawString("Let's Go Shoplifting!", 250, 70);
		}

		if(currentLevel == 5) {
			playGame.setBounds(800, 5, 200, 80);
			this.add(playGame);
			try {                
				BufferedImage concrete = ImageIO.read(new File("concrete.jpg"));
	        	g.drawImage(concrete, 0, 0, this);
	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

			g.setFont(new Font("Arial", Font.BOLD, 50));
	       	g.drawString("Let's Go Shoplifting!", 250, 70);

	       	g.setFont(new Font("Arial", Font.PLAIN, 20));
	       	g.setColor(Color.white);
	       	g.drawString("There are 2 levels, and in each level there are 3 stores. Each store has a given amount of items.", 50, 120);

	       	try {                
				BufferedImage redButton = ImageIO.read(new File("redButton.png"));
				BufferedImage blueButton = ImageIO.read(new File("blueButton.png"));
				BufferedImage cyanButton = ImageIO.read(new File("cyanButton.png"));
				BufferedImage yellowButton = ImageIO.read(new File("yellowButton.png"));
				BufferedImage greenButton = ImageIO.read(new File("greenButton.png"));

	        	g.drawImage(redButton, 600, 150, this);
	        	g.drawImage(blueButton, 650, 150, this);
	        	g.drawImage(cyanButton, 600, 190, this);
	        	g.drawImage(yellowButton, 650, 190, this);
	        	g.drawImage(greenButton, 625, 220, this);

	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

	       	g.drawString("In each shop, you will see 5 different", 50, 180);
	       	g.drawString("buttons like the ones pictured here.", 50, 200);
	       	g.drawString("When you click on the correct button", 50, 230);
	       	g.drawString("the corresponding item will show up.", 50, 250);

	       	try {                
				BufferedImage shopkeeper = ImageIO.read(new File("shopkeeper.png"));

	        	g.drawImage(shopkeeper, 100, 340, this);

	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}


	       	g.drawString("Watch out for the enemies! They will", 500, 350);
	       	g.drawString("be the shopkeepers that will make you", 500, 370);
	       	g.drawString("lose health if you hit them.", 500, 390);
	       	g.drawString("There are a few different enemies,", 500, 420);
	       	g.drawString("and one is pictured here.", 500, 440);
	       	g.drawString("The moving cars are also enemies,", 500, 470);
	       	g.drawString("not the stationary ones.", 500, 490);
	       	g.drawString("Watch out! Sometimes when you enter ", 500, 520);
	       	g.drawString("a shop, you will hit an enemy immediately.", 500, 540);

	       	try {                
				BufferedImage reddoor = ImageIO.read(new File("reddoor.png"));

	        	g.drawImage(reddoor, 600, 610, this);

	       	} catch (IOException ex) {
	            System.out.println(ex);
	       	}

	       	g.drawString("Once you collect all the items required", 50, 640);
	       	g.drawString("for that level, a red door will show ", 50, 660);
	       	g.drawString("up on the scene with the stores and it", 50, 680);
	       	g.drawString("will allow you to go to the next level.", 50, 700);

		}
	}

    public void checkCollison() {
    	if(currentLevel == 1) {
    		level1.checkCollison(player.getX(), player.getY(), playerWidth, playerHeight);

			for(int i = 0; i < itemsLevel1.length; i++) {
	    		if(itemsLevel1[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel1[i].getImageName().equals("chest.png")) {
	    			itemsLevel1[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("chest.png", 0, 0, 0, 0, "chest"), inventory.get(new Item("chest.png", 0, 0, 0, 0, "chest")) + 1);
	    		} 
	    		else if(itemsLevel1[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel1[i].getImageName().equals("key.png")) {
	    			itemsLevel1[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), inventory.get(new Item("key.png", 0, 0, 0, 0, "key")) + 1);
	    		}
	    		else if(itemsLevel1[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel1[i].getImageName().equals("money.png")) {
	    			itemsLevel1[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("money.png", 0, 0, 0, 0, "money"), inventory.get(new Item("money.png", 0, 0, 0, 0, "money")) + 1);
	    		}
	    	}
	    	for(int i = 0; i < foodLevel1.length; i++) {
	    		if(foodLevel1[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true) {
	    			foodLevel1[i].setEnabled(false);
	    			playItemPickUp();
	    			health.push(1);
	    			health.push(1);
	    		}
	    	}
    		for(int i = 0; i < enemiesLevel1.length; i++) {
	    		if(enemiesLevel1[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight)) {
	    			if(health.size() > 0) {
	    				health.pop();
	    				playHealthLoss();
	    				level1.hitEnemy();
	    				player.setX(100);
	    				player.setY(100);
	    			}
	    			enemiesLevel1[i].setEnabled(false);
	    		}
	    	}
	    	for(int i = 0; i < obstaclesLevel1.length; i++) {
	    		if(obstaclesLevel1[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight)) {
	    			if(health.size() > 0) {
	    				health.pop();
	    				playHealthLoss();
	    				level1.hitEnemy();
	    				player.setX(100);
	    				player.setY(100);
	    			}
	    			obstaclesLevel1[i].setEnabled(false);
	    		}
	    	}
	    	for(int i = 0; i < carEnemiesLevel1.length; i++) {
	    		if(carEnemiesLevel1[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight)) {
	    			if(health.size() > 0) {
	    				health.pop();
	    				playHealthLoss();
	    				if(health.size() > 0) {
	    					health.pop();
	    				}
	    				level1.hitEnemy();
	    				player.setX(100);
	    				player.setY(100);
	    			}
	    			carEnemiesLevel1[i].setEnabled(false);
	    		}
	    	}
    	} else if(currentLevel == 2) {
    		level2.checkCollison(player.getX(), player.getY(), playerWidth, playerHeight);

    		for(int i = 0; i < itemsLevel2.length; i++) {
	    		if(itemsLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel2[i].getImageName().equals("engine.png")) {
	    			itemsLevel2[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("engine.png", 0, 0, 0, 0, "engine"), inventory.get(new Item("engine.png", 0, 0, 0, 0, "engine")) + 1);
	    		} 
	    		else if(itemsLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel2[i].getImageName().equals("key.png")) {
	    			itemsLevel2[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), inventory.get(new Item("key.png", 0, 0, 0, 0, "key")) + 1);
	    		}
	    		else if(itemsLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel2[i].getImageName().equals("battery.png")) {
	    			itemsLevel2[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("battery.png", 0, 0, 0, 0, "battery"), inventory.get(new Item("battery.png", 0, 0, 0, 0, "battery")) + 1);
	    		}
	    		else if(itemsLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel2[i].getImageName().equals("iphone.png")) {
	    			itemsLevel2[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("iphone.png", 0, 0, 0, 0, "iphone"), inventory.get(new Item("iphone.png", 0, 0, 0, 0, "iphone")) + 1);
	    		}
	    		else if(itemsLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true && itemsLevel2[i].getImageName().equals("card.png")) {
	    			itemsLevel2[i].setEnabled(false);
	    			playItemPickUp();
	    			inventory.put(new Item("card.png", 0, 0, 0, 0, "card"), inventory.get(new Item("card.png", 0, 0, 0, 0, "card")) + 1);
	    		}
	    	}

	    	for(int i = 0; i < foodLevel2.length; i++) {
	    		if(foodLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight) == true) {
	    			foodLevel2[i].setEnabled(false);
	    			playItemPickUp();
	    			health.push(1);
	    			health.push(1);
	    		}
	    	}

    		for(int i = 0; i < enemiesLevel2.length; i++) {
	    		if(enemiesLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight)) {
	    			if(health.size() > 0) {
	    				health.pop();
	    				playHealthLoss();
	    				level2.hitEnemy();
	    				player.setX(100);
	    				player.setY(100);
	    			}
	    			enemiesLevel2[i].setEnabled(false);
	    		}
	    	}
	    	for(int i = 0; i < obstaclesLevel2.length; i++) {
	    		if(obstaclesLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight)) {
	    			if(health.size() > 0) {
	    				health.pop();
	    				playHealthLoss();
	    				level2.hitEnemy();
	    				player.setX(100);
	    				player.setY(100);
	    			}
	    			obstaclesLevel2[i].setEnabled(false);
	    		}
	    	}
    		for(int i = 0; i < carEnemiesLevel2.length; i++) {
	    		if(carEnemiesLevel2[i].checkCollison(player.getX(), player.getY(), playerWidth, playerHeight)) {
	    			if(health.size() > 0) {
	    				health.pop();
	    				playHealthLoss();
	    				if(health.size() > 0) {
	    					health.pop();
	    				}
	    				level2.hitEnemy();
	    				player.setX(100);
	    				player.setY(100);
	    			}
	    			carEnemiesLevel2[i].setEnabled(false);
	    		}
	    	}
    	}
    }

    public void animate() throws IOException {

    	String hostName = "localhost"; 
        int portNumber = 1024;

        try {
            serverSocket = new ServerSocket(portNumber);
            clientSocket = serverSocket.accept();
            outObj = new ObjectOutputStream(clientSocket.getOutputStream());
            inObj = new ObjectInputStream(clientSocket.getInputStream());

            while(true) {
	    		try {
	    			Thread.sleep(5);
	    		} catch(InterruptedException ex) {
	    			Thread.currentThread().interrupt();
	    		}
	    		if(playerMoveUp) {
	    			player.moveY(-1);
	    		} 
	    		if(playerMoveDown) {
	    			player.moveY(1);
	    		}
	    		if(playerMoveRight) {
	    			player.moveX(1);
	    		}
	    		if(playerMoveLeft) {
	    			player.moveX(-1);
	    		}

	    		
	    		if(currentLevel == 1) {
	    			if(level1.inShop()) {
		    			int start = 0;
						int end = 0;
						if(level1.getShop() == 1) {
							start = 0;
							end = 2;
						} else if(level1.getShop() == 2) {
							start = 2;
							end = 4;
						} else if(level1.getShop() == 3) {
							start = 4;
							end = 6;
						}

		    			for(int i = start; i < end; i++) {
		    				enemiesLevel1[i].setHidden(false);
		    			}
		    			for(int i = start; i < end; i++) {
							enemiesLevel1[i].move();
						}
						for(int i = start; i < end; i++) {
		    				obstaclesLevel1[i].setHidden(false);
		    			}
		    			for(int i = 0; i < carEnemiesLevel1.length; i++) {
							carEnemiesLevel1[i].setHidden(true);
						}
		    		} 
		    		else {
		    			for(int i = 0; i < enemiesLevel1.length; i++) {
		    				enemiesLevel1[i].setHidden(true);
		    			}
		    			for(int i = 0; i < obstaclesLevel1.length; i++) {
		    				obstaclesLevel1[i].setHidden(true);
		    			}
		    			for(int i = 0; i < carEnemiesLevel1.length; i++) {
							carEnemiesLevel1[i].setHidden(false);
						}
		    			for(int i = 0; i < carEnemiesLevel1.length; i++) {
							carEnemiesLevel1[i].move();
						}
		    		}

		    		if(level1.getLevel1Done()) {
		    			currentLevel = 2;
		    			carEnemiesLevel1 = new CarEnemy[2];
		    			enemiesLevel1 = new Enemy[6];
		    			obstaclesLevel1 = new Obstacle[6];
			    	}
	    		}
	    		else if(currentLevel == 2) {
	    			if(level2.inShop()) {
		    			int start = 0;
						int end = 0;
						if(level2.getShop() == 1) {
							start = 0;
							end = 3;
						} else if(level2.getShop() == 2) {
							start = 3;
							end = 6;
						} else if(level2.getShop() == 3) {
							start = 6;
							end = 9;
						}

		    			for(int i = start; i < end; i++) {
		    				enemiesLevel2[i].setHidden(false);
		    			}
		    			for(int i = start; i < end; i++) {
							enemiesLevel2[i].move();
						}
						for(int i = start; i < end; i++) {
		    				obstaclesLevel2[i].setHidden(false);
		    			}
		    			for(int i = 0; i < carEnemiesLevel2.length; i++) {
							carEnemiesLevel2[i].setHidden(true);
						}
		    		} 
		    		else {
		    			for(int i = 0; i < enemiesLevel2.length; i++) {
		    				enemiesLevel2[i].setHidden(true);
		    			}
		    			for(int i = 0; i < obstaclesLevel2.length; i++) {
		    				obstaclesLevel2[i].setHidden(true);
		    			}
		    			for(int i = 0; i < carEnemiesLevel2.length; i++) {
							carEnemiesLevel2[i].setHidden(false);
						}
		    			for(int i = 0; i < carEnemiesLevel2.length; i++) {
							carEnemiesLevel2[i].move();
						}
		    		}

		    		if(level2.getLevel2Done()) {
		    			currentLevel = 3;
		    			carEnemiesLevel2 = new CarEnemy[4];
		    			enemiesLevel2 = new Enemy[9];
		    			obstaclesLevel2 = new Obstacle[9];
			    	}
	    		}
	    		
	    		if(currentLevel == 0) {
	    			police2.move();
	    			police1.move();
	    		}
	    		
	    		if(health.size() == 0) {
	    			currentLevel = 4;
	    		}

	    		writeData();

	    		int index = 0;

	    		boolean currentLevel0 = (boolean) inObj.readObject();
	    		boolean currentLevel1 = (boolean) inObj.readObject();
	    		boolean currentLevel2 = (boolean) inObj.readObject();
	    		boolean currentLevel3 = (boolean) inObj.readObject();
	    		boolean currentLevel4 = (boolean) inObj.readObject();
	    		boolean currentLevel5 = (boolean) inObj.readObject();
	    		boolean checkInventoryTemp = (boolean) inObj.readObject();
	    		if(checkInventoryTemp != true) {
	    			checkInventory = checkInventoryTemp;
	    			if(inventory.get(new Item("battery.png", 0, 0, 0, 0, "battery")) != 0) {
	    				inventory.put(new Item("battery.png", 0, 0, 0, 0, "battery"), 0);
						inventory.put(new Item("iphone.png", 0, 0, 0, 0, "iphone"), 0);
						inventory.put(new Item("card.png", 0, 0, 0, 0, "card"), 0);
						inventory.put(new Item("engine.png", 0, 0, 0, 0, "engine"), 0);
						inventory.put(new Item("chest.png", 0, 0, 0, 0, "chest"), 0);
						inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), 0);
						inventory.put(new Item("money.png", 0, 0, 0, 0, "money"), 0);
	    			} else {
	    				checkInventory = true;
	    			}
	    			
	    		}
	    		
	    		if(currentLevel0 && currentLevel != 1 && currentLevel != 5) {
	    			if(currentLevel == 3) {
	    				currentLevel = 0;
			    		health.clear();

						inventory.put(new Item("battery.png", 0, 0, 0, 0, "battery"), 0);
						inventory.put(new Item("iphone.png", 0, 0, 0, 0, "iphone"), 0);
						inventory.put(new Item("card.png", 0, 0, 0, 0, "card"), 0);
						inventory.put(new Item("engine.png", 0, 0, 0, 0, "engine"), 0);
						inventory.put(new Item("chest.png", 0, 0, 0, 0, "chest"), 0);
						inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), 0);
						inventory.put(new Item("money.png", 0, 0, 0, 0, "money"), 0);

						level1 = new Level1();
						level2 = new Level2();

						this.requestFocus();
						resetGame();
						removeAll();
	    			} else {
	    				currentLevel = 0;
	    			}
	    		}
	    		if(currentLevel1 && (currentLevel == 0 || currentLevel == 5)) {
	    			currentLevel = 1;
	    			this.requestFocus();
	    			removeAll();
	    		}
	    		if(currentLevel2 && currentLevel == 1) {
	    			currentLevel = 2;
	    		} 
	    		if(currentLevel3 && currentLevel == 2) {
	    			currentLevel = 3;
	    		}
	    		if(currentLevel5 && currentLevel == 0) {
	    			currentLevel = 5;
	    			this.requestFocus();
			    	removeAll();
	    		}
	    		
	    		TreeMap<Item, Integer> inventoryTemp = (TreeMap<Item, Integer>) inObj.readObject();
	    		if(checkInventory) {
		    		for(Item each : inventoryTemp.keySet()) {
		    			if(inventoryTemp.get(each).intValue() != 0 || currentLevel0) {
		    				inventory.put(each, inventoryTemp.get(each));
		    			}
		    		}
	    		}
	    		
    			
    			index = 0;
    			Item[] itemsLevel1Temp = (Item[]) inObj.readObject();
    			for(Item each : itemsLevel1Temp) {
    				if(each != null) {
    					if(each.getEnabled() == false) {
	    					itemsLevel1[index] = each;
	    				}
    				}
    				index++;
    			}

    			index = 0;
    			Enemy[] enemiesLevel1Temp = (Enemy[]) inObj.readObject();
    			for(Enemy each : enemiesLevel1Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					enemiesLevel1[index] = each;
	    				}
	    			}
    				index++;
    			}

    			index = 0;
    			Obstacle[] obstaclesLevel1Temp = (Obstacle[]) inObj.readObject();
    			for(Obstacle each : obstaclesLevel1Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					obstaclesLevel1[index] = each;
	    				}
	    			}
    				index++;
    			}
    			
    			index = 0;
    			CarEnemy[] carEnemiesLevel1Temp = (CarEnemy[]) inObj.readObject();
    			for(CarEnemy each : carEnemiesLevel1Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					carEnemiesLevel1[index] = each;
	    				}
	    			}
    				index++;
    			}

    			index = 0;
    			Food[] foodLevel1Temp = (Food[]) inObj.readObject();
    			for(Food each : foodLevel1Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					foodLevel1[index] = each;
	    				}
	    			}
    				index++;
    			}

    			index = 0;
    			Item[] itemsLevel2Temp = (Item[]) inObj.readObject();
    			for(Item each : itemsLevel2Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					itemsLevel2[index] = each;
	    				}
	    			}
    				index++;
    			}

    			index = 0;
    			Enemy[] enemiesLevel2Temp = (Enemy[]) inObj.readObject();
    			for(Enemy each : enemiesLevel2Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					enemiesLevel2[index] = each;
	    				}
	    			}
    				index++;
    			}

    			index = 0;
    			Obstacle[] obstaclesLevel2Temp = (Obstacle[]) inObj.readObject();
    			for(Obstacle each : obstaclesLevel2Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					obstaclesLevel2[index] = each;
	    				}
	    			}
    				index++;
    			}

    			index = 0;
    			CarEnemy[] carEnemiesLevel2Temp = (CarEnemy[]) inObj.readObject();
    			for(CarEnemy each : carEnemiesLevel2Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					carEnemiesLevel2[index] = each;
	    				}
	    			}
    				index++;
    			}

    			index = 0;
    			Food[] foodLevel2Temp = (Food[]) inObj.readObject();
    			for(Food each : foodLevel2Temp) {
    				if(each != null) {
	    				if(each.getEnabled() == false) {
	    					foodLevel2[index] = each;
	    				}
	    			}
    				index++;
    			}

    			otherPlayer = (Player) inObj.readObject();

    			Object otherSceneTemp = inObj.readObject();
	    		if(otherSceneTemp != null) {
	    			int otherScene = ((Integer) otherSceneTemp).intValue();
		    		if(currentLevel == 1) {
		    			if(level1.getScene() == otherScene) {
		    				otherPlayer.setHidden(false);
		    			} else {
		    				otherPlayer.setHidden(true);
		    			}
		    		} else if(currentLevel == 2) {
		    			if(level2.getScene() == otherScene) {
		    				otherPlayer.setHidden(false);
		    			} else {
		    				otherPlayer.setHidden(true);
		    			}
		    		}
	    		}

    			if(checkInventory == false) {
    				checkInventory = true;
    			}

	    		checkCollison();
	    		repaint();
                
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 38) {
        	playerMoveUp = true;
        } else if(e.getKeyCode() == 39) {
        	playerMoveRight = true;
        } else if(e.getKeyCode() == 40) {
        	playerMoveDown = true;
        } else if(e.getKeyCode() == 37) {
        	playerMoveLeft = true;
        } else if(e.getKeyCode() == 80) {
        	if(currentLevel == 1) {
				inventory.put(new Item("chest.png", 0, 0, 0, 0, "chest"), 1);
				inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), 1);
				inventory.put(new Item("money.png", 0, 0, 0, 0, "money"), 1);
				carEnemiesLevel1 = new CarEnemy[2];
	    		enemiesLevel1 = new Enemy[6];
	    		obstaclesLevel1 = new Obstacle[6];

        		currentLevel = 2;
        	} else if(currentLevel == 2) {
        		inventory.put(new Item("battery.png", 0, 0, 0, 0, "battery"), 1);
				inventory.put(new Item("iphone.png", 0, 0, 0, 0, "iphone"), 1);
				inventory.put(new Item("card.png", 0, 0, 0, 0, "card"), 1);
				inventory.put(new Item("engine.png", 0, 0, 0, 0, "engine"), 1);
				carEnemiesLevel2 = new CarEnemy[4];
	    		enemiesLevel2 = new Enemy[9];
	    		obstaclesLevel2 = new Obstacle[9];

				currentLevel = 3;
        	}
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    	if(e.getKeyCode() == 38) {
        	playerMoveUp = false;
        } else if(e.getKeyCode() == 39) {
        	playerMoveRight = false;
        } else if(e.getKeyCode() == 40) {
        	playerMoveDown = false;
        } else if(e.getKeyCode() == 37) {
        	playerMoveLeft = false;
        }
    }

    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == playGame) {
    		currentLevel = 1;
    		checkInventory = true;

    		inventory.put(new Item("battery.png", 0, 0, 0, 0, "battery"), 0);
			inventory.put(new Item("iphone.png", 0, 0, 0, 0, "iphone"), 0);
			inventory.put(new Item("card.png", 0, 0, 0, 0, "card"), 0);
			inventory.put(new Item("engine.png", 0, 0, 0, 0, "engine"), 0);
			inventory.put(new Item("chest.png", 0, 0, 0, 0, "chest"), 0);
			inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), 0);
			inventory.put(new Item("money.png", 0, 0, 0, 0, "money"), 0);
    	}
    	else if(e.getSource() == instructions) {
    		currentLevel = 5;
    	}
    	else if(e.getSource() == mainMenu) {
			playGame.setBounds(400, 350, 200, 80);
    		currentLevel = 0;
    		health.clear();

			inventory.put(new Item("battery.png", 0, 0, 0, 0, "battery"), 0);
			inventory.put(new Item("iphone.png", 0, 0, 0, 0, "iphone"), 0);
			inventory.put(new Item("card.png", 0, 0, 0, 0, "card"), 0);
			inventory.put(new Item("engine.png", 0, 0, 0, 0, "engine"), 0);
			inventory.put(new Item("chest.png", 0, 0, 0, 0, "chest"), 0);
			inventory.put(new Item("key.png", 0, 0, 0, 0, "key"), 0);
			inventory.put(new Item("money.png", 0, 0, 0, 0, "money"), 0);

			level1 = new Level1();
			level2 = new Level2();

			checkInventory = false;

			resetGame();
		}
		this.requestFocus();
    	removeAll();
    	repaint();
    }
    public void writeData() {
     	try {
     		outObj.reset();

     		if(currentLevel == 0) {
     			outObj.writeObject(true);
     		} else {
     			outObj.writeObject(false);
     		}
     		if(currentLevel == 1) {
     			outObj.writeObject(true);
     		} else {
     			outObj.writeObject(false);
     		}
     		if(currentLevel == 2) {
     			outObj.writeObject(true);
     		} else {
     			outObj.writeObject(false);
     		}
     		if(currentLevel == 3) {
     			outObj.writeObject(true);
     		} else {
     			outObj.writeObject(false);
     		}
     		if(currentLevel == 4) {
     			outObj.writeObject(true);
     		} else {
     			outObj.writeObject(false);
     		}
     		if(currentLevel == 5) {
     			outObj.writeObject(true);
     		} else {
     			outObj.writeObject(false);
     		}
     		outObj.writeObject(checkInventory);
            outObj.writeObject(inventory);
            outObj.writeObject(itemsLevel1);
            outObj.writeObject(enemiesLevel1);
            outObj.writeObject(obstaclesLevel1);
            outObj.writeObject(carEnemiesLevel1);
            outObj.writeObject(foodLevel1);
            outObj.writeObject(itemsLevel2);
            outObj.writeObject(enemiesLevel2);
            outObj.writeObject(obstaclesLevel2);
            outObj.writeObject(carEnemiesLevel2);
            outObj.writeObject(foodLevel2);
            outObj.writeObject(player);
            if(currentLevel == 1) {
     			outObj.writeObject(level1.getScene());
     		} else if(currentLevel == 2) {
     			outObj.writeObject(level2.getScene());
     		} else {
     			outObj.writeObject(null);
     		}
        }  catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void playHealthLoss() {
    	try {
            URL url = this.getClass().getClassLoader().getResource("healthLose.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    public void playItemPickUp() {
    	try {
            URL url = this.getClass().getClassLoader().getResource("itemPickUp.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}