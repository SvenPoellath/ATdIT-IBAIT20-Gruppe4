package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicVertrag;
import atdit_ibait_20.database.presentation.SwingPresentation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static atdit_ibait_20.database.App.DATABASE;

    /**
    * Legt fest welche Elemente das Layout enthalten soll, wenn man einen Vertrag hinzufügen will.
    **/
public class VertragHinzufuegen implements SwingPresentation {
    static final int monatlicherPreis = 300;
    static final int jahresPreis =3000;

    static final JPanel vertragsDaten = new JPanel();
    static final JPanel hinzufuegen = new JPanel();
    static final JPanel preise = new JPanel();
    static final JPanel neueIBAN = new JPanel();
    static final JPanel hinzugefuegt = new JPanel();


    private static final JLabel buchungsArtText = new JLabel();
    private static final JLabel tageText = new JLabel();
    private static final JLabel landText = new JLabel();
    private static final JLabel preisMonatlich = new JLabel();
    private static final JLabel preisJahr = new JLabel();
    private static final JLabel falscheIBAN = new JLabel();
    private static final JLabel versicherungArtText = new JLabel();
    private static final JLabel IBAN = new JLabel("IBAN");
    private static final JLabel fertig = new JLabel();

    private final JTextField tfIBAN = new JTextField();

    private static String[] versicherungsArten;
    private static String[] buchungsArten;
    private static Integer[] tage;
    private static String[] laender;

    private static JComboBox <String> versicherungsArt;
    private static JComboBox <String> buchungsArt;
    private static JComboBox <Integer> anzahlDerTage;
    private static JComboBox <String> land;

    private static final JButton hinzufuegenButton = new JButton();
    private static final JButton preis = new JButton();
    private static final JButton addIBAN = new JButton();

    private static Integer tagesPreis;
    static int betrag;
    private final Person angemeldetePerson;

    public VertragHinzufuegen(Person person){
        angemeldetePerson = person;
        tagesPreis=50;
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        setFrame();
        StartLayer.fenster.validate();
        StartLayer.fenster.repaint();
    }

    /**
    * @setStringt legt fest welche Schriftzüge die einzelnen Elemente tragen
    **/
    static void setStrings(){
        buchungsArtText.setText(App.resourceBundle.getString("payment.type"));
        tageText.setText(App.resourceBundle.getString("days.of.visiting"));
        landText.setText(App.resourceBundle.getString("country"));
        preisMonatlich.setText(monatlicherPreis+App.resourceBundle.getString("currency"));
        preisJahr.setText(jahresPreis+App.resourceBundle.getString("currency"));
        falscheIBAN.setText(App.resourceBundle.getString("failed.to.add.IBAN"));
        hinzufuegenButton.setText(App.resourceBundle.getString("add.contract"));
        preis.setText(App.resourceBundle.getString("get.price"));
        addIBAN.setText(App.resourceBundle.getString("add.iban"));
        versicherungsArten = new String[]{"*",App.resourceBundle.getString("luggage.insurance")};
        versicherungArtText.setText(App.resourceBundle.getString("insurance.type"));
        fertig.setText(App.resourceBundle.getString("successfully.added.new.contract"));
        buchungsArten = new String[]{"*",App.resourceBundle.getString("monthly"),App.resourceBundle.getString("yearly"),App.resourceBundle.getString("per.trip")};
        tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        laender = new String[]{App.resourceBundle.getString("germany"),App.resourceBundle.getString("usa"),App.resourceBundle.getString("portugal")};
        anzahlDerTage = new JComboBox<>(tage);
        land = new JComboBox<>(laender);
        versicherungsArt = new JComboBox<>(versicherungsArten);
        buchungsArt = new JComboBox<>(buchungsArten);
    }

    /**
    * @setFrame Legt die Größe des Fensters, welches dem Nutzer angezeigt wird fest
    **/
    @Override
    public void setFrame() {
        StartLayer.fenster.setSize(400,400);
    }

    /**
    * @setLayout Legt fest welche Art das Layout haben soll
    **/
    @Override
    public void setLayout() {
        vertragsDaten.setLayout(new GridLayout(0,2));
        neueIBAN.setLayout(new GridLayout(0,2));
        preise.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        falscheIBAN.setForeground(Color.red);
    }

    /**
    * @addListener Verbindet die Elemtente mit der Art ihres Auswahlmenüs
    **/
    @Override
    public void addListeners() {
        removeListeners();
        buchungsArt.addItemListener(e->this.buchungsArtWurdeGeaendert(e));
        versicherungsArt.addItemListener(e->this.versicherungsArtWurdeGeaendert());
        hinzufuegenButton.addActionListener(e->this.hinzufuegeButtonWurdeGedrueckt());
        preis.addActionListener(e->this.preisButtonWurdeGedrueckt());
        addIBAN.addActionListener(e->this.IBANButtonWurdeGedrueckt());
    }

    public void removeListeners() {
        for (ItemListener listener : buchungsArt.getItemListeners())
            buchungsArt.removeItemListener(listener);

        for (ItemListener listener : versicherungsArt.getItemListeners())
            versicherungsArt.removeItemListener(listener);

        for (ActionListener listener : hinzufuegenButton.getActionListeners())
            hinzufuegenButton.removeActionListener(listener);

        for (ActionListener listener : preis.getActionListeners())
            preis.removeActionListener(listener);

        for (ActionListener listener : addIBAN.getActionListeners())
            addIBAN.removeActionListener(listener);
    }

    @Override
    public void addComponentsToPanels() {
        vertragsDaten.removeAll();
        hinzufuegen.removeAll();
        preise.removeAll();
        neueIBAN.removeAll();
        hinzugefuegt.removeAll();
        vertragsDaten.add(versicherungArtText);
        vertragsDaten.add(versicherungsArt);
        hinzufuegen.add(preis);
        neueIBAN.add(IBAN);
        neueIBAN.add(tfIBAN);
        neueIBAN.add(addIBAN);
        hinzugefuegt.add(fertig);
    }

    @Override
    public void addPanelsToFrame() {
        StartLayer.fenster.add(vertragsDaten);
        StartLayer.fenster.add(preise);
        StartLayer.fenster.add(hinzufuegen);
    }


        public void removePanelsFromFrame() {
            StartLayer.fenster.remove(vertragsDaten);
            StartLayer.fenster.remove(preise);
            StartLayer.fenster.remove(hinzufuegen);
        }

        /**
    * Legt die Funktionsweise fest was passiert wenn der Nutzer den Hinzufügen Button drückt
    **/


    public void preisButtonWurdeGedrueckt(){
        preise.removeAll();
        setPreis();
        hinzufuegen.add(hinzufuegenButton);
        StartLayer.fenster.validate();
    }
    public void hinzufuegeButtonWurdeGedrueckt(){
        pruefenObIBANVorhanden();
    }
    public void IBANButtonWurdeGedrueckt(){
        eingegebeneIBANPruefen();
        StartLayer.fenster.validate();
    }
    public void versicherungsArtWurdeGeaendert(){
        displayBuchungsartOptionen();
        StartLayer.fenster.validate();
    }
    public void buchungsArtWurdeGeaendert(ItemEvent e){
        hideAngabenZurReise();
        StartLayer.fenster.validate();
        if(e.getItem().equals(App.resourceBundle.getString("per.trip"))) {
            displayAngabenZurReise();
            StartLayer.fenster.setSize(600,400);
        }
    }
    public void displayBuchungsartOptionen(){
        vertragsDaten.add(buchungsArtText);
        vertragsDaten.add(buchungsArt);
    }
    public void displayAngabenZurReise(){
        vertragsDaten.add(tageText);
        vertragsDaten.add(anzahlDerTage);
        vertragsDaten.add(landText);
        vertragsDaten.add(land);
    }
    public void hideAngabenZurReise(){
        vertragsDaten.remove(tageText);
        vertragsDaten.remove(anzahlDerTage);
        vertragsDaten.remove(landText);
        vertragsDaten.remove(land);
    }
    public void setPreis(){
        if(versicherungsArt.getSelectedItem().equals(App.resourceBundle.getString("luggage.insurance"))){
            setPreisForLuggageInsurance();
        }
    }
    /**
    * @ZeitraumIf Gibt die monatlichen oder jährlichen Preise der Versicherung aus
    **/
    public void setPreisForLuggageInsurance(){
        if(buchungsArt.getSelectedItem().equals(App.resourceBundle.getString("monthly"))) {
            preise.add(preisMonatlich);
            betrag = monatlicherPreis;
        }
        else if(buchungsArt.getSelectedItem().equals(App.resourceBundle.getString("yearly"))) {
            preise.add(preisJahr);
            betrag = jahresPreis;
        }
        else if(buchungsArt.getSelectedItem().equals(App.resourceBundle.getString("per.trip"))){
            tagesPreis = (anzahlDerTage.getSelectedIndex()+1)*50;
            JLabel preisEinmalig = new JLabel(tagesPreis + App.resourceBundle.getString("currency"));
            preise.add(preisEinmalig);
            betrag = tagesPreis;
        }
    }
    /**
    * @PersonenIf fügt bei der Person die IBAN zu bzw. ändert sie
    **/
    public void pruefenObIBANVorhanden(){
        if(angemeldetePerson.getIBAN()==null){
            displayIBANHinzufuegePanel();
        }
        else if (angemeldetePerson.getIBAN()!=null){
            neueIBANHinzufuegen();
        }
    }
    public void displayIBANHinzufuegePanel(){
        StartLayer.fenster.remove(hinzufuegen);
        StartLayer.fenster.add(neueIBAN);
        StartLayer.fenster.add(hinzufuegen);
        StartLayer.fenster.validate();
    }
    public void neueIBANHinzufuegen(){
        System.out.println("Contract added.");
        BasicVertrag vertrag = new BasicVertrag(versicherungsArt.getSelectedItem().toString(),buchungsArt.getSelectedItem().toString(),betrag);
        DATABASE.create_contract_entry(vertrag,angemeldetePerson.getSozialversicherungsnummer());
        angemeldetePerson.addVertrag(vertrag);
        StartLayer.fenster.add(hinzugefuegt);
        StartLayer.fenster.validate();
    }

    /**
    * @IBANIf überträgt die Änderung der IBAN an die Datenbank
    **/
    public void eingegebeneIBANPruefen(){
        if(tfIBAN.getText().length()==22) {
            angemeldetePerson.setIBAN(tfIBAN.getText());
            DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "IBAN", angemeldetePerson.getIBAN());
            neueIBAN.remove(falscheIBAN);
            StartLayer.fenster.remove(neueIBAN);
            StartLayer.fenster.validate();
        } else{
            neueIBAN.add(falscheIBAN);
        }
    }
}
