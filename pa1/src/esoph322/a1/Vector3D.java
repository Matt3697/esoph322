package esoph322.a1;

public final class Vector3D {
	
	private double x;
    private double y;
	private double z;
	
	
	//Constructor that takes x, y, and z coordinates.
	public Vector3D(double x, double y, double z) { 
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//Multiplies x, y, and z by a common factor f.
	public Vector3D scale(int f) {
		x *= f;
		y *= f;
		z *= f;
		Vector3D newVector = new Vector3D(x, y, z);
		return newVector;
	}
	
	/*
	 * Takes one Vector3D as an argument, adds the corresponding coordinates to its 
	 * own and produces a new Vector3D.
	 */
	public Vector3D add(Vector3D v) {
		x = this.x + v.x;
		y = this.y + v.y;
		z = this.z + v.z;
		Vector3D newVector = new Vector3D(x, y, z);
		return newVector;
	}
	
	/*
	 * Similar to Add, but subtract argument v's coordinates from the corresponding
	 * coordinates in "this" producing a new Vector3D object.
	 */
	public Vector3D subtract(Vector3D v) {
		x = this.x - v.x;
		y = this.y - v.y;
		z = this.z - v.z;
		Vector3D newVector = new Vector3D(x, y, z);
		return newVector;
	}

	//Scale by -1
	public Vector3D negate() {
		x = this.x * (-1);
		y = this.y * (-1);
		z = this.z * (-1);
		Vector3D newVector = new Vector3D(x, y, z);
		return newVector;
	}
	
	//Produce the dot product of "this" Vector3D and argument Vector3D v.
	public double dot(Vector3D v) {
		x = this.x * v.x;
		y = this.y * v.y;
		z = this.z * v.z;
		double result = x + y + z;
		return result;
	}
	
	//returns the magnitude of a Vector3D (sqrt(x*x + y*y + z*z)).
	public double magnitude() {
		double result = Math.sqrt(x*x + y*y + z*z);
		return result;
	}
	
	//method for reasonable output.
	public String toString() {
	   String myString = String.format("(%s, %s, %s)", x, y, z);
	   return myString;
	}
	
	//float and double arithmetic is not exact, so this must allow for a tolerance.
	public boolean equals(Vector3D v) {
		boolean isEqual = false;
		if((Math.abs(this.x - v.x) < .2) && (Math.abs(this.y - v.y) < .2) && (Math.abs(this.z - v.z) < .2)){
			isEqual = true;
		}
		return isEqual;
	}
}
