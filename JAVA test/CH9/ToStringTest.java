package CH9;

public class ToStringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = new String("KOREA");
		java.util.Date today = new java.util.Date();
		
		System.out.println(str);
		System.out.println(str.toString());//오버라이딩
		System.out.println(today);
		System.out.println(today.toString());//오버라이딩
	}

}
