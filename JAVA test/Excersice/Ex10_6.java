package Excersice;

import java.time.*;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Ex10_6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate mybirthday = LocalDate.of(1995, 4, 15);
		LocalDate today = LocalDate.of(2018, 12, 6);
		Period pe = Period.between(mybirthday,today);

		System.out.println("mybirthday =" + mybirthday);
		System.out.println("today=" + today);

		System.out.println(pe.get(ChronoUnit.DAYS));
		
	}

}
