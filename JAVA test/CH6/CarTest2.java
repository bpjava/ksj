package CH6;

class Car3 {
	String color;
	String gearType;
	int door;

	Car3() {
		this("white", "auto", 4);

	}

	Car3(String color) {
		this(color, "auto", 4);
		
	}
		Car3(String color, String gearType, int door){
			this.color = color;
			this.gearType = gearType;
			this.door = door;
			
		}
	
}

public class CarTest2 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

		Car3 c1 = new Car3();
		Car3 c2 = new Car3("blue");
		
		System.out.println("c1ÀÇ  color =" + c1.color + ", gearType=" + c1.gearType+ ", door=" + c1.door); 
		System.out.println("c2ÀÇ  color =" + c2.color + ", gearType=" + c2.gearType+ ", door=" + c2.door); 
	}

}
