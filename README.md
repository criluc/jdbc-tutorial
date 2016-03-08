## Synopsis

Tutorial in Italiano su Java DataBase Connnectity (JDBC).
Il progetto contiene:
 - le slide introduttive all'utilizzo di JDBC che contengono le basi
 della teoria generale e degli esempi pratici
 - le slide per un'esercizione sull'utilizzo di JDBC, disponibii nei formati:
  - revealjs dentro il file slide-esercizione.zip
  - asciidoctor nella file src/docs/jdbc-training.adoc
 - il codice sorgente sia degli esempi mostrati nella teoria che nell'esercitazione

Il progetto contiene inoltre:
 - slide introduttive su Java
 - slide introduttive su Maven
 
Gli esempi sono basati sull'utlizzo di Java 8, Maven per la
risoluzione delle dipendenze e h2 come database.

Le slide dell'esercitazione sono scritte con asciidoctor + revealjs.

## Codice di esempio

La teoria e gli esercizi pratici guidano fino alla scrittura di un DAO
con metodi tipo:

```
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
```

## Installation

Per la parte dei sorgenti Java è possibile utilizzare questo progetto
come progetto Maven ed importarlo nel proprio editor preferito.

Le slide della parte teorica sono in Openoffice.

Le slide delle esercitazioni sono in ascidoctor + revealjs per
produrle seguire le istruzioni in src/docs/README.txt.

## Contributors

Cristian Lucchesi <cristian.lucchesi@gmail.com>, @criluc

## License

GNU GENERAL PUBLIC LICENSE
Version 3, 29 June 2007
