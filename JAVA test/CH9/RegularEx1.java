package CH9;

import java. util. regex. *; // Pattern�� Matcher�� ���� ��Ű ��

public class RegularEx1 {

	public static void main(String[] args) {
		String[] data = {"bat","baby","bonus","cA","ca","co","c.",
				"c0","car","combat","count","date","disc"};
		
		Pattern p = Pattern. compile ("c[a-z]*") ; //���Խ�(����)�� ����
		
		for(int i=0; i<data.length;i++) {
			Matcher m = p.matcher(data[i]); //���Խ�(����)�� �����Ϳ� ��
			if(m.matches())
				System.out.print(data[i]+",");
		}
	}

}
