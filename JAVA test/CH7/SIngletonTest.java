package CH7;

final class Singleton{
	private static Singleton s = new Singleton();
	private Singleton() {
		//...
	}
	
	public static Singleton getInstance() {
		
		if(s==null) {
			s= new Singleton();
		}
		return s;
	}
}
public class SIngletonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Singleton s =new Singleton ();
		Singleton s = Singleton.getInstance();
	}

}
