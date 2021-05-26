package atdit_ibait_20.database.presentation;

import javax.swing.*;
/** 
* Legt fest welche Bestandteile das Layout haben kann
 * Die UI Elemente werden in den jeweiligen Klassen gesetzt
*/
public interface SwingPresentation {

    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();
    JButton button7 = new JButton();
    JButton button8 = new JButton();
    JButton button9 = new JButton();
    JButton button10 = new JButton();
    JButton button11 = new JButton();
    JButton button12 = new JButton();
    JButton button13 = new JButton();

    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();
    JLabel label5 = new JLabel();
    JLabel label6 = new JLabel();
    JLabel label7 = new JLabel();
    JLabel label8 = new JLabel();
    JLabel label9 = new JLabel();
    JLabel label10 = new JLabel();
    JLabel label11 = new JLabel();
    JLabel label12 = new JLabel();
    JLabel label13 = new JLabel();
    JLabel label14 = new JLabel();
    JLabel label15 = new JLabel();
    JLabel label16 = new JLabel();

    JTextField textField1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    JTextField textField5 = new JTextField();
    JTextField textField6 = new JTextField();
    JTextField textField7 = new JTextField();
    JTextField textField8 = new JTextField();
    JTextField textField9 = new JTextField();
    JTextField textField10 = new JTextField();

    /**
     *setzt die Texte fuer die UI Felder
     */
    static void setStrings() {}

    /**
     * erstellt das Grundraster des Layouts
     */
    void setLayout();

    /**
     * weisst jedem Button den entsprechenden Listener zu
     */
    void addListeners();

    /**
     * Die UI Komponenten werden auf die Panels geladen
     */
    void addComponentsToPanels();

    /**
     * Die Panels werden zum Frame hinzugefuegt
     */
    void addPanelsToFrame();

    /**
     * Es werden alle Panels entfernt um eine neue Seite laden zu koennen
     */
    void removePanelsFromFrame();

    /**
     * Es werden alle Textfelder geleert um beim erneuten laden einer Seite die vorherigen Einträge nicht mehr angezeigt
     * zu bekommen
     */
    static void clearAllTextFields(){
        textField1.setText(null);
        textField2.setText(null);
        textField3.setText(null);
        textField4.setText(null);
        textField5.setText(null);
        textField6.setText(null);
        textField7.setText(null);
        textField8.setText(null);
        textField9.setText(null);
        textField10.setText(null);
    }
}
