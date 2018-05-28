import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Grades {

	public static void main(String[] args) {
		DataBase db = new DataBase();
		boolean exit = false;
		do {
			System.out.println("Menu:");
			System.out.println("1. UTWORZ I WYPE£NIJ TABELE");
			System.out.println("2. SKASUJ WSZYSTKIE TABELE");
			System.out.println("3. OCEÑ");
			System.out.println("4. WYŒWIETL TABELÊ OCEN");
			System.out.println("5. WYJŒCIE Z PROGRAMU\n");
			Scanner sd = new Scanner(System.in);
			System.out.print("Wybierz: ");
			System.out.println();
			int num = sd.nextInt();
			switch (num) {
			case 1:
				db.createTables();
				break;
			case 2:
				db.deleteTables();
				System.out.println("USUNIÊTO TABELE");
				break;

			case 3:
				Marking marking = new Marking();
				marking.dispaly();
				break;

			case 4:
				db.showMarks();
				break;

			case 5:
				exit = true;
				break;
			}
		} while (!exit);

	}

}
