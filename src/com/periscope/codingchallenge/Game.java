package com.periscope.codingchallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Game {

	static Coordinates area = new Coordinates();
	static Coordinates hoverOverLocation = new Coordinates();

	public static void main(String[] args) {

		Game game = new Game();

		//will store positions of patches of dirt and boolean to indicate that it is dirty
		Map<Coordinates, Boolean> checkDirtkMap = new HashMap<Coordinates, Boolean>();
		
		//store directions string 
		String directionsDetails = null;
		
		//count the number of dirt patches cleaned by robot		
		int cleanCount = 0;

		Coordinates dirtyCoordinate = new Coordinates();
		char direction;

		BufferedReader br = null;

		try {

			String sCurrentLine;
			File file = new File("input.txt");
			FileReader fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);

			int fileLineCounter = 0;
			while ((sCurrentLine = br.readLine()) != null) {

				String[] tokens = sCurrentLine.split(" ");
				//1st line will hold room dimensions
				if (fileLineCounter == 0) {
					area.setX(Integer.parseInt(tokens[0]));
					area.setY(Integer.parseInt(tokens[1]));
				//2nd line would hold hover over position
				} else if (fileLineCounter == 1) {
					hoverOverLocation.setX(Integer.parseInt(tokens[0]));
					hoverOverLocation.setY(Integer.parseInt(tokens[1]));
				} else {
					if (tokens.length == 1) {
						directionsDetails = tokens[0];
					} else {
						dirtyCoordinate.setX(Integer.parseInt(tokens[0]));
						dirtyCoordinate.setY(Integer.parseInt(tokens[1]));
						checkDirtkMap.put(dirtyCoordinate, true);
					}

				}
				fileLineCounter++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		for (int i = 0; i < directionsDetails.length(); i++) {
			direction = directionsDetails.charAt(i);
			game.navigateRobot(direction);

			if (checkDirtkMap.containsKey(hoverOverLocation) && checkDirtkMap.get(hoverOverLocation)) {
				// if robot visits dirty location then boolean will be set to false indicating it is not dirty
				checkDirtkMap.put(hoverOverLocation, false);
				cleanCount++;
			}

		}

		System.out.println("Hover Over Position after processing all commands :" + hoverOverLocation);
		System.out.println("No of dirt positions cleaned: " + cleanCount);

	}

	public void navigateRobot(char direction) {
		switch (direction) {
		case 'N':
			goNorth();
			break;

		case 'S':
			goSouth();
			break;

		case 'E':
			goEast();
			break;

		case 'W':
			goWest();
			break;

		default:
			System.out.println("not a valid direction");
			break;

		}
	}

	public void goWest() {
		if (hoverOverLocation.getX() > 0) {
			hoverOverLocation.setX(hoverOverLocation.getX() - 1);
		}
	}

	public void goEast() {
		if (hoverOverLocation.getX() < area.getX()) {
			hoverOverLocation.setX(hoverOverLocation.getX() + 1);
		}

	}

	public void goSouth() {
		if (hoverOverLocation.getY() > 0) {
			hoverOverLocation.setY(hoverOverLocation.getY() - 1);
		}
	}

	public void goNorth() {
		if (hoverOverLocation.getY() < area.getY()) {
			hoverOverLocation.setY(hoverOverLocation.getY() + 1);
		}
	}

}
