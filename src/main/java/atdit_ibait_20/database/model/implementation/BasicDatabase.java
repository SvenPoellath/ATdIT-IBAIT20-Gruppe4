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

        return conn;
    }

    public static void execute(String sql) {
        Connection conn = connect();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                System.out.println("Command successfully executed: " + sql);
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        close(conn);
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

    public static void create_tables() {
        String sql = """
                CREATE TABLE IF NOT EXISTS person (
                 id         text(12) PRIMARY KEY, \s
                 first_name text     NOT NULL,    \s
                 last_name  text     NOT NULL,    \s
                 birth_date DATE     NOT NULL     \s
                );""";
        execute(sql);
    }

    public static void create_person_entry(BasicPerson person) {
        Connection conn = connect();

        String sql = "INSERT INTO person(id,first_name,last_name,birth_date) VALUES(?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getSozialversicherungsnummer());
            pstmt.setString(2, person.getVorname());
            pstmt.setString(3, person.getNachname());
            pstmt.setString(4, person.getGeburtsdatum());
            pstmt.executeUpdate();
            System.out.println("Entry successfully added." + pstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close(conn);
    }

    public static void main(String[] args) {
        create_tables();

    }
}
