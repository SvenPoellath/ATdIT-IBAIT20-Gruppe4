package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.persistence.Database;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private static String[] namen = new String[]{ "*",App.resourceBundle.getString("mister"), App.resourceBundle.getString("mrs")};
    private static Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private static Integer[] monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
    private static Integer[] jahre = new Integer[] {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004};
    private static String [] familienstandArten = new String[]{App.resourceBundle.getString("single"),App.resourceBundle.getString("married"),App.resourceBundle.getString("divorced"),App.resourceBundle.getString("widowed")};

    static JComboBox<String> cbAnrede = new JComboBox<>(namen);
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
        namen = new String[]{ "*",App.resourceBundle.getString("mister"), App.resourceBundle.getString("mrs")};
        tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
        jahre = new Integer[] {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004};
        familienstandArten = new String[]{App.resourceBundle.getString("single"),App.resourceBundle.getString("married"),App.resourceBundle.getString("divorced"),App.resourceBundle.getString("widowed")};
        cbAnrede  = new JComboBox<>(namen);
        GeburtsdatumTag  = new JComboBox<>(tage);
        GeburtsdatumMonat = new JComboBox<>(monate);
        GeburtsdatumJahr = new JComboBox<>(jahre);
        cbFamilienstand = new JComboBox<>(familienstandArten);
    }

    @Override
    public void setFrame() {
        StartLayer.fenster.setSize(400,550);
    }

    @Override
    public void setLayout() {
        zurueckPanel.setLayout(new FlowLayout());
        datenPanel.setLayout(new GridLayout(0,2));
        geburtsDatumsPanel.setLayout(new GridLayout(0,3));
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
            falscheAngabe.setText(App.resourceBundle.getString("invalid") + " " + App.resourceBundle.getString("social.security.number"));
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
                BasicGeburtsdatum neuesGeburtsDatum = new BasicGeburtsdatum((Integer) GeburtsdatumTag.getSelectedItem(), (Integer) GeburtsdatumMonat.getSelectedItem(), (Integer) GeburtsdatumJahr.getSelectedItem());
                BasicPerson person = new BasicPerson(tfVersicherungsNummer.getText(), tfName.getText(), tfNachName.getText(), neuesGeburtsDatum, new String(tfRegistrierPasswort.getPassword()), cbAnrede.getSelectedItem().toString(), Integer.parseInt(tfPlz.getText()), tfOrt.getText(), tfHausnummer.getText(), cbFamilienstand.getSelectedItem().toString(), tfEmailadresse.getText(), Long.parseLong(tfTelefonnummer.getText()), tfStaatsangehoerigkeit.getText(), tfStrasse.getText());
                DATABASE.create_person_entry(person);
                removePanelsFromFrame();
                clearAllTextFields();
                new Vertragsuebersicht(person);
            } catch (Exception exp) {
                falscheAngabe.setText(App.resourceBundle.getString("check.your.inputs"));
                StartLayer.fenster.add(falscheAngabePanel);
                System.out.println(exp.getMessage());
                StartLayer.fenster.validate();
            }
        }
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
    }
    public void removePanelsFromFrame(){
        StartLayer.fenster.remove(registrierButtonPanel);
        StartLayer.fenster.remove(datenPanel);
        StartLayer.fenster.remove(geburtsDatumsPanel);
        StartLayer.fenster.remove(zurueckPanel);
        StartLayer.fenster.remove(falscheAngabePanel);
    }
}

