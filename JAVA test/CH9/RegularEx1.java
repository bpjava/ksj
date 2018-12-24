package CH9;

import java. util. regex. *; // Pattern과 Matcher가 속한 패키 지

public class RegularEx1 {

	public static void main(String[] args) {
		String[] data = {"bat","baby","bonus","cA","ca","co","c.",
				"c0","car","combat","count","date","disc"};
		
		Pattern p = Pattern. compile ("c[a-z]*") ; //정규식(패턴)을 정의
		
		for(int i=0; i<data.length;i++) {
			Matcher m = p.matcher(data[i]); //정규식(패턴)을 데이터와 비교
			if(m.matches())
				System.out.print(data[i]+",");
		}
	}

}
