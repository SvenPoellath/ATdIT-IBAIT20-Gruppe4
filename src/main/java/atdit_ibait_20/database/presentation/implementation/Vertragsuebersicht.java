package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    /**
    * Anlegen der Elemente für das Layout der Vertragsübersicht
    **/
public class Vertragsuebersicht implements SwingPresentation {
    private static final JPanel add = new JPanel();
    private static final JMenuBar menuBar = new JMenuBar();
    private static final JMenu menu = new JMenu(App.resourceBundle.getString("menu"));
    private static final JMenuItem einstellungen = new JMenuItem();
    private static final JMenuItem home = new JMenuItem();
    private static final JMenuItem logout = new JMenuItem();
    private static final JLabel vertragsUebersicht = new JLabel();

    private static final JButton plusButton = new JButton("+");

    private static Person angemeldetePerson;
    public Vertragsuebersicht(Person person){
        angemeldetePerson = person;
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        setFrame();
    }

    /**
    * Genaue Einteilung wie vorhandene Verträge angezeigt werden sollen. 
    **/
    public static void addVorhandeneVertraegetoGUI(Person person){
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
                JLabel betragText = new JLabel(App.resourceBundle.getString("payment.amount")+App.resourceBundle.getString("currency"));
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
    
    /**
    * @setString Schriftzüge der Elemente des Layouts werden festgelegt
    **/
    static void setStrings(){
        einstellungen.setText(App.resourceBundle.getString("settings"));
        home.setText(App.resourceBundle.getString("home"));
        logout.setText(App.resourceBundle.getString("logout"));
        vertragsUebersicht.setText(App.resourceBundle.getString("welcome.to.your.contract.overview"));
        add.removeAll();
        if (angemeldetePerson != null)
            addVorhandeneVertraegetoGUI(angemeldetePerson);
        add.add(vertragsUebersicht);
        add.add(plusButton);
    }

    /**
    * @setFrame legt die Größe des Fensters, welches die Applikation anzeigt fest
    **/
    @Override
    public void setFrame() {
        StartLayer.fenster.setSize(400,400);
    }

    /**
    * @setLayout Legt die Art des Layoutes fest, welches verwendet wird
    **/
    @Override
    public void setLayout() {
        add.setLayout(new GridLayout(0,1));
    }
    
    @Override
    public void addListeners() {
        einstellungen.addActionListener(new menuItemListener());
        logout.addActionListener(new menuItemListener());
        home.addActionListener(new menuItemListener());
        plusButton.addActionListener(new VertragHinzufuegenButton());
    }

    @Override
    public void addComponentsToPanels() {
        menu.add(home);
        menu.add(einstellungen);
        menu.add(logout);
        menuBar.add(menu);
    }

    @Override
    public void addPanelsToFrame() {
        StartLayer.fenster.setJMenuBar(menuBar);
        StartLayer.fenster.add(add);
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

    /**
    * @actionPerformed legt fest was passiert wenn der Nutzer den Vertrag hinzufügt und alle Eingaben korrekt waren.
    * In diesem Fall wird er auf das Hauptmenü weitergeleitet
    **/
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
