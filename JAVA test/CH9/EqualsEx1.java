package CH9;

public class EqualsEx1 {

	public static void main(String[] args) {
		

		Value vl = new Value(10);
		Value v2 = new Value(10);
		
		if (vl.equals(v2)) {
			System.out.println("vl �� v2 �� �� �� �� �� .");
		} else {
			System.out.println("vl�� v2�� �ٸ��ϴ�.");
		}
		
		v2 = vl; //v2�� v1�� �ּҰ��� �ش�
		
		if (vl.equals(v2)) {
			System.out.println("v1��  v2�� �����ϴ�.");
		} else {
			System.out.println("vl�� v2�� �ٸ��ϴ�.");
		}
	} // main

}

class Value {
	int value;

	Value(int value) {
		this.value = value;
	}
}
