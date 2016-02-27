/**
 *
 */
package tutorial.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author cristian
 *
 */
public class HelloJdbcJre7 {

  private final static String QUERY_EMPLOYEES =
      "SELECT * FROM employees LIMIT 10";

  @SuppressWarnings("resource")
  public static void main(String[] args) throws SQLException {
    System.out.print("Eseguo la connessione al db...");

    Connection con = DriverManager.getConnection(
        DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);

    System.out.println("connessione effettuata.");

    Statement stmt = con.createStatement();

    //Esegue la query
    ResultSet rs = stmt.executeQuery(QUERY_EMPLOYEES);

    //Mostra i risultati
    //rs.next() == false quando non ci sono più i risultati
    while(rs.next()) {
      String nome = rs.getString("name");
      String cognome = rs.getString("surname");
      System.out.println(String.format("Trovata persona %s %s.", nome, cognome));
    }

    stmt.close();
    rs.close();
    con.close();
  }

}
