/**
 *
 */
package tutorial.jdbc;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author cristian
 *
 */
public class CommandLine {

  private final Scanner scanner;
  private final EmployeeDao employeeDao;

  public CommandLine(Scanner scanner) {
    this.scanner = scanner;
    employeeDao = new EmployeeDao();
  }

  public void elencoDipendenti() throws SQLException {
    for(Employee emp : employeeDao.list(Optional.empty())) {
      System.out.println(emp);
    }
    System.out.println("\n");
  }

  public void inserisciDipendente() throws SQLException {

    System.out.println("== Inserimento di una persona nel db ==");

    System.out.print("Inserisci il cognome: ");
    // get their input as a String
    String surname = scanner.next();

    System.out.print("Inserisci il nome: ");
    // get their input as a String
    String name = scanner.next();

    employeeDao.persist(name, surname);

    System.out.println(String.format("%s %s inserito in anagrafica\n", surname, name));
  }

  private void menu() {
    System.out.println("= Anagrafica Dipendenti =");
    System.out.println("== Menu Principale ==\n");
    System.out.println("1) Elenco dipendenti");
    System.out.println("2) Inserisci dipendente");
    System.out.println("3) Modifica dipendente");
    System.out.println("4) Elimina dipendente\n");

    System.out.println("0) Termina applicazione\n\n");
    System.out.println("Seleziona una operazione\n");
  }

  public void main() throws SQLException {
    String command = scanner.next();
      switch (command) {
        case "0":
          System.out.println("Anagrafica terminata");
          return;
        case "1":
          elencoDipendenti();
          break;
        case "2":
          inserisciDipendente();
          break;
        default:
          System.out.println(String.format("Operazione %s non riconosciuta", command));
      }
      System.out.println("Seleziona un'operazione (da 0 a 4)\n");
      main();
  }
  
  public void start() throws SQLException {
    menu();
    main();
  }

  public static void main(String[] args) throws SQLException {
    // create a scanner so we can read the command-line input
    try (Scanner scanner = new Scanner(System.in)) {
      CommandLine cl = new CommandLine(scanner);
      cl.start();
    }
  }


}
