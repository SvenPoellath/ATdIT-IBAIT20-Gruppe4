package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Vertrag;
import atdit_ibait_20.database.persistence.Database;

import java.security.SecureRandom;
import java.util.Random;

import static atdit_ibait_20.database.App.DATABASE;

/**
* Es wird eine Klasse BasicVertrag angelegt, die alle Attribute aus der Datenbank von Vertrag implementiert
*/
public class BasicVertrag implements Vertrag {
    private final String Versicherungsart;
    private final String Auftragsnummer;
    private final String Buchungsart;
    private final int EURBetrag;

    /**
     * es wird fuer den jeweiligen VErtrag eine Auftragsnummer generiert, welche einziartig ist
     * @return die einzigartige, generierte Auftragsnummer
     */
    public String generateAuftragsnummer() {
        int LENGTH = 12;
        int ITERATIONS = 1;
        int KEY_LENGTH = 256;

        Random RANDOM = new SecureRandom();
        String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder auftragsnummer = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++)
            auftragsnummer.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

        return new String (auftragsnummer);

    }

/**
* Ein Konstruktor für den Vertrag wird angelegt in den dem Vertrag die zufällig generierte Auftragsnummer sowie Buchungsart, Auftragsnummer und EURBetrag übergeben werden
*/
    public BasicVertrag(String versicherungsart,String buchungsart, int eurBetrag) {
        Versicherungsart = versicherungsart;
        String auftragsNummer;
        do {
            auftragsNummer = generateAuftragsnummer();
        } while (!(DATABASE.check_order_number(auftragsNummer)));
        Auftragsnummer = auftragsNummer;
        Buchungsart = buchungsart;
        EURBetrag = eurBetrag;
    }

    public BasicVertrag(String auftragsNummer,String versicherungsart,String buchungsart, int eurBetrag) {
        Versicherungsart = versicherungsart;
        Auftragsnummer = auftragsNummer;
        Buchungsart = buchungsart;
        EURBetrag = eurBetrag;
    }
/**
* @Method getter ermöglicht das Zugreifen auf die Parameter von außerhalb der Klasse
* @Param Versicherungsart
* @Param Buchungsart
* @Param EURBetrag
*/
    @Override
    public String getVersicherungsart() {
        return this.Versicherungsart;
    }

    @Override
    public String getAuftragsnummer() {
        return this.Auftragsnummer;
    }

    @Override
    public String getBuchungsart() {
        return this.Buchungsart;
    }

    @Override
    public int getEURBetrag() {
        return this.EURBetrag;
    }
}
