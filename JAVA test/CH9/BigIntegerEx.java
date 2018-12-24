package CH9;

import java.math.*;

public class BigIntegerEx {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		for (int i=1; i<100;i++) {    // 1!부터 99! 까지 출력 
System.out.printf("%d!=%s%n",  i, calcFactorial (i)) ;
Thread.sleep(300);
}
}
	static String calcFactorial(int n) { 
		return factorial(BigInteger.valueOf(n)).toString();
		}
	
	}
	
