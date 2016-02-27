/**
 *
 */
package tutorial.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data Accesso Object responsabile delle operazioni di lettura scrittura
 * degli oggetti Employee dal database.
 *
 * @author cristian
 *
 */
public class EmployeeDao {

  private final static String SELECT_EMPLOYEE = "SELECT * FROM employees WHERE id = ?";
  private final static String INSERT_EMPLOYEE = "INSERT INTO employees (name, surname) values (?, ?)";
  private final static String SELECT_EMPLOYEES = "SELECT * FROM employees ORDER BY surname, name";
  private final static String SELECT_EMPLOYEES_LIMITED = SELECT_EMPLOYEES + " limit ?";
  /**
   * @param id l'id dell'Employee da cercare.
   * @return un Optional.absenct() se l'id passato non è trovato sul db, altrimenti
   *   l'Optional contenente l'Employee cercato.
   *
   * @throws SQLException
   */
  public Optional<Employee> findById(int id) throws SQLException {
    try(Connection con = DBConfig.getConnection()) {
      PreparedStatement pstmt = con.prepareStatement(SELECT_EMPLOYEE);
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        //Riga con l'id passato non trovata
        if (!rs.next()) {
          return Optional.empty();
        }
        return Optional.of(
            Employee.builder()
            .id(id).name(rs.getString("name")).surname(rs.getString("surname"))
            .build());
      }
    }
  }

  public List<Employee> list(Optional<Integer> limit) throws SQLException {
    try(Connection con = DBConfig.getConnection()) {
      PreparedStatement ps;
      if (limit.isPresent()) {
        ps = con.prepareStatement(SELECT_EMPLOYEES_LIMITED);
        ps.setInt(1, limit.get());
      } else {
        ps = con.prepareStatement(SELECT_EMPLOYEES);
      }
      List<Employee> employees = new ArrayList<>();
      try (ResultSet rs = ps.executeQuery()) {
        while(rs.next()) {
          employees.add(
              Employee.builder()
              .id(rs.getInt("id"))
              .name(rs.getString("name"))
              .surname(rs.getString("surname"))
              .build());
        }
        return employees;
      }
    }
  }

  /**
   * @param name nome dell'Employee
   * @param surname cognome dell'Employee
   * @return true se l'Employee è stato salvato sul db, false altrimenti.
   *
   * @throws SQLException
   */
  public boolean persist(String name, String surname) throws SQLException {
    try(Connection con = DBConfig.getConnection()) {
      try(PreparedStatement ps =
          con.prepareStatement(INSERT_EMPLOYEE)) {
        ps.setString(1, name);
        ps.setString(2, surname);
        return ps.execute();
      }
    }
  }

  /**
   * @param employee l'oggetto Employee da aggiornare sul database
   * @return true se i dati dell'Employee sono stati salvati sul db, false altrimenti.
   *
   * @throws SQLException
   * @throws IllegalArgumentException se l'Employee passato è null o è null il suo id.
   */
  public boolean save(Employee employee) throws SQLException {
    if (employee == null || employee.getId() == null) {
      throw new IllegalArgumentException(
          "L'employee passato come parametro e il suo id non possono essere NULL");
    }
    try(Connection con = DBConfig.getConnection()) {
      try(PreparedStatement ps = con.prepareStatement(
          SELECT_EMPLOYEE,
          ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
        ps.setInt(1, employee.getId());
        try (ResultSet rs = ps.executeQuery()) {
          //id dell'employee non trovato
          if (!rs.next()) {
            return false;
          }
          rs.updateString("name", employee.getName());
          rs.updateString("surname", employee.getSurname());
          rs.updateRow();
        }
      }
    }
    return true;
  }

  /**
   * @param employee l'oggetto Employee da eliminare dal database
   * @return true se l'Employee è stato cancellato dal db, false altrimenti.
   *
   * @throws SQLException
   * @throws IllegalArgumentException se l'Employee passato è null o è null il suo id.
   */
  public boolean remove(Employee employee) throws SQLException {
    if (employee == null || employee.getId() == null) {
      throw new IllegalArgumentException(
          "L'employee passato come parametro e il suo id non possono essere NULL");
    }
    try(Connection con = DBConfig.getConnection()) {
      try(PreparedStatement ps = con.prepareStatement(
          SELECT_EMPLOYEE,
          ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
        ps.setInt(1, employee.getId());
        try (ResultSet rs = ps.executeQuery()) {
          //id dell'employee non trovato
          if (!rs.next()) {
            return false;
          }
          rs.deleteRow();
        }
      }
    }
    return true;
  }
}
