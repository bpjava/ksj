package CH6;

class Tvv {
	String color;
	boolean power;
	int channel;

	void power() {
		power = !power;
	}
    void channelUp() {
    	++channel;
    }
    void channelDown() {
    	--channel;
    }
}

public class TvTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Tvv t1 = new Tvv();
        Tvv t2 = new Tvv();
        System.out.println("t1�� channel���� " + t1.channel + "�Դϴ�.");
        System.out.println("t2�� channel���� " + t2.channel + "�Դϴ�.");
        t2 = t1;
        t1.channel = 7;
        System.out.println("t1�� channel���� 7�� �����Ͽ����ϴ�.");
        
        System.out.println("t1�� channel���� " + t1.channel + "�Դϴ�.");
        System.out.println("t2�� channel���� " + t2.channel + "�Դϴ�.");
        
	}

}
