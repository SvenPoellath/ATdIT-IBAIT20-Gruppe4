package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Database;
import atdit_ibait_20.database.model.Person;

import java.sql.*;
import java.util.*;

import static atdit_ibait_20.database.persistence.implementation.PasswordService.*;

public class BasicDatabase extends ArrayList<Person> implements Database {

    public static final String URL = "jdbc:sqlite:test.db";

    public static Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to Database established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        create_tables(conn);

        return conn;
    }

    public static void execute(String sql, Connection conn) {
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                System.out.println("Command successfully executed: create_tables");
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    public static void close(Connection conn) {
        try {
            if (conn != null)
                conn.close();
            System.out.println("Connection to SQLite closed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void create_tables(Connection conn) {
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
                 salt            text     NOT NULL     \s
                );""";
        execute(sql, conn);
    }

    public static void create_person_entry(BasicPerson person) {
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

    public static BasicPerson get_person_by_id(String id) {
        String sql = "SELECT id, form_of_address, first_name, last_name, birth_date, nationality, marital_status, zip_code, city, street, house_number, email_address, phone_number FROM person WHERE id = ?";
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

            }
            System.out.println("Data for id: " + id + " successfully received.");
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    return returnPerson;
    }

    public static boolean check_Login(String id, String providedPassword) {

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
}