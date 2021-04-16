package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.AccountingData;

public class BasicAccountingData implements AccountingData {
    private final String Versicherungsart;
    private final int Versicherungsnummer;
    private final String Buchungsart;
    private final int EURBetrag;

    public BasicAccountingData(String versicherungsart, int versicherungsnummer, String buchungsart, int eurBetrag) {
        Versicherungsart = versicherungsart;
        Versicherungsnummer = versicherungsnummer;
        Buchungsart = buchungsart;
        EURBetrag = eurBetrag;
    }

    @Override
    public String getVersicherungsart() {
        return this.Versicherungsart;
    }

    @Override
    public int getVersicherungsnummer() {
        return this.Versicherungsnummer;
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
