import java.net.SocketTimeoutException;
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

		ArrayList<Integer> teacherCount = db.count("NAUCZYCIEL", "IDN");
		ArrayList<Integer> studentCount = db.count("UCZEN", "IDU");
		ArrayList<Integer> courseCount = db.count("PRZEDMIOT", "IDP");
		ArrayList<Integer> markCount = db.count("OCENA", "IDO");

		do {
			System.out.println("ID nauczyciela: ");
			idn = getNumber();
			if (!teacherCount.contains(idn)) {
				System.out.println("Nieporpawne ID nauczyciela");
			}
		} while (!teacherCount.contains(idn));
		do {
			System.out.println("ID ucznia: ");
			idu = getNumber();
			if (!studentCount.contains(idu)) {
				System.out.println("Nieporpawne ID ucznia");
			}
		} while (!studentCount.contains(idu));
		do {
			System.out.println("ID przedmiotu: ");
			idp = getNumber();
			if (!courseCount.contains(idp)) {
				System.out.println("Nieporpawne ID przedmiotu");
			}
		} while (!courseCount.contains(idp));
		do {
			System.out.println("ID oceny: ");
			ido = getNumber();
			if (!markCount.contains(idp)) {
				System.out.println("Nieporpawne ID oceny");
			}
		} while (!markCount.contains(ido));

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
		int number=0;
		Scanner sd = new Scanner(System.in);
		try {
			if (sd.hasNext()) {
			nb = sd.nextInt();
			number = nb;}
		} catch (InputMismatchException e) {
			System.out.println("Nie wprowadzono liczby");
		} catch (NoSuchElementException e) {
		}
		return number;
	}
}
