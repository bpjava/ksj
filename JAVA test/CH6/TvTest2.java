package CH6;

class ttv {
	//Tv�� �Ӽ�(�������)
	String color;
	boolean power;
	int channel;
	
	//Tv�� ���(�޼���)
	void power() {
		power = ! power;
	}
	void channelUp() {
		++channel;
	}
	void channelDown() {
		--channel;
	}
}


public class TvTest2 {

	public static void main(String[] args) {
	
		ttv t1 = new ttv();
		ttv t2 = new ttv();
		System.out.println("t1�� channel���� " + t1.channel + "�Դϴ�.");
		System.out.println("t2�� channel���� " + t2.channel + "�Դϴ�.");
		
		t1.channel = 7;
		System.out.println("t1�� channel���� 7�� �����Ͽ����ϴ�.");
		
		System.out.println("t1�� channel���� " + t1.channel + "�Դϴ�.");
		System.out.println("t2�� channel���� " + t2.channel + "�Դϴ�.");

	}

}
