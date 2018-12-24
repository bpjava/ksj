package CH6;


public class ReferenceRaramEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data d = new Data();
		d.x = 10;
		System.out.println("main() : x = " + d.x );
		
		change(d);
		System.out.println("After change(d)");
		System.out.println("main() : x = " + d.x );
	}

	static void change (Data d) {
		d.x = 1000;
		System.out.println("change() : x = " + d.x );
		
	}
}
