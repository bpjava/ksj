package CH7;

class AA{
	void autoPlay(I i) {
		i.play();
	}
}

interface I{
	public abstract void play();
}

class BB implements I{
	public void play() {
		System.out.println("play in B class");
	}
}

class C implements I{
	public void play() {
		System.out.println("play in C class");
	}
}


public class InterfaceTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AA a = new AA();
		a.autoPlay(new BB());
		a.autoPlay(new C());
	}

}