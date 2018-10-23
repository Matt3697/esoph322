package pa2;
/**
 * Player class handles all functions of a player in our game
 * 
 * @authors Matt Sagen, Hugh Jackovich, James Mitchell, Cory Petersen
 */

import java.util.ArrayList;

import javax.swing.JLabel;

public class Player
{
	private int money, iter, iterator = 0; 
	private String name;	
	private Space currentSpace;
	private Token playerToken;
	private int sum = 0;
	private int jailTime = 0;
	private boolean pass, sufficientFunds;
	private boolean jail = false;
	private ArrayList<Deed> ownedDeeds;
	
	public Player(String name, int starting_cash){
		pass = false;
		money = starting_cash;
		this.name = name;
		playerToken = new Token();
		ownedDeeds = new ArrayList<Deed>();
	}
	
	public int getCash(){	
		//return the money the player has
		return money;
	}
	
	public void addCash(int amount){ 	
		//increment the amount of money the player has
		money += amount;
		
	}
	public void subCash(int amount) {
		money -= amount;
	}
	
	public void takeTurn(int rand1, int rand2, Space[] spaces) {
		//increment sum by the addition of the two random numbers generated after the mouse click
		sum += (rand1 + rand2);
		//if the sum equals the number of spaces on the board, pass go and reset the sum to 0.
		if(sum == spaces.length) {
			sum = 0;
			setPassGo(true);
		}
		//if the sum is greater than the number of spaces on the board, pass go and set the sum to the difference of the number of spaces
		//on the board and the sum.
		else if(sum > spaces.length) {
			sum -= spaces.length;
			setPassGo(true);
		}
		//set the players current space equal to the sum 
		currentSpace = spaces[sum];
	}
	public String getName() {
		//get the players name
		return name;
	}
	public void setToken(int i, String theme) {
		//set the players token
		playerToken.setToken(i, theme);
	}
	
	public JLabel getToken() {
		//get the players game piece
		return playerToken.getToken();
	}
	public Space getSpace() {
		//get the players current space
		return currentSpace;
	}
	public String getSpaceName() {
		return currentSpace.getName();
	}
	public void setSpace(Space space) {
		//set the players current space
		currentSpace = space;
	}
	public boolean setPassGo(boolean hasPassed) {
		//set the boolean value of pass go.
		pass = hasPassed;
		return pass;
	}
	public boolean hasPassedGo() {
		//returns whether the player has passed go or not.
		return pass;
	}
	public boolean hasSufficientFunds() {
		//If the player has enough money to purchase a property return true, else return false
		if((money - currentSpace.getDeed().getPrice()) >= 0) {
			sufficientFunds = true;
		}
		else {
			sufficientFunds = false;
		}
		return sufficientFunds;
	}
	
	public void goToJail()
	{
		jail = true;
	}
	
	public void setFree()
	{
		jail = false;
	}
	
	public boolean inJail()
	{
		return jail;
	}
	
	public void jailCount()
	{
		jailTime++;
	}
	
	public int getJailCount()
	{
		return jailTime;
	}
	public void addDeed(Deed deed) {
		ownedDeeds.add(deed);
	}
	public ArrayList<Deed> getDeeds() {
		return ownedDeeds;
	}
}
