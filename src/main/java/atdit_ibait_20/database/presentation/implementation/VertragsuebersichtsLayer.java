package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Die Klasse erstellt das UI fuer die Vertragsuebersichtsseite
 * Hier werden die laufenden Vertraege der angemeldeten Person angezeigt
 * Von hier aus koennen neue Vertraege gebucht werden
 */
public class VertragsuebersichtsLayer implements SwingPresentation {
    private static final JPanel add = new JPanel();
    private static final JMenuBar menuBar = new JMenuBar();
    private static final JMenu menu = new JMenu(App.resourceBundle.getString("menu"));
    private static final JMenuItem einstellungen = new JMenuItem();
    private static final JMenuItem home = new JMenuItem();
    private static final JMenuItem logout = new JMenuItem();


    private static Person angemeldetePerson;

    /**
     * Konstruktor der Klasse VertragsuebersichtsLayer
     * Laedt das UI der Vertragsuebersichtsseite
     * @param person die in der App angemeldete Person
     */
    public VertragsuebersichtsLayer(Person person){
        angemeldetePerson = person;
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        MasterController.fenster.validate();
        MasterController.fenster.repaint();

    }

    /**
    * laedt die von der Person gebuchten Vertraege ins GUI
    */
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
                JLabel betragText = new JLabel(App.resourceBundle.getString("payment.amount"));
                JLabel betrag = new JLabel(person.getVertraege().get(i).getEURBetrag()+App.resourceBundle.getString("currency"));
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

    static void setStrings(){
        einstellungen.setText(App.resourceBundle.getString("settings"));
        home.setText(App.resourceBundle.getString("home"));
        logout.setText(App.resourceBundle.getString("logout"));
        label5.setText(App.resourceBundle.getString("welcome.to.your.contract.overview"));
        button6.setText("+");
        add.removeAll();
        if (angemeldetePerson != null)
            addVorhandeneVertraegetoGUI(angemeldetePerson);
        add.add(label5);
        add.add(button6);
    }

    @Override
    public void setLayout() {
        add.setLayout(new GridLayout(0,1));
    }

    @Override
    public void addListeners() {
        removeListeners();
        einstellungen.addActionListener(e->einstellungenWurdenAusgewaelt());
        logout.addActionListener(e->logoutWurdeAusgewaelt());
        home.addActionListener(e->vertragsuebersichtWurdeAusgewaelt());
        button6.addActionListener(e->vertraghinzufuegenWurdeGedrueckt());
    }

    public void removeListeners() {
        for (ActionListener listener : einstellungen.getActionListeners())
            einstellungen.removeActionListener(listener);
        for (ActionListener listener : logout.getActionListeners())
            logout.removeActionListener(listener);
        for (ActionListener listener : home.getActionListeners())
            home.removeActionListener(listener);
        for (ActionListener listener : button6.getActionListeners())
            button6.removeActionListener(listener);
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
        MasterController.fenster.setJMenuBar(menuBar);
        MasterController.fenster.add(add);
    }

        @Override
        public void removePanelsFromFrame() {
            MasterController.fenster.remove(add);
        }

    /**
     * leadt die Vertragsbuchungsseite wenn der Vertrag Hinzufuegen Konopf gedrueckt wurde
     */
        public void vertraghinzufuegenWurdeGedrueckt(){
            MasterController.fenster.remove(add);
            App.masterController.loadVertragHinzufuegenLayer(angemeldetePerson);
            MasterController.fenster.validate();
    }

    public void removeAllPanelsFromFrame(){
        removePanelsFromFrame();
        MasterController.fenster.remove(VertragHinzufuegenLayer.neueIBAN);
        MasterController.fenster.remove(VertragHinzufuegenLayer.hinzugefuegt);
        MasterController.fenster.remove(EinstellungsLayer.einstellungen);
        MasterController.fenster.remove(EinstellungsLayer.datenAendern);
        MasterController.fenster.remove(EinstellungsLayer.geburtsdatum);
        MasterController.fenster.remove(VertragHinzufuegenLayer.vertragsDaten);
        MasterController.fenster.remove(VertragHinzufuegenLayer.hinzufuegen);
        MasterController.fenster.remove(VertragHinzufuegenLayer.preise);
    }

    /**
     * laedt die Einstellungsseite falls ueber das Menu Einstellungen ausgewaehlt wurde
     */
    public void einstellungenWurdenAusgewaelt(){
        removeAllPanelsFromFrame();
        App.masterController.loadEinstellungsLayer(angemeldetePerson);
        MasterController.fenster.validate();
    }

    /**
     * laedt die Vertragsuebersicht, falls ueber das Menu Vertragsuebersicht ausgewahlt wurde
     */
    public void vertragsuebersichtWurdeAusgewaelt(){
        removeAllPanelsFromFrame();
        App.masterController.loadVertragsuebersicht(angemeldetePerson);
        MasterController.fenster.validate();
    }

    /**
     * loggt die angemeldetet Person aus
     * Die Startseite wird angezeigt
     * Dort werden alle vorherigen Eingaben zurueckgesetzt
     * Die MenuBar wir ausgeblendet
     */
    public void logoutWurdeAusgewaelt(){
        removeAllPanelsFromFrame();
        MasterController.fenster.setJMenuBar(null);
        angemeldetePerson = null;
        App.masterController.loadStartLayer();
        MasterController.fenster.validate();
        AnmeldeLayer.resetLogin();
    }
}
