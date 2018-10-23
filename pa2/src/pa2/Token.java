package pa2;

/**
 * Token class that represents the token a player has chosen
 * 
 * @authors Matt Sagen, Hugh Jackovich, James Mitchell, Cory Petersen
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Token {
	
	JLabel swordPic, bowPic, staffPic, axePic, pencilPic, calculatorPic, laptopPic, gradCapPic;
	JLabel selectedToken;
	public Token() {
	//create four tokens for players to use i.e sword, bow, staff, axe
		BufferedImage sword = null;
		BufferedImage bow = null;
		BufferedImage staff = null;
		BufferedImage axe = null;
		BufferedImage pencil = null;
		BufferedImage calculator = null;
		BufferedImage laptop = null;
		BufferedImage gradCap = null;
		
		
		try {
			sword = ImageIO.read(new File("src/gamePieces/sword.png"));
			bow = ImageIO.read(new File("src/gamePieces/bow.jpg"));
			staff = ImageIO.read(new File("src/gamePieces/staff.png"));
			axe = ImageIO.read(new File("src/gamePieces/axe.png"));
			pencil = ImageIO.read(new File("src/gamePieces/pencil.png"));
			calculator = ImageIO.read(new File("src/gamePieces/calculator.png"));
			laptop = ImageIO.read(new File("src/gamePieces/laptop.png"));
			gradCap = ImageIO.read(new File("src/gamePieces/gradCap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create gamePieces
		swordPic = new JLabel(new ImageIcon(sword));
		bowPic = new JLabel(new ImageIcon(bow));
		staffPic = new JLabel(new ImageIcon(staff));
		axePic = new JLabel(new ImageIcon(axe));
		pencilPic = new JLabel(new ImageIcon(pencil));
		calculatorPic = new JLabel(new ImageIcon(calculator));
		laptopPic = new JLabel(new ImageIcon(laptop));
		gradCapPic = new JLabel(new ImageIcon(gradCap));
		
	}
	
	public JLabel getToken() {
		return selectedToken;
	}
	public void setToken(int i, String theme) {
		if(theme == "Middle Earth") {
			if(i == 0) {
				selectedToken = swordPic;
			}
			else if (i == 1) {
				selectedToken = bowPic;
			}
			else if(i == 2) {
				selectedToken =staffPic;
			}
			else {
				selectedToken = axePic;
			}
		}
		else if(theme == "MSU"){
			if(i == 0) {
				selectedToken = pencilPic;
			}
			else if (i == 1) {
				selectedToken = calculatorPic;
			}
			else if(i == 2) {
				selectedToken = laptopPic;
			}
			else {
				selectedToken = gradCapPic;
			}
		}
	}
	
	
}
