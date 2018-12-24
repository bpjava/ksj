package CH7;

public class InnerEx3 {

	private int outerIv = 0;
	static int outerCv = 0;

	class Instancelnner {
		int iiv = outerIv;
		int iiv2 = outerCv;
	}

	static class Staticlnner {
		int siv = outerIv;
		static int scv = outerCv;
	}

	void myMethod() {
		int lv = 0;
		final int LV = 0;

		class LocalInner{
			int iiv = outerIv;
			int iiv2 = outerCv;
			int iiv4 = LV;
		}
}
}