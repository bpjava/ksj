package Excersice;

public class exercise5_6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] coinUnit = {500, 100, 50, 10};
		
		int money = 2680;
		System.out.println("money="+money);
		
		for(int i =0; i<coinUnit.length;i++) {
			
			System.out.println("500��:"+ money / 500);
			 money = money % 500;
			 System.out.println("100��:"+ money / 100);
			 money = money % 100;
			 System.out.println("50��:" + money / 50);
			 money = money % 50;
			 System.out.println("10��:" + money / 10);
			 
			// System.out.println(coinUnit[i]+"��: " money / coinUnit[i]);
			 money = money % coinUnit[i];
			 
			 break;
			
			
		}
	}

}
