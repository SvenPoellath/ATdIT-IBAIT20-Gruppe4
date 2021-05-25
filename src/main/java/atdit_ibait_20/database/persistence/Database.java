package atdit_ibait_20.database.persistence;

import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.Vertrag;

/**
 * Das Interface dient zur einfachen Implementierung verschiedener Datenbank Services
 */
public interface Database {

    void create_tables();

    void create_person_entry(Person person);

    void create_contract_entry(Vertrag contract, String person_id);

    Person get_person_by_id(String id);

    void get_contract_by_id(Person person);

    void update_person_by_id(String id, String entry, String value);

    void update_person_by_id(String id, String entry, Integer value);

    void update_person_by_id(String id, String entry, Long value);

    void update_password_by_id(String id, String password);

    void delete_person_by_id(String id);

    boolean check_id(String id);

    boolean check_Login(String id, String providedPassword);

    boolean check_order_number(String orderNumber);

}
