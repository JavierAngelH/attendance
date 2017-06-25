/**
 * Utilities.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utilities -
 *
 */
public final class Utilities {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static String imagesPath = "C:\\Users\\Virgilio Melo\\Desktop\\SD Card\\";

	public static boolean isWeekend(int day, int month, int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, day);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.YEAR, year);

		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if ((dayOfWeek == 1) || (dayOfWeek == 7)) {
			return true;
		}
		return false;
	}
	
	
}
