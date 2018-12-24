package CH7;

public class InnerEX2 {

	class InstanceInner {
	}

	static class StaticInner {
	}
	InstanceInner iv = new InstanceInner();
	static StaticInner cv = new StaticInner(); 
	
	static void staticMethod() { 
		StaticInner obj2 = new StaticInner();
		
		InnerEX2 outer = new InnerEX2(); 
		InstanceInner obj1 = outer.new InstanceInner();
		
	}
	
	void instanceMethod() { 
		InstanceInner obj1 = new InstanceInner();
		StaticInner obj2 = new StaticInner();
	}
	
	
	void myMethod() {
		class LocalInner{}
		LocalInner lv = new LocalInner();
	}
}
