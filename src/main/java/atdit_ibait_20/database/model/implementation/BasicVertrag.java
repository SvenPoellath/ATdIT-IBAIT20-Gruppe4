package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Vertrag;
import atdit_ibait_20.database.persistence.implementation.DatabaseService;

import java.security.SecureRandom;
import java.util.Random;

public class BasicVertrag implements Vertrag {
    private final String Versicherungsart;
    private final String Auftragsnummer;
    private final String Buchungsart;
    private final int EURBetrag;

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


    public BasicVertrag(String versicherungsart,String buchungsart, int eurBetrag) {
        Versicherungsart = versicherungsart;
        String auftragsNummer;
        do {
            auftragsNummer = generateAuftragsnummer();
        } while (!(DatabaseService.check_order_number(auftragsNummer)));
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
