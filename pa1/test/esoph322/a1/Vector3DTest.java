package esoph322.a1;

import static org.junit.Assert.*;

import org.junit.Test;

import esoph322.a1.Vector3D;

public class Vector3DTest {

	
	//Tests the method scale in Vector3D.java
	@Test
	public void scale() {
		Vector3D vector = new Vector3D(2, 4, 6);
		Vector3D expectedVector = new Vector3D(4, 8, 12);
		Vector3D actualVector = vector.scale(2);
		String expectedResult = expectedVector.toString();
		String actualResult = actualVector.toString();
		assertEquals(expectedResult, actualResult);
	}
	
	//Tests the method add() in Vector3D.java
	@Test
	public void add() {
		Vector3D vector = new Vector3D(2, 4, 6);
		Vector3D vector2 = new Vector3D(1, 2, 3);
		Vector3D expectedVector = new Vector3D(3, 6, 9);
		Vector3D actualVector = vector.add(vector2);
		String expectedResult = expectedVector.toString();
		String actualResult = actualVector.toString();
		assertEquals(expectedResult, actualResult);
	}
	
	//Tests the method subtract() in Vector3D.java
	@Test
	public void subtract() {
		Vector3D vector = new Vector3D(3, 2 ,1);
		Vector3D vector2 = new Vector3D(1, 1, -1);
		Vector3D expectedVector = new Vector3D(2, 1, 2);
		Vector3D resultVector = vector.subtract(vector2);
		assertEquals(expectedVector.toString(), resultVector.toString());
	}
	
	//Tests the method negate() in Vector3D.java
	@Test
	public void negate() {
		Vector3D vector = new Vector3D(2, 4, 6);
		Vector3D expectedVector = new Vector3D(-2, -4, -6);
		Vector3D actualVector = vector.negate();
		String expectedResult = expectedVector.toString();
		String actualResult = actualVector.toString();
		assertEquals(expectedResult, actualResult);
	}
	
	//Tests the method dot() in Vector3D.java
	@Test
	public void dot() {
		Vector3D vector1 = new Vector3D(1, 2, 3);
		Vector3D vector2 = new Vector3D(3, 2, -2);
		double expectedValue = 1;
		double result = vector1.dot(vector2);
		assertTrue(expectedValue == result);
	}
	
	//Tests the method magnitude() in Vector3D.java
	@Test
	public void magnitutde() {
		Vector3D vector = new Vector3D(2, 2, 1);
		double expectedValue = 3;
		double actualResult = vector.magnitude();
		assertTrue(expectedValue == actualResult);
	}
	
	//Tests the method equals() in Vector3D.java
	@Test
	public void equals() {
		Vector3D vector1 = new Vector3D((1/3), (1/Math.sqrt(2.2)), -2);
		Vector3D vector2 = new Vector3D((2/6), (Math.sqrt(2.2)/2.2), (-2/1));
		assertTrue(vector1.equals(vector2));
		
	}
	
	

}
