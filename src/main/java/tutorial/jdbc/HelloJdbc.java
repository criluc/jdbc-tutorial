/**
 *
 */
package tutorial.jdbc;

import java.sql.*;

/**
 * @author cristian
 *
 */
public class HelloJdbc {

  private final static String QUERY_EMPLOYEES =
      "SELECT * FROM employees LIMIT 10";

  public static void main(String[] args) throws SQLException {
    System.out.print("Eseguo la connessione al db...");

    try (Connection con = DriverManager.getConnection(
        DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD)) {

      System.out.println("Connessione effettuata.");

      try (Statement stmt = con.createStatement()) {

        //Esegue la query
        try (ResultSet rs = stmt.executeQuery(QUERY_EMPLOYEES)) {

          //Mostra i risultati
          //rs.next() == false quando non ci sono più i risultati
          while(rs.next()) {
            rs.getInt("number");
            if (rs.wasNull()) {
              //Trattamento del caso SQL NULL
            }
            String nome = rs.getString("name");
            String cognome = rs.getString("surname");
            System.out.println(
                String.format("Trovata persona %s %s.", nome, cognome));
          }
        }
      }
    }
  }
}
