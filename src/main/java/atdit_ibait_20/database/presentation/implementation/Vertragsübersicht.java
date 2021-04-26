package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vertragsübersicht {
    private final JPanel add = new JPanel();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu(App.resourceBundle.getString("menu"));
    private final JMenuItem einstellungen = new JMenuItem(App.resourceBundle.getString("settings"));
    private final JMenuItem home = new JMenuItem(App.resourceBundle.getString("home"));

    private final JLabel vertragsUebersicht = new JLabel(App.resourceBundle.getString("welcome.to.your.contract.overview"));

    private final JButton plusButton = new JButton("+");

    private final Person angemeldetePerson;
    public Vertragsübersicht(Person person){
        einstellungen.addActionListener(new menuItemListener());
        home.addActionListener(new menuItemListener());
        plusButton.addActionListener(new VertragHinzufuegenButton());
        menu.add(home);
        menu.add(einstellungen);
        menuBar.add(menu);

        StartLayer.fenster.setJMenuBar(menuBar);
        addVorhandeneVertraegetoGUI(person);
        add.add(vertragsUebersicht);
        add.add(plusButton);

        StartLayer.fenster.add(add);
        angemeldetePerson = person;
    }

    public void addVorhandeneVertraegetoGUI(Person person){
        for(int i = 0; i < person.getVertraege().size(); i++){
            JPanel jp = new JPanel();
            jp.setLayout(new GridLayout(0,2));
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
            StartLayer.fenster.add(jp);
        }
    }
    class VertragHinzufuegenButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=1;i<StartLayer.fenster.getComponentCount();i++)
                StartLayer.fenster.remove(i);
            new VertragHinzufuegen(angemeldetePerson);
        }
    }
    class menuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=1;i<StartLayer.fenster.getComponentCount();i++)
                StartLayer.fenster.remove(i);
            if(e.getSource().equals(einstellungen))
                new Einstellungen(angemeldetePerson);
            else if(e.getSource().equals(home)) {
                new Vertragsübersicht(angemeldetePerson);
            }
        }
    }
}
