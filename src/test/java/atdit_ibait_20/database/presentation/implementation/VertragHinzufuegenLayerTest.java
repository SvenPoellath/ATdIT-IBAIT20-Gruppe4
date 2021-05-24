package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import org.junit.jupiter.api.*;

class VertragHinzufuegenLayerTest {

    static VertragHinzufuegenLayer Vertrag;
    static BasicPerson person;
    Object buchungsArtObjektMonat = App.resourceBundle.getString("monthly");
    Object buchungsArtObjektJahr = App.resourceBundle.getString("yearly");
    int ZweiTage = 2;
    int ExpectedZweiTage = (ZweiTage + 1)*50;
    int FuenfTage = 5;
    int ExpectedFuenfTage = (FuenfTage + 1)*50;
    String IBANPassend = "DE12345678901234567890";
    String IBANFalsch = "DE101010";

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

    @BeforeAll
    public static void setUp(){
        person = new BasicPerson(id, vorname, nachname, geburtsdatum, passwort, anrede,
                plz, ort, hausnummer, familienstand, mail, telefonnr, staat, strasse);
        App.DATABASE.create_tables();
        App.DATABASE.create_person_entry(person);
        Vertrag = new VertragHinzufuegenLayer(person);
    }

    @AfterAll
    public static void tearDown(){
        App.DATABASE.delete_person_by_id(id);
    }

    @Test
    public void setPreisTestMonat() {
        Assertions.assertEquals(300, Vertrag.setPreisForLuggageInsurance(buchungsArtObjektMonat));
    }

    @Test
    public void setPreisTestJahr() {
        Assertions.assertEquals(3000, Vertrag.setPreisForLuggageInsurance(buchungsArtObjektJahr));
    }

    @Test
    public void setPreisPerTripZweiTageTest(){
        Assertions.assertEquals(ExpectedZweiTage, Vertrag.setPreisForLuggageInsurancePerTrip(ZweiTage));
    }

    @Test
    public void setPreisPerTripFuenfTageTest(){
        Assertions.assertEquals(ExpectedFuenfTage, Vertrag.setPreisForLuggageInsurancePerTrip(FuenfTage));
    }
/*
    @Test
    @Order(1)
    public void eingegebeneIBANPruefenTestIBANFalsch() {
        Vertrag.eingegebeneIBANPruefen(IBANFalsch);
        Assertions.assertEquals(null,person.getIBAN());
    }*/

    @Test
    @Order(2)
    public void eingegebeneIBANPruefenTestIBANPassend() {
        Vertrag.eingegebeneIBANPruefen(IBANPassend);
        Assertions.assertEquals(IBANPassend,person.getIBAN());
    }



}