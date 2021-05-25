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
* Die Klasse legt das Startfenster fest was erscheint, wenn der Nutzer die Applikation startet
*/
public class StartLayer implements SwingPresentation {




    private static final JPanel willkommenPanel = new JPanel();
    private static final JPanel eingangsButtonsPanel = new JPanel();
    static final JPanel sprache = new JPanel();
    private static String[] sprachen;
    private static JComboBox<String> spracheWaelen;


    public StartLayer(){
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        MasterController.fenster.validate();
        MasterController.fenster.repaint();
    }

    /**
    * @method Legt fest wie das Layout der Nutzeroberfläche aussieht
    */
    public void setLayout(){
        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));
    }
    /**
     * @method Fügt die Funktion der einzelnen Buttons dem Layout hinzu
     *
     * */
    public void addListeners(){
        removeListeners();
        button2.addActionListener(e-> registrierenFensterButtonWurdeGedrueckt());
        spracheWaelen.addItemListener(e->spracheWurdeGeaendert(e));
        button1.addActionListener(e->anmeldenFensterButtonWurdeGedrueckt());
    }

    /**
     * @method Entfernt die Funktion der einzelnen Buttons
     *
     * */
    public void removeListeners() {
        for (ActionListener listener : button1.getActionListeners())
            button1.removeActionListener(listener);

        for (ActionListener listener : button2.getActionListeners())
            button2.removeActionListener(listener);

        for (ItemListener listener : spracheWaelen.getItemListeners())
            spracheWaelen.removeItemListener(listener);
    }


    /**
    * @method verknüpft die Buttons mit den Funktionen die passieren sollen, wenn sie gedrückt werden
    */
    public void addComponentsToPanels(){
        sprache.removeAll();
        sprache.add(spracheWaelen);
        willkommenPanel.add(label1);
        eingangsButtonsPanel.add(button2);
        eingangsButtonsPanel.add(button1);
    }
    
    /**
    * @method fügt die Buttons dem Rahmen hinzu
    */
    public void addPanelsToFrame(){
        MasterController.fenster.add(sprache);
        MasterController.fenster.add(willkommenPanel);
        MasterController.fenster.add(eingangsButtonsPanel);
    }


    public void removePanelsFromFrame() {
        MasterController.fenster.remove(willkommenPanel);
        MasterController.fenster.remove(eingangsButtonsPanel);
    }

    /**
    * @method Legt die Schriftzüge der einzelnen Elemente des Layouts fest
    */
    static void setStrings(){
        button1.setText(App.resourceBundle.getString("sign.in"));
        label1.setText(App.resourceBundle.getString("welcome.to.our.app"));
        button2.setText(App.resourceBundle.getString("register"));
        sprachen = new String[]{App.resourceBundle.getString("choose.your.language"), App.resourceBundle.getString("german"), App.resourceBundle.getString("english")};
        spracheWaelen = new JComboBox<>(sprachen);
    }
    /**
    * Die Klasse liefert die Funktionalität, die es dem Nutzer ermöglicht die Applikation zu schließen
    */
    void registrierenFensterButtonWurdeGedrueckt(){
        removePanelsFromFrame();
        App.masterController.loadRegistrierLayer();
    }
    void anmeldenFensterButtonWurdeGedrueckt(){
        App.masterController.loadAnmeldeLayer();
        removePanelsFromFrame();
    }
    /**
    * @method ermöglicht es die Sprache zu ändern
    * @param cb
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
    * @method setzt die Sprache auf Deutsch
    */
    void setSpracheToGerman(){
        Locale.setDefault(Locale.GERMAN);
        App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
    }
    
    /**
    * @method setzt die Sprache auf Englisch
    */
    void setSpracheToEnglish(){
        Locale.setDefault(Locale.ENGLISH);
        App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
    }
}

