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
* Die Klasse legt das Layout für die Registrierseite fest und regelt im Detail welche Eingaben für welches Feld zulässig sind
*/
public class RegistrierLayer implements SwingPresentation {

    private static final JPanel datenPanel = new JPanel();
    private static final JPanel geburtsDatumsPanel = new JPanel();
    private static final JPanel zurueckPanel = new JPanel();
    private static final JPanel registrierButtonPanel = new JPanel();
    private static final JPanel falscheAngabePanel = new JPanel();

    private static final JPasswordField tfRegistrierPasswort = new JPasswordField();

    private static String[] anredeAuswahl = new String[]{ "*",App.resourceBundle.getString("mister"), App.resourceBundle.getString("mrs")};
    private static Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private static Integer[] monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
    private static Integer[] jahre = new Integer[(Year.now().getValue()-1899)];
    private static String [] familienstandArten = new String[]{App.resourceBundle.getString("single"),App.resourceBundle.getString("married"),App.resourceBundle.getString("divorced"),App.resourceBundle.getString("widowed")};

    static JComboBox<String> cbAnrede = new JComboBox<>(anredeAuswahl);
    static JComboBox<Integer> GeburtsdatumTag = new JComboBox<>(tage);
    static JComboBox<Integer> GeburtsdatumMonat = new JComboBox<>(monate);
    static JComboBox<Integer> GeburtsdatumJahr = new JComboBox<>(jahre);
    static JComboBox<String> cbFamilienstand = new JComboBox<>(familienstandArten);

    static boolean istErsterAufruf = true;

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
* @method überprüft ob gültige Daten eingegeben wurden und gibt dem Nutzer bei Problemen eine Fehlermeldung zurück
*/
    boolean checkInputs(){
        label1.setText(null);

        if(!DATABASE.check_id(textField3.getText())) {
            label1.setText(App.resourceBundle.getString("social.security.number") + " " + App.resourceBundle.getString("is.taken"));
        }

        if(textField3.getText().length()!=12){
            label1.setText(App.resourceBundle.getString("invalid")+" "+App.resourceBundle.getString("social.security.number"));
        }
        try{
            Integer.parseInt(textField4.getText());
        }catch (NumberFormatException nfe){
            label1.setText(App.resourceBundle.getString("invalid")+" "+App.resourceBundle.getString("postcode"));
        }
        try{
            Long.parseLong(textField9.getText());
        }catch (NumberFormatException nfe){
            label1.setText(App.resourceBundle.getString("invalid")+" "+App.resourceBundle.getString("phone.number"));
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
* Die Klasse ermöglicht es dem Nutzer eine Seite zurück zu gehen in der Registrierung
*/
    void zurueckButtonWurdeGedrueckt(){
        removePanelsFromFrame();
        new StartLayer();
    }
    /**
* @method wird vom Nutzer aktiviert wenn er alle seine Daten eingegeben hat. Das bisherige Layout verschwindet.
* Wenn der Nutzer ungültige Eingaben getätigt hat, weisst das Programm ihn an dieser Stelle darauf hin.
* @Param Vorname
* @Param Nachname
* @Param Geburtsdatum
* @Param Passwort
* @Param Anrede
* @Param PLZ
* @Param Ort
* @Param Hausnummer
* @Param Familienstand
* @Param Mailadresse
* @Param Telefonnummer
* @Param Staatsangehörigkeit
* @Param Strasse
* @Param IBAN
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
* @method fügt alle Daten aus dem Registrieren der Datenbank hinzu und erzeugt damit eine neue Person
* @Param Vorname
* @Param Nachname
* @Param Geburtsdatum
* @Param Passwort
* @Param Anrede
* @Param PLZ
* @Param Ort
* @Param Hausnummer
* @Param Familienstand
* @Param Mailadresse
* @Param Telefonnummer
* @Param Staatsangehörigkeit
* @Param Strasse
* @Param IBAN
*/
    public void registrieren(String versicherungsNummer, String vorname, String nachname, BasicGeburtsdatum neuesGeburtsDatum, String passwort, String anrede, int plz, String ort, String hausnummer, String familienstand, String mailAdresse, long telefonNummer, String staatsangehoerigkeit, String strasse) {
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
    * @method leert alle Boxen nach der Registrierung wieder
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

