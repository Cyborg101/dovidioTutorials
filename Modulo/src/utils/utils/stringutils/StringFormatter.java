package utils.stringutils;

public class StringFormatter {
	
	public static String capitalize(String s) {
		if (s.length() == 1) {
			return String.valueOf(Character.toUpperCase(s.charAt(0)));
		} 
		if (s.length() > 1) {
			return Character.toUpperCase(s.charAt(0)) + s.substring(1);	
		} 
		return "";
	}

	public static String toUpperCase(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			sb.append(Character.toUpperCase(c));
		}
		return sb.toString();
	}
}