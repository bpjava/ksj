package CH6;

class MemberCall {
	int iv = 10;
	static int cv = 20;

	int iv2 = cv;
	// static int cv2 = iv ; //에러 클래스변수는 인스턴습 변수 사용못함
	static int cv2 = new MemberCall().iv; // 이처럼 생성

	static void staticMethod1() {
		System.out.println(cv);
		// System.out.println(iv); 클래스메서트에서 인스턴스변수 불가
		MemberCall c = new MemberCall();
		System.out.println(c.iv); // 이렇게 객체 생성 후 참조 클래스메서드 참조가능

	}
   void instanceMethod1() {
		System.out.println(cv);
		System.out.println(iv);
	   
   }
	static void staticMethod2() {
		staticMethod2();
		//instanceMethod(); 
		MemberCall c = new MemberCall();
		c.instanceMethod1(); //인스턴스 생성 후 호출
	}
	void instancMethod2() {
		staticMethod1();
		instanceMethod1();
	}
}
