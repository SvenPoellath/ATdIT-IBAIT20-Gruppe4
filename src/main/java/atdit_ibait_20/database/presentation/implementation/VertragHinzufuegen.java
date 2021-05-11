package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.persistence.implementation.DatabaseService;
import atdit_ibait_20.database.model.implementation.BasicVertrag;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VertragHinzufuegen {
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

    private final JComboBox <String> versicherungsArt;
    private final JComboBox <String> buchungsArt;
    private final JComboBox <Integer> anzahlDerTage;
    private final JComboBox <String> land;

    private static final JButton hinzufuegenButton = new JButton();
    private static final JButton preis = new JButton();
    private static final JButton addIBAN = new JButton();

    private static Integer tagesPreis;
    static int betrag;
    private final Person angemeldetePerson;

    public VertragHinzufuegen(Person person){
        setStringsInVertragHinzufuegen();
        angemeldetePerson = person;
        vertragsDaten.setLayout(new GridLayout(0,2));
        neueIBAN.setLayout(new GridLayout(0,2));
        preise.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        vertragsDaten.removeAll();
        hinzufuegen.removeAll();
        preise.removeAll();
        neueIBAN.removeAll();
        hinzugefuegt.removeAll();
        falscheIBAN.setForeground(Color.red);
        anzahlDerTage = new JComboBox<>(tage);
        land = new JComboBox<>(laender);
        versicherungsArt = new JComboBox<>(versicherungsArten);
        buchungsArt = new JComboBox<>(buchungsArten);
        versicherungsArt.addItemListener(new ComboBoxListener());
        buchungsArt.addItemListener(new ComboBoxListener());
        hinzufuegenButton.addActionListener(new hinzufuegenButtonListener());
        preis.addActionListener(new hinzufuegenButtonListener());
        addIBAN.addActionListener(new hinzufuegenButtonListener());

        vertragsDaten.add(versicherungArtText);
        vertragsDaten.add(versicherungsArt);
        hinzufuegen.add(preis);
        neueIBAN.add(IBAN);
        neueIBAN.add(tfIBAN);
        neueIBAN.add(addIBAN);

        hinzugefuegt.add(fertig);
        StartLayer.fenster.setSize(400,400);
        StartLayer.fenster.add(vertragsDaten);
        StartLayer.fenster.add(preise);
        StartLayer.fenster.add(hinzufuegen);

        tagesPreis=50;
    }
    static void setStringsInVertragHinzufuegen(){
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

    }
    class hinzufuegenButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(preis)){
                if(versicherungsArt.getSelectedIndex()==1){
                    if(buchungsArt.getSelectedItem().equals(App.resourceBundle.getString("monthly"))) {
                        preise.removeAll();
                        preise.add(preisMonatlich);
                        betrag = monatlicherPreis;
                    }
                    if(buchungsArt.getSelectedItem().equals(App.resourceBundle.getString("yearly"))) {
                        preise.removeAll();
                        preise.add(preisJahr);
                        betrag = jahresPreis;
                    }
                    if(buchungsArt.getSelectedItem().equals(App.resourceBundle.getString("per.trip"))){
                        tagesPreis = (anzahlDerTage.getSelectedIndex()+1)*50;
                        JLabel preisEinmalig = new JLabel(tagesPreis + App.resourceBundle.getString("currency"));
                        preise.removeAll();
                        preise.add(preisEinmalig);
                        betrag = tagesPreis;
                    }

                }
                hinzufuegen.add(hinzufuegenButton);
                StartLayer.fenster.validate();
            }
            if(e.getSource().equals(hinzufuegenButton)) {

                if(angemeldetePerson.getIBAN()==null){
                    StartLayer.fenster.remove(hinzufuegen);
                    StartLayer.fenster.add(neueIBAN);
                    StartLayer.fenster.add(hinzufuegen);
                    StartLayer.fenster.validate();
                }
                else if (angemeldetePerson.getIBAN()!=null){
                    System.out.println("Contract added.");
                    BasicVertrag vertrag = new BasicVertrag(versicherungsArt.getSelectedItem().toString(),buchungsArt.getSelectedItem().toString(),betrag);
                    DatabaseService.create_contract_entry(vertrag,angemeldetePerson.getSozialversicherungsnummer());
                    angemeldetePerson.addVertrag(vertrag);
                    StartLayer.fenster.add(hinzugefuegt);
                    StartLayer.fenster.validate();
                }
            }
            if(e.getSource().equals(addIBAN)){
                if(tfIBAN.getText().length()==22) {
                    angemeldetePerson.setIBAN(tfIBAN.getText());
                    DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "IBAN", angemeldetePerson.getIBAN());
                    neueIBAN.removeAll();
                } else{
                    neueIBAN.add(falscheIBAN);
                }
                StartLayer.fenster.validate();
            }
        }
    }
    class ComboBoxListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource().equals(versicherungsArt)) {
                vertragsDaten.add(buchungsArtText);
                vertragsDaten.add(buchungsArt);
                StartLayer.fenster.validate();
            }
            else if(e.getSource().equals(buchungsArt)){
                vertragsDaten.remove(tageText);
                vertragsDaten.remove(anzahlDerTage);
                vertragsDaten.remove(landText);
                vertragsDaten.remove(land);
                if(e.getItem().equals(App.resourceBundle.getString("per.trip"))) {
                    vertragsDaten.add(tageText);
                    vertragsDaten.add(anzahlDerTage);
                    vertragsDaten.add(landText);
                    vertragsDaten.add(land);
                    StartLayer.fenster.setSize(600,400);
                }
                StartLayer.fenster.validate();
            }
        }
    }

}
