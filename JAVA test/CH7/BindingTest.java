package CH7;

public class BindingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Parentt p = new Childd () ; 
		Childd c = new Childd () ; 
		System.out .println ("p.x = "  + p.x) ;
		p.method();
		
		System.out .println ("c.x = " + c.x) ; 
		c.method();

		}
}

class Parentt { 
	int x = 100; 
	void method() {

	System.out.println ("Parent Method");
}
}
class Childd extends Parentt { 
	int x = 200; 
	void method() { 
	System.out.println ("Child Method") ;
}
}