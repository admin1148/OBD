import java.util.InputMismatchException;
import java.util.Scanner;

public class Marking {
	private int idn;
	private int idu;
	private int idp;
	private int ido;
	private String type;

	public void dispaly() {

		DataBase db = new DataBase();

		int teacherCount = db.count("NAUCZYCIEL");
		int studentCount = db.count("UCZEN");
		int courseCount = db.count("PRZEDMIOT");
		int markCount = db.count("OCENA");

		do {
			System.out.println("ID nauczyciela: ");
			idn = getNumber();
		} while (idn > teacherCount || idn <= 0);
		do {
			System.out.println("ID ucznia: ");
			idu = getNumber();
		} while (idu > studentCount || idu <= 0);
		do {
			System.out.println("ID przedmiotu: ");
			idp = getNumber();
		} while (idp > courseCount || idp <= 0);
		do {
			System.out.println("ID oceny: ");
			ido = getNumber();
		} while (ido > markCount || ido <= 0);

		System.out.println("typ oceny (C/S): ");
		Scanner sS = new Scanner(System.in);
		type = sS.next();
		if (type.matches("c|C|s|S")) {
			db.insert(idn, idu, idp, ido, type);
		} else {
			System.out.println("Nie wprowadzono rodzaju oceny");
		}

	}

	private int getNumber() {
		int number = 0;
		Scanner sd = new Scanner(System.in);
		try {
			number = sd.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Nie wprowadzono liczby");
		}
		return number;
	}
}
