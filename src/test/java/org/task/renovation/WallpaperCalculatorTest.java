package org.task.renovation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.InvalidPathException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * The type Wallpaper calculator test.
 */
public class WallpaperCalculatorTest {

	private final PrintStream standardOut = System.out;

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	/**
	 * Sets up.
	 */
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		System.setOut(standardOut);
	}

	/**
	 * Test read sample data from incorrect place.
	 */
	@Test
	public void testReadSampleDataFromIncorrectPlace() {
		assertThrows(InvalidPathException.class, () -> new WallpaperCalculator("#@#@%!_))(_@(_@#@WD:S"));
		WallpaperCalculator wallpaperCalculator = new WallpaperCalculator("incorrect");
		assertFalse(wallpaperCalculator.readSampleData());
		assertTrue(outputStreamCaptor.toString().trim().contains("Error during reading/parsing the sample file: "));
		assertEquals(-1, wallpaperCalculator.getTotalSquareFeet());
	}

	/**
	 * Test parse incorrect sample data.
	 */
	@Test
	public void testParseIncorrectSampleData() {
		WallpaperCalculator wallpaperCalculator = new WallpaperCalculator("src/test/resources/incorrect-sample-input.txt");
		assertTrue(wallpaperCalculator.readSampleData());
		wallpaperCalculator.parseSampleData();
		assertEquals(75, wallpaperCalculator.getTotalSquareFeet());
	}

	/**
	 * Test calculate correct sample data.
	 */
	@Test
	public void testCalculateCorrectSampleData() {
		WallpaperCalculator wallpaperCalculator = new WallpaperCalculator("src/test/resources/correct-sample-input.txt");
		assertNull(wallpaperCalculator.getCubicRoomsInDescOrder());
		assertNull(wallpaperCalculator.getMoreThanOnceRoom());
		assertTrue(wallpaperCalculator.readSampleData());
		wallpaperCalculator.parseSampleData();
		assertEquals(10389, wallpaperCalculator.getTotalSquareFeet());

		LinkedHashMap<Integer, String> cubicRooms = wallpaperCalculator.getCubicRoomsInDescOrder();
		assertEquals(2, cubicRooms.size());
		assertEquals("5x5x5", cubicRooms.get(175));

		Map<String, Integer> moreThanOnceRooms = wallpaperCalculator.getMoreThanOnceRoom();
		assertEquals(2, moreThanOnceRooms.size());
		assertEquals(2, (int) moreThanOnceRooms.get("13x5x19"));
	}

}