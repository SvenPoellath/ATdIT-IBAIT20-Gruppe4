package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicVertrag;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VertragHinzufuegen {
    private final JPanel vertragsDaten = new JPanel();
    private final JPanel hinzufuegen = new JPanel();
    private final JPanel preise = new JPanel();

    private final JLabel versicherungArtText = new JLabel(App.resourceBundle.getString("insurance.type"));
    private final JLabel buchungsArtText = new JLabel(App.resourceBundle.getString("payment.type"));
    private final JLabel tageText = new JLabel(App.resourceBundle.getString("days.of.visiting"));
    private final JLabel landText = new JLabel(App.resourceBundle.getString("country"));
    private final JLabel preisMonatlich = new JLabel("300€");
    private final JLabel preisJahr = new JLabel("3000€");


    private JComboBox versicherungsArt;
    private JComboBox buchungsArt;
    private JComboBox anzahlDerTage;
    private JComboBox land;

    JButton hinzufuegenButton = new JButton(App.resourceBundle.getString("add.contract"));
    JButton preis = new JButton(App.resourceBundle.getString("get.price"));

    private Person angemeldetePerson;

    public VertragHinzufuegen(Person person){
        angemeldetePerson = person;
        String[] versicherungsArten = new String[]{App.resourceBundle.getString("luggage.insurance")};
        String[] buchungsArten = new String[]{App.resourceBundle.getString("monthly"),App.resourceBundle.getString("yearly"),App.resourceBundle.getString("per.trip")};
        Integer[] tage = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        String[] laender = new String[]{App.resourceBundle.getString("germany"),App.resourceBundle.getString("usa"),App.resourceBundle.getString("portugal")};
        anzahlDerTage = new JComboBox(tage);
        land = new JComboBox(laender);
        versicherungsArt = new JComboBox(versicherungsArten);
        buchungsArt = new JComboBox(buchungsArten);
        versicherungsArt.addItemListener(new ComboBoxListener());
        buchungsArt.addItemListener(new ComboBoxListener());
        hinzufuegenButton.addActionListener(new hinzufuegenButtonListener());
       vertragsDaten.add(versicherungArtText);
       vertragsDaten.add(versicherungsArt);
       hinzufuegen.add(preis);
       StartLayer.fenster.add(vertragsDaten);
       StartLayer.fenster.add(preise);
       StartLayer.fenster.add(hinzufuegen);


    }
    class hinzufuegenButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(preis)){
                if(versicherungsArt.getSelectedIndex()==0){
                    if(buchungsArt.getSelectedIndex()==0)
                        preise.add(preisMonatlich);
                    if(buchungsArt.getSelectedIndex()==1)
                        preise.add(preisJahr);
                }
                hinzufuegen.removeAll();
                hinzufuegen.add(hinzufuegenButton);
            }
            if(e.getSource().equals(hinzufuegenButton)) {

                //BasicVertrag vertrag = new BasicVertrag(versicherungsArt.getSelectedItem(),Integer.parseInt(auftragsNummer.getText()),buchungsArt.getSelectedItem(),Integer.parseInt(betrag.getText()));
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
                vertragsDaten.add(tageText);
                vertragsDaten.add(anzahlDerTage);
                vertragsDaten.add(landText);
                vertragsDaten.add(land);
            }
        }
    }

}
