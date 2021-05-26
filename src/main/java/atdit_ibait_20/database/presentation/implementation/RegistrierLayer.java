package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Year;

import static atdit_ibait_20.database.App.DATABASE;

/**
* Das UI fuer das Registrierfenster wird geladen
 * Hier kann sich eine Person fuer die App registrieren
*/
public class RegistrierLayer implements SwingPresentation {

    /**
     * Hier werden die UI Elemente fuer die Dateneingabe angezeigt, ausser dem Geburtsdatum
     */
    private static final JPanel datenPanel = new JPanel();
    /**
     * Zum Anzeigen des Geburtsdatums
     */
    private static final JPanel geburtsDatumsPanel = new JPanel();
    /**
     * Zum Anzeigen des Zurueck-Knopfes
     */
    private static final JPanel zurueckPanel = new JPanel();
    /**
     * Zum Anzeigen des "Registrieren" Knopfes
     */
    private static final JPanel registrierButtonPanel = new JPanel();
    /**
     * Hierauf werden Fehlermeldungen angezeigt wenn eine Eingabe nicht korrekt war
     */
    private static final JPanel falscheAngabePanel = new JPanel();

    /**
     * Feld zum eingeben des Passwortes,
     * die eingegebenen Zeichen sind nicht lesbar sondern werden als Punkte angezeigt
     */
    private static final JPasswordField tfRegistrierPasswort = new JPasswordField();

    /**
     * Array zum speichern der Strings fuer die Anrede-ComboBox
     */
    private static String[] anredeAuswahl = new String[]{ "*",App.resourceBundle.getString("mister"), App.resourceBundle.getString("mrs")};
    /**
     * Array zum Abspericher der Tage fuer die ComboBox
     */
    private static Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    /**
     * Array zum Abspeichern der Monate fuer die ComboBox
     */
    private static Integer[] monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
    /**
     * Array zum Abspeichern der Jahre fuer die CombBox
     * Die Länge wird aus dem aktuellen Jahr berechnet, sodass ab 1900 alle Jahre angezeigt werden
     */
    private static Integer[] jahre = new Integer[(Year.now().getValue()-1899)];
    /**
     * Array zum Abspeichern der Familienstand-Moeglichkeiten fuer die ComboBox
     */
    private static String [] familienstandArten = new String[]{App.resourceBundle.getString("single"),App.resourceBundle.getString("married"),App.resourceBundle.getString("divorced"),App.resourceBundle.getString("widowed")};

    /**
    Fuer die Anrede
     */
    static JComboBox<String> cbAnrede = new JComboBox<>(anredeAuswahl);
    /**
     * Fuer die Auswahl des Tages des Geburtsdatums
     */
    static JComboBox<Integer> GeburtsdatumTag = new JComboBox<>(tage);
    /**
     * Fuer die Auswahl des Monats des Geburtsdatums
     */
    static JComboBox<Integer> GeburtsdatumMonat = new JComboBox<>(monate);
    /**
     * Fuer die Auswahl des Jahres des Geburtsdatums
     */
    static JComboBox<Integer> GeburtsdatumJahr = new JComboBox<>(jahre);
    /**
     * Fuer die Auswahl des Familienstandes
     */
    static JComboBox<String> cbFamilienstand = new JComboBox<>(familienstandArten);

    /**
     * gibt an, ob das Fenster davor schon einmal aufgerufen wurde
     * als Information ob setStrings aufgerufen werden muss oder nicht
     */
    static boolean istErsterAufruf = true;

    /**
     * Konstruktor der Klasse RegistrierLayer
     * Setzt das UI des Registrierfensters
     * Falls das Fenster schon einmal aufgerufen wurde werden die Texte der UI Elemente nicht noch einmal gesetzt
     */
    public RegistrierLayer(){
        if(istErsterAufruf){
            setStrings();
            istErsterAufruf = false;
        }
            setLayout();
            addListeners();
            addComponentsToPanels();
            addPanelsToFrame();
        MasterController.fenster.validate();
        MasterController.fenster.repaint();
    }
    /**
* @method legt die dem Nutzer angezeigten Namen der einzelnen Felder fest sowie die Auswahloptionen des Geburtsdatums
*/
    static void setStrings(){
        label2.setText(App.resourceBundle.getString("title"));
        label3.setText(App.resourceBundle.getString("first.name"));
        label4.setText(App.resourceBundle.getString("last.name"));
        label5.setText(App.resourceBundle.getString("nationality"));
        label6.setText(App.resourceBundle.getString("marital.status"));
        label7.setText(App.resourceBundle.getString("postcode"));
        label8.setText(App.resourceBundle.getString("city"));
        label9.setText(App.resourceBundle.getString("street"));
        label10.setText(App.resourceBundle.getString("house.number"));
        label11.setText(App.resourceBundle.getString("e.mail.address"));
        label12.setText(App.resourceBundle.getString("phone.number"));
        label13.setText(App.resourceBundle.getString("social.security.number"));
        label14.setText(App.resourceBundle.getString("password"));
        label15.setText(App.resourceBundle.getString("date.of.birth"));
        button5.setText(App.resourceBundle.getString("register"));
        button4.setText("<--");
        for(int JahreCounter = 1900; JahreCounter <= Year.now().getValue(); JahreCounter++){
            jahre[JahreCounter-1900] = JahreCounter;
        }
        anredeAuswahl = new String[]{ "*",App.resourceBundle.getString("mister"), App.resourceBundle.getString("mrs")};
        familienstandArten = new String[]{App.resourceBundle.getString("single"),App.resourceBundle.getString("married"),App.resourceBundle.getString("divorced"),App.resourceBundle.getString("widowed")};
        cbAnrede  = new JComboBox<>(anredeAuswahl);
        GeburtsdatumTag  = new JComboBox<>(tage);
        GeburtsdatumMonat = new JComboBox<>(monate);
        GeburtsdatumJahr = new JComboBox<>(jahre);
        cbFamilienstand = new JComboBox<>(familienstandArten);
    }

/**
* @method legt fest wie das Layout beim Registrieren aussieht
*/
    @Override
    public void setLayout() {
        zurueckPanel.setLayout(new FlowLayout());
        datenPanel.setLayout(new GridLayout(0,2));
        geburtsDatumsPanel.setLayout(new GridLayout(0,3));
        registrierButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));

    }

/**
* @method legt fest welche Funktionen der zurück und der registrieren Button haben
*/
    @Override
    public void addListeners() {
        removeListeners();
        button4.addActionListener(e->zurueckButtonWurdeGedrueckt());
        button5.addActionListener(e->registerierButtonWurdeGedrueckt());
    }

    public void removeListeners() {
        for (ActionListener listener : button4.getActionListeners())
            button4.removeActionListener(listener);

        for (ActionListener listener : button5.getActionListeners())
            button5.removeActionListener(listener);
    }


/**
* @method fügt die Komponenten ins Panel ein
*/
    @Override
    public void addComponentsToPanels() {
        datenPanel.removeAll();
        geburtsDatumsPanel.removeAll();
        registrierButtonPanel.add(button5);
        datenPanel.add(label2);
        datenPanel.add(cbAnrede);
        datenPanel.add(label3);
        datenPanel.add(textField1);
        datenPanel.add(label4);
        datenPanel.add(textField2);
        datenPanel.add(label5);
        datenPanel.add(textField10);
        datenPanel.add(label6);
        datenPanel.add(cbFamilienstand);
        datenPanel.add(label7);
        datenPanel.add(textField4);
        datenPanel.add(label8);
        datenPanel.add(textField5);
        datenPanel.add(label9);
        datenPanel.add(textField6);
        datenPanel.add(label10);
        datenPanel.add(textField7);
        datenPanel.add(label11);
        datenPanel.add(textField8);
        datenPanel.add(label12);
        datenPanel.add(textField9);
        datenPanel.add(label13);
        datenPanel.add(textField3);
        datenPanel.add(label14);
        datenPanel.add(tfRegistrierPasswort);
        datenPanel.add(label15);
        geburtsDatumsPanel.add(GeburtsdatumTag);
        geburtsDatumsPanel.add(GeburtsdatumMonat);
        geburtsDatumsPanel.add(GeburtsdatumJahr);
        zurueckPanel.add(button4);
        falscheAngabePanel.add(label1);
    }

/**
* @method fügt die Panels dem Frame hinzu
*/
    @Override
    public void addPanelsToFrame() {
        MasterController.fenster.add(zurueckPanel);
        MasterController.fenster.add(datenPanel);
        MasterController.fenster.add(geburtsDatumsPanel);
        MasterController.fenster.add(registrierButtonPanel);
    }
/**
* Es wird ueberprueft ob alle Felder gefuellt sind, falls Eines leer ist wird eine entsprechende Fehlermeldung
 * auf dem UI angezeigt
 * Sonderueberpruefungen:
 * PLZ darf nur Zahlen enthalten
 * Die Telefonnummer darf nur Zahlen enthalten
 * Die Sozialversicherungsnummer muss 12 Zeichen haben
 * Es wird ueberprueft ob die Sozialversicherungsnummer schon in der Datenbank vorhanden ist
 * @return Ein Boolean welcher angibt ob die Eingaben korrekt sind
*/
    boolean checkInputs(){
        label1.setText(null);
        String invalid_m = App.resourceBundle.getString("invalid.m");
        String invalid_f = App.resourceBundle.getString("invalid.f");
        String invalid_it = App.resourceBundle.getString("invalid.it");
        textField1.setBorder(null);
        textField2.setBorder(null);
        textField3.setBorder(null);
        textField4.setBorder(null);
        textField5.setBorder(null);
        textField6.setBorder(null);
        textField7.setBorder(null);
        textField8.setBorder(null);
        textField9.setBorder(null);
        textField10.setBorder(null);
        tfRegistrierPasswort.setBorder(null);
        if (new String(tfRegistrierPasswort.getPassword()).length() == 0){
            label1.setText(invalid_it+" "+App.resourceBundle.getString("password"));
        }
        //ID
        if(textField3.getText().length()!=12){
            label1.setText(invalid_f+" "+App.resourceBundle.getString("social.security.number"));
            textField3.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        else if(!DATABASE.check_id(textField3.getText())) {
            label1.setText(App.resourceBundle.getString("social.security.number") + " " + App.resourceBundle.getString("is.taken"));
            textField3.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //phone number
        if(textField9.getText().length() != 0) {
            try {
                Long.parseLong(textField9.getText());
            } catch (NumberFormatException nfe) {
                label1.setText(invalid_f + " " + App.resourceBundle.getString("phone.number"));
                textField9.setBorder(BorderFactory.createLineBorder(Color.red,1));
            }
        }
        else {
            label1.setText(invalid_f + " " + App.resourceBundle.getString("phone.number"));
            textField9.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //mail address
        if (textField8.getText().length() == 0){
            label1.setText(invalid_f + " " + App.resourceBundle.getString("e.mail.address"));
            textField8.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //house number
        if(textField7.getText().length() == 0){
            label1.setText(invalid_f+" "+App.resourceBundle.getString("house.number"));
            textField7.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //street
        if(textField6.getText().length() == 0){
            label1.setText(invalid_f+" "+App.resourceBundle.getString("street"));
            textField6.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //city
        if(textField5.getText().length() == 0){
            label1.setText(invalid_f+" "+App.resourceBundle.getString("city"));
            textField5.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //postcode
        if (textField4.getText().length() != 0){
            try{
                Integer.parseInt(textField4.getText());
            }catch (NumberFormatException nfe){
                label1.setText(invalid_f+" "+App.resourceBundle.getString("postcode"));
                textField4.setBorder(BorderFactory.createLineBorder(Color.red,1));
            }
        }
        else if (textField4.getText().length() != 5) {
            label1.setText(invalid_f+" "+App.resourceBundle.getString("postcode"));
            textField4.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //nationality
        if(textField10.getText().length() == 0){
            label1.setText(invalid_f+" "+App.resourceBundle.getString("nationality"));
            textField10.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //surname
        if(textField2.getText().length() == 0){
            label1.setText(invalid_m+ " "+App.resourceBundle.getString("surname"));
            textField2.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //forename
        if(textField1.getText().length() == 0){
            label1.setText(invalid_m+ " "+App.resourceBundle.getString("forename"));
            textField1.setBorder(BorderFactory.createLineBorder(Color.red,1));
        }
        //title
        if(cbAnrede.getSelectedItem() == "*"){
            label1.setText((invalid_f+ " "+ App.resourceBundle.getString("title")));
        }

        if (label1.getText()!=null){
            MasterController.fenster.add(falscheAngabePanel);
            MasterController.fenster.repaint();
            MasterController.fenster.validate();
            return false;
        }
        else{
            return true;
        }
    }
    /**
* Zum Zurueckkehren auf die Startseite
*/
    void zurueckButtonWurdeGedrueckt(){
        removePanelsFromFrame();
        new StartLayer();
    }
    /**
     * Zuerst wird ueberprueft ob die Angaben vollstaendig sind
     * Falls ja wird mit der Registrierung in der Methode registrieren() fortgefahren
     * Davor wird das Geburtsdatum aus den einzelnen Feldern ausgelesen und in ein BasicGeburtsdatum gecastet
     */
    void registerierButtonWurdeGedrueckt(){
        if(checkInputs()) {

            try {
                BasicGeburtsdatum neuesGeburtsDatum = new BasicGeburtsdatum((Integer) GeburtsdatumTag.getSelectedItem(),
                        (Integer) GeburtsdatumMonat.getSelectedItem(), (Integer) GeburtsdatumJahr.getSelectedItem());
                String versicherungsNummer = textField3.getText();
                String vorname = textField1.getText();
                String nachname = textField2.getText();
                String passwort = new String(tfRegistrierPasswort.getPassword());
                String anrede = cbAnrede.getSelectedItem().toString();
                int plz = Integer.parseInt(textField4.getText());
                String ort = textField5.getText();
                String hausnummer = textField7.getText();
                String familienstand = cbFamilienstand.getSelectedItem().toString();
                String mailAdresse = textField8.getText();
                Long telefonNummer = Long.parseLong(textField9.getText());
                String staatsangehoerigkeit = textField10.getText();
                String strasse = textField6.getText();

                registrieren(versicherungsNummer, vorname, nachname, neuesGeburtsDatum, passwort, anrede, plz, ort, hausnummer,
                        familienstand, mailAdresse, telefonNummer, staatsangehoerigkeit, strasse);

            } catch (Exception exp) {
                label1.setText(App.resourceBundle.getString("check.your.inputs"));
                MasterController.fenster.add(falscheAngabePanel);
                System.out.println(exp.getMessage());
                MasterController.fenster.validate();
            }
        }
    }

    /**
     * Es wird eine neue Person auf der Datenbank angelegt und die Vertragsuebersicht aufgerufen
     * @param versicherungsNummer die eingegebene Versicherungsnummer
     * @param vorname der eingegebene Vorname
     * @param nachname der eingegebene Nachname
     * @param neuesGeburtsDatum das eingegebene Geburtsdatum
     * @param passwort das eingegebene Passwort
     * @param anrede die eingegebene Anrede
     * @param plz die eingegebene Postleitzahl
     * @param ort der eingegebene Wohnort
     * @param hausnummer die eingegebene Hausnummer
     * @param familienstand der eingegebene Familienstand
     * @param mailAdresse die eingegebene E-Mail Adresse
     * @param telefonNummer die eingegebene Telefonnummer
     * @param staatsangehoerigkeit die eingegebene Staatsangehoerigkeit
     * @param strasse die eingegebene Strasse
     */
    public void registrieren(String versicherungsNummer,
                             String vorname,
                             String nachname,
                             BasicGeburtsdatum neuesGeburtsDatum,
                             String passwort,
                             String anrede,
                             int plz,
                             String ort,
                             String hausnummer,
                             String familienstand,
                             String mailAdresse,
                             long telefonNummer,
                             String staatsangehoerigkeit,
                             String strasse) {
        BasicPerson person = new BasicPerson(
                versicherungsNummer,
                vorname,
                nachname,
                neuesGeburtsDatum,
                passwort,
                anrede,
                plz,
                ort,
                hausnummer,
                familienstand,
                mailAdresse,
                telefonNummer,
                staatsangehoerigkeit,
                strasse);
        DATABASE.create_person_entry(person);
        removePanelsFromFrame();
        SwingPresentation.clearAllTextFields();
        clearAllComboBoxes();
        App.masterController.loadVertragsuebersicht(person);
    }
    /**
    * Die ComboBoxen werden zurueckgesetzt um beim neuladen der Seite keine alten Daten stehen zu haben
    */
    public void clearAllComboBoxes(){
        cbFamilienstand.setSelectedIndex(0);
        cbAnrede.setSelectedIndex(0);
        GeburtsdatumTag.setSelectedIndex(0);
        GeburtsdatumMonat.setSelectedIndex(0);
        GeburtsdatumJahr.setSelectedIndex(0);
    }
    /**
    * @method sorgt dafür dass die Panels vom Frame wieder verschwinden
    */
    public void removePanelsFromFrame(){
        MasterController.fenster.remove(registrierButtonPanel);
        MasterController.fenster.remove(datenPanel);
        MasterController.fenster.remove(geburtsDatumsPanel);
        MasterController.fenster.remove(zurueckPanel);
        MasterController.fenster.remove(falscheAngabePanel);
    }
}

