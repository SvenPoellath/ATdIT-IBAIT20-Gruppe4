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
**/
public class RegistrierLayer implements SwingPresentation {

    private static final JPanel datenPanel = new JPanel();
    private static final JPanel geburtsDatumsPanel = new JPanel();
    private static final JPanel zurueckPanel = new JPanel();
    private static final JPanel registrierButtonPanel = new JPanel();
    private static final JPanel falscheAngabePanel = new JPanel();

    private static final JTextField tfName = new JTextField();
    private static final JTextField tfNachName = new JTextField();
    private static final JTextField tfVersicherungsNummer = new JTextField();
    private static final JTextField tfPlz = new JTextField();
    private static final JTextField tfOrt = new JTextField();
    private static final JTextField tfStrasse = new JTextField();
    private static final JTextField tfHausnummer = new JTextField();
    private static final JTextField tfEmailadresse = new JTextField();
    private static final JTextField tfTelefonnummer = new JTextField();
    private static final JTextField tfStaatsangehoerigkeit = new JTextField();

    private static final JPasswordField tfRegistrierPasswort = new JPasswordField();

    private static final JLabel falscheAngabe = new JLabel();
    private static final JLabel anrede = new JLabel();
    private static final JLabel name = new JLabel();
    private static final JLabel nachName = new JLabel();
    private static final JLabel staatsangehoerigkeit = new JLabel();
    private static final JLabel familienstand = new JLabel();
    private static final JLabel plz = new JLabel();
    private static final JLabel ort = new JLabel();
    private static final JLabel strasse = new JLabel();
    private static final JLabel hausnummer = new JLabel();
    private static final JLabel mailadresse = new JLabel();
    private static final JLabel telefonnummer = new JLabel();
    private static final JLabel versicherungsNummer = new JLabel();
    private static final JLabel registrierPasswort = new JLabel();
    private static final JLabel geburtsDatum = new JLabel();

    private static final JButton registrierenButton = new JButton();
    private static final JButton zurueckButton = new JButton("<--");

    private static String[] anredeAuswahl;
    private static Integer[] tage;
    private static Integer[] monate;
    private static Integer[] jahre;
    private static String [] familienstandArten;

    static JComboBox<String> cbAnrede;
    static JComboBox<Integer> GeburtsdatumTag;
    static JComboBox<Integer> GeburtsdatumMonat;
    static JComboBox<Integer> GeburtsdatumJahr;
    static JComboBox<String> cbFamilienstand;
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
            setFrame();
        StartLayer.fenster.validate();
        StartLayer.fenster.repaint();
    }
    /**
* @setString legt die dem Nutzer angezeigten Namen der einzelnen Felder fest sowie die Auswahloptionen des Geburtsdatums
**/
    static void setStrings(){
        anrede.setText(App.resourceBundle.getString("title"));
        name.setText(App.resourceBundle.getString("first.name"));
        nachName.setText(App.resourceBundle.getString("last.name"));
        staatsangehoerigkeit.setText(App.resourceBundle.getString("nationality"));
        familienstand.setText(App.resourceBundle.getString("marital.status"));
        plz.setText(App.resourceBundle.getString("postcode"));
        ort.setText(App.resourceBundle.getString("city"));
        strasse.setText(App.resourceBundle.getString("street"));
        hausnummer.setText(App.resourceBundle.getString("house.number"));
        mailadresse.setText(App.resourceBundle.getString("e.mail.address"));
        telefonnummer.setText(App.resourceBundle.getString("phone.number"));
        versicherungsNummer.setText(App.resourceBundle.getString("social.security.number"));
        registrierPasswort.setText(App.resourceBundle.getString("password"));
        geburtsDatum.setText(App.resourceBundle.getString("date.of.birth"));
        registrierenButton.setText(App.resourceBundle.getString("register"));
        anredeAuswahl = new String[]{ "*",App.resourceBundle.getString("mister"), App.resourceBundle.getString("mrs")};
        tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
        jahre = new Integer[(Year.now().getValue()-1899)];
        for(int JahreCounter = 1900; JahreCounter <= Year.now().getValue(); JahreCounter++){
                jahre[JahreCounter-1900] = JahreCounter;
        }
        familienstandArten = new String[]{App.resourceBundle.getString("single"),App.resourceBundle.getString("married"),App.resourceBundle.getString("divorced"),App.resourceBundle.getString("widowed")};
        cbAnrede  = new JComboBox<>(anredeAuswahl);
        GeburtsdatumTag  = new JComboBox<>(tage);
        GeburtsdatumMonat = new JComboBox<>(monate);
        GeburtsdatumJahr = new JComboBox<>(jahre);
        cbFamilienstand = new JComboBox<>(familienstandArten);
    }

    @Override
    public void setFrame() {
        StartLayer.fenster.setSize(400,600);
    }

    @Override
    public void setLayout() {
        zurueckPanel.setLayout(new FlowLayout());
        datenPanel.setLayout(new GridLayout(0,2));
        geburtsDatumsPanel.setLayout(new GridLayout(0,3));
        registrierButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        falscheAngabe.setForeground(Color.red);
    }

    @Override
    public void addListeners() {
        removeListeners();
        zurueckButton.addActionListener(e->zurueckButtonWurdeGedrueckt());
        registrierenButton.addActionListener(e->registerierButtonWurdeGedrueckt());
    }

    public void removeListeners() {
        for (ActionListener listener : zurueckButton.getActionListeners())
            zurueckButton.removeActionListener(listener);

        for (ActionListener listener : registrierenButton.getActionListeners())
            registrierenButton.removeActionListener(listener);
    }


    @Override
    public void addComponentsToPanels() {
        registrierButtonPanel.add(registrierenButton);
        datenPanel.add(anrede);
        datenPanel.add(cbAnrede);
        datenPanel.add(name);
        datenPanel.add(tfName);
        datenPanel.add(nachName);
        datenPanel.add(tfNachName);
        datenPanel.add(staatsangehoerigkeit);
        datenPanel.add(tfStaatsangehoerigkeit);
        datenPanel.add(familienstand);
        datenPanel.add(cbFamilienstand);
        datenPanel.add(plz);
        datenPanel.add(tfPlz);
        datenPanel.add(ort);
        datenPanel.add(tfOrt);
        datenPanel.add(strasse);
        datenPanel.add(tfStrasse);
        datenPanel.add(hausnummer);
        datenPanel.add(tfHausnummer);
        datenPanel.add(mailadresse);
        datenPanel.add(tfEmailadresse);
        datenPanel.add(telefonnummer);
        datenPanel.add(tfTelefonnummer);
        datenPanel.add(versicherungsNummer);
        datenPanel.add(tfVersicherungsNummer);
        datenPanel.add(registrierPasswort);
        datenPanel.add(tfRegistrierPasswort);
        datenPanel.add(geburtsDatum);
        geburtsDatumsPanel.add(GeburtsdatumTag);
        geburtsDatumsPanel.add(GeburtsdatumMonat);
        geburtsDatumsPanel.add(GeburtsdatumJahr);
        zurueckPanel.add(zurueckButton);
        falscheAngabePanel.add(falscheAngabe);
    }

    @Override
    public void addPanelsToFrame() {
        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(datenPanel);
        StartLayer.fenster.add(geburtsDatumsPanel);
        StartLayer.fenster.add(registrierButtonPanel);
    }
/**
* @checkInputs überprüft ob gültige Daten eingegeben wurden und gibt dem Nutzer bei Problemen eine Fehlermeldung zurück
**/
    boolean checkInputs(){
        falscheAngabe.setText(null);

        if(!DATABASE.check_id(tfVersicherungsNummer.getText())) {
            falscheAngabe.setText(App.resourceBundle.getString("social.security.number") + " " + App.resourceBundle.getString("is.taken"));
        }

        if(tfVersicherungsNummer.getText().length()!=12){
            falscheAngabe.setText(App.resourceBundle.getString("invalid")+" "+App.resourceBundle.getString("social.security.number"));
        }
        try{
            Integer.parseInt(tfPlz.getText());
        }catch (NumberFormatException nfe){
            falscheAngabe.setText(App.resourceBundle.getString("invalid")+" "+App.resourceBundle.getString("postcode"));
        }
        try{
            Long.parseLong(tfTelefonnummer.getText());
        }catch (NumberFormatException nfe){
            falscheAngabe.setText(App.resourceBundle.getString("invalid")+" "+App.resourceBundle.getString("phone.number"));
        }
        if (falscheAngabe.getText()!=null){
            StartLayer.fenster.add(falscheAngabePanel);
            StartLayer.fenster.repaint();
            StartLayer.fenster.validate();
            return false;
        }
        else{
            return true;
        }
    }
    /**
* Die Klasse ermöglicht es dem Nutzer eine Seite zurück zu gehen in der Registrierung
**/
    void zurueckButtonWurdeGedrueckt(){
        removePanelsFromFrame();
        new StartLayer();
    }
    /**
* Die Klasse wird vom Nutzer gedrückt wenn er alle seine Daten eingegeben hat. Das bisherige Layout verschwindet.
* Wenn der Nutzer ungültige Eingaben getätigt hat, weisst das Programm ihn an dieser Stelle darauf hin.
**/
    void registerierButtonWurdeGedrueckt(){
        if(checkInputs()) {

            try {
                BasicGeburtsdatum neuesGeburtsDatum = new BasicGeburtsdatum((Integer) GeburtsdatumTag.getSelectedItem(),
                        (Integer) GeburtsdatumMonat.getSelectedItem(), (Integer) GeburtsdatumJahr.getSelectedItem());
                String versicherungsNummer = tfVersicherungsNummer.getText();
                String vorname = tfName.getText();
                String nachname = tfNachName.getText();
                String passwort = new String(tfRegistrierPasswort.getPassword());
                String anrede = cbAnrede.getSelectedItem().toString();
                int plz = Integer.parseInt(tfPlz.getText());
                String ort = tfOrt.getText();
                String hausnummer = tfHausnummer.getText();
                String familienstand = cbFamilienstand.getSelectedItem().toString();
                String mailAdresse = tfEmailadresse.getText();
                Long telefonNummer = Long.parseLong(tfTelefonnummer.getText());
                String staatsangehoerigkeit = tfStaatsangehoerigkeit.getText();
                String strasse = tfStrasse.getText();

                registrieren(versicherungsNummer, vorname, nachname, neuesGeburtsDatum, passwort, anrede, plz, ort, hausnummer,
                        familienstand, mailAdresse, telefonNummer, staatsangehoerigkeit, strasse);

            } catch (Exception exp) {
                falscheAngabe.setText(App.resourceBundle.getString("check.your.inputs"));
                StartLayer.fenster.add(falscheAngabePanel);
                System.out.println(exp.getMessage());
                StartLayer.fenster.validate();
            }
        }
    }

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
        clearAllTextFields();
        new Vertragsuebersicht(person);
    }
    public void clearAllTextFields(){
        tfVersicherungsNummer.setText(null);
        tfEmailadresse.setText(null);
        tfHausnummer.setText(null);
        tfName.setText(null);
        tfRegistrierPasswort.setText(null);
        tfNachName.setText(null);
        tfOrt.setText(null);
        tfPlz.setText(null);
        tfStaatsangehoerigkeit.setText(null);
        tfStrasse.setText(null);
        tfTelefonnummer.setText(null);
        cbFamilienstand.setSelectedIndex(0);
        cbAnrede.setSelectedIndex(0);
        GeburtsdatumTag.setSelectedIndex(0);
        GeburtsdatumMonat.setSelectedIndex(0);
        GeburtsdatumJahr.setSelectedIndex(0);
    }
    public void removePanelsFromFrame(){
        StartLayer.fenster.remove(registrierButtonPanel);
        StartLayer.fenster.remove(datenPanel);
        StartLayer.fenster.remove(geburtsDatumsPanel);
        StartLayer.fenster.remove(zurueckPanel);
        StartLayer.fenster.remove(falscheAngabePanel);
    }
}

