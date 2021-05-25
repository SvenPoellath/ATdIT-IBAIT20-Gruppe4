package atdit_ibait_20.database.persistence.implementation;

import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.Vertrag;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import atdit_ibait_20.database.model.implementation.BasicVertrag;
import atdit_ibait_20.database.persistence.Database;

import java.sql.*;

import static atdit_ibait_20.database.persistence.implementation.PasswordService.*;
/**
* Die Klasse DatabaseService wird erstellt um eine Verbindung mit der Datenbank herzustellen.
 * Hier sind alle Methoden abgelegt die zum Erstellen, Aendern oder Loeschen von Datenbankeintraegen notwendig sind
*/
public class SQLiteService implements Database {

    public static final String URL = "jdbc:sqlite:src/main/java/atdit_ibait_20/database/persistence/database.sqlite";

    /**
     * ew wird eine Verbindung zur Datenbank aufgebaut
     * @return die Verbindung zur Datenbank
     */
    public Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to Database established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Es werden die Befehle auf der Datenbank ausgefuehrt
     * @param sql der auszufuehrende Befehl
     * @param conn die Verbindung zur Datenbank
     */
    public void execute(String sql, Connection conn) {
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                System.out.println("Command successfully executed: " + sql);
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    /**
     * schliesst die Verbindung zur Datenbank
     * @param conn Verbindung welche geschlossen werden soll
     */
    public void close(Connection conn) {
        try {
            if (conn != null)
                conn.close();
            System.out.println("Connection to SQLite closed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Erstellt beim ersten Ausfuehren der App die Tabellen der Datenbank, falls diese noch nicht existieren
     */
    public void create_tables() {
        Connection conn = connect();
        String sql = """
                CREATE TABLE IF NOT EXISTS person (
                 id              text(12) PRIMARY KEY, \s
                 form_of_address text     NOT NULL,    \s
                 first_name      text     NOT NULL,    \s
                 last_name       text     NOT NULL,    \s
                 birth_date      DATE     NOT NULL,    \s
                 nationality     text     NOT NULL,    \s
                 marital_status  text     NOT NULL,    \s
                 zip_code        int(5)   NOT NULL,    \s
                 city            text     NOT NULL,    \s
                 street          text     NOT NULL,    \s
                 house_number    text     NOT NULL,    \s
                 email_address   text     NOT NULL,    \s
                 phone_number    long     NOT NULL,    \s
                 password        text     NOT NULL,    \s
                 salt            text     NOT NULL,    \s
                 label16            text(22)              \s
                );""";
        execute(sql, conn);

        sql = """
              CREATE TABLE IF NOT EXISTS contract (
                order_number text(12) PRIMARY KEY,             \s
                person_id    text(12)   NOT NULL,
                insurance_type text    NOT NULL,  \s
                booking_type    text    NOT NULL,  \s
                price           int     NOT NULL,  \s
                FOREIGN KEY (person_id) REFERENCES person(id) \s
                );""";
        execute(sql,conn);

        close(conn);
    }

    /**
     * E wird ein Eintrag einer Person auf der Datenbank erstellt
     * @param person Die zu erstellende Person
     */
    public void create_person_entry(Person person) {
        Connection conn = connect();

        String salt = getSalt();
        String securePassword = generateSecurePassword(person.getPasswort(),salt);


        String sql = "INSERT INTO person(id,form_of_address,first_name,last_name,birth_date,nationality,marital_status,zip_code,city,street,house_number,email_address,phone_number,password,salt) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getSozialversicherungsnummer());
            pstmt.setString(2, person.getAnrede());
            pstmt.setString(3, person.getVorname());
            pstmt.setString(4, person.getNachname());
            pstmt.setString(5, person.getGeburtsdatum());
            pstmt.setString(6, person.getStaatsangehoerigkeit());
            pstmt.setString(7, person.getFamilienstand());
            pstmt.setString(8, String.valueOf(person.getPLZ()));
            pstmt.setString(9, person.getOrt());
            pstmt.setString(10, person.getStrasse());
            pstmt.setString(11, person.getHausnummer());
            pstmt.setString(12, person.getMailAdresse());
            pstmt.setString(13, String.valueOf(person.getTelefonnummer()));
            pstmt.setString(14, securePassword);
            pstmt.setString(15, salt);
            pstmt.executeUpdate();
            System.out.println("Entry successfully added." + pstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close(conn);
    }

    /**
     * Es wird ein neuer Vertrag in der Datenbank erstellt
     * @param contract der zu erstellende Vertrag
     * @param person_id die ID der Person, welche den Vertrag abschliesst
     */
    public void create_contract_entry(Vertrag contract, String person_id) {
        Connection conn = connect();
        String sql = "INSERT INTO contract(order_number,person_id,insurance_type,booking_type,price) VALUES(?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contract.getAuftragsnummer());
            pstmt.setString(2, person_id);
            pstmt.setString(3, contract.getVersicherungsart());
            pstmt.setString(4, contract.getBuchungsart());
            pstmt.setInt(5, contract.getEURBetrag());
            pstmt.executeUpdate();
            System.out.println("Entry successfully added." + pstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close(conn);
    }

    /**
     * Gibt den Eintrag einer Person von der Datenbank anhand des Primaerschluessels ID zurueck
     * @param id Sozialversicherungsnummer der abgefragten Person
     * @return die Person, zu der Daten abgefragt wurden
     */
    public BasicPerson get_person_by_id(String id) {
        String sql = "SELECT id, form_of_address, first_name, last_name, birth_date, nationality, marital_status, zip_code, city, street, house_number, email_address, phone_number, label16 FROM person WHERE id = ?";
        Connection conn = connect();
        BasicPerson returnPerson = new BasicPerson();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                returnPerson.setSozialversicherungsnummer(rs.getString("id"));
                returnPerson.setAnrede(rs.getString("form_of_address"));
                returnPerson.setVorname(rs.getString("first_name"));
                returnPerson.setNachname(rs.getString("last_name"));
                returnPerson.setGeburtsdatum(new BasicGeburtsdatum(rs.getString("birth_date")));
                returnPerson.setStaatsangehoerigkeit(rs.getString("nationality"));
                returnPerson.setFamilienstand(rs.getString("marital_status"));
                returnPerson.setPLZ(rs.getInt("zip_code"));
                returnPerson.setOrt(rs.getString("city"));
                returnPerson.setStrasse(rs.getString("street"));
                returnPerson.setHausnummer(rs.getString("house_number"));
                returnPerson.setMailAdresse(rs.getString("email_address"));
                returnPerson.setTelefonnummer(rs.getInt("phone_number"));
                returnPerson.setIBAN(rs.getString("label16"));
            }
            System.out.println("Data for id: " + id + " successfully received.");
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    get_contract_by_id(returnPerson);
    return returnPerson;
    }

    /**
     * gibt die Vertraege zu einer Person anhand der ID zurueck
     * @param person Die Person zu der die Vertraege abgefragt werden
     */
    public void get_contract_by_id(Person person) {
        String sql = "SELECT order_number,person_id,insurance_type,booking_type,price FROM contract WHERE person_id = ?";
        try (PreparedStatement pstmt = connect().prepareStatement(sql)) {
            pstmt.setString(1,person.getSozialversicherungsnummer());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                person.addVertrag(new BasicVertrag(rs.getString("order_number"),
                        rs.getString("insurance_type"),
                        rs.getString("booking_type"),
                        rs.getInt("price")));
                System.out.println("Contract " + rs.getString("order_number") + " added.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("All contracts for id: " + person.getSozialversicherungsnummer() + " added.");
    }

    /**
     * Ermoegllicht die Aenderung von Eintraegen einer Person welche als String typisiert sind
     * @param id Sozialversicherungsnummer der Person
     * @param entry Spalte der Datenbanktabelle
     * @param value Wert welcher eingetragen werden soll
     */
    public void update_person_by_id( String id, String entry, String value) {
        String sql = "UPDATE person SET " + entry + "='" + value + "' WHERE id='" + id + "'";
        Connection conn = connect();
        execute(sql,conn);
    }
    /**
     * Ermoegllicht die Aenderung von Eintraegen einer Person welche als Integer typisiert sind
     * @param id Sozialversicherungsnummer der Person
     * @param entry Spalte der Datenbanktabelle
     * @param value Wert welcher eingetragen werden soll
     */
    public void update_person_by_id(String id, String entry, Integer value) {
        String sql = "UPDATE person SET " + entry + "=" + value + " WHERE id='" + id + "'";
        Connection conn = connect();
        execute(sql,conn);
    }
    /**
     * Ermoegllicht die Aenderung von Eintraegen einer Person welche als Long typisiert sind
     * @param id Sozialversicherungsnummer der Person
     * @param entry Spalte der Datenbanktabelle
     * @param value Wert welcher eingetragen werden soll
     */
    public void update_person_by_id(String id, String entry, Long value) {
        String sql = "UPDATE person SET " + entry + "=" + value + " WHERE id='" + id + "'";
        Connection conn = connect();
        execute(sql,conn);
    }

    /**
     * Loescht eine Person aus der Datenbank
     * @param id Sozialversicherungsnummer der zu loeschenden Person
     */
    public void delete_person_by_id(String id) {
        String sql = "DELETE FROM person WHERE id='" + id + "'";
        Connection conn = connect();
        execute(sql,conn);
    }

    /**
     * erlaubt das Aendern des Passwortes
     * @param id Sozialversicherungsnummer der Person, welche ihr Passwort aendert
     * @param password das neue Passwort welches eingetragen werden soll
     */
    public void update_password_by_id(String id, String password) {
        String salt = getSalt();
        String securePassword = generateSecurePassword(password,salt);

        update_person_by_id(id,"password",securePassword);
        update_person_by_id(id,"salt",salt);

    }

    /**
     * ueberprueft ob die eingegebene Sozialversicherungsnummer schon auf der Datenbank vorhanden ist
     * Dient zur Sicherung des Primaerschluessels
     * @param id Sozialversciherungsnummer, welche ueberprueft werden soll
     * @return true falls die ID noch nicht vergeben ist, false falls doch
     */
    @Override
    public boolean check_id(String id) {
        String sql = "SELECT (count(*) > 0) as found FROM person WHERE id=?";
        Connection conn = connect();
        boolean returnValue = false;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    boolean found = rs.getBoolean(1);
                    if (found) {
                        System.out.println("ID vergeben");
                        returnValue = false;
                    } else {
                        System.out.println("ID frei");
                        returnValue = true;
                    }

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }

    /**
     * ueberprueft die bei der Anmeldung uebergebenen Daten mit den Eintraegen auf der Datenbank
     * @param id Sozialversicherungsnummer der Person, welche sich anmeldet
     * @param providedPassword Passwort welches eingegeben wurde
     * @return true falls die Eitraege uebereinstimmen, false falls nicht
     */
    public boolean check_Login(String id, String providedPassword) {

        String salt = null;
        String securePassword = null;

    String sql = "SELECT password, salt FROM person WHERE id = ?";
    Connection conn = connect();
    try (PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            securePassword = rs.getString("password");
            salt = rs.getString("salt");
        }
        System.out.println("Password Data for id: " + id + " retrieved from database.");
    } catch (SQLException exp) {
        System.out.println(exp.getMessage());
    }

    return verifyUserPassword(providedPassword,securePassword,salt);
    }

    /**
     * ueberprueft ob die generierte Vertragsnummer noch nicht vergeben ist
     * @param orderNumber die generierte Vertragsnummer
     * @return true falls noch nicht vergeben, false falls doch
     */
    public boolean check_order_number(String orderNumber) {
        boolean returnValue = true;
        Connection conn = connect();
        String sql = "SELECT (count(*) > 0) as found FROM contract WHERE order_number=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,orderNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    boolean found = rs.getBoolean(1);
                    if (found) {
                        System.out.println("Auftragsnummer vergeben");
                        returnValue = false;
                    } else {
                        System.out.println("Auftragsnummer frei");
                        returnValue = true;
                    }
                    }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }


}
