package CH7;

public class BindingTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Parenttt p = new Childdd () ;
		Childdd c = new Childdd () ; 
		
		System.out.println("p.x="+p.x); 
		p.method();
	    System.out.println("c.x =" + c.x);
	    c.method();
	}

}
class Parenttt{
	
	int x=100;
	
	void method() {
		System.out.println("Parent Method");
	}
}

class Childdd extends Parenttt{}
