package atdit_ibait_20.database.presentation.implementation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;

public class BasicGUI {
    JFrame fenster = new JFrame();
    JPanel willkommenPanel = new JPanel();
    JPanel eingangsButtonsPanel = new JPanel();
    JPanel registrierButtonPanel = new JPanel();
    JPanel datenPanel = new JPanel();
    JPanel geburtsDatumsPanel = new JPanel();
    JPanel zurückPanel = new JPanel();
    JPanel anmeldungsPanel = new JPanel();
    JPanel anmeldungsButtonPanel = new JPanel();

    JLabel willkommenSchrift = new JLabel("Willkommen in unserer App");
    JLabel name = new JLabel("Vorname : ");
    JLabel nachName = new JLabel("Nachname : ");
    JLabel anmeldeName = new JLabel("Anmeldename : ");
    JLabel passwort = new JLabel("Passwort : ");
    JLabel versicherungsNummer = new JLabel("VersicherungsNummer : ");
    JLabel anrede = new JLabel("Anrede : ");
    JLabel geburtsDatum = new JLabel("Geburtsdatum(tt.mm.jjjj) : ");

    JButton registrierenFensterButton = new JButton("Registrieren");
    JButton anmeldenFensterButton = new JButton("Anmelden");
    JButton zurückButton = new JButton("<--");
    JButton registrierenButton = new JButton("Registrieren");
    JButton anmeldeButton = new JButton("Anmelden");

    JComboBox<String> titel;
    JComboBox<Integer> GeburtsdatumTag;
    JComboBox<Integer> GeburtsdatumMonat;
    JComboBox<Integer> GeburtsdatumJahr;

    JTextField tfName = new JTextField();
    JTextField tfNachName = new JTextField();
    JTextField tfAnmeldeName = new JTextField();
    JTextField tfpasswort = new JTextField();
    JTextField tfVersicherungsNummer = new JTextField();

    static int woBinIch = 0;

    public BasicGUI() {

        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle("Krankenkassenapp");
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(500, 250);
        fenster.setLayout(new GridLayout(0,1));

        String[] namen = new String[]{ "*","Herr", "Frau", "Doktor", "Professor" };
        Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        Integer[] monate = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
        Integer[] jahre = new Integer[] {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004};

        titel = new JComboBox<String>(namen);
        GeburtsdatumTag = new JComboBox<Integer>(tage);
        GeburtsdatumMonat = new JComboBox<Integer>(monate);
        GeburtsdatumJahr = new JComboBox<Integer>(jahre);

        ZurückButtonListener zurückButtonListener = new ZurückButtonListener();
        AnfangsButtonListener anfangsButtonListener = new AnfangsButtonListener();
        RegistrierButtonListener registrierButtonListener = new RegistrierButtonListener();
        AnmeldeButtonListener anmeldeButtonListener = new AnmeldeButtonListener();

        registrierenFensterButton.addActionListener(anfangsButtonListener);
        anmeldenFensterButton.addActionListener(anfangsButtonListener);
        zurückButton.addActionListener(zurückButtonListener);
        registrierenButton.addActionListener(registrierButtonListener);
        anmeldeButton.addActionListener(anmeldeButtonListener);

        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));
        zurückPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        zurückPanel.setLayout(new FlowLayout());
        registrierButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,30, 10, 30));
        datenPanel.setLayout(new GridLayout(0,2));
        geburtsDatumsPanel.setLayout(new GridLayout(0,3));
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        anmeldungsPanel.setLayout(new GridLayout(0,2));
        anmeldungsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,30, 10, 30));

//		label.setVerticalTextPosition(JLabel.CENTER);
        willkommenSchrift.setVerticalAlignment(JLabel.CENTER);
//		label.setHorizontalTextPosition(JLabel.CENTER);
        willkommenSchrift.setHorizontalAlignment(JLabel.CENTER);

        willkommenPanel.add(willkommenSchrift);
        eingangsButtonsPanel.add(registrierenFensterButton);
        eingangsButtonsPanel.add(anmeldenFensterButton);
        registrierButtonPanel.add(registrierenButton);
        datenPanel.add(name);
        datenPanel.add(tfName);
        datenPanel.add(nachName);
        datenPanel.add(tfNachName);
        datenPanel.add(versicherungsNummer);
        datenPanel.add(tfVersicherungsNummer);
        datenPanel.add(passwort);
        datenPanel.add(tfpasswort);
        datenPanel.add(geburtsDatum);
        geburtsDatumsPanel.add(GeburtsdatumTag);
        geburtsDatumsPanel.add(GeburtsdatumMonat);
        geburtsDatumsPanel.add(GeburtsdatumJahr);
        zurückPanel.add(zurückButton);
        anmeldungsPanel.add(anmeldeName);
        anmeldungsPanel.add(tfAnmeldeName);
        anmeldungsPanel.add(passwort);
        anmeldungsPanel.add(tfpasswort);
        anmeldungsButtonPanel.add(anmeldeButton);

        fenster.add(willkommenPanel);
        fenster.add(eingangsButtonsPanel);



    }

    public static void main (String[] args) {
        new BasicGUI();
    }
    class ZurückButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(woBinIch == 1) {
                fenster.remove(registrierButtonPanel);
                fenster.remove(datenPanel);
                fenster.remove(geburtsDatumsPanel);
            }
            else if(woBinIch == 2) {
                fenster.remove(anmeldungsPanel);
                fenster.remove(anmeldungsButtonPanel);
            }
            fenster.remove(zurückPanel);
            fenster.add(willkommenPanel);
            fenster.add(eingangsButtonsPanel);
        }
    }
    class AnfangsButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            fenster.add(zurückPanel);
            if(e.getSource().equals(registrierenFensterButton) || e.getSource().equals(anmeldenFensterButton)) {
                fenster.remove(willkommenPanel);
                fenster.remove(eingangsButtonsPanel);
                if(e.getSource().equals(registrierenFensterButton)) {
                    fenster.add(datenPanel);
                    fenster.add(geburtsDatumsPanel);
                    fenster.add(registrierButtonPanel);
                    woBinIch = 1;
                }
                else if(e.getSource().equals(anmeldenFensterButton)) {
                    fenster.add(anmeldungsPanel);
                    fenster.add(anmeldungsButtonPanel);
                    woBinIch = 2;
                }
            }
        }
    }
    class RegistrierButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }

    }
    class AnmeldeButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }

    }

}