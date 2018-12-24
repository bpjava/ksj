package CH9;

import java.util.*;

public class RandomEx1 {

	public static void main(String[] args) {
		Random rand = new Random(1);
		Random rand2 = new Random(1);
		
		System.out.println("= rnad =");
		for(int i=0; i<5 ;i++)
			System.out.println(i +":" + rand.nextInt());
		
		System.out.println();
		System.out.println("= rnad2 =");
		for(int i=0; i<5 ;i++)
			System.out.println(i +":" + rand.nextInt());
	

	}

}
