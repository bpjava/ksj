package CH9;

class Card1 {
	String kind;
	int number;

	Card1() {
		this("SPADE", 1);// Card (String kind, int number) 를 호출

	}

	Card1(String kind, int number) {
		this.kind = kind;
		this.number = number;
	}

	public String toString() {
		// Card인스턴스의 kind와 number를 문자열로 반환한다.
		return "kind : " + kind + ", number:" + number;
	}
}
	public class CardToString2 {
		public static void main(String[] args) {  //public접근제어자
			Card1 cl = new Card1 () ; 
			Card1 c2 = new Card1 ("HEART", 10); 
			System.out.println(cl.toString());
			System.out.println(c2.toString()); //오버라이딩
		}
	}


