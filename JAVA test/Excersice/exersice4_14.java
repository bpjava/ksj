package Excersice;

public class exersice4_14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1~100 answer . 
		int answer = (int) (Math.random() * 100) + 1 ;
		int input = 0; // �õ�Ƚ���������� ����
		int count = 0;
		
		java.util.Scanner s = new java.util.Scanner(System.in); 
		
		do {
			count++;
		System.out.print("1�� 100������ ���� �Է��ϼ��� :"); 
		input = s.nextInt();
		
		if(input==answer) {
			System.out.println("������ϴ�");
			
			}
		elseif(input>answer){
			System.out.println("�� ���� ���� �Է��ϼ���");
			
		}
		else{
			System.out.println("�� ū ���� �Է��ϼ���");
		}
		
		
		}while(true);
		

	}

}
