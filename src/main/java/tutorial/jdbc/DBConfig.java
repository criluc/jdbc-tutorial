/**
 *
 */
package tutorial.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author cristian
 *
 */
public class DBConfig {

  public final static String DB_URL = "jdbc:h2:~/corsojava";
  public final static String DB_USER = "sa";
  public final static String DB_PASSWORD = "";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
  }

}
