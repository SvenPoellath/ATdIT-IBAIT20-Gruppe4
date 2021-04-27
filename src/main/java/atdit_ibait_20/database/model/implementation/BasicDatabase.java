package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Database;
import atdit_ibait_20.database.model.Person;

import java.sql.*;
import java.util.*;

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
                System.out.println("Command successfully executed: " + sql);
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
            pstmt.setString(14, person.getPasswort());
            pstmt.setString(15, person.getGeburtsdatum());
            pstmt.executeUpdate();
            System.out.println("Entry successfully added." + pstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close(conn);
    }

}
