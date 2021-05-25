package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;
/**
* Die Klasse laedt da UI des Startfensters
 * Das ist die Seite welche beim Starten der App angezeigt wird
*/
public class StartLayer implements SwingPresentation {

    private static final JPanel willkommenPanel = new JPanel();
    private static final JPanel eingangsButtonsPanel = new JPanel();
    static final JPanel sprache = new JPanel();
    private static String[] sprachen;
    private static JComboBox<String> spracheWaelen;

    /**
     * Konstruktor der Klasse StartLayer
     * Setzt das UI des Startfensters
     */
    public StartLayer(){
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        MasterController.fenster.validate();
        MasterController.fenster.repaint();
    }


    public void setLayout(){
        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));
    }

    public void addListeners(){
        removeListeners();
        button2.addActionListener(e-> registrierenFensterButtonWurdeGedrueckt());
        spracheWaelen.addItemListener(e->spracheWurdeGeaendert(e));
        button1.addActionListener(e->anmeldenFensterButtonWurdeGedrueckt());
    }


    public void removeListeners() {
        for (ActionListener listener : button1.getActionListeners())
            button1.removeActionListener(listener);

        for (ActionListener listener : button2.getActionListeners())
            button2.removeActionListener(listener);

        for (ItemListener listener : spracheWaelen.getItemListeners())
            spracheWaelen.removeItemListener(listener);
    }

    public void addComponentsToPanels(){
        sprache.removeAll();
        sprache.add(spracheWaelen);
        willkommenPanel.add(label1);
        eingangsButtonsPanel.add(button2);
        eingangsButtonsPanel.add(button1);
    }
    

    public void addPanelsToFrame(){
        MasterController.fenster.add(sprache);
        MasterController.fenster.add(willkommenPanel);
        MasterController.fenster.add(eingangsButtonsPanel);
    }

    public void removePanelsFromFrame() {
        MasterController.fenster.remove(willkommenPanel);
        MasterController.fenster.remove(eingangsButtonsPanel);
    }

    static void setStrings(){
        button1.setText(App.resourceBundle.getString("sign.in"));
        label1.setText(App.resourceBundle.getString("welcome.to.our.app"));
        button2.setText(App.resourceBundle.getString("register"));
        sprachen = new String[]{App.resourceBundle.getString("choose.your.language"), App.resourceBundle.getString("german"), App.resourceBundle.getString("english")};
        spracheWaelen = new JComboBox<>(sprachen);
    }
    /**
    * ruft die Registrierseite auf
    */
    void registrierenFensterButtonWurdeGedrueckt(){
        removePanelsFromFrame();
        App.masterController.loadRegistrierLayer();
    }

    /**
     * ruft die Anmeldeseite auf
     */
    void anmeldenFensterButtonWurdeGedrueckt(){
        App.masterController.loadAnmeldeLayer();
        removePanelsFromFrame();
    }

    /**
     * Aendert die Anzeigesprache des UIs
     * @param e gibt mit welche Sprache in der ComboBox ausgewaehlt wurde
     */
    void spracheWurdeGeaendert(ItemEvent e){
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        if(cb.getSelectedIndex() == 1){
           setSpracheToGerman();
        }
        else if(cb.getSelectedIndex() == 2){
            setSpracheToEnglish();
        }
        App.masterController.resetAllStrings();
    }

    /**
     * setzt die UI Sprache auf Deutsch
     */
    void setSpracheToGerman(){
        Locale.setDefault(Locale.GERMAN);
        App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
    }
    
    /**
    * setzt die UI Sprache auf Englisch
    */
    void setSpracheToEnglish(){
        Locale.setDefault(Locale.ENGLISH);
        App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
    }

    /**
     * setzt alle Strings zurueck
     */
    void resetAllStrings(){
        setStrings();
        RegistrierLayer.setStrings();
        AnmeldeLayer.setStrings();
        EinstellungsLayer.setStrings();
        VertragHinzufuegenLayer.setStrings();
        VertragsuebersichtsLayer.setStrings();
    }
}

