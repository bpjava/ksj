package CH7;

class TV1{
	boolean power;
	int channel;
	
	void power() { power =! power;}
	void channelUp() { ++channel;}
	void channelDown() { --channel;}
	
}

class CaptionTv extends TV1{
	boolean caption;
	void displayCaption (String text) {
		if(caption) {
			System.out.println(text);
		}
	}
}
public class CaptionTVTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CaptionTv ctv = new CaptionTv();
		ctv.channel = 10;
		ctv.channelUp();
		System.out.println(ctv.channel);
		ctv.displayCaption("Hello, World");
		ctv.caption = true;
		ctv.displayCaption("Hello, World");
		
	}

}
