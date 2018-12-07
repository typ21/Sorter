package typ21;

public class Starter {

	public static void main(String[] args) {
		if (args.length == 0) {
			new Main("untracked/list.txt");
		} else {
			new Main(args[0]);
		}
	}

}
