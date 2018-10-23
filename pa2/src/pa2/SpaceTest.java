package pa2;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class SpaceTest {
	
Space spaceTest;
Color blue;

	@Test													//Test for setting and getting space name
	public void testName() {
		spaceTest = new Space(3, 7);
		spaceTest.setName("testSpace");
		assertSame("testSpace", spaceTest.getName());
	}

	@Test													//Test for setting and getting space color
	public void testColor() {
		spaceTest = new Space(3, 7);
		spaceTest.setColor(blue);
		assertSame(blue, spaceTest.getColor());
	}
	
	@Test													//Test for getting space x and y locations
	public void testXY() {
		spaceTest = new Space(3, 7);
		assertEquals(3, spaceTest.getX());
		assertEquals(7, spaceTest.getY());
	}
	
	@Test													//Test for creating a deed in the space class
	public void testDeedCreation() {
		spaceTest = new Space(3, 7);
		spaceTest.setDeed(100, 50, 25, 70, 150, blue);
		assertNotNull(spaceTest.getDeed());
	}
	
	@Test													//Test for testing the increment and getter methods 
	public void testHousesHotels() {							//for number of buildings on a property
		spaceTest = new Space(3, 7);
		spaceTest.addHouse();
		spaceTest.addHotel();
		spaceTest.addHotel();
		assertEquals(1, spaceTest.getNumHouses());
		assertEquals(2, spaceTest.getNumHotels());
	}
}
