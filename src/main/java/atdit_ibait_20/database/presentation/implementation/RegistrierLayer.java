package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class RegistrierLayer {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH);

    JPanel datenPanel = new JPanel();
    JPanel geburtsDatumsPanel = new JPanel();
    JPanel zurueckPanel = new JPanel();
    JPanel registrierButtonPanel = new JPanel();
    JPanel falscheAngabePanel = new JPanel();

    JLabel name = new JLabel(resourceBundle.getString("first.name"));
    JLabel nachName = new JLabel(resourceBundle.getString("last.name"));
    JLabel registrierPasswort = new JLabel(resourceBundle.getString("password"));
    JLabel versicherungsNummer = new JLabel(resourceBundle.getString("social.security.number"));
    JLabel anrede = new JLabel(resourceBundle.getString("title"));
    JLabel geburtsDatum = new JLabel(resourceBundle.getString("date.of.birth"));
    JLabel plz = new JLabel(resourceBundle.getString("postcode"));
    JLabel ort = new JLabel(resourceBundle.getString("city"));
    JLabel strasse = new JLabel(resourceBundle.getString("street"));
    JLabel hausnummer =new JLabel(resourceBundle.getString("house.number"));
    JLabel familienstand = new JLabel(resourceBundle.getString("marital.status"));
    JLabel mailadresse = new JLabel(resourceBundle.getString("e.mail.address"));
    JLabel telefonnummer = new JLabel(resourceBundle.getString("phone.number"));
    JLabel staatsangehoerigkeit = new JLabel(resourceBundle.getString("nationality"));
    JLabel falscheAngabe = new JLabel(resourceBundle.getString("check.your.inputs"));


    JButton zurueckButton = new JButton("<--");
    JButton registrierenButton = new JButton(resourceBundle.getString("register"));

    JTextField tfName = new JTextField();
    JTextField tfNachName = new JTextField();
    JTextField tfVersicherungsNummer = new JTextField();
    JTextField tfPlz = new JTextField();
    JTextField tfOrt = new JTextField();
    JTextField tfStrasse = new JTextField();
    JTextField tfHausnummer = new JTextField();
    JTextField tfFamilienstand = new JTextField();
    JTextField tfEmailadresse = new JTextField();
    JTextField tfTelefonnummer = new JTextField();
    JTextField tfStaatsangehoerigkeit = new JTextField();

    JPasswordField tfRegistrierPasswort = new JPasswordField();

    String[] namen = new String[]{ "*",resourceBundle.getString("mister"), resourceBundle.getString("mrs"), resourceBundle.getString("doctor"), resourceBundle.getString("professor" )};
    Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    Integer[] monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
    Integer[] jahre = new Integer[] {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004};

    JComboBox<String> cbAnrede = new JComboBox<>(namen);
    JComboBox<Integer> GeburtsdatumTag = new JComboBox<>(tage);
    JComboBox<Integer> GeburtsdatumMonat = new JComboBox<>(monate);
    JComboBox<Integer> GeburtsdatumJahr = new JComboBox<>(jahre);



    public RegistrierLayer(){
        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(datenPanel);
        StartLayer.fenster.add(geburtsDatumsPanel);
        StartLayer.fenster.add(registrierButtonPanel);
        StartLayer.fenster.setSize(400,550);

        zurueckButton.addActionListener(new ZurueckButtonListener());
        registrierenButton.addActionListener(new RegistrierButtonListener());

        zurueckPanel.setLayout(new FlowLayout());
        datenPanel.setLayout(new GridLayout(0,2));
        geburtsDatumsPanel.setLayout(new GridLayout(0,3));

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
        datenPanel.add(tfFamilienstand);
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
                BasicGeburtsdatum neuesGebursDatum = new BasicGeburtsdatum((Integer) GeburtsdatumTag.getSelectedItem(), (Integer) GeburtsdatumMonat.getSelectedItem(), (Integer) GeburtsdatumJahr.getSelectedItem());
                new BasicPerson(tfVersicherungsNummer.getText(), tfName.getText(), tfNachName.getText(), neuesGebursDatum,tfRegistrierPasswort.getText(), cbAnrede.getSelectedItem().toString(), Integer.parseInt(tfPlz.getText()), tfOrt.getText(), Integer.parseInt(tfHausnummer.getText()), tfFamilienstand.getText(), tfEmailadresse.getText(), Integer.parseInt(tfTelefonnummer.getText()), tfStaatsangehoerigkeit.getText(), tfStrasse.getText());
            }catch (NullPointerException npe){
                StartLayer.fenster.add(falscheAngabePanel);
            }
        }

    }

}
