package atdit_ibait_20.database.presentation.implementation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;

public class BasicGUI implements ActionListener{
    JFrame fenster = new JFrame();
    JPanel willkommenPanel = new JPanel();
    JPanel eingangsButtonsPanel = new JPanel();
    JPanel registrierButtonPanel = new JPanel();
    JPanel datenPanel = new JPanel();
    JPanel zurückPanel = new JPanel();
    JPanel anmeldungsPanel = new JPanel();
    JPanel anmeldungsButtonPanel = new JPanel();

    JLabel willkommenSchrift = new JLabel("Willkommen in unserer App");

    JButton registrierenFensterButton = new JButton("Registrieren");
    JButton anmeldenFensterButton = new JButton("Anmelden");
    JButton zurückButton = new JButton("<--");
    JButton registrierenButton = new JButton("Registrieren");
    JButton anmeldeButton = new JButton("Anmelden");

    JLabel name = new JLabel("Vorname : ");
    JTextField tfName = new JTextField();
    JLabel nachName = new JLabel("Nachname : ");
    JTextField tfNachName = new JTextField();
    JLabel anrede = new JLabel("Anrede : ");
    JComboBox titel;
    JLabel anmeldeName = new JLabel("Anmeldename : ");
    JTextField tfAnmeldeName = new JTextField();
    JLabel passwort = new JLabel("Passwort : ");
    JTextField tfpasswort = new JTextField();
    JLabel geburtsDatum = new JLabel("Geburtsdatum(tt.mm.jjjj) : ");
    JTextField tfGeburtsDatum = new JTextField();
    JLabel versicherungsNummer = new JLabel("VersicherungsNummer : ");
    JTextField tfVersicherungsNummer = new JTextField();
    public BasicGUI() {

        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle("Krankenkassenapp");
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(500, 250);

        String[] namen = new String[]{ "*","Herr", "Frau", "Doktor", "Professor" };
        titel = new JComboBox(namen);

        registrierenFensterButton.addActionListener(this);
        anmeldenFensterButton.addActionListener(this);
        zurückButton.addActionListener(this);
        registrierenButton.addActionListener(this);
        anmeldeButton.addActionListener(this);

        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));
        zurückPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        zurückPanel.setLayout(new FlowLayout());
        registrierButtonPanel.setLayout(new GridLayout());
        datenPanel.setLayout(new GridLayout(0,2));
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        anmeldungsPanel.setLayout(new GridLayout(0,2));
        anmeldungsButtonPanel.setLayout(new GridLayout());

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
        datenPanel.add(geburtsDatum);
        datenPanel.add(tfGeburtsDatum);
        datenPanel.add(versicherungsNummer);
        datenPanel.add(tfVersicherungsNummer);
        datenPanel.add(passwort);
        datenPanel.add(tfpasswort);
        zurückPanel.add(zurückButton);
        anmeldungsPanel.add(anmeldeName);
        anmeldungsPanel.add(tfAnmeldeName);
        anmeldungsPanel.add(passwort);
        anmeldungsPanel.add(tfpasswort);
        anmeldungsButtonPanel.add(anmeldeButton);

        fenster.add(willkommenPanel, BorderLayout.CENTER);
        fenster.add(eingangsButtonsPanel, BorderLayout.SOUTH);



    }

    public static void main (String[] args) {
        new BasicGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(zurückButton)) {
            fenster.removeAll();
            fenster.add(willkommenPanel);
            fenster.add(eingangsButtonsPanel);
        }
        else {
            fenster.add(zurückPanel, BorderLayout.NORTH);
            if(e.getSource().equals(registrierenFensterButton) || e.getSource().equals(anmeldenFensterButton)) {
                fenster.remove(willkommenPanel);
                fenster.remove(eingangsButtonsPanel);
                if(e.getSource().equals(registrierenFensterButton)) {
                    fenster.add(registrierButtonPanel, BorderLayout.SOUTH);
                    fenster.add(datenPanel, BorderLayout.CENTER);
                }
                else if(e.getSource().equals(anmeldenFensterButton)) {
                    fenster.add(anmeldungsPanel, BorderLayout.CENTER);
                }
            }
        }
    }

}

