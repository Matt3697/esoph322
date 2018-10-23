package pa2;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class DeedTest {
	private Color blue;
	Deed testDeed = new Deed(100, 50, 10, 70, 150, blue);

	@Test						//test the methods for incrementing house tier and getting the rent
	public void testRent() {
		testDeed.incrementTierHouse();
		assertEquals(20, testDeed.getRent());
	}

	@Test						//test the methods for setting and getting property owner
	public void testOwner() {
		Player playerTest = new Player("Test", 500);
		testDeed.setOwner(playerTest);
		assertSame(playerTest, testDeed.getOwner());
	}
	
	@Test						//test the methods for getting property price
	public void testPrice() {
		assertSame(100, testDeed.getPrice());
	}
	
	@Test						//test the methods for getting and setting property housing cost
	public void testBuildPrice() {
		testDeed.setHouseBuildCost(80);
		testDeed.setHotelBuildCost(170);
		assertEquals(80, testDeed.getHouseBuildPrice());
		assertEquals(170, testDeed.getHotelBuildPrice());
	}
	
	@Test						//test the methods for getting property color
	public void testColor() {
		assertSame(blue, testDeed.getColor());
	}
	
	@Test						//test the methods for getting property house condition
	public void testHasHouse() {
		assertFalse(testDeed.hasHouse());
	}
	
	@Test						//test the methods for getting property hotel condition, and incrementing hotels
	public void testHasHotel() {
		testDeed.incrementTierHotel();
		assertTrue(testDeed.hasHotel());
	}
}
