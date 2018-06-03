import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.ParseConversionEvent;

public class Grades {

	public static void main(String[] args) {

		DataBase db = new DataBase();
		//db.open();
		boolean exit = false;
		String num = "";
		if (db.open()) {
			do {
				System.out.println("Menu:");
				System.out.println("1. UTWORZ I WYPE£NIJ TABELE");
				System.out.println("2. SKASUJ WSZYSTKIE TABELE");
				System.out.println("3. OCEÑ");
				System.out.println("4. WYŒWIETL TABELÊ OCEN");
				System.out.println("5. WYJŒCIE Z PROGRAMU\n");

				System.out.print("Wybierz: ");

				Scanner sc = new Scanner(System.in);
				// if (sc.hasNextLine()) {
				num = sc.nextLine();

				// }

				if (num.equals("1") || num.equals("2") || num.equals("3") || num.equals("4") || num.equals("5")) {
					int numb = Integer.parseInt(num);
					switch (numb) {
					case 1:
						db.createTables();
						break;
					case 2:
						db.deleteTables();
						break;

					case 3:
						Marking marking = new Marking();
						db.createTables();
						marking.dispaly(db);
						break;

					case 4:
						db.showMarks();
						break;

					case 5:
						db.close();
						System.out.println("Program zakoñczy³ pracê.");
						exit = true;
						break;

					}
				} else {
					System.out.println("Nie wprowadzono poprawnej opcji.");
				}

			} while (!exit);
		}
	}

}
