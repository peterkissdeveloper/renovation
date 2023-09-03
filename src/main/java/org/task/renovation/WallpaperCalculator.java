package org.task.renovation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Wallpaper calculator.
 */
public class WallpaperCalculator {

	private final Path path;

	private List<String> rows;

	private Map<Integer, String> cubicRooms;

	private Map<String, Integer> sameRooms;

	private int totalSquareFeet;

	/**
	 * Instantiates a new Wallpaper calculator.
	 *
	 * @param sampleFilePath the sample file path
	 * @throws InvalidPathException the invalid path exception
	 */
	public WallpaperCalculator(String sampleFilePath) throws InvalidPathException {
		path = Paths.get(sampleFilePath);
		totalSquareFeet = -1;
		cubicRooms = null;
		sameRooms = null;
	}

	/**
	 * Read sample data and returns whether it was successful or not.
	 *
	 * @return the success
	 */
	public boolean readSampleData() {
		try {
			rows = Files.readAllLines(path);
		} catch (IOException e) {
			System.out.println("Error during reading/parsing the sample file: " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Parse sample data.
	 */
	public void parseSampleData() {
		totalSquareFeet = 0;
		cubicRooms = new HashMap<>();
		sameRooms = new HashMap<>();
		for (String row : rows) {
			String[] currentRoomSize = row.split("x");
			if (currentRoomSize.length == 3) {
				try {
					int length = Integer.parseInt(currentRoomSize[0]);
					int width = Integer.parseInt(currentRoomSize[1]);
					int height = Integer.parseInt(currentRoomSize[2]);
					int min = length * width;
					int second = width * height;
					if (second < min) {
						int temp = second;
						second = min;
						min = temp;
					}
					int third = length * height;
					if (third < min) {
						int temp = third;
						third = min;
						min = temp;
					}
					int squareFeet = 2 * (min + second + third) + min;
					totalSquareFeet += squareFeet;
					if (min == second && second == third) {
						cubicRooms.put(squareFeet, row);
					}
					if (sameRooms.containsKey(row)) {
						sameRooms.put(row, sameRooms.get(row) + 1);
					} else {
						sameRooms.put(row, 1);
					}
				} catch (NumberFormatException e) {
					System.out.println("Incorrect number: " + row);
				}
			} else {
				System.out.println("Incorrect entry: " + row);
			}
		}
	}

	/**
	 * Gets total square feet. If the return value is -1 then it means that the file is not yet parsed.
	 *
	 * @return the total square feet
	 */
	public int getTotalSquareFeet() {
		return totalSquareFeet;
	}

	/**
	 * Gets cubic rooms in desc order.
	 *
	 * @return the cubic rooms in desc order
	 */
	public LinkedHashMap<Integer, String> getCubicRoomsInDescOrder() {
		if (cubicRooms == null) {
			return null;
		}
		return cubicRooms.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new
				));
	}

	/**
	 * Gets those rooms that are available more than once.
	 *
	 * @return the more than once room
	 */
	public Map<String, Integer> getMoreThanOnceRoom() {
		if (sameRooms == null) {
			return null;
		}
		return sameRooms.entrySet()
				.stream()
				.filter(entry -> entry.getValue() > 1)
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue
				));
	}
}
