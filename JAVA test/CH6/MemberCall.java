package CH6;

class MemberCall {
	int iv = 10;
	static int cv = 20;

	int iv2 = cv;
	// static int cv2 = iv ; //���� Ŭ���������� �ν��Ͻ� ���� ������
	static int cv2 = new MemberCall().iv; // ��ó�� ����

	static void staticMethod1() {
		System.out.println(cv);
		// System.out.println(iv); Ŭ�����޼�Ʈ���� �ν��Ͻ����� �Ұ�
		MemberCall c = new MemberCall();
		System.out.println(c.iv); // �̷��� ��ü ���� �� ���� Ŭ�����޼��� ��������

	}
   void instanceMethod1() {
		System.out.println(cv);
		System.out.println(iv);
	   
   }
	static void staticMethod2() {
		staticMethod2();
		//instanceMethod(); 
		MemberCall c = new MemberCall();
		c.instanceMethod1(); //�ν��Ͻ� ���� �� ȣ��
	}
	void instancMethod2() {
		staticMethod1();
		instanceMethod1();
	}
}
