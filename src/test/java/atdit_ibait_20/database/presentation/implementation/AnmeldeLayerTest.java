package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AnmeldeLayerTest{

/*  Der Test prüft auf die Konsolenausgabe
    Falls die Tests failen kann eine Änderung der Methode anmeldenCheck der Grund sein
    Um das Problem zu Lösen kann entweder die Änderung der Methode rückgängig geacht werden,
    die TestMethoden angepasst werden
    oder falls beides nicht möglich oder zu aufwändig ist können die Methoden durch @Disable oder durch
    @Ignore übersprungen werden*/


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    static String id = "DE0000000000";
    static String passwort = "xyz";
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
    static String anmeldePasswort;
    static String anmeldeName;
    String anmeldePasswortFalsch = "zyx";
    String anmeldeNameFalsch = "DE0000";

    AnmeldeLayer anmeldeLayer = new AnmeldeLayer();

    @BeforeAll
    public static void create () {
        BasicPerson person = new BasicPerson(id, "vorname", "nachname", geburtsdatum, passwort, anrede,
                plz, ort, hausnummer, familienstand, mail, telefonnr, staat, strasse);
        App.DATABASE.create_tables();
        App.DATABASE.create_person_entry(person);
       anmeldePasswort = person.getPasswort();
       anmeldeName = person.getSozialversicherungsnummer();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @AfterAll
    public static void deleteTestPerson() {
        App.DATABASE.delete_person_by_id(id);
    }

    @Test
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Differences in line indent on different OS")
    public void anmeldenCheckTestSuccess(){
        String a = "Connection to Database established\r\n";
        anmeldeLayer.anmeldenCheck(anmeldeName, anmeldePasswort);
        Assertions.assertEquals( a +
                "Password Data for id: DE0000000000 retrieved from database.\r\n" +
                "Connection to Database established\r\n" +
                "Data for id: DE0000000000 successfully received.\r\n" +
                "Connection to Database established\r\n" +
                "All contracts for id: DE0000000000 added.\r\n" +
                App.resourceBundle.getString("loginSuccess"),
                outputStreamCaptor.toString().trim());
    }

    @Test
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Differences in line indent on different OS")
    public void anmeldenCheckTestFailPassword(){
        anmeldeLayer.anmeldenCheck(anmeldeName, anmeldePasswortFalsch);
        Assertions.assertEquals("Connection to Database established\r\n" +
                "Password Data for id: " + anmeldeName+ " retrieved from database.\r\n" +
                App.resourceBundle.getString("wrongLogin"), outputStreamCaptor.toString().trim());
    }

    @Test
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Differences in line indent on different OS")
    public void anmeldenCheckTestFailID(){
        anmeldeLayer.anmeldenCheck(anmeldeNameFalsch, anmeldePasswort);
        Assertions.assertEquals("Connection to Database established\r\n" +
                "Password Data for id: " + anmeldeNameFalsch+ " retrieved from database.\r\n" +
                App.resourceBundle.getString("wrongLogin"), outputStreamCaptor.toString().trim());
    }

}