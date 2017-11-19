package moduloprincipale.appesempio;

import utils.stringutils.StringFormatter;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Siamo nella classe principale");

		String umberto = "umberto";

		System.out.println("capitalizziamo la stringa " + umberto + ": " + StringFormatter.capitalize(umberto));

		System.out.println("Ora invece rendiamola maiuscola: " + StringFormatter.toUpperCase(umberto));
	}
}