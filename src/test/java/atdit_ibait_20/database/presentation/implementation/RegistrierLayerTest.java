package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrierLayerTest {

    static RegistrierLayer registrierLayer;
    static String id = "DE0000000000";
    static String passwort = "xyz";
    static String vorname = "vorname";
    static String nachname = "nachname";
    static BasicGeburtsdatum geburtsdatum = new BasicGeburtsdatum(1, 1, 2000);
    static String anrede = App.resourceBundle.getString("mrs");
    static int plz = 12345;
    static String ort = "Oberammergau";
    static String hausnummer = "1";
    static String familienstand = App.resourceBundle.getString("widowed");
    static String mail = "abc";
    static long telefonnr = 0123;
    static String staat = "EN";
    static String strasse = "cba";
    BasicPerson person = (BasicPerson) App.DATABASE.get_person_by_id(id);
    String geburtsdatumReturnForm = "2000-1-1";

    @BeforeAll
    public static void setUp() {
        App.DATABASE.create_tables();
        registrierLayer = new RegistrierLayer();
    }

    @BeforeEach
    public void registrieren(){
        registrierLayer.registrieren(id,
                vorname,
                nachname,
                geburtsdatum,
                passwort,
                anrede,
                plz,
                ort,
                hausnummer,
                familienstand,
                mail,
                telefonnr,
                staat,
                strasse);
        person = (BasicPerson) App.DATABASE.get_person_by_id(id);
    }

    @Test
    public void regstrierenTest(){
        Assertions.assertEquals(id, person.getSozialversicherungsnummer());
        Assertions.assertEquals(vorname, person.getVorname());
        Assertions.assertEquals(nachname, person.getNachname());
        Assertions.assertEquals(geburtsdatumReturnForm, person.getGeburtsdatum());
        //Assertions.assertEquals(label3, person.getPasswort());
        Assertions.assertEquals(anrede, person.getAnrede());
        Assertions.assertEquals(plz, person.getPLZ());
        Assertions.assertEquals(ort, person.getOrt());
        Assertions.assertEquals(hausnummer, person.getHausnummer());
        Assertions.assertEquals(familienstand, person.getFamilienstand());
        Assertions.assertEquals(mail, person.getMailAdresse());
        Assertions.assertEquals(telefonnr, person.getTelefonnummer());
        Assertions.assertEquals(staat, person.getStaatsangehoerigkeit());
        Assertions.assertEquals(strasse, person.getStrasse());
    }
}