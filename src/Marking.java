import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Marking {
	private int idn;
	private int idu;
	private int idp;
	private int ido;
	private String type;

	public void dispaly(DataBase db) {

		do {
			System.out.println("ID nauczyciela: ");
			idn = getNumber();
			if (idn <= 0 || !(idn == db.searchForID("NAUCZYCIEL", "IDN", idn))) {
				System.out.println("Nieporpawne ID nauczyciela");
			}
		} while (idn <= 0 || !(idn == db.searchForID("NAUCZYCIEL", "IDN", idn)));
		do {
			System.out.println("ID ucznia: ");
			idu = getNumber();
			if (idu <= 0 || !(idu == db.searchForID("UCZEN", "IDU", idu))) {
				System.out.println("Nieporpawne ID ucznia");
			}
		} while (idu <= 0 || !(idu == db.searchForID("UCZEN", "IDU", idu)));
		do {
			System.out.println("ID przedmiotu: ");
			idp = getNumber();
			if (idp <= 0 || !(idp == db.searchForID("PRZEDMIOT", "IDP", idp))) {
				System.out.println("Nieporpawne ID przedmiotu");
			}
		} while (idp <= 0 || !(idp == db.searchForID("PRZEDMIOT", "IDP", idp)));
		do {
			System.out.println("ID oceny: ");
			ido = getNumber();
			if (ido <= 0 || !(ido == db.searchForID("OCENA", "IDO", ido))) {
				System.out.println("Nieporpawne ID oceny");
			}
		} while (ido <= 0 || !(ido == db.searchForID("OCENA", "IDO", ido)));

		do {
			System.out.println("typ oceny (C/S): ");
			Scanner sS = new Scanner(System.in);
			type = sS.next();
			if (type.matches("C|S")) {
				db.insert(idn, idu, idp, ido, type);
				System.out.println("Wprowadzono ocenê.");
			} else {
				System.out.println("Niepoprawa nazwa oceny");
			}
		} while (!type.matches("C|S"));

	}

	private int getNumber() {
		int nb;
		int number = 0;
		Scanner sd = new Scanner(System.in);
		try {
			if (sd.hasNext()) {
				nb = sd.nextInt();
				number = nb;
			}
		} catch (InputMismatchException e) {
			System.out.println("Nie wprowadzono liczby");
		} catch (NoSuchElementException e) {
		}
		return number;
	}
}
