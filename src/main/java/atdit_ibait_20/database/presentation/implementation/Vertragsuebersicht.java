package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vertragsuebersicht {
    private final JPanel add = new JPanel();
    private final JMenuItem einstellungen = new JMenuItem(App.resourceBundle.getString("settings"));
    private final JMenuItem home = new JMenuItem(App.resourceBundle.getString("home"));
    private final JMenuItem logout = new JMenuItem(App.resourceBundle.getString("logout"));

    private Person angemeldetePerson;
    public Vertragsuebersicht(Person person){
        add.setLayout(new GridLayout(0,1));
        einstellungen.addActionListener(new menuItemListener());
        logout.addActionListener(new menuItemListener());
        home.addActionListener(new menuItemListener());
        JButton plusButton = new JButton("+");
        plusButton.addActionListener(new VertragHinzufuegenButton());
        JMenu menu = new JMenu(App.resourceBundle.getString("menu"));
        menu.add(home);
        menu.add(einstellungen);
        menu.add(logout);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        StartLayer.fenster.setJMenuBar(menuBar);
        addVorhandeneVertraegetoGUI(person);
        JLabel vertragsUebersicht = new JLabel(App.resourceBundle.getString("welcome.to.your.contract.overview"));
        add.add(vertragsUebersicht);
        add.add(plusButton);
        StartLayer.fenster.setSize(400,400);
        StartLayer.fenster.add(add);
        angemeldetePerson = person;
    }

    public void addVorhandeneVertraegetoGUI(Person person){
        if(person.getVertraege() != null) {
            for (int i = 0; i < person.getVertraege().size(); i++) {
                JPanel jp = new JPanel();
                jp.setLayout(new GridLayout(0, 2));
                jp.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
                JLabel auftragsnummerText = new JLabel(App.resourceBundle.getString("contract"));
                JLabel auftragsNummer = new JLabel(String.valueOf(person.getVertraege().get(i).getAuftragsnummer()));
                JLabel versicherungArtText = new JLabel(App.resourceBundle.getString("insurance.type"));
                JLabel versicherungsArt = new JLabel(String.valueOf(person.getVertraege().get(i).getVersicherungsart()));
                JLabel buchungsArtText = new JLabel(App.resourceBundle.getString("payment.type"));
                JLabel buchungsArt = new JLabel(String.valueOf(person.getVertraege().get(i).getBuchungsart()));
                JLabel betragText = new JLabel(App.resourceBundle.getString("payment.amount"));
                JLabel betrag = new JLabel(String.valueOf(person.getVertraege().get(i).getEURBetrag()));
                jp.add(auftragsnummerText);
                jp.add(auftragsNummer);
                jp.add(versicherungArtText);
                jp.add(versicherungsArt);
                jp.add(buchungsArtText);
                jp.add(buchungsArt);
                jp.add(betragText);
                jp.add(betrag);
                add.add(jp);
            }
        }
    }
    class VertragHinzufuegenButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            StartLayer.fenster.remove(add);
            new VertragHinzufuegen(angemeldetePerson);
            StartLayer.fenster.validate();
        }
    }
    class menuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            StartLayer.fenster.remove(VertragHinzufuegen.neueIBAN);
            StartLayer.fenster.remove(VertragHinzufuegen.hinzugefuegt);
            StartLayer.fenster.remove(add);
            StartLayer.fenster.remove(Einstellungen.einstellungen);
            StartLayer.fenster.remove(Einstellungen.datenAendern);
            StartLayer.fenster.remove(Einstellungen.geburtsdatum);
            StartLayer.fenster.remove(VertragHinzufuegen.vertragsDaten);
            StartLayer.fenster.remove(VertragHinzufuegen.hinzufuegen);
            StartLayer.fenster.remove(VertragHinzufuegen.preise);
            if(e.getSource().equals(einstellungen)) {
                new Einstellungen(angemeldetePerson);
                StartLayer.fenster.validate();
            }
            else if(e.getSource().equals(home)) {
                new Vertragsuebersicht(angemeldetePerson);
                StartLayer.fenster.validate();

            }
            else if(e.getSource().equals(logout)) {
                StartLayer.fenster.setJMenuBar(null);
                angemeldetePerson = null;
                new StartLayer();
            }

        }
    }
}
