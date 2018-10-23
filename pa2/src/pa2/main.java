package pa2;
/**
 *  main is our driver class that will server as the main running portion of the program
 *  and connect the classes into a single game
 * 
 * @authors Matt Sagen, Hugh Jackovich, James Mitchell, Cory Petersen
 */
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class main{

	public static void main(String[] args){
		//Create GUI
		
		//make input how many new players to create, ask for name (later on item name)
		JFrame myFrame = new JFrame();
		myFrame.setSize(660, 680);
		myFrame.setLayout(null);
		int[] times = {1,5,15,25,35};
		int[] players = {2,3,4};
		Boolean[] ais = {true, false};
		String[] themes = {"Middle Earth", "MSU"};
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
		
		//create buttons and labels
		JButton start = new JButton("Start");
		JLabel title = new JLabel("Monopoly: ");
		JLabel timeLab = new JLabel("Select Time Limit: (minutes)");
		JComboBox<Integer> time = new JComboBox<Integer>();
		JComboBox<Integer> selectPlayers = new JComboBox<Integer>();
		JLabel playerLab = new JLabel("Select Number of Players: ");
		JLabel AILab = new JLabel("Add an AI player?");
		JComboBox<Boolean> AIs = new JComboBox<Boolean>();
		JLabel selectTheme = new JLabel("Select Theme: ");
		JComboBox<String> themeBox = new JComboBox<String>();
		
		title.setHorizontalAlignment(JLabel.CENTER);
		
		//set locations of buttons and labels
		title.setBounds(200, 50, 250, 85);
		timeLab.setBounds(240,110,200,100);
		time.setBounds(290,180,50,40);
		playerLab.setBounds(245, 185, 200, 100);
		selectPlayers.setBounds(290,245, 50, 40);
		AILab.setBounds(270, 250, 200, 100);
		AIs.setBounds(290, 315, 50, 40);
		selectTheme.setBounds(280,315,100,100);
		themeBox.setBounds(290, 375, 50, 40 );
		start.setBounds(250, 450, 150, 50);
		//set size of buttons and labels
		
		title.setBorder(border);
		
		
		//add time options
		for(int i = 0; i < times.length; i ++) {
			time.addItem(times[i]);
		}
		//add player options
		for(int i = 0; i < players.length; i++) {
			selectPlayers.addItem(players[i]);
		}
		//add theme options
		for(int i = 0; i < themes.length; i++) {
			themeBox.addItem(themes[i]);
		}
		//add ai options
		for(int i = 0; i < ais.length; i++) {
			AIs.addItem(ais[i]);
		}
		
		//add buttons and labels to the JFrame
		myFrame.add(title);
		myFrame.add(start);
		myFrame.add(timeLab);
		myFrame.add(time);
		myFrame.add(playerLab);
		myFrame.add(selectPlayers);
		myFrame.add(AILab);
		myFrame.add(AIs);
		myFrame.add(selectTheme);
		myFrame.add(themeBox);
		myFrame.setVisible(true);
		
		//create a mouse listener for the start button
		start.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    		int selectedTime = times[time.getSelectedIndex()];
		    		int numPlayers = players[selectPlayers.getSelectedIndex()];
		    		
		    		//create an array of players based on user selection
		    	    Player[] players = new Player[numPlayers];
		    		if(ais[AIs.getSelectedIndex()])
		    		{
		    			numPlayers -= 1;
		    			players[players.length-1] = new Player("AI", 250);
		    		}
		    	    for(int i = 0; i < numPlayers; i++) {
		    	    		if(i == 0) {
		    	    			players[i] = new Player("Player 1", 250);
		    	    		}
		    	    		else if(i == 1) {
		    	    			players[i] = new Player("Player 2", 250);
		    	    		}
		    	    		else if(i == 2) {
		    	    			players[i] = new Player("Player 3", 250);
		    	    		}
		    	    		else if(i == 3) {
		    	    			players[i] = new Player("Player 4", 250);
		    	    		}
		    	    }
				Board game = new Board(players);
				game.setTheme(themes[themeBox.getSelectedIndex()]);
				myFrame.getDefaultCloseOperation();
				game.setTime(selectedTime);
				game.setNumPlayers(numPlayers);
		    }
		});
		
		
	}

}
