package CH9;

public class EqualsEx1 {

	public static void main(String[] args) {
		

		Value vl = new Value(10);
		Value v2 = new Value(10);
		
		if (vl.equals(v2)) {
			System.out.println("vl 과 v2 는 같 습 니 다 .");
		} else {
			System.out.println("vl과 v2는 다릅니다.");
		}
		
		v2 = vl; //v2에 v1의 주소값을 준다
		
		if (vl.equals(v2)) {
			System.out.println("v1과  v2는 같습니다.");
		} else {
			System.out.println("vl과 v2는 다릅니다.");
		}
	} // main

}

class Value {
	int value;

	Value(int value) {
		this.value = value;
	}
}
