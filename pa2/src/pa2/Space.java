package pa2;

/**
 * Space Class that will be the object of each individual space on the board
 * 
 * @authors Matt Sagen, Hugh Jackovich, James Mitchell, Cory Petersen
 */

import java.awt.Color;

public class Space{
	
	private int x, y, numHouses, numHotels;
	private String name;
	private Color color;
	private Deed deed;
	private boolean hasDeed;
	
	public Space(int x, int y){
		this.x = x;
		this.y = y;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
    
    public void setDeed(int price, int mortgageVal, int rent, int housebuildCost, int hotelBuildCost, Color color){
    		//create a deed for the space
    		deed = new Deed(price, mortgageVal, rent, housebuildCost, hotelBuildCost, color);
    }
    
    public Deed getDeed() {
    		return deed;
    }
    
    public int getNumHouses() {
    		return numHouses;
    }
    public void addHouse() {
    		numHouses++;
    }
    public int getNumHotels() {
    		return numHotels;
    }
    public void addHotel() {
    		numHotels++;
    }
}
