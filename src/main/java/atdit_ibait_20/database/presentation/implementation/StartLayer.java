package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class StartLayer{


    static final JFrame fenster = new JFrame();

    private final JPanel willkommenPanel = new JPanel();
    private final JPanel eingangsButtonsPanel = new JPanel();
    static final JPanel sprache = new JPanel();

    private static String[] sprachen;
    private static final JLabel willkommenSchrift = new JLabel();
    private static final JButton registrierenFensterButton = new JButton();
    private static final JButton anmeldenFensterButton = new JButton();

    public StartLayer(){
        setStringsInStartLayer();
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle(App.resourceBundle.getString("health.insurance.app"));
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(200, 300);
        fenster.setLayout(new FlowLayout());

        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));

        JComboBox<String> spracheWaelen = new JComboBox<>(sprachen);

        registrierenFensterButton.addActionListener(new AnfangsButtonListener());
        anmeldenFensterButton.addActionListener(new AnfangsButtonListener());
        spracheWaelen.addItemListener(new SpracheWaehlen());



        sprache.removeAll();
        sprache.add(spracheWaelen);
        willkommenPanel.add(willkommenSchrift);
        eingangsButtonsPanel.add(registrierenFensterButton);
        eingangsButtonsPanel.add(anmeldenFensterButton);
        fenster.add(sprache);
        fenster.add(willkommenPanel);
        fenster.add(eingangsButtonsPanel);
        fenster.validate();

    }
    private static void setStringsInStartLayer(){
        fenster.setTitle(App.resourceBundle.getString("health.insurance.app"));
        anmeldenFensterButton.setText(App.resourceBundle.getString("sign.in"));
        willkommenSchrift.setText(App.resourceBundle.getString("welcome.to.our.app"));
        registrierenFensterButton.setText(App.resourceBundle.getString("register"));
        sprachen = new String[]{App.resourceBundle.getString("choose.your.language"), App.resourceBundle.getString("german"), App.resourceBundle.getString("english")};
    }
    class AnfangsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fenster.remove(willkommenPanel);
            fenster.remove(eingangsButtonsPanel);
            if(e.getSource().equals(registrierenFensterButton)) {
                new RegistrierLayer();
            }
            else if(e.getSource().equals(anmeldenFensterButton)) {
                new AnmeldeLayer();
            }
        }
    }


    static class SpracheWaehlen implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            if(cb.getSelectedIndex()==1){
                Locale.setDefault(Locale.GERMAN);
                App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
            }
            else if(cb.getSelectedIndex()==2){
                Locale.setDefault(Locale.ENGLISH);
                App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
            }
            setStringsInStartLayer();
            RegistrierLayer.setStringsInRegistrierLayer();
            AnmeldeLayer.setStringsInAnmeldeLayer();
            Einstellungen.setStringsInEinstellungen();
            VertragHinzufuegen.setStringsInVertragHinzufuegen();
            Vertragsuebersicht.setStringsVertragsuebersicht();
        }
    }
}
