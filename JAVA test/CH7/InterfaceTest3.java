package CH7;

public class InterfaceTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AAA a = new AAA();
		a.methodA();

	}

}

class AAA {
	void methodA() {
		II i = InstanceManager.getInstance();
		i.methodB();
		System.out.println(i.toString());
	}

}

interface II {
	public abstract void methodB();
}

class BBB implements II {
	public void methodB() {
		System.out.println("methodB in B class");
	}

	public String toString() {
		return "class B";
	}
}

class InstanceManager {
	public static II getlnstance() {
		return new BBB();
	}
}
