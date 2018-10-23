package pa2;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.junit.Test;

public class PlayerTest {					//Junit Test class for the player class
Player playerTest;
BufferedImage calculator;					//creates test versions of a player and jLabel for use later
JLabel calculatorPic;
private static final int board_spaces = 40;
private Space[] testSpace = new Space[board_spaces];

	@Test
	public void adjustCash() {										//test adjustCash method
		Player player1 = new Player("Jimmy", 1000);
		Player expectedPlayer1 = new Player("Jimmy", 2000);
		//Player actualPlayer1 = player1.adjustCash(1000);
		int expectedResult = expectedPlayer1.getCash();
		//int actualResult = actualPlayer1.getCash();
		//assertEquals(expectedResult, actualResult);
	}
	
	@Test															//Test the cash get method
	public void getCashTest() {
		playerTest = new Player("cashTest", 500);
		assertEquals(500, playerTest.getCash());
	}
	
	@Test															//Test the cash subtraction method
	public void subCashTest() {
		playerTest = new Player("subTest", 500);
		playerTest.subCash(100);
		assertEquals(400, playerTest.getCash());
	}
	
	@Test															//Test the cash addition method
	public void addCashTest() {
		playerTest = new Player("addTest", 500);
		playerTest.addCash(200);
		assertEquals(700, playerTest.getCash());
	}
	
	@Test															//Test the player name setter and getter
	public void getNameTest() {
		playerTest = new Player("NameTest", 500);
		assertEquals("NameTest", playerTest.getName());
	}
	
	
	@Test															//Test the player space getter
	public void spaceTest() {
		playerTest = new Player("NameTest", 500);
		playerTest.setSpace(testSpace[10]);
		assertSame(testSpace[10], playerTest.getSpace());
	}
	
	@Test															//Test the player passing go conditions
	public void testPassGo() {
		playerTest = new Player("Test", 500);
		playerTest.setPassGo(true);
		assertTrue(playerTest.hasPassedGo());
	}
	
	@Test															//Test the player going to jail conditions
	public void testJailStatus() {
		playerTest = new Player("Test", 500);
		playerTest.goToJail();
		assertTrue(playerTest.inJail());
	}
	
	@Test															//Test the method for finding the amount of time the 
	public void testJailCount() {                                    //player is in jail
		playerTest = new Player("Test", 500);
		playerTest.jailCount();
		assertEquals(1, playerTest.getJailCount());
	}
	
	

}