package CH7;


public class DefaultMethodTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Child c = new Child();
		c.methodl();
		c.method2();
		Mylnterface.staticMethod();
		Mylnterface2.staticMethod();

	}

}

class Child extends Parent implements Mylnterface, Mylnterface2 {
	public void methodl() {
		System.out.println("method1()in child");
	}

}

class Parent {
	public void method2() {
		System.out.println("method2() in parent");
	}
}
	interface Mylnterface {
		default void methodl() {
			System.out.println("method1()in MyInterface");
		}

		default void method2() {
			System.out.println("method2()in MyInterface");
		}
		static void staticMethod() {
			System.out.println("staticmethod()in MyInterface");
		}

	}
	
	
	interface Mylnterface2 { 
		default void methodl () { 
			System.out.println("method()in myinterface2");
		}
		static void staticMethod() {
			System.out.println("staticmethod()in MyInterface2");
		}
	}
	
	
	

