package CH7;

public class PointTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point3D p = new Point3D (1,2,3);
	}

}

class Pointt{
	int x;
	int y;
	
	Pointt(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	String getLocation() {
		return "x : " +x +", y:" + y;
	}
}
class Point3D extends Pointt{
	int z;
	Point3D(int x, int y, int z){
	super(x,y);
		
		
		this.z = z;
		
	}
	String getLocation() {
		return "x : " +x +", y:" + y + ",z:" +z;
	}
}

