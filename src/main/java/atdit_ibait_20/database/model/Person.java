package atdit_ibait_20.database.model;

public interface Person {
    public int getSozialversicherungsnummer();
    public String getVorname();
    public String getNachname();
    public String getGeburtsdatum();
    public AccountingData getAccountingData();
}
