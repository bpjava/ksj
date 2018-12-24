package Excersice;

public class Exerciese7_18 {

	publlic
	static void action(Robot r) {
		if(r instanceof DanceRobot ){
			DanceRobot dr = (DanceRobot)t;
			dr.dance();
			}else if(r instanceof SingRobot) {
				SingRobot sr = (SingRobot)r;
						sr.sing();
							
			}
			else if(r instanceof DrawRobot) {
				DrawRobot drr = (DrawRobot)d;
				drr.Draw();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
