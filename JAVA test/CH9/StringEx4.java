package CH9;

import java.util.StringJoiner;

public class StringEx4 {

	public static void main(String[] args) {
		String animals = "dog,cat,bear";
		String[] arr = animals.split(",");

		System.out.println(String.join("-", arr)); //join

		StringJoiner sj = new StringJoiner("/", "[", "]");
		for (String s : arr)
			sj.add(s);                          //stringJoiner

		System.out.println(sj.toString());

	}

}
