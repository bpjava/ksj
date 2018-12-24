package Excersice;

public class exersice4_14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1~100 answer . 
		int answer = (int) (Math.random() * 100) + 1 ;
		int input = 0; // 시도횟수세기위한 변수
		int count = 0;
		
		java.util.Scanner s = new java.util.Scanner(System.in); 
		
		do {
			count++;
		System.out.print("1과 100사이의 값을 입력하세요 :"); 
		input = s.nextInt();
		
		if(input==answer) {
			System.out.println("맞췄습니다");
			
			}
		elseif(input>answer){
			System.out.println("더 작은 수를 입력하세요");
			
		}
		else{
			System.out.println("더 큰 수를 입력하세요");
		}
		
		
		}while(true);
		

	}

}
