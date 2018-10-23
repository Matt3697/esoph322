package pa2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
/**
 * Board class that creates a viewable board after the user initializes
 * the game. Additionally keeps track of players and their locations on
 * the board, as well as the relative placement of each player based on 
 * current cash.
 * 
 * @authors Matt Sagen, Hugh Jackovich, Cory Petersen, James Mitchell
 *
 */
public class Board
{
	/**
	 * Board Class will construct a single board that players can view
	 * to play the game
	 * 
	 * @param int board_spaces is total spaces around a monopoly board
	 * @param colors are the colors of the spaces around the board
	 * @param spaces are the names of each space around the board
	 * @param spot is an array holder for the board spaces
	 * @param players is an array to hold the players in the game
	 * @param time is the time limit of the game
	 * @param numPlayers tracks how many players are playing
	 * @param randomNum is used for the dice rolls
	 * @param count and timer are for the timing of the game
	 * @param start and elapsed time as well as clock are all for ending
	 * the game at the previously set game length 
	 */
	private static final int board_spaces = 40;
	private static final Color[] colors = {Color.WHITE, Color.CYAN, Color.WHITE, Color.CYAN, Color.WHITE, Color.WHITE, Color.BLUE, Color.WHITE, Color.BLUE, Color.BLUE, Color.WHITE, Color.PINK, Color.WHITE,  Color.PINK, Color.PINK, Color.WHITE, Color.ORANGE, Color.WHITE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.YELLOW, Color.WHITE, Color.YELLOW, Color.YELLOW, Color.WHITE, Color.RED, Color.RED, Color.WHITE, Color.RED, Color.WHITE, Color.GREEN, Color.GREEN, Color.WHITE, Color.GREEN, Color.WHITE, Color.WHITE, Color.MAGENTA, Color.WHITE, Color.MAGENTA};
	private String[] midEarthSpaces = {"GO!", "Hobbiton", "Community Chest", "Fornost", "Vilya", "Radagast", "Amon Sul", "Chance", "Rivendale", "Bruinen River", "Jail", "Old Forest Road", "Nenya", "Mirkwood", "Dol Guldor", "Blue Wizards", "Mines of Moria", "Community Chest", "Erebor", "Iron Hills", "Free Parking", "Pelargir", "Narya", "Dol Amroth", "Valley of Erech", "Saruman", "Helm's Deep", "Isengard", "Treebeard", "Fangorn Forest","GO TO JAIL", "Grey Havens", "The One Ring", "Community Chest", "Eye of Sauron", "Gandalf", "Chance", "Mt. Doom", "Black Gate", "Dead Marches"};
	private String[] msuSpaces = {"GO!", "SUB","Community Chest", "Roberts Hall", "Wilson Hall", "Reid Hall", "Linfield Hall", "AJM Hall", "Barnard Hall", "Cobleigh Hall", "Jail","Gaines Hall", "Bobcat Stadium", "Montana Hall", "Jake Jabs Hall", "Bobcat Statue", "South Hedges", "Community Chest", "North Hedges", "Langford", "Free Parking",  "Yellowstone Hall", "Gallatin Hall", "Miller", "Fitness Center", "Hannon", "Hapner", "Johnstone", "Headwaters Complex", "Quads", "GO TO JAIL","Roskie", "ResLife Apartments", "Community Chest", "Book Store", "Lewis Hall", "Chance", "Howard Hall" ,"Graduate Housing", "Leon Johnson Hall"};
	private Space[] spot = new Space[board_spaces];
	private int[] deedPrices = {0,60,0,60,0,200,100,0,100,120,0,140,150,140,160,200,180,0,180,200,0,220,0,220,240,200,260,260,0,280,0,300,300,0,320,200,0,350,0,400};
	private int[] mortgagePrices = {0,30,0,30,0,0,50,0,50,60,0,70,0,70,80,0,90,0,90,100,0,110,0,110,120,0,130,130,0,140,0,150,150,0,160,0,0,175,0,200};
	private int[] rentPrices = {0,2,0,4,0,0,6,0,6,8,0,10,0,10,12,0,14,0,14,16,0,18,0,18,20,0,22,22,0,24,0,26,26,0,28,0,0,35,0,50};
	private int[] houseBuildCosts = {0,50,0,50,0,0,50,0,50,50,0,100,0,100,100,0,100,0,100,100,0,150,0,150,150,0,150,150,0,150,0,200,200,0,200,0,0,200,0,200};
	private int[] hotelBuildCosts = {0,50,0,50,0,0,50,0,50,50,0,100,0,100,100,0,100,0,100,100,0,150,0,150,150,0,150,150,0,150,0,200,200,0,200,0,0,200,0,200};
	private Player[] players;
	private int numPlayers, randomNum, randomNum2, time, jailChoice, buildChoice, houseIter = 0, onesIter = 0, twosIter = 0, threesIter = 0, foursIter = 0, aiIter = 0;
	private int iterator = 0, double_count = 0;
	private String theme;
    private Timer timer;
    private JFrame mainFrame;
	private JLabel curTime, playerCash, buildingIcon, oneIcon, twoIcon, threeIcon, fourIcon, ailab;
	private JButton takeTurn;
	private MouseListener mouseListener;
	private Icon cashLabel, houseIcon;
	private JLayeredPane layers = new JLayeredPane();
	private JLabel[] houses = new JLabel[board_spaces];
	private JLabel[] oneLabs = new JLabel[board_spaces];
	private JLabel[] twoLabs = new JLabel[board_spaces];
	private JLabel[] threeLabs = new JLabel[board_spaces];
	private JLabel[] fourLabs = new JLabel[board_spaces];
	private JLabel[] aiLabs = new JLabel[board_spaces];
	
	public Board(Player[] players){
		this.players = players;	
		
	}
	
	protected void printResults() {
		int highest = 0;
		Player winner = null;
		//loop through the array of players to find the player with the most money, and display which player it is.
		   for(int i = 0; i < players.length; i++)
		   {
			   if(players[i].getCash() > highest)
			   {
				   highest = players[i].getCash();
				   winner = players[i];
			   }
		   }
		   curTime.setText("Game Over! The Winner Is: " + winner.getName());
	}

	public void setTime(int time) {
		this.time = time;
	}
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public int roll() {
		// Usually this can be a field rather than a method variable
	    Random rand = new Random();
	    int random = rand.nextInt(6) + 1;
	    return random;
	}
	
	//gets remaining time via simple math for use in executing the game
	int timeIter = 0;
	public int getRemainingTime(){
		if(timeIter == 60) {
			timeIter = 0;
			return (time -= 1);
		}
		else {
			timeIter++;
			return time;
		}
	}
	//sets the theme to the theme selected by the user
	public void setTheme(String newTheme) {
		theme = newTheme;
		if(newTheme == "Middle Earth") {
			startGame(midEarthSpaces);
		}
		else if(newTheme == "MSU"){
			startGame(msuSpaces);
		}
	}
	public String getTheme() {
		return theme;
	}
	public void startGame(String[] spaces) {
		//Create new JFrame for game. is the first thing when players start
		//the game that shows the board game.
		mainFrame = new JFrame();
		if(getTheme() == "Middle Earth") {
			mainFrame.setTitle("Middle Earth Monopoly Game");
		}
		else if(getTheme() == "MSU") {
			mainFrame.setTitle("MSU Monopoly Game");
		}
		mainFrame.setSize(1375, 900);
		mainFrame.setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border eventBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);
		Border timeBorder = BorderFactory.createLineBorder(Color.GREEN, 1);
		Border chanceBorder = BorderFactory.createLineBorder(Color.ORANGE, 1);
		Border commChestBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
		Border cashBorder = BorderFactory.createLineBorder(Color.RED, 1);
		
		JLabel events = new JLabel();
		events.setBounds(200, 450, 300, 50);
		events.setBorder(eventBorder);
		mainFrame.add(events);
		
		playerCash = new JLabel();
		playerCash.setBounds(1050, 130, 130, 100);
		playerCash.setBorder(cashBorder);
		playerCash.setVerticalAlignment(JLabel.TOP);
		playerCash.setHorizontalAlignment(JLabel.CENTER);
		mainFrame.add(playerCash);
		
		
		
		events.setText("Welcome!");
		
		curTime = new JLabel();
		curTime.setBounds(550, 200, 250, 50);
		curTime.setHorizontalAlignment(JLabel.CENTER);
		curTime.setBorder(timeBorder);
		
		//create game timer counter
		timer = new Timer(1000, new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	//if the time is less than one, print the the winner of the game.
		    	 if(time < 1)
	    		   {
		    		 endGame();
	    		   }
		    	 else {
		    	   time = getRemainingTime();
		       curTime.setText("Time Remaining: " + String.valueOf(time) + " min.");
		    	 }
		    }
		});
		
		//start the game timer
		timer.start();
		
		mainFrame.add(curTime);
		
		//initializes the dice images
		BufferedImage dice1 = null;
		BufferedImage dice2 = null;
		BufferedImage dice3 = null;
		BufferedImage dice4 = null;
		BufferedImage dice5 = null;
		BufferedImage dice6 = null;
		BufferedImage cash = null;
		BufferedImage house = null;
		BufferedImage building = null;
		BufferedImage one = null;
		BufferedImage two = null;
		BufferedImage three = null;
		BufferedImage four = null;
		BufferedImage ai = null;
		
		//attempt to read in all images
		try {
			dice1 = ImageIO.read(new File("src/dice/dice1.png"));
			dice2 = ImageIO.read(new File("src/dice/dice2.png"));
			dice3 = ImageIO.read(new File("src/dice/dice3.png"));
			dice4 = ImageIO.read(new File("src/dice/dice4.png"));
			dice5 = ImageIO.read(new File("src/dice/dice5.png"));
			dice6 = ImageIO.read(new File("src/dice/dice6.png"));	
			cash = ImageIO.read(new File("src/gamePieces/cash.png"));	
			house = ImageIO.read(new File("src/gamePieces/house.jpeg"));
			building = ImageIO.read(new File("src/gamePieces/house2.png"));
			one = ImageIO.read(new File("src/gamePieces/1.png"));
			two = ImageIO.read(new File("src/gamePieces/2.png"));
			three = ImageIO.read(new File("src/gamePieces/3.png"));
			four = ImageIO.read(new File("src/gamePieces/4.png"));
			ai = ImageIO.read(new File("src/gamePieces/a.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//create first dice
		cashLabel = new ImageIcon(cash);
		houseIcon = new ImageIcon(house);
		
		for(int i = 0; i < houses.length; i++) {
			buildingIcon = new JLabel(new ImageIcon(building));
			houses[i] = buildingIcon;
			layers.add(houses[i], i);
		}
		for(int i = 0; i < houses.length; i ++){
			oneIcon = new JLabel(new ImageIcon(one));
			oneLabs[i] = oneIcon;
			layers.add(oneLabs[i], 40 + i);
		}
		for(int i = 0; i < houses.length; i ++){
			twoIcon = new JLabel(new ImageIcon(two));
			twoLabs[i] = twoIcon;
			layers.add(twoLabs[i], 80 + i);
		}
		for(int i = 0; i < houses.length; i ++){
			threeIcon = new JLabel(new ImageIcon(three));
			threeLabs[i] = threeIcon;
			layers.add(threeLabs[i], 120 + i);
		}
		for(int i = 0; i < houses.length; i ++){
			fourIcon = new JLabel(new ImageIcon(four));
			fourLabs[i] = fourIcon;
			layers.add(fourLabs[i], 160 + i);
		}
		for(int i = 0; i < board_spaces; i++) {
			ailab = new JLabel(new ImageIcon(ai));
			aiLabs[i] = ailab;
			layers.add(aiLabs[i], 200 + i);
		}
		JLabel picLabel = new JLabel(new ImageIcon(dice1));
		JLabel picLabel2 = new JLabel(new ImageIcon(dice2));
		JLabel picLabel3 = new JLabel(new ImageIcon(dice3));
		JLabel picLabel4 = new JLabel(new ImageIcon(dice4));
		JLabel picLabel5 = new JLabel(new ImageIcon(dice5));
		JLabel picLabel6 = new JLabel(new ImageIcon(dice6));
		//create second dice
		JLabel picLabel7 = new JLabel(new ImageIcon(dice1));
		JLabel picLabel8 = new JLabel(new ImageIcon(dice2));
		JLabel picLabel9 = new JLabel(new ImageIcon(dice3));
		JLabel picLabel10 = new JLabel(new ImageIcon(dice4));
		JLabel picLabel11 = new JLabel(new ImageIcon(dice5));
		JLabel picLabel12 = new JLabel(new ImageIcon(dice6));
		
		
		//simply creating settings for inserted images
		picLabel.setBounds(450, 430, 300, 300);
		picLabel2.setBounds(450, 430, 300, 300);
		picLabel3.setBounds(450, 430, 300, 300);
		picLabel4.setBounds(450, 430, 300, 300);
		picLabel5.setBounds(450, 430, 300, 300);
		picLabel6.setBounds(450, 430, 300, 300);
		
		picLabel7.setBounds(600, 430, 300, 300);
		picLabel8.setBounds(600, 430, 300, 300);
		picLabel9.setBounds(600, 430, 300, 300);
		picLabel10.setBounds(600, 430, 300, 300);
		picLabel11.setBounds(600, 430, 300, 300);
		picLabel12.setBounds(600, 430, 300, 300);
		
		mainFrame.add(picLabel6);
		mainFrame.add(picLabel5);
		mainFrame.add(picLabel4);
		mainFrame.add(picLabel3);
		mainFrame.add(picLabel2);
		mainFrame.add(picLabel);
		mainFrame.add(picLabel7);
		mainFrame.add(picLabel8);
		mainFrame.add(picLabel9);
		mainFrame.add(picLabel10);
		mainFrame.add(picLabel11);
		mainFrame.add(picLabel12);
		
		picLabel.setVisible(false);
		picLabel2.setVisible(false);
		picLabel3.setVisible(false);
		picLabel4.setVisible(false);
		picLabel5.setVisible(false);
		picLabel6.setVisible(false);
		picLabel7.setVisible(false);
		picLabel8.setVisible(false);
		picLabel9.setVisible(false);
		picLabel10.setVisible(false);
		picLabel11.setVisible(false);
		picLabel12.setVisible(false);
		
		int spotIter = 0;
		
		
		
		//Create spaces on the board
		for(int i = 0; i < 10; i++) {
			Space bottomRow = new Space((mainFrame.getWidth() - 125) - (i * 125), mainFrame.getHeight() - 200);
			bottomRow.setName(spaces[i]);
			bottomRow.setColor(colors[i]);
			bottomRow.setDeed(deedPrices[i], mortgagePrices[i], rentPrices[i], houseBuildCosts[i], hotelBuildCosts[i], colors[i]);
			JLabel space2 = new JLabel(bottomRow.getName());
			space2.setVerticalAlignment(JLabel.TOP);
			space2.setHorizontalAlignment(JLabel.CENTER);
			space2.setBounds(bottomRow.getX(), bottomRow.getY(), 125, 70);
			space2.setBorder(border);
			space2.setOpaque(true);
			space2.setBackground(bottomRow.getColor());
			mainFrame.add(space2);
			spot[spotIter] = bottomRow;
			spotIter++;
			layers.add(space2, 240 + i);
		}
		for(int i = 0; i < 10; i++) {
			//creates the left column for the board
			Space leftColumn = new Space(0, (mainFrame.getHeight() - 200) - (i * 70));
			leftColumn.setName(spaces[10 + i]);
			leftColumn.setColor(colors[10 + i]);
			leftColumn.setDeed(deedPrices[10 + i], mortgagePrices[10 + i], rentPrices[10 + i], houseBuildCosts[10 + i], hotelBuildCosts[10 + i], colors[10 + i]);
			JLabel space3 = new JLabel(leftColumn.getName());
			space3.setVerticalAlignment(JLabel.TOP);
			space3.setHorizontalAlignment(JLabel.CENTER);
			space3.setBounds(leftColumn.getX(),leftColumn.getY(),125,70);
			space3.setBorder(border);
			space3.setOpaque(true);
			space3.setBackground(leftColumn.getColor());
			mainFrame.add(space3);
			spot[spotIter] = leftColumn;
			spotIter++;
			layers.add(space3, 250 + i);
		}
		for(int i = 0; i < 10; i++) {
			Space topRow = new Space(i*125, 0);
			topRow.setName(spaces[20 + i]);
			topRow.setColor(colors[20 + i]);
			topRow.setDeed(deedPrices[20 + i], mortgagePrices[20 + i], rentPrices[20 + i], houseBuildCosts[20 + i], hotelBuildCosts[20 + i], colors[20 + i]);
			JLabel space = new JLabel(topRow.getName());
			space.setVerticalAlignment(JLabel.TOP);
			space.setHorizontalAlignment(JLabel.CENTER);
			space.setBounds((125*(i)), 0, 125, 70);
			space.setBorder(border);
			space.setOpaque(true);
			space.setBackground(topRow.getColor());
			mainFrame.add(space);
			spot[spotIter] = topRow;
			spotIter++;
			layers.add(space, 260 + i);
		}
		for(int i = 0; i < 10; i++) {
			Space rightColumn = new Space(mainFrame.getWidth() - 125, (i)*70);
			rightColumn.setName(spaces[30 + i]);
			rightColumn.setColor(colors[30 + i]);
			rightColumn.setDeed(deedPrices[30 + i], mortgagePrices[30 + i], rentPrices[30 + i], houseBuildCosts[30 + i], hotelBuildCosts[30 + i], colors[30 + i]);
			JLabel space1 = new JLabel(rightColumn.getName());
			space1.setVerticalAlignment(JLabel.TOP);
			space1.setHorizontalAlignment(JLabel.CENTER);
			space1.setBounds(rightColumn.getX(), rightColumn.getY(), 125, 70);
			space1.setBorder(border);
			space1.setOpaque(true);
			space1.setBackground(rightColumn.getColor());
			mainFrame.add(space1);
			spot[spotIter] = rightColumn;
			spotIter++;
			layers.add(space1, 270 + i);
		}
		
		
		mainFrame.setContentPane(layers);
		layers.add(events, 280);
		layers.add(playerCash, 281);
		layers.add(curTime, 282);
		
		//creates the community chest space
		Space commChest = new Space(1000, 600);
		commChest.setName("Community Chest");
		JLabel commChestSpace = new JLabel(commChest.getName());
		commChestSpace.setHorizontalAlignment(JLabel.CENTER);
		commChestSpace.setBounds(commChest.getX(),commChest.getY(),190,85);
		commChestSpace.setBorder(commChestBorder);
		mainFrame.add(commChestSpace);
		layers.add(commChestSpace, 283);
						
		//creates the chance space 
		Space chance = new Space(190, 130);
		chance.setName("Chance");
		JLabel chanceSpace = new JLabel(chance.getName());
		chanceSpace.setHorizontalAlignment(JLabel.CENTER);
		chanceSpace.setBounds(chance.getX(),chance.getY(),190,85);
		chanceSpace.setBorder(chanceBorder);
		mainFrame.add(chanceSpace);
		layers.add(chanceSpace, 284);

		layers.add(picLabel, 285);
		layers.add(picLabel2, 286);
		layers.add(picLabel3, 287);
		layers.add(picLabel4, 288);
		layers.add(picLabel5, 289);
		layers.add(picLabel6, 290);
		layers.add(picLabel7, 291);
		layers.add(picLabel8, 292);
		layers.add(picLabel9, 293);
		layers.add(picLabel10, 294);
		layers.add(picLabel11, 295);
		layers.add(picLabel12, 296);
		
		//give each player a game piece and initialize the piece on the starting point
		for(int i = 0; i < players.length; i++) {
			players[i].setToken(i, theme);
			players[i].setSpace(spot[0]);
			players[i].getToken().setBounds(players[i].getSpace().getX(),players[i].getSpace().getY(),50,50);
			layers.add(players[i].getToken(), i + 297);
			mainFrame.add(players[i].getToken(), i);
		}

		//Create takeTurn button
		takeTurn = new JButton("Take Turn");
		takeTurn.setBounds(610, 650, 150, 50);
		mainFrame.add(takeTurn);
		mouseListener = new MouseAdapter() {
			
		    public void mouseClicked(MouseEvent e) {
	    		Player currentPlayer = players[iterator];
	    	 	if(currentPlayer.inJail())
		    	{
		    		if(currentPlayer.getJailCount() == 3)
		    		{
		    			if(currentPlayer.getName() != "AI")
		    			{
			    			JOptionPane.showMessageDialog(mainFrame, currentPlayer.getName() + ": You must pay $50 to be released from jail.",
					    			"Final Option", JOptionPane.INFORMATION_MESSAGE, cashLabel);
		    			}
		    			currentPlayer.subCash(50);
		    			currentPlayer.setFree();
		    		}
		    		else
		    		{
		    			if(currentPlayer.getName() == "AI")
		    			{
		    				jailChoice = 1;
		    			}
		    			else
		    			{
			    			Object[] options = {"Pay $50", "Attempt Doubles"};
						    jailChoice = JOptionPane.showOptionDialog(mainFrame, currentPlayer.getName() + " Pay $50 to be released from jail\nor\nattempt a roll at doubles", 
						    		"Jail Option",
						    		JOptionPane.YES_NO_CANCEL_OPTION,
						    		JOptionPane.QUESTION_MESSAGE,
						    		houseIcon,
						    		options,
						    		options[1]);
		    			}

					   if(jailChoice == 0)
					   {
						   currentPlayer.subCash(50);
						   currentPlayer.setFree();
					   }
					   else
					   {
						   currentPlayer.jailCount();
					   }
		    		}
		    	}
	    		//remove previous rolls 
	    		picLabel.setVisible(false);
	    		picLabel2.setVisible(false);
	    		picLabel3.setVisible(false);
	    		picLabel4.setVisible(false);
	    		picLabel5.setVisible(false);
	    		picLabel6.setVisible(false);
	    		picLabel7.setVisible(false);
	    		picLabel8.setVisible(false);
	    		picLabel9.setVisible(false);
	    		picLabel10.setVisible(false);
	    		picLabel11.setVisible(false);
	    		picLabel12.setVisible(false);
	    	
	    		
	   
	    		//get two random numbers
	    		randomNum = roll();
	    		randomNum2 = roll();
	    		
	    		//display dice corresponding to the two random numbers.
    		    if(randomNum == 1) {
    		    		picLabel.setVisible(true);
    		    }
    		    else if(randomNum == 2) {
    		    		picLabel2.setVisible(true);	
    			}
    		    else if(randomNum == 3) {
    		    		picLabel3.setVisible(true);
    			}
    		    else if(randomNum == 4) {
    		    		picLabel4.setVisible(true);
    			}
    		    else if(randomNum == 5) {
    		    		picLabel5.setVisible(true);	
    			}
    		    else if(randomNum == 6) {
    		    		picLabel6.setVisible(true);
    			}
    			if(randomNum2 == 1) {
    				picLabel7.setVisible(true);
    			}
    			else if(randomNum2== 2) {
    				picLabel8.setVisible(true);
    			}
    			else if(randomNum2 == 3) {
    				picLabel9.setVisible(true);
    			}
    			else if(randomNum2 == 4) {
    				picLabel10.setVisible(true);
    			} 
    			else if(randomNum2 == 5) {
    				picLabel11.setVisible(true);
    			}
    			else if(randomNum2 == 6) {
    				picLabel12.setVisible(true);
    			}
    			
    			if(currentPlayer.inJail())
    			{
    				if(randomNum == randomNum2)
    				{
    					currentPlayer.setFree();
    					currentPlayer.takeTurn(randomNum, randomNum2, spot);
    					
    				}
	    		   iterator++;
	    		   double_count = 0;
	    		   if(iterator == players.length) {
	    			   iterator = 0;
	    		   }
    			}
    			else
    			{
	    		   //move the current players token to the next space
	    		   currentPlayer.takeTurn(randomNum, randomNum2, spot);
	    		   //move the token after the players turn;
	    		   currentPlayer.getToken().setVisible(false);
	    		   currentPlayer.getToken().setBounds(currentPlayer.getSpace().getX(), currentPlayer.getSpace().getY(), 50, 50);
	    		   currentPlayer.getToken().setVisible(true);
	    		   events.setText(currentPlayer.getName() + ": landed on " + currentPlayer.getSpaceName() + ".");
	    		   //if the current player passes go, give them $200
	    		   if(currentPlayer.getSpace().getName() == "GO TO JAIL")
	    		   {
	    			   currentPlayer.goToJail();
	    			   currentPlayer.takeTurn(10, 10, spot);
	    			   currentPlayer.setPassGo(false);
		    		   currentPlayer.getToken().setVisible(false);
		    		   currentPlayer.getToken().setBounds(currentPlayer.getSpace().getX(), currentPlayer.getSpace().getY(), 50, 50);
		    		   currentPlayer.getToken().setVisible(true);
	    		   }
	    		   if(currentPlayer.hasPassedGo() == true) {
	    			   currentPlayer.addCash(200);
	    			   cashUpdate(currentPlayer);
	    			   events.setText(currentPlayer.getName() + ": You Have Passed Go! Collect $200");
	    			   currentPlayer.setPassGo(false);
	    		   }
	    		   
	    		 //If a player lands on a property that has no owner offer to sell it to them
	    		   if(currentPlayer.getSpace().getDeed().getOwner() == null && currentPlayer.getSpace().getName() != "GO!" && currentPlayer.getSpace().getName() != "Community Chest"
	    				   && currentPlayer.getSpace().getName() != "Jail" && currentPlayer.getSpace().getName() != "Free Parking"  && currentPlayer.getSpace().getName() != "Go To Jail" && currentPlayer.getSpace().getDeed().getPrice() != 0) {
	    			   
	    			   if(currentPlayer.getName() == "AI")
	    			   {
	    				   if(currentPlayer.hasSufficientFunds())
	    				   {
	    					   JOptionPane.showMessageDialog(mainFrame, currentPlayer.getName() + ": Now owns " + currentPlayer.getSpaceName() + "!",
			        					  "Property Update!", JOptionPane.INFORMATION_MESSAGE, houseIcon);
			    				   currentPlayer.getSpace().getDeed().setOwner(currentPlayer);
			    				   currentPlayer.subCash(currentPlayer.getSpace().getDeed().getPrice());
			    				   cashUpdate(currentPlayer);
			    				   currentPlayer.addDeed(currentPlayer.getSpace().getDeed());  
			    				   aiLabs[aiIter].setBounds(currentPlayer.getSpace().getX() + 90, currentPlayer.getSpace().getY() + 40, 30, 30);
			    				   mainFrame.add(aiLabs[aiIter], aiIter);
			    				   aiIter++;
	    				   }
	    			   }
	    			   else
	    			   {
		    			   Object[] options = {"Yes!", "No, thanks!"};
		    			   int propChoice = JOptionPane.showOptionDialog(mainFrame, currentPlayer.getName() + ": Would you like to purchase " + currentPlayer.getSpace().getName() + " for $" + currentPlayer.getSpace().getDeed().getPrice() + "?\n" + 
		    			    " You currently have $" + currentPlayer.getCash() + ".", 
						   "Property for sale!",
					       JOptionPane.YES_NO_CANCEL_OPTION,
					       JOptionPane.QUESTION_MESSAGE,
					       houseIcon,
					       options,
					       options[1]);
		    		   
			    		   if(propChoice == 0) {
			    			   if(currentPlayer.hasSufficientFunds()) {
			    				   JOptionPane.showMessageDialog(mainFrame, currentPlayer.getName() + ": You now own " + currentPlayer.getSpaceName() + "!",
			        					  "Property Update!", JOptionPane.INFORMATION_MESSAGE, houseIcon);
			    				   currentPlayer.getSpace().getDeed().setOwner(currentPlayer);
			    				   currentPlayer.subCash(currentPlayer.getSpace().getDeed().getPrice());
			    				   cashUpdate(currentPlayer);
			    				   currentPlayer.addDeed(currentPlayer.getSpace().getDeed());
			    				   if(currentPlayer.getName() == "Player 1") {
				    				   oneLabs[onesIter].setBounds(currentPlayer.getSpace().getX() + 90, currentPlayer.getSpace().getY() + 40, 30, 30);
				    				   mainFrame.add(oneLabs[onesIter], onesIter);
				    				   onesIter++;
			    				   }
			    				   if(currentPlayer.getName() == "Player 2") {
				    				   twoLabs[twosIter].setBounds(currentPlayer.getSpace().getX() + 90, currentPlayer.getSpace().getY() + 40, 30, 30);
				    				   mainFrame.add(twoLabs[twosIter], twosIter);
				    				   twosIter++;
			    				   }
			    				   else if(currentPlayer.getName() == "Player 3") {
				    				   threeLabs[threesIter].setBounds(currentPlayer.getSpace().getX() + 90, currentPlayer.getSpace().getY() + 40, 30, 30);
				    				   mainFrame.add(threeLabs[threesIter], threesIter);
				    				   threesIter++;
				    			  }
			    				   else if(currentPlayer.getName() == "Player 4"){
				    				   fourLabs[foursIter].setBounds(currentPlayer.getSpace().getX() + 90, currentPlayer.getSpace().getY() + 40, 30, 30);
				    				   mainFrame.add(fourLabs[foursIter], foursIter);
				    				   foursIter++;
				    			  }
			    				
			    			   }
			    			   else {
			    				  JOptionPane.showMessageDialog(mainFrame,"You do not have sufficient funds.",
			    						  "Bad News!", JOptionPane.INFORMATION_MESSAGE);
			    			   }
			    		   }
		    		   }
	    		   }
	    		   
	    		   //If a player lands on a space that belongs to another player
	    		   else if(currentPlayer.getSpace().getDeed().getOwner() != currentPlayer && currentPlayer.getSpace().getDeed().getOwner() != null) {
	    			   JOptionPane.showMessageDialog(mainFrame, currentPlayer.getName() + ": Must pay " + currentPlayer.getSpace().getDeed().getOwner().getName() + " $" +
	    					   currentPlayer.getSpace().getDeed().getRent() + " for rent.",
	    					  "Cash Update!", JOptionPane.INFORMATION_MESSAGE, cashLabel);
	    			   currentPlayer.subCash(currentPlayer.getSpace().getDeed().getRent());
	    			   currentPlayer.getSpace().getDeed().getOwner().addCash(currentPlayer.getSpace().getDeed().getRent());
	    		   }
	    		   
	    		   //If a player lands on a space that belongs to them AND they own all three properties with the same color, offer to let them build on the property
	    		   else if(currentPlayer.getSpace().getDeed().getOwner() == currentPlayer) {
	    			   int colorChecker = 0;
	    			   ArrayList<Deed> currentPlayerDeeds = currentPlayer.getDeeds();
	    			   for(int i = 0; i < currentPlayerDeeds.size(); i ++) {
	    				   if(currentPlayerDeeds.get(i).getColor() == currentPlayer.getSpace().getDeed().getColor()) {
	    					   colorChecker++;
	    				   }
	    			   }
	    			   //if they have all the deeds, and the current space doesn't have 3 or more houses/hotels prompt them to build.
	    			   if(colorChecker == 3 && currentPlayer.getSpace().getNumHotels() < 3 
	    					   && currentPlayer.getSpace().getNumHouses() < 3
	    					   && !currentPlayer.getSpace().getDeed().hasHotel()) {
	    				   buildChoice = 3;
	    				   if(currentPlayer.getName() != "AI")
	    				   {
			    			   Object[] options = {"House!", "Hotel!", "Neither!"};
			    			   buildChoice = JOptionPane.showOptionDialog(mainFrame, currentPlayer.getName() + ": Would you like build a house or a hotel? " + currentPlayer.getSpace().getName() + " for $" + currentPlayer.getSpace().getDeed().getHouseBuildPrice() + "?\n" + 
			    	    			    " You currently have $" + currentPlayer.getCash() + ".", 
			    					   "Property for sale!",
			    				       JOptionPane.YES_NO_CANCEL_OPTION,
			    				       JOptionPane.QUESTION_MESSAGE,
			    				       houseIcon,
			    				       options,
			    				       options[2]);
	    				   }
	    				   else
	    				   {
	    					   if((currentPlayer.getCash() - currentPlayer.getSpace().getDeed().getHotelBuildPrice()) > 350)
	    					   {
	    						   buildChoice = 1;
	    					   }
	    					   else if((currentPlayer.getCash() - currentPlayer.getSpace().getDeed().getHouseBuildPrice()) > 250)
	    					   {
	    						   buildChoice = 0;
	    					   }
	    				   }
		    			   //if they choose house or hotel, subtract the cost of building for the corresponding choice and deed. Place a building on the board
		    			   if(buildChoice == 0 && !currentPlayer.getSpace().getDeed().hasHouse()) {
		    				   currentPlayer.subCash(currentPlayer.getSpace().getDeed().getHouseBuildPrice());
		    				   currentPlayer.getSpace().getDeed().incrementTierHouse();
		    				   events.setText(currentPlayer.getName() + " has purchased house on " + currentPlayer.getSpaceName());
		    				   houses[houseIter].setBounds(currentPlayer.getSpace().getX() + 90, currentPlayer.getSpace().getY(), 30, 30);
		    				   mainFrame.add(houses[houseIter], houseIter);
		    				   houseIter++;
		    			   }
		    			   else if(buildChoice == 1 && !currentPlayer.getSpace().getDeed().hasHotel()) {
		    				   currentPlayer.subCash(currentPlayer.getSpace().getDeed().getHotelBuildPrice());
		    				   currentPlayer.getSpace().getDeed().incrementTierHotel();
		    				   events.setText(currentPlayer.getName() + " has purchased hotel on " + currentPlayer.getSpaceName());
		    				   houses[houseIter].setBounds(currentPlayer.getSpace().getX() + 90, currentPlayer.getSpace().getY(), 30, 30);
		    				   mainFrame.add(houses[houseIter], houseIter);
		    				   houseIter++;
		    			   }
	    			   }
	    			   else {
	    				   events.setText("<html>" + currentPlayer.getName() + ": You must own all deeds of this" + "<br>" +  "color to build. You have " + colorChecker + "/3 of the required properties." + "</html>");
	    			   }
	    		   }
	    		   if(randomNum == randomNum2)
	    		   {
	    			   events.setText(currentPlayer.getName() + " rolled Doubles!");
	    			   double_count++;
	    			   if(double_count == 3)
	    			   {
	    				   JOptionPane.showMessageDialog(mainFrame,"You have been sent to jail, for rolling triple doubles.",
		    						  "Arrested!", JOptionPane.WARNING_MESSAGE);
	    				   events.setText(currentPlayer.getName() + " has been sent to jail.");
	    				   currentPlayer.goToJail();
	    				   for(Space space : spot)
	    				   {
	    					   if(space.getName() == "Jail")
	    					   {
	    						   currentPlayer.setSpace(space);
	    			    		   currentPlayer.getToken().setVisible(false);
	    			    		   currentPlayer.getToken().setBounds(currentPlayer.getSpace().getX(), currentPlayer.getSpace().getY(), 50, 50);
	    			    		   currentPlayer.getToken().setVisible(true);
	    					   }
	    				   }
	    				   
			    		   iterator++;
			    		   double_count = 0;
			    		   if(iterator == players.length) {
			    			   iterator = 0;
			    		   }

	    			   }
	    			   if(currentPlayer.inJail())
	    			   {
	    				   iterator++;
			    		   double_count = 0;
			    		   if(iterator == players.length) {
			    			   iterator = 0;
			    		   }
	    			   }
	    		   }
	    		   else
	    		   {
		    		   iterator++;
		    		   double_count = 0;
		    		   if(iterator == players.length) {
		    			   iterator = 0;
		    		   }
	    		   }
    		  }
    			switch(players.length)
    			{
    			case 2:
    				playerCash.setText("<html>" + "Current Cash:" + "<br>"+ "<br>" + players[0].getName() + ":" + " $" + players[0].getCash() + 
    						"<br>" + players[1].getName() + ":" + " $" + players[1].getCash()  + "</html>");
    				break;
    			case 3:
    				playerCash.setText("<html>" + "Current Cash:" + "<br>"+ "<br>" + players[0].getName() + ": $" + players[0].getCash() + "<br>"
    						+ players[1].getName() + ": $" + players[1].getCash() + "<br>"
    						+ players[2].getName() + ": $" + players[2].getCash() + "</html>");
    				break;
    			case 4:
    				playerCash.setText("<html>" + "Current Cash:" + "<br>" + "<br>" + players[0].getName() + ": $" + players[0].getCash() + "<br>"
    					+ players[1].getName() + ": $" + players[1].getCash() + "<br>"
    					+ players[2].getName() + ": $" + players[2].getCash() + "<br>"
    					+ players[3].getName() + ": $" + players[3].getCash() + "</html>");
    				break;
    			default:
    			}
	    }
		    
		    
	};
	//create a mouse listener for the take turn button
	takeTurn.addMouseListener(mouseListener);
	mainFrame.setVisible(true);
	}
	
	
	public void endGame() {
		//disable the take turn button, stop the game timer, and print the results.
		 takeTurn.setEnabled(false);
		 takeTurn.removeMouseListener(mouseListener);
		 timer.stop();  
		 printResults();   
		//Custom button text
		 Object[] options = {"Yes!",
		                     "No, thanks!"};
		 int choice = JOptionPane.showOptionDialog(mainFrame,
		     "Would you like to play again?",
		     "Continue Playing?",
		     JOptionPane.YES_NO_CANCEL_OPTION,
		     JOptionPane.QUESTION_MESSAGE,
		     null,
		     options,
		     options[1]);
		 if(choice == 0) {
			 mainFrame.dispose();
		 }
		 else {
			 System.exit(0);
		 }
	}
	
	public void cashUpdate(Player currentPlayer) {
		//display a message dialog to the user to show them how much money they have.
		JOptionPane.showMessageDialog(mainFrame,
			    currentPlayer.getName() + ": You now have $" + currentPlayer.getCash(), "Cash Update!",
			    JOptionPane.INFORMATION_MESSAGE, cashLabel);
	}
}
