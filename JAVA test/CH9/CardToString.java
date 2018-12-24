package CH9;

class Card {
	String kind;
	int number;

	Card() {
		this("SPADE",1);
	}

	Card(String kind, int number) {
		this.kind = kind;
		this.number = number;
	}
}


class CardToString {
	public static void main(String[] args) {
		Card cl = new Card () ; 
		Card c2 = new Card () ;
		System.out.println(cl.toString());
		System.out.println(c2.toString());
	}

} //object클래스의 toString이 호출됨.
