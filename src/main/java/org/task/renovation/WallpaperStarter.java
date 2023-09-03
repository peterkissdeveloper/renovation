package org.task.renovation;

/**
 * The type Wallpaper starter.
 */
public class WallpaperStarter {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		WallpaperCalculator wallpaperCalculator = new WallpaperCalculator("src/main/resources/sample-input.txt");
		if (wallpaperCalculator.readSampleData()) {
			wallpaperCalculator.parseSampleData();
			System.out.println("Number of total square feet of wallpaper the company should order for all rooms: " + wallpaperCalculator.getTotalSquareFeet());
			System.out.println("All rooms that have cubic shape in order by total needed wallpaper descending: " + wallpaperCalculator.getCubicRoomsInDescOrder());
			System.out.println("All rooms which are appearing more than once: " + wallpaperCalculator.getMoreThanOnceRoom());
		}
	}
}