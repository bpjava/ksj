package CH9;

import java.util.regex.*;

public class RegularEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String source = "A broken hand works, but not a broken heart.";
		String pattern = "broken";
		StringBuffer sb = new StringBuffer();
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		System.out.println("source:"+source); 
		
		int i =0;
		while(m.find()) {
			System.out.println(++i +"��° ��Ī:" + m.start() + "~"+m.end());
		// broken�� drunken���� ġȯ�Ͽ� sb�� ������ �� .
			m.appendReplacement(sb, "drunken");
			
		}
		m.appendTail(sb);
		System.out.println("Replacemant count : "+i);
		System.out.println("result:"+sb.toString());
		
	}

}
