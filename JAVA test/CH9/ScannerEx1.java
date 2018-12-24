package CH9;

import java.util.*;

public class ScannerEx1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String[] argArr = null;

		while (true) {
			String prompt = ">>";
			System.out.println(prompt);
//화면으로부터 라인단위로 입력받는다.
			String input = s.nextLine();

			input = input.trim(); // 입력받은 값에서 불필요한 앞뒤 공백을 제 거 한 다 .
			argArr = input.split(" +");
 
			String command = argArr[0].trim();
			
			if("".equals(command)) continue;
			
			//명령어를 소문자로 바꾼다.
			command = command.toLowerCase();
			
			//q또는Q를 입력하면 실행종료한다.
			if(command.equals("q")) {
				System.exit(0);
				
			}else{
				for(int i=0; i<argArr.length;i++)
					System.out.println(argArr[i]);
			}
			
		}

	}

}
