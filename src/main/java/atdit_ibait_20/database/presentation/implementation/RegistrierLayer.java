package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.model.implementation.BasicPerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrierLayer {
    JPanel datenPanel = new JPanel();
    JPanel geburtsDatumsPanel = new JPanel();
    JPanel zurueckPanel = new JPanel();
    JPanel registrierButtonPanel = new JPanel();

    JLabel name = new JLabel("Vorname : ");
    JLabel nachName = new JLabel("Nachname : ");
    JLabel registrierPasswort = new JLabel("Passwort : ");
    JLabel versicherungsNummer = new JLabel("Sozialversicherungsnummer : ");
    JLabel anrede = new JLabel("Anrede : ");
    JLabel geburtsDatum = new JLabel("Geburtsdatum(tt.mm.jjjj) : ");

    JButton zurueckButton = new JButton("<--");
    JButton registrierenButton = new JButton("Registrieren");

    JTextField tfName = new JTextField();
    JTextField tfNachName = new JTextField();
    JTextField tfAnmeldeName = new JTextField();
    JTextField tfVersicherungsNummer = new JTextField();

    JPasswordField tfRegistrierPasswort = new JPasswordField();

    String[] namen = new String[]{ "*","Herr", "Frau", "Doktor", "Professor" };
    Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    Integer[] monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
    Integer[] jahre = new Integer[] {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004};

    JComboBox<String> titel = new JComboBox<>(namen);
    JComboBox<Integer> GeburtsdatumTag = new JComboBox<>(tage);
    JComboBox<Integer> GeburtsdatumMonat = new JComboBox<>(monate);
    JComboBox<Integer> GeburtsdatumJahr = new JComboBox<>(jahre);



    public RegistrierLayer(){
        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(datenPanel);
        StartLayer.fenster.add(geburtsDatumsPanel);
        StartLayer.fenster.add(registrierButtonPanel);

        zurueckButton.addActionListener(new ZurueckButtonListener());
        registrierenButton.addActionListener(new RegistrierButtonListener());

        zurueckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        zurueckPanel.setLayout(new FlowLayout());
        registrierButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,30, 10, 30));
        datenPanel.setLayout(new GridLayout(0,2));
        geburtsDatumsPanel.setLayout(new GridLayout(0,3));

        registrierButtonPanel.add(registrierenButton);
        datenPanel.add(anrede);
        datenPanel.add(titel);
        datenPanel.add(name);
        datenPanel.add(tfName);
        datenPanel.add(nachName);
        datenPanel.add(tfNachName);
        datenPanel.add(versicherungsNummer);
        datenPanel.add(tfVersicherungsNummer);
        datenPanel.add(registrierPasswort);
        datenPanel.add(tfRegistrierPasswort);
        datenPanel.add(geburtsDatum);
        geburtsDatumsPanel.add(GeburtsdatumTag);
        geburtsDatumsPanel.add(GeburtsdatumMonat);
        geburtsDatumsPanel.add(GeburtsdatumJahr);
        zurueckPanel.add(zurueckButton);
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
                new BasicPerson(Integer.parseInt(tfVersicherungsNummer.getText()), tfName.getText(), tfNachName.getText(), neuesGebursDatum, null, tfRegistrierPasswort.getText());
            }catch (NullPointerException npe){
                System.out.println("Überprüfen sie ihre eingaben");
            }
        }

    }

}
