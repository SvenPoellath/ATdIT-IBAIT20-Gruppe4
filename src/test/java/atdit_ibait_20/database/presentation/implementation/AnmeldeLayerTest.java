package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AnmeldeLayerTest{


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    String id = "DE0000000000";
    String passwort = "xyz";
    BasicGeburtsdatum geburtsdatum = new BasicGeburtsdatum(1, 1, 2000);
    String anrede = App.resourceBundle.getString("mrs");
    int plz = 12345;
    String ort = "Oberammergau";
    String hausnummer = "1";
    String familienstand = App.resourceBundle.getString("widowed");
    String mail = "abc";
    long telefonnr = 0123;
    String staat = "EN";
    String strasse = "cba";
    String anmeldePasswort;
    String anmeldeName;
    String anmeldePasswortFalsch = "zyx";
    String anmeldeNameFalsch = "DE0000";
    BasicPerson person = new BasicPerson(id, "vorname", "nachname", geburtsdatum, passwort, anrede,
            plz, ort, hausnummer, familienstand, mail, telefonnr, staat, strasse);
    AnmeldeLayer anmeldeLayer = new AnmeldeLayer();


    @BeforeEach
    public void create () {
        App.DATABASE.create_person_entry(person);
        anmeldePasswort = person.getPasswort();
        anmeldeName = person.getSozialversicherungsnummer();

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
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