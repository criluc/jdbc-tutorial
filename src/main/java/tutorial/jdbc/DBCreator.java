/**
 *
 */
package tutorial.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author cristian
 *
 */
public class DBCreator {

  private final static String CREATE_DB_DDL =
      "CREATE TABLE employees ("
          + "id SERIAL NOT NULL PRIMARY KEY,"
          + "name VARCHAR(255), surname VARCHAR(255))";

  public static void main(String[] args) throws SQLException {
    System.out.print("Eseguo la connessione al db...");

    try (Connection con = DriverManager.getConnection(
        DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD)) {

      try (Statement stmt = con.createStatement()) {
        //Esegue la query
        stmt.executeUpdate(CREATE_DB_DDL);
        System.out.print("Tabelle create.");
      }
    }
  }
}
