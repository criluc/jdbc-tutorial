/**
 *
 */
package tutorial.jdbc;

import lombok.Builder;
import lombok.Data;

/**
 * @author cristian
 *
 */
@Data @Builder
public class Employee {

  private Integer id;
  private String name;
  private String surname;

  @Override
  public String toString() {
    return String.format("%s %s (id=%d)", surname, name, id);
  }
}
