package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicVertrag;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VertragHinzufuegen {
    final int monatlicherPreis = 300;
    final int jahresPreis =3000;

    static final JPanel vertragsDaten = new JPanel();
    static final JPanel hinzufuegen = new JPanel();
    static final JPanel preise = new JPanel();
    static final JPanel neueIBAN = new JPanel();
    static final JPanel hinzugefuegt = new JPanel();


    private final JLabel versicherungArtText = new JLabel(App.resourceBundle.getString("insurance.type"));
    private final JLabel buchungsArtText = new JLabel(App.resourceBundle.getString("payment.type"));
    private final JLabel tageText = new JLabel(App.resourceBundle.getString("days.of.visiting"));
    private final JLabel landText = new JLabel(App.resourceBundle.getString("country"));
    private final JLabel preisMonatlich = new JLabel(monatlicherPreis+App.resourceBundle.getString("currency"));
    private final JLabel preisJahr = new JLabel(jahresPreis+App.resourceBundle.getString("currency"));
    private final JLabel IBAN = new JLabel("IBAN");
    private final JLabel fertig = new JLabel(App.resourceBundle.getString("successfully.added.new.contract"));

    JTextField tfIBAN = new JTextField();

    private JComboBox <String> versicherungsArt;
    private JComboBox <String> buchungsArt;
    private JComboBox <Integer> anzahlDerTage;
    private JComboBox <String> land;

    JButton hinzufuegenButton = new JButton(App.resourceBundle.getString("add.contract"));
    JButton preis = new JButton(App.resourceBundle.getString("get.price"));
    JButton addIBAN = new JButton(App.resourceBundle.getString("add.iban"));
    static Integer tagesPreis;
    static int betrag;
    private Person angemeldetePerson;
    JLabel preisEinmalig;
    public VertragHinzufuegen(Person person){
        angemeldetePerson = person;
        vertragsDaten.setLayout(new GridLayout(0,2));
        neueIBAN.setLayout(new GridLayout(0,2));
        preise.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));
        String[] versicherungsArten = new String[]{"*",App.resourceBundle.getString("luggage.insurance")};
        String[] buchungsArten = new String[]{"*",App.resourceBundle.getString("monthly"),App.resourceBundle.getString("yearly"),App.resourceBundle.getString("per.trip")};
        Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        String[] laender = new String[]{App.resourceBundle.getString("germany"),App.resourceBundle.getString("usa"),App.resourceBundle.getString("portugal")};
        anzahlDerTage = new JComboBox<>(tage);
        land = new JComboBox<>(laender);
        versicherungsArt = new JComboBox<>(versicherungsArten);
        buchungsArt = new JComboBox<>(buchungsArten);
        versicherungsArt.addItemListener(new ComboBoxListener());
        buchungsArt.addItemListener(new ComboBoxListener());
        hinzufuegenButton.addActionListener(new hinzufuegenButtonListener());
        preis.addActionListener(new hinzufuegenButtonListener());
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
    class hinzufuegenButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(preis)){
                if(versicherungsArt.getSelectedIndex()==1){
                    if(buchungsArt.getSelectedIndex()==1) {
                        preise.removeAll();
                        preise.add(preisMonatlich);
                        betrag = monatlicherPreis;
                    }
                    if(buchungsArt.getSelectedIndex()==2) {
                        preise.removeAll();
                        preise.add(preisJahr);
                        betrag = jahresPreis;
                    }
                    if(buchungsArt.getSelectedIndex()==3){
                        tagesPreis = (anzahlDerTage.getSelectedIndex()+1)*50;
                        preisEinmalig = new JLabel(tagesPreis+App.resourceBundle.getString("currency"));
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
                }else {
                    BasicVertrag vertrag = new BasicVertrag(versicherungsArt.getSelectedItem().toString(),buchungsArt.getSelectedItem().toString(),betrag);
                    angemeldetePerson.getSozialversicherungsnummer();
                    angemeldetePerson.addVertrag(vertrag);
                    StartLayer.fenster.add(hinzugefuegt);
                    StartLayer.fenster.validate();
                }
            }
            if(e.getSource().equals(addIBAN)){
                angemeldetePerson.setIBAN(tfIBAN.getText());
                neueIBAN.removeAll();
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
                if(e.getItem().equals(App.resourceBundle.getString("per.trip"))) {
                    vertragsDaten.add(tageText);
                    vertragsDaten.add(anzahlDerTage);
                    vertragsDaten.add(landText);
                    vertragsDaten.add(land);
                    StartLayer.fenster.setSize(600,400);
                }
            }
        }
    }

}
