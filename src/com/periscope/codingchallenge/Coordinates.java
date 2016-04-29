package com.periscope.codingchallenge;

public class Coordinates {
	private int X, Y;

	public void setX(int X) {
		this.X = X;
	}

	public void setY(int Y) {
		this.Y = Y;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
		return result;
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		Coordinates cordinates = (Coordinates) obj;
		return ((cordinates.X == X && cordinates.Y == Y));
	}

	@Override
	public String toString() {
		return "Coordinates [X=" + X + ", Y=" + Y + "]";
	}

}
