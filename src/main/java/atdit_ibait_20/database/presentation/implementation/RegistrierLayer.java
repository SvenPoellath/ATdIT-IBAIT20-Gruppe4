package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicDatabase;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrierLayer {

    private final JPanel datenPanel = new JPanel();
    private final JPanel geburtsDatumsPanel = new JPanel();
    private final JPanel zurueckPanel = new JPanel();
    private final JPanel registrierButtonPanel = new JPanel();
    private final JPanel falscheAngabePanel = new JPanel();

    private final JTextField tfName = new JTextField();
    private final JTextField tfNachName = new JTextField();
    private final JTextField tfVersicherungsNummer = new JTextField();
    private final JTextField tfPlz = new JTextField();
    private final JTextField tfOrt = new JTextField();
    private final JTextField tfStrasse = new JTextField();
    private final JTextField tfHausnummer = new JTextField();
    private final JTextField tfEmailadresse = new JTextField();
    private final JTextField tfTelefonnummer = new JTextField();
    private final JTextField tfStaatsangehoerigkeit = new JTextField();

    private final JPasswordField tfRegistrierPasswort = new JPasswordField();

    private static final String[] namen = new String[]{ "*",App.resourceBundle.getString("mister"), App.resourceBundle.getString("mrs"), App.resourceBundle.getString("doctor"), App.resourceBundle.getString("professor" )};
    private static final Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private static final Integer[] monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
    private static final Integer[] jahre = new Integer[] {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004};
    private static final String [] familienstandArten = new String[]{App.resourceBundle.getString("single"),App.resourceBundle.getString("married"),App.resourceBundle.getString("divorced"),App.resourceBundle.getString("widowed")};

    public static final JComboBox<String> cbAnrede = new JComboBox<>(namen);
    public static final JComboBox<Integer> GeburtsdatumTag = new JComboBox<>(tage);
    public static final JComboBox<Integer> GeburtsdatumMonat = new JComboBox<>(monate);
    public static final JComboBox<Integer> GeburtsdatumJahr = new JComboBox<>(jahre);
    public static final JComboBox<String> cbFamilienstand = new JComboBox<>(familienstandArten);



    public RegistrierLayer(){
        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(datenPanel);
        StartLayer.fenster.add(geburtsDatumsPanel);
        StartLayer.fenster.add(registrierButtonPanel);
        StartLayer.fenster.setSize(400,550);

        JButton zurueckButton = new JButton("<--");
        zurueckButton.addActionListener(new ZurueckButtonListener());
        JButton registrierenButton = new JButton(App.resourceBundle.getString("register"));
        registrierenButton.addActionListener(new RegistrierButtonListener());

        zurueckPanel.setLayout(new FlowLayout());
        datenPanel.setLayout(new GridLayout(0,2));
        geburtsDatumsPanel.setLayout(new GridLayout(0,3));

        registrierButtonPanel.add(registrierenButton);
        JLabel anrede = new JLabel(App.resourceBundle.getString("title"));
        datenPanel.add(anrede);
        datenPanel.add(cbAnrede);
        JLabel name = new JLabel(App.resourceBundle.getString("first.name"));
        datenPanel.add(name);
        datenPanel.add(tfName);
        JLabel nachName = new JLabel(App.resourceBundle.getString("last.name"));
        datenPanel.add(nachName);
        datenPanel.add(tfNachName);
        JLabel staatsangehoerigkeit = new JLabel(App.resourceBundle.getString("nationality"));
        datenPanel.add(staatsangehoerigkeit);
        datenPanel.add(tfStaatsangehoerigkeit);
        JLabel familienstand = new JLabel(App.resourceBundle.getString("marital.status"));
        datenPanel.add(familienstand);
        datenPanel.add(cbFamilienstand);
        JLabel plz = new JLabel(App.resourceBundle.getString("postcode"));
        datenPanel.add(plz);
        datenPanel.add(tfPlz);
        JLabel ort = new JLabel(App.resourceBundle.getString("city"));
        datenPanel.add(ort);
        datenPanel.add(tfOrt);
        JLabel strasse = new JLabel(App.resourceBundle.getString("street"));
        datenPanel.add(strasse);
        datenPanel.add(tfStrasse);
        JLabel hausnummer = new JLabel(App.resourceBundle.getString("house.number"));
        datenPanel.add(hausnummer);
        datenPanel.add(tfHausnummer);
        JLabel mailadresse = new JLabel(App.resourceBundle.getString("e.mail.address"));
        datenPanel.add(mailadresse);
        datenPanel.add(tfEmailadresse);
        JLabel telefonnummer = new JLabel(App.resourceBundle.getString("phone.number"));
        datenPanel.add(telefonnummer);
        datenPanel.add(tfTelefonnummer);
        JLabel versicherungsNummer = new JLabel(App.resourceBundle.getString("social.security.number"));
        datenPanel.add(versicherungsNummer);
        datenPanel.add(tfVersicherungsNummer);
        JLabel registrierPasswort = new JLabel(App.resourceBundle.getString("password"));
        datenPanel.add(registrierPasswort);
        datenPanel.add(tfRegistrierPasswort);
        JLabel geburtsDatum = new JLabel(App.resourceBundle.getString("date.of.birth"));
        datenPanel.add(geburtsDatum);
        geburtsDatumsPanel.add(GeburtsdatumTag);
        geburtsDatumsPanel.add(GeburtsdatumMonat);
        geburtsDatumsPanel.add(GeburtsdatumJahr);
        zurueckPanel.add(zurueckButton);
        JLabel falscheAngabe = new JLabel(App.resourceBundle.getString("check.your.inputs"));
        falscheAngabePanel.add(falscheAngabe);
    }
    class ZurueckButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StartLayer.fenster.remove(registrierButtonPanel);
            StartLayer.fenster.remove(datenPanel);
            StartLayer.fenster.remove(geburtsDatumsPanel);
            StartLayer.fenster.remove(zurueckPanel);
            new StartLayer();
        }
    }
    class RegistrierButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                BasicGeburtsdatum neuesGeburtsDatum = new BasicGeburtsdatum((Integer) GeburtsdatumTag.getSelectedItem(), (Integer) GeburtsdatumMonat.getSelectedItem(), (Integer) GeburtsdatumJahr.getSelectedItem());
                BasicPerson person = new BasicPerson(tfVersicherungsNummer.getText(), tfName.getText(), tfNachName.getText(), neuesGeburtsDatum,new String(tfRegistrierPasswort.getPassword()), cbAnrede.getSelectedItem().toString(), Integer.parseInt(tfPlz.getText()), tfOrt.getText(), tfHausnummer.getText(), cbFamilienstand.getSelectedItem().toString(), tfEmailadresse.getText(), Long.parseLong(tfTelefonnummer.getText()), tfStaatsangehoerigkeit.getText(), tfStrasse.getText());
                BasicDatabase.create_person_entry(person);
                StartLayer.fenster.remove(registrierButtonPanel);
                StartLayer.fenster.remove(datenPanel);
                StartLayer.fenster.remove(geburtsDatumsPanel);
                StartLayer.fenster.remove(zurueckPanel);
                new Vertragsübersicht(person);
            }catch (Exception exp){
                StartLayer.fenster.add(falscheAngabePanel);
                System.out.println(exp.getMessage());
            }
        }

    }

}
