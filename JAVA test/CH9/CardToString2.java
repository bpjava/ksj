package CH9;

class Card1 {
	String kind;
	int number;

	Card1() {
		this("SPADE", 1);// Card (String kind, int number) �� ȣ��

	}

	Card1(String kind, int number) {
		this.kind = kind;
		this.number = number;
	}

	public String toString() {
		// Card�ν��Ͻ��� kind�� number�� ���ڿ��� ��ȯ�Ѵ�.
		return "kind : " + kind + ", number:" + number;
	}
}
	public class CardToString2 {
		public static void main(String[] args) {  //public����������
			Card1 cl = new Card1 () ; 
			Card1 c2 = new Card1 ("HEART", 10); 
			System.out.println(cl.toString());
			System.out.println(c2.toString()); //�������̵�
		}
	}


