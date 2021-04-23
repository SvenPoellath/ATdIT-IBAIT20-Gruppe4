package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Vertrag;

public class BasicVertrag implements Vertrag {
    private final String Versicherungsart;
    private final int Auftragsnummer;
    private final String Buchungsart;
    private final int EURBetrag;

    public BasicVertrag(String versicherungsart, int versicherungsnummer, String buchungsart, int eurBetrag) {
        Versicherungsart = versicherungsart;
        Auftragsnummer = versicherungsnummer;
        Buchungsart = buchungsart;
        EURBetrag = eurBetrag;
    }

    @Override
    public String getVersicherungsart() {
        return this.Versicherungsart;
    }

    @Override
    public int getVersicherungsnummer() {
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
