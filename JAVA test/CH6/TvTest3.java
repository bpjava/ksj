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
        System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel값은 " + t2.channel + "입니다.");
        t2 = t1;
        t1.channel = 7;
        System.out.println("t1의 channel값을 7로 변경하였습니다.");
        
        System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel값은 " + t2.channel + "입니다.");
        
	}

}
