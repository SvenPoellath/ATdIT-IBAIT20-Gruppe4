package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicVertrag;
import atdit_ibait_20.database.presentation.SwingPresentation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static atdit_ibait_20.database.App.DATABASE;

/**
 * Die Klasse legt das UI der Vertragsbuchungsseite fest
 * Sie beschreibt alle Ablaeufe zum Buchen eines Vertrages
 * Momentan ist nur die Gepaecksversicherung implementiert
 */
public class VertragHinzufuegenLayer implements SwingPresentation {
    static final int monatlicherPreis = 300;
    static final int jahresPreis =3000;

    static final JPanel vertragsDaten = new JPanel();
    static final JPanel hinzufuegen = new JPanel();
    static final JPanel preise = new JPanel();
    static final JPanel neueIBAN = new JPanel();
    static final JPanel hinzugefuegt = new JPanel();
    static final JPanel fehlerPanel  = new JPanel();

    private static String[] versicherungsArten;
    private static String[] buchungsArten;
    private static Integer[] tage;
    private static String[] laender;

    private static JComboBox <String> versicherungsArt;
    private static JComboBox <String> buchungsArt;
    private static JComboBox <Integer> anzahlDerTage;
    private static JComboBox <String> land;

    private static Integer tagesPreis;
    static int betrag;
    private final Person angemeldetePerson;

    /**
     * Konstruktor der Klasse VertragHinzufuegenLayer
     * laedt das UI des Vertragsbuchungsfensters
     * @param person die in der App angemeldete Person
     */
    public VertragHinzufuegenLayer(Person person){
        angemeldetePerson = person;
        tagesPreis=50;
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        MasterController.fenster.validate();
        MasterController.fenster.repaint();
    }

    static void setStrings(){
        label6.setText(App.resourceBundle.getString("payment.type"));
        label7.setText(App.resourceBundle.getString("days.of.visiting"));
        label8.setText(App.resourceBundle.getString("country"));
        label9.setText(monatlicherPreis+App.resourceBundle.getString("currency"));
        label10.setText(jahresPreis+App.resourceBundle.getString("currency"));
        label11.setText(App.resourceBundle.getString("noPrice"));
        label12.setText((App.resourceBundle.getString("noInsuranceSelected")));
        label13.setText(App.resourceBundle.getString("failed.to.add.IBAN"));
        button7.setText(App.resourceBundle.getString("add.contract"));
        button8.setText(App.resourceBundle.getString("get.price"));
        button9.setText(App.resourceBundle.getString("add.iban"));
        versicherungsArten = new String[]{"*",App.resourceBundle.getString("luggage.insurance")};
        label14.setText(App.resourceBundle.getString("insurance.type"));
        label15.setText(App.resourceBundle.getString("successfully.added.new.contract"));
        label16.setText("IBAN");
        buchungsArten = new String[]{"*",App.resourceBundle.getString("monthly"),App.resourceBundle.getString("yearly"),App.resourceBundle.getString("per.trip")};
        tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        laender = new String[]{App.resourceBundle.getString("germany"),App.resourceBundle.getString("usa"),App.resourceBundle.getString("portugal")};
        anzahlDerTage = new JComboBox<>(tage);
        land = new JComboBox<>(laender);
        versicherungsArt = new JComboBox<>(versicherungsArten);
        buchungsArt = new JComboBox<>(buchungsArten);
    }

    @Override
    public void setLayout() {
        vertragsDaten.setLayout(new GridLayout(0,2));
        neueIBAN.setLayout(new GridLayout(0,2));
        preise.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        label12.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
    }

    @Override
    public void addListeners() {
        removeListeners();
        buchungsArt.addItemListener(e->this.buchungsArtWurdeGeaendert(e));
        versicherungsArt.addItemListener(e->this.versicherungsArtWurdeGeaendert());
        button7.addActionListener(e->this.hinzufuegeButtonWurdeGedrueckt());
        button8.addActionListener(e->this.preisButtonWurdeGedrueckt());
        button9.addActionListener(e->this.IBANButtonWurdeGedrueckt());
    }

    public void removeListeners() {
        for (ItemListener listener : buchungsArt.getItemListeners())
            buchungsArt.removeItemListener(listener);

        for (ItemListener listener : versicherungsArt.getItemListeners())
            versicherungsArt.removeItemListener(listener);

        for (ActionListener listener : button7.getActionListeners())
            button7.removeActionListener(listener);

        for (ActionListener listener : button8.getActionListeners())
            button8.removeActionListener(listener);

        for (ActionListener listener : button9.getActionListeners())
            button9.removeActionListener(listener);
    }

    @Override
    public void addComponentsToPanels() {
        vertragsDaten.removeAll();
        hinzufuegen.removeAll();
        preise.removeAll();
        neueIBAN.removeAll();
        hinzugefuegt.removeAll();
        vertragsDaten.add(label14);
        vertragsDaten.add(versicherungsArt);
        hinzufuegen.add(button8);
        neueIBAN.add(label16);
        neueIBAN.add(textField2);
        neueIBAN.add(button9);
        hinzugefuegt.add(label15);
    }

    @Override
    public void addPanelsToFrame() {
        MasterController.fenster.add(vertragsDaten);
        MasterController.fenster.add(preise);
        MasterController.fenster.add(hinzufuegen);
        MasterController.fenster.add(fehlerPanel);
    }


        public void removePanelsFromFrame() {
            MasterController.fenster.remove(vertragsDaten);
            MasterController.fenster.remove(preise);
            MasterController.fenster.remove(hinzufuegen);
            MasterController.fenster.remove(fehlerPanel);
        }

    /**
     * ueberprueft ob die Eingaben stimmen, d.h. das fuer jede ComboBox eine Auswahl getroffen wurde
     * ruft die Methode setPreis() zum berechnen des Preises auf
     */
    public void preisButtonWurdeGedrueckt(){
        preise.removeAll();
        eingabenStimmen();
        setPreis();
        hinzufuegen.add(button7);
        MasterController.fenster.validate();
    }

    /**
     * ueberprueft die Eingaben
     * wenn diese stimmen wird die Seite neu geladen, damit wenn gewuenscht ein weiterer Vertrag gebucht werden kann
     */
    public void hinzufuegeButtonWurdeGedrueckt(){
        if(eingabenStimmen()){
            neuerVertragHinzufuegen();
        }
    }

    /**
     * ruft eine weitere Methode auf um zu ueberpruefen ob die eingegebene IBAN zulaessig ist
     */
    public void IBANButtonWurdeGedrueckt(){
        String IBAN = textField2.getText();
        eingegebeneIBANPruefen(IBAN);
        MasterController.fenster.validate();
    }
    /**
    * wenn eine Versicherungsart ausgewaehlt wird, werden die Buchungsartoptionen angezeigt
    */
    public void versicherungsArtWurdeGeaendert(){
        displayBuchungsartOptionen();
        MasterController.fenster.validate();
    }

    /**
     * Falls die Option "nur fuer eine Reise" ausgewaehlt wurde werden weitere UI Elemente geladen
     * um weitere Informatinen zu der Reise eingeben zu koennen
     * Falls eine adere Option gewaehlt wurde werden diese Angaben wieder entfernt
     * @param e gibt an welche Buchungsoption gewaehlt wurde
     */
    public void buchungsArtWurdeGeaendert(ItemEvent e){
        hideAngabenZurReise();
        MasterController.fenster.validate();
        if(e.getItem().equals(App.resourceBundle.getString("per.trip"))) {
            displayAngabenZurReise();
            MasterController.fenster.setSize(600,400);
            MasterController.fenster.validate();
        }
    }

    /**
     * zeigt nach Auswahltder Versicherungsart die ComboBox zur Auswahl der Buchungsart Optionen an
     */
    public void displayBuchungsartOptionen(){
        vertragsDaten.add(label6);
        vertragsDaten.add(buchungsArt);
    }

    /**
     * zeigt weitere UI Elemente um die Angaben zu der fuer die Versicherung relevante machen zu koennen
     */
    public void displayAngabenZurReise(){
        vertragsDaten.add(label7);
        vertragsDaten.add(anzahlDerTage);
        vertragsDaten.add(label8);
        vertragsDaten.add(land);
    }

    /**
     * entfernt die Angaben zu der fuer die Versicherung relevante Reise
     */
    public void hideAngabenZurReise(){
        vertragsDaten.remove(label7);
        vertragsDaten.remove(anzahlDerTage);
        vertragsDaten.remove(label8);
        vertragsDaten.remove(land);
    }

    /**
     * je nach ausgewaehlter Versicherungsart wird die entsprechende Methode zur Preisberechnung aufgerufen
     */
    public void setPreis(){
        if(versicherungsArt.getSelectedItem().equals(App.resourceBundle.getString("luggage.insurance"))){
            Object buchungsArtObjekt = buchungsArt.getSelectedItem();
                betrag = setPreisForLuggageInsurance(buchungsArtObjekt);
                eingabenStimmen();
        }
    }

    /**
     * berechnet den Preis fuer die Gepaecksversicherung je nach ausgewaehlter Buchungsart
     * @param buchungsArtObjekt gibt an, welche Buchungsart ausgewaehlt wurde
     * @return den berechneten Preis fuer die Gepaecksversicherung
     */
    public int setPreisForLuggageInsurance(Object buchungsArtObjekt){
        if(buchungsArtObjekt.equals(App.resourceBundle.getString("monthly"))) {
            preise.add(label9);
            return monatlicherPreis;
        }
        else if(buchungsArtObjekt.equals(App.resourceBundle.getString("yearly"))) {
            preise.add(label10);
            return jahresPreis;
        }
        else if(buchungsArtObjekt.equals(App.resourceBundle.getString("per.trip"))){
            int anzahlDerTageObjekt = anzahlDerTage.getSelectedIndex();
            tagesPreis = setPreisForLuggageInsurancePerTrip(anzahlDerTageObjekt);
            JLabel preisEinmalig = new JLabel(tagesPreis + App.resourceBundle.getString("currency"));
            preise.add(preisEinmalig);
            return tagesPreis;
        }
        else{
            return 0;
        }
    }

    /**
     * wird aufgerufen wenn bei der Gepaecksversicherung "nur fuer eine Reise" ausgewaehlt wurde
     * berechnet aus der Anzahl der Tage fuer die verreist wird den Preis der Versicherung
     * @param anzahlDerTageObjekt gibt mit, wie viele Tage verreist wird
     * @return den Peis fuer die Gepackversicherung wenn "nur fuer eine Reise" gewaehlt wurde
     */
    public int setPreisForLuggageInsurancePerTrip(int anzahlDerTageObjekt){
        tagesPreis = (anzahlDerTageObjekt + 1)*50;
        return tagesPreis;
    }


    /**
     * ueberpruft ob die eingaben Stimmen, d.h. dass fuer sowohl Versicherungsart als auch Buchungsart ausgewaehlt wurden
     * @return true wenn alle Angaben gemacht wurden, ansonsten false
     */
    public boolean eingabenStimmen(){
        boolean angabenStimmen;
        fehlerPanel.removeAll();

        if (versicherungsArt.getSelectedItem() == "*") {
            fehlerPanel.add(label12);
            angabenStimmen = false;
        }
        else if (betrag == 0) {
            fehlerPanel.add(label11);
            angabenStimmen = false;
        }else if(angemeldetePerson.getIBAN()==null){
            displayIBANHinzufuegePanel();
            angabenStimmen = false;
        }
        else{
            angabenStimmen = true;
        }

        MasterController.fenster.validate();
        return angabenStimmen;
    }

    /**
     * wird angezeigt falls fuer die Person noch keine IBAN hinterlegt ist
     */
    public void displayIBANHinzufuegePanel(){
        MasterController.fenster.remove(hinzufuegen);
        MasterController.fenster.add(neueIBAN);
        MasterController.fenster.add(hinzufuegen);
        MasterController.fenster.validate();
    }

    /**
     * erstellt einen neuen Vertrag un der Datenbank
     */
    public void neuerVertragHinzufuegen(){
        System.out.println("Contract added.");
        BasicVertrag vertrag = new BasicVertrag(versicherungsArt.getSelectedItem().toString(),
                buchungsArt.getSelectedItem().toString(),betrag);
        DATABASE.create_contract_entry(vertrag,angemeldetePerson.getSozialversicherungsnummer());
        angemeldetePerson.addVertrag(vertrag);
        MasterController.fenster.add(hinzugefuegt);
        MasterController.fenster.validate();
    }

    /**
     * ueberprueft ob die eingegebene IBAN zulaessig ist, d.h. dass sie 22 Zeichen hat
     * ansonsten wird ein Fehler ausgegeben
     * @param IBAN die eingegebene IBAN
     */
    public void eingegebeneIBANPruefen(String IBAN){
        if(IBAN.length()==22) {
            angemeldetePerson.setIBAN(IBAN);
            DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                    "label16", angemeldetePerson.getIBAN());
            neueIBAN.remove(label13);
            MasterController.fenster.remove(neueIBAN);
            MasterController.fenster.validate();
        } else{
            neueIBAN.add(label13);
            MasterController.fenster.validate();
        }
    }
}
