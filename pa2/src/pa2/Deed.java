package pa2;

import java.awt.Color;

/**
 * Every space that is a property will have a Deed attached to it, to allow changes to the space
 * 
 * @authors Matt Sagen, Hugh Jackovich, James Mitchell, Cory Petersen
 */

public class Deed{
	
	private Player owner;
	private int price, mortgageVal, rent, houseBuildCost, hotelBuildCost, tier;
	private Color color;
	private boolean house = false;
	private boolean hotel = false;
	
	//creates the deed for the property
	public Deed(int price, int mortgageVal, int rent, int houseBuildCost, int hotelBuildCost, Color color) {
		this.price = price;
		this.mortgageVal = mortgageVal;
		this.rent = rent;
		this.hotelBuildCost = hotelBuildCost;
		this.houseBuildCost = houseBuildCost;
		this.color = color;
		this.tier = 1;
	}
	
	public void incrementTierHouse() {
		house = true;
		tier = 2;
	}
	
	public void incrementTierHotel() {
		tier = 3;
		hotel = true;
		house = true;
	}
	
	//returns rent on the property
	public int getRent(){
		return rent * tier;
	}

	//creates an owner for the deed
	public void setOwner(Player newOwner){
		owner = newOwner;
	}
	
	//returns the owner for the deed
	public Player getOwner(){
		return owner;
	}
	
	//returns the price for the deed
	public int getPrice(){
		return price;
	}
	
	//creates the cost to build a house on the property
	public void setHouseBuildCost(int cost) {
		houseBuildCost = cost;
	}
	
	
	//creates the cost to build a hotel on the property
	public void setHotelBuildCost(int cost) {
		hotelBuildCost = cost;
	}
	
	//returns the cost to build a house on the property
	public int getHouseBuildPrice() {
		return houseBuildCost;
	}
	
	//returns the cost to build a hotel on the property
	public int getHotelBuildPrice() {
		return hotelBuildCost;
	}
	
	//returns the color of the property
	public Color getColor() {
		return color;
	}
	
	
	//returns if the property has a house on it
	public boolean hasHouse()
	{
		return house;
	}
	
	//returns if the property has a hotel on it
	public boolean hasHotel()
	{
		return hotel;
	}
}
