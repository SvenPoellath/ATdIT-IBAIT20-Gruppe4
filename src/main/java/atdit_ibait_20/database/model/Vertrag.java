package atdit_ibait_20.database.model;

/**
 * Interface fuer die Vertraege
 */
public interface Vertrag {
    /**
     *
     * @return die Versicherungsart des Vertrages
     */
    public String getVersicherungsart();

    /**
     *
     * @return die generierte Auftragsnummer des Vertrages
     */
    public String getAuftragsnummer();

    /**
     *
     * @return die Buchungsart des Vertrages
     */
    public String getBuchungsart();

    /**
     *
     * @return den Preis der Vertrages
     */
    public int getEURBetrag();
}
