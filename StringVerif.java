
public class StringVerif {
	private StringVerif() { }
	
	public static String alphanum(String str) {
		return str.replaceAll("[^a-zA-Z0-9]", "");
	}
}
