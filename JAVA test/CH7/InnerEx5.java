package CH7;

class Outerr {
	int value = 10;

	class Inner {
		int value = 20;

		void methodl() {
int value=30;
System.out.println("           value:"+value);
System.out.println("       this.value:"+this.value);
System.out.println("Outer.this.value:"+Outerr.this.value);
		}

	}

}

public class InnerEx5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Outerr outer = new Outerr () ; 
		Outerr.Inner inner = outer.new Inner ();
		inner.methodl();

	}

}
