package CH7;

public class InnerEX1 {

	class Instancelnner {
		int iv = 100;
		final static int CONST = 100;
	}

	static class Staticlnner {
		int iv = 200;
		static int cv = 200;
	}

	void myMethod() {
		class Locallnner {
			int iv = 300;
			final static int CONST = 300;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Instancelnner.CONST);
		System.out.println(Staticlnner.cv);

	}

}
