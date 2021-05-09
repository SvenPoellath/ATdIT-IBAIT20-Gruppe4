package atdit_ibait_20.database.persistence;

import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import atdit_ibait_20.database.model.implementation.BasicVertrag;

import java.sql.*;

import static atdit_ibait_20.database.persistence.implementation.PasswordService.*;

public interface Database {

    static Connection connect() {
        return null;
    }

    static void execute(String sql, Connection conn) {

    }

    static void close(Connection conn) {

    }

    static void create_tables(Connection conn) {

    }

    static void create_person_entry(BasicPerson person) {

    }

    static void create_contract_entry(BasicVertrag contract, String person_id) {

    }

    static BasicPerson get_person_by_id(String id) {
        return null;
    }

    static void get_contract_by_id(BasicPerson person) {

    }

    static void update_person_by_id(String id, String entry, String value) {

    }

    static void update_person_by_id(String id, String entry, Integer value) {

    }

    static void update_person_by_id(String id, String entry, Long value) {

    }

    static void update_password_by_id(String id, String password) {

    }

    static boolean check_Login(String id, String providedPassword) {
        return false;
    }

    static boolean check_order_number(String orderNumber) {
        return false;
    }


}