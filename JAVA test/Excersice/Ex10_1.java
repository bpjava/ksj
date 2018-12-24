package Excersice;

import java.util.*;
import java.text.*;

public class Ex10_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar date = Calendar.getInstance();
		date.set(2010, 0, 1);

		for (int i = 0; i < 12; i++) {
			int weekday = date.get(Calendar.DAY_OF_WEEK);
 
			int secondSunday = (weekday ==1)? 8 : 16-weekday;
			
			date.set(Calendar.DAY_OF_MONTH, secondSunday);
	
			Date.d = date.getTime();
			System.out.println(new SimpleDateFormat("yyyy-"));
		}

		
		// System.out.println(toString(date));
		System.out.println(sdf1.format(date));
		System.out.println(sdf3.format(date));

	}

	// public static String toString(Calendar date) {
	// return date.get(Calendar.YEAR)
	// }
}
