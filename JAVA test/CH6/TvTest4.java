package CH6;

public class TvTest4 {

	public static void main(String[] args) {
		TV[] tvArr = new TV[3];
		
		//TV��ü�� �����ؼ� Tv��ü �迭�� �� �伭�� ����
		for(int i=0; i < tvArr.length;i++) {
			tvArr[i] = new TV();
			tvArr[i].channel = i+10;
			
		}
		for(int i=0; i < tvArr.length;i++) {
			tvArr[i].channelUp();
			System.out.printf("tvArr[%d].channel=%d%n",i,tvArr[i].channel);
		}
		
	}//main�� ��

}