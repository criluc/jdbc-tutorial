/**
 *
 */
package tutorial.jdbc;

import java.sql.Connection;
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
    try (Connection con = DBConfig.getConnection()) {

      try (Statement stmt = con.createStatement()) {
        //Esegue la query
        stmt.executeUpdate(CREATE_DB_DDL);
      }
    }
  }
}
