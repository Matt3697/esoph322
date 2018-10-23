package pa2;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	static Player[] players = new Player[2];{ //creates test versions of players, a player array, and a board
	players[0] = new Player("test1", 250);
	players[1] = new Player("test2", 300);}
	static Board boardTest = new Board(players);
	
	@Test
	public void rollTest() {								//test the dice roll in our game
		boolean rollTest = false;
		if(boardTest.roll()>2 && boardTest.roll()<13) {
			rollTest = true;
		}
		assertTrue(rollTest);
	}
	
	@Test
	public void remainingTimeTest() {					//test method that sets and gets remaining time in the 
		boardTest.setTime(50);							//game
		assertEquals(50, boardTest.getRemainingTime());
	}

	@Test
	public void ThemeTest() {							//test the theme getter and setter functions
		boardTest.setTheme("test Theme");
		assertSame("test Theme", boardTest.getTheme());
	}
	
}
