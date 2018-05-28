import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

class DataBase {

	private Connection conn;

	public Connection open() {
		try {
			String url = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
			String user = "mkloc";
			String pass = "mkloc";
			conn = DriverManager.getConnection(url, user, pass);
			//System.out.println("Autocommit: " + conn.getAutoCommit());

		} catch (SQLException e) {
			System.out.println("B³¹d po³¹czenia z baz¹.");
			e.printStackTrace();
		}
		return conn;
	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Nie mo¿na zamkn¹æ po³¹czenia: " + e.getMessage());
		}
	}

	public void createTables() {
		try {
			// Create tables
			open();
			if (conn != null) {
				Statement statement = conn.createStatement();
				statement.execute("SELECT 1 FROM user_tables WHERE TABLE_NAME='NAUCZYCIEL'");
				ResultSet check = statement.getResultSet();
				if (!check.next()) {
					System.out.println("Tworzenie tabel");
					statement.execute(
							"CREATE TABLE NAUCZYCIEL (IDN INT NOT NULL , LASTNAME VARCHAR2(30) NOT NULL , FIRSTNAME VARCHAR2(20) NOT NULL)");
					statement.execute(
							"CREATE TABLE UCZEN (IDU INT NOT NULL , LASTNAME VARCHAR2(30) NOT NULL , FIRSTNAME VARCHAR2(20) NOT NULL)");
					statement.execute("CREATE TABLE PRZEDMIOT (IDP INT NOT NULL , COURSE VARCHAR2(20) NOT NULL)");
					statement.execute(
							"CREATE TABLE OCENA (IDO INT NOT NULL , DESCRIPTION VARCHAR2(30) NOT NULL , VALUE FLOAT NOT NULL)");
					statement.execute(
							"CREATE TABLE OCENIANIE   (IDN INT NOT NULL , IDU INT NOT NULL , IDP INT NOT NULL , IDO INT NOT NULL , GRADETYPE VARCHAR2(1) NOT NULL)");
				}
				check.close();

				// Fill tables with sample data
				statement.execute("INSERT INTO NAUCZYCIEL VALUES (1, 'Szulc', 'Damian')");
				statement.execute("INSERT INTO NAUCZYCIEL VALUES (2, 'Mazur', 'Halina')");
				statement.execute("INSERT INTO NAUCZYCIEL VALUES (3, 'Malinowska', 'Irena')");
				statement.execute("INSERT INTO NAUCZYCIEL VALUES (4, 'Wojciechowski', 'Artur')");
				statement.execute("INSERT INTO NAUCZYCIEL VALUES (5, 'Zakrzewski', 'Micha³')");
				statement.execute("INSERT INTO NAUCZYCIEL VALUES (6, 'Gajewska', 'Marta')");

				statement.execute("INSERT INTO UCZEN VALUES (1, 'Majewska', 'Paulina')");
				statement.execute("INSERT INTO UCZEN VALUES (2, 'Wasilewski', 'Edward')");
				statement.execute("INSERT INTO UCZEN VALUES (3, 'Górski', 'Jerzy')");
				statement.execute("INSERT INTO UCZEN VALUES (4, 'Król', 'W³adys³aw')");
				statement.execute("INSERT INTO UCZEN VALUES (5, 'Ostrowski', 'Adam')");
				statement.execute("INSERT INTO UCZEN VALUES (6, 'Kowalski', 'Ryszard')");
				statement.execute("INSERT INTO UCZEN VALUES (7, 'Sadowski', 'Pawe³')");
				statement.execute("INSERT INTO UCZEN VALUES (8, 'Ostrowski', 'Waldemar')");
				statement.execute("INSERT INTO UCZEN VALUES (9, 'Stêpieñ', 'Paulina')");

				statement.execute("INSERT INTO PRZEDMIOT VALUES (1, 'Matematyka')");
				statement.execute("INSERT INTO PRZEDMIOT VALUES (2, 'Fizyka')");
				statement.execute("INSERT INTO PRZEDMIOT VALUES (3, 'j. polski')");
				statement.execute("INSERT INTO PRZEDMIOT VALUES (4, 'j. angielski')");
				statement.execute("INSERT INTO PRZEDMIOT VALUES (5, 'Biologia')");

				statement.execute("INSERT INTO OCENA VALUES (1, 'niedostateczny', 1)");
				statement.execute("INSERT INTO OCENA VALUES (2, 'dopuszczaj¹cy', 2)");
				statement.execute("INSERT INTO OCENA VALUES (3, 'dostateczny', 3)");
				statement.execute("INSERT INTO OCENA VALUES (4, 'dobry', 4)");
				statement.execute("INSERT INTO OCENA VALUES (5, 'bardzo dobry', 5)");
				statement.execute("INSERT INTO OCENA VALUES (6, 'celuj¹cy', 6)");
				
				statement.execute("INSERT INTO OCENIANIE VALUES (1, 1, 1, 1, 'S')");
				statement.execute("INSERT INTO OCENIANIE VALUES (1, 2, 4, 5, 'S')");
				statement.execute("INSERT INTO OCENIANIE VALUES (3, 3, 2, 1, 'C')");
				statement.execute("INSERT INTO OCENIANIE VALUES (3, 5, 5, 3, 'S')");
				statement.execute("INSERT INTO OCENIANIE VALUES (5, 2, 4, 1, 'C')");

				statement.close();
				close();
			}
		} catch (SQLException e) {

		}
	}

	public void deleteTables() {
		// delete tables
		try {
			open();
			if (conn != null) {
				Statement statement = conn.createStatement();
				statement.execute("DROP TABLE NAUCZYCIEL");
				statement.execute("DROP TABLE UCZEN");
				statement.execute("DROP TABLE PRZEDMIOT");
				statement.execute("DROP TABLE OCENA");
				statement.execute("DROP TABLE OCENIANIE");
				statement.close();
			}
			close();
		} catch (SQLException e) {

		}
	}

	public void insert(int idn, int idu, int idp, int ido, String type) {
		try {
			// Create tables
			open();
			if (conn != null) {
				Statement statement = conn.createStatement();
				statement.execute("SELECT 1 FROM user_tables WHERE TABLE_NAME='NAUCZYCIEL'");
				ResultSet check = statement.getResultSet();
				if (!check.next()) {
					System.out.println("Aby dodaæ ocenê utwórz tabele.");
				}
				check.close();

				// Insert into OCENIANIE
				statement.execute("INSERT INTO OCENIANIE VALUES (" + idn + "," + idu + "," + idp + "," + ido + ",'"
						+ type + "')");

				statement.close();
				close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public int count(String table) {
		int count=0;
		try {
			// Create tables
			open();
			if (conn != null) {
				Statement statement = conn.createStatement();
                ResultSet res = statement.executeQuery("SELECT COUNT(*) FROM " + table);
                while (res.next()){
                    count = res.getInt(1);
                }
				statement.close();
			}
			close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return count;
	}
	
	public void showMarks() {
		try {
			// Create tables
			open();
			if (conn != null) {
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT NAUCZYCIEL.LASTNAME, NAUCZYCIEL.FIRSTNAME,PRZEDMIOT.COURSE,UCZEN.FIRSTNAME,UCZEN.LASTNAME,OCENA.VALUE,OCENA.DESCRIPTION FROM OCENIANIE JOIN NAUCZYCIEL ON ocenianie.idn = NAUCZYCIEL.IDN JOIN UCZEN ON uczen.idu = OCENIANIE.IDU JOIN PRZEDMIOT ON przedmiot.idp = OCENIANIE.IDP JOIN OCENA ON ocenianie.ido = OCENA.IDO");
				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				for (int i = 1; i <= columnsNumber; i++) {
					System.out.print(rsmd.getColumnName(i) + " ");
				}
				System.out.println();
				while (resultSet.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) System.out.print(",  ");
				        String columnValue = resultSet.getString(i);
				        System.out.print(columnValue);
				    }
				    System.out.println("");
				}
				System.out.println();
				
				
				statement.close();
			}
			close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
