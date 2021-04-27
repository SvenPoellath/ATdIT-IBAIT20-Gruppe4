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

    private final JPanel vertragsDaten = new JPanel();
    private final JPanel hinzufuegen = new JPanel();
    private final JPanel preise = new JPanel();


    private final JLabel versicherungArtText = new JLabel(App.resourceBundle.getString("insurance.type"));
    private final JLabel buchungsArtText = new JLabel(App.resourceBundle.getString("payment.type"));
    private final JLabel tageText = new JLabel(App.resourceBundle.getString("days.of.visiting"));
    private final JLabel landText = new JLabel(App.resourceBundle.getString("country"));
    private final JLabel preisMonatlich = new JLabel(monatlicherPreis+App.resourceBundle.getString("currency"));
    private final JLabel preisJahr = new JLabel(jahresPreis+App.resourceBundle.getString("currency"));


    private JComboBox <String> versicherungsArt;
    private JComboBox <String> buchungsArt;
    private JComboBox <Integer> anzahlDerTage;
    private JComboBox <String> land;

    JButton hinzufuegenButton = new JButton(App.resourceBundle.getString("add.contract"));
    JButton preis = new JButton(App.resourceBundle.getString("get.price"));
    static Integer tagesPreis;
    static int betrag;
    private Person angemeldetePerson;

    public VertragHinzufuegen(Person person){
        angemeldetePerson = person;
        vertragsDaten.setLayout(new GridLayout(0,2));
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
                    if(buchungsArt.getSelectedIndex()==1)
                        preise.add(preisMonatlich);
                        betrag = monatlicherPreis;
                    if(buchungsArt.getSelectedIndex()==2)
                        preise.add(preisJahr);
                        betrag = jahresPreis;
                    if(buchungsArt.getSelectedIndex()==3){
                        tagesPreis = (buchungsArt.getSelectedIndex()+1)*50;
                        JLabel preisEinmalig = new JLabel(tagesPreis+App.resourceBundle.getString("currency"));
                        preise.add(preisEinmalig);
                        betrag = tagesPreis;
                    }

                }
                hinzufuegen.remove(preis);
                hinzufuegen.add(hinzufuegenButton);
            }
            if(e.getSource().equals(hinzufuegenButton)) {

                BasicVertrag vertrag = new BasicVertrag((String) versicherungsArt.getSelectedItem(), (int) Math.random(),(String) buchungsArt.getSelectedItem(),betrag);
                angemeldetePerson.getSozialversicherungsnummer();
            }
        }
    }
    class ComboBoxListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource().equals(versicherungsArt)) {
                vertragsDaten.add(buchungsArtText);
                vertragsDaten.add(buchungsArt);
            }
            else if(e.getSource().equals(buchungsArt)){
                if(e.getItem().equals(App.resourceBundle.getString("per.trip"))) {
                    vertragsDaten.add(tageText);
                    vertragsDaten.add(anzahlDerTage);
                    vertragsDaten.add(landText);
                    vertragsDaten.add(land);
                }
            }
        }
    }

}
