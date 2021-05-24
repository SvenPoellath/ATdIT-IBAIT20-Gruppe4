package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static atdit_ibait_20.database.App.DATABASE;

/**
* In der Klasse wird das Layout für die EinstellungsLayer festgelegt. Es werden Textfelder angelegt und Buttons,
* die dem Nutzer später die Möglichkeit bieten sollen seine Nutzerdaten zu ändern.
**/
public class EinstellungsLayer implements SwingPresentation {
    static final JPanel einstellungen = new JPanel();
    static final JPanel datenAendern = new JPanel();
    static final JPanel geburtsdatum = new JPanel();


    static Person angemeldetePerson;
    public EinstellungsLayer(Person person){
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
* @setString ermöglicht es dem Nutzer seine Daten zu ändern.
**/
    static void setStrings(){
        label1.setText(App.resourceBundle.getString("welcome.to.your.settings"));
        label2.setText(App.resourceBundle.getString("make.changes.here"));
        label3.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("first.name"));
        label4.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("last.name"));
        label5.setText(App.resourceBundle.getString("new.2")+" "+App.resourceBundle.getString("date.of.birth"));
        label6.setText(App.resourceBundle.getString("new.2")+" "+App.resourceBundle.getString("password"));
        label7.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("title"));
        label8.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("postcode"));
        label9.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("city"));
        label10.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("street"));
        label11.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("house.number"));
        label12.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("marital.status"));
        label13.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("e.mail.address"));
        label14.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("phone.number"));
        button13.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("nationality"));

        button1.setText(App.resourceBundle.getString("first.name")+" "+App.resourceBundle.getString("change"));
        button2.setText(App.resourceBundle.getString("last.name")+" "+App.resourceBundle.getString("change"));
        button3.setText(App.resourceBundle.getString("date.of.birth")+" "+App.resourceBundle.getString("change"));
        button4.setText(App.resourceBundle.getString("password")+" "+App.resourceBundle.getString("change"));
        button5.setText(App.resourceBundle.getString("title")+" "+App.resourceBundle.getString("change"));
        button6.setText(App.resourceBundle.getString("postcode")+" "+App.resourceBundle.getString("change"));
        button7.setText(App.resourceBundle.getString("city")+" "+App.resourceBundle.getString("change"));
        button8.setText(App.resourceBundle.getString("street")+" "+App.resourceBundle.getString("change"));
        button9.setText(App.resourceBundle.getString("house.number")+" "+App.resourceBundle.getString("change"));
        button10.setText(App.resourceBundle.getString("marital.status")+" "+App.resourceBundle.getString("change"));
        button11.setText(App.resourceBundle.getString("change")+" "+App.resourceBundle.getString("e.mail.address"));
        button12.setText(App.resourceBundle.getString("phone.number")+" "+App.resourceBundle.getString("change"));
        button13.setText(App.resourceBundle.getString("nationality")+" "+App.resourceBundle.getString("change"));

        if (angemeldetePerson != null) {
            RegistrierLayer.cbAnrede.setSelectedItem(angemeldetePerson.getAnrede());
            textField1.setText(angemeldetePerson.getVorname());
            textField2.setText(angemeldetePerson.getNachname());
            textField4.setText(String.valueOf(angemeldetePerson.getPLZ()));
            textField5.setText(angemeldetePerson.getOrt());
            textField6.setText(angemeldetePerson.getStrasse());
            textField7.setText(angemeldetePerson.getHausnummer());
            textField9.setText(angemeldetePerson.getMailAdresse());
            textField8.setText("0" + angemeldetePerson.getTelefonnummer());
            RegistrierLayer.cbFamilienstand.setSelectedItem(angemeldetePerson.getFamilienstand());
            textField10.setText(angemeldetePerson.getStaatsangehoerigkeit());

            BasicGeburtsdatum transfer = new BasicGeburtsdatum(angemeldetePerson.getGeburtsdatum());
            RegistrierLayer.GeburtsdatumTag.setSelectedItem(transfer.getGeburtsdatumTag());
            RegistrierLayer.GeburtsdatumMonat.setSelectedItem(transfer.getGeburtsdatumMonat());
            RegistrierLayer.GeburtsdatumJahr.setSelectedItem(transfer.getGeburtsdatumJahr());
        }
    }

    @Override
    public void setLayout() {
        einstellungen.setLayout(new GridLayout(0,1));
        datenAendern.setLayout(new GridLayout(0,3));
        geburtsdatum.setLayout(new GridLayout(0,5));
    }

    @Override
    public void addListeners() {
        removeListeners();
        button1.addActionListener(e->vornameAendernWurdeAusgewaelt());
        button2.addActionListener(e->nachnameAendernWurdeAusgewaelt());
        button3.addActionListener(e->geburtsdatumAendernWurdeAusgewaelt());
        button4.addActionListener(e->passwortAendernWurdeAusgewaelt());
        button5.addActionListener(e->anredeAendernWurdeAusgewaelt());
        button6.addActionListener(e->plzAendernWurdeAusgewaelt());
        button7.addActionListener(e->ortAendernWurdeAusgewaelt());
        button8.addActionListener(e->strasseAendernWurdeAusgewaelt());
        button9.addActionListener(e->hausnummerAendernWurdeAusgewaelt());
        button10.addActionListener(e->familienstandAendernWurdeAusgewaelt());
        button11.addActionListener(e->mailAendernWurdeAusgewaelt());
        button13.addActionListener(e->staatsangehoerigkeitAendernWurdeAusgewaelt());
        button12.addActionListener(e->telefonnummerAendernWurdeAusgewaelt());
    }

    public void removeListeners() {
        for (ActionListener listener : button1.getActionListeners())
            button1.removeActionListener(listener);
        for (ActionListener listener : button2.getActionListeners())
            button2.removeActionListener(listener);
        for (ActionListener listener : button3.getActionListeners())
            button3.removeActionListener(listener);
        for (ActionListener listener : button4.getActionListeners())
            button4.removeActionListener(listener);
        for (ActionListener listener : button5.getActionListeners())
            button5.removeActionListener(listener);
        for (ActionListener listener : button6.getActionListeners())
            button6.removeActionListener(listener);
        for (ActionListener listener : button7.getActionListeners())
            button7.removeActionListener(listener);
        for (ActionListener listener : button8.getActionListeners())
            button8.removeActionListener(listener);
        for (ActionListener listener : button9.getActionListeners())
            button9.removeActionListener(listener);
        for (ActionListener listener : button10.getActionListeners())
            button10.removeActionListener(listener);
        for (ActionListener listener : button11.getActionListeners())
            button11.removeActionListener(listener);
        for (ActionListener listener : button13.getActionListeners())
            button13.removeActionListener(listener);
        for (ActionListener listener : button12.getActionListeners())
            button12.removeActionListener(listener);
    }


    @Override
    public void addComponentsToPanels() {
        einstellungen.add(label1);
        einstellungen.add(label2);
        datenAendern.add(label7);
        datenAendern.add(RegistrierLayer.cbAnrede);
        datenAendern.add(button5);
        datenAendern.add(label3);
        datenAendern.add(textField1);
        datenAendern.add(button1);
        datenAendern.add(label4);
        datenAendern.add(textField2);
        datenAendern.add(button2);
        datenAendern.add(label6);
        datenAendern.add(textField3);
        datenAendern.add(button4);
        datenAendern.add(label8);
        datenAendern.add(textField4);
        datenAendern.add(button6);
        datenAendern.add(label9);
        datenAendern.add(textField5);
        datenAendern.add(button7);
        datenAendern.add(label10);
        datenAendern.add(textField6);
        datenAendern.add(button8);
        datenAendern.add(label11);
        datenAendern.add(textField7);
        datenAendern.add(button9);
        datenAendern.add(label13);
        datenAendern.add(textField9);
        datenAendern.add(button11);
        datenAendern.add(label14);
        datenAendern.add(textField8);
        datenAendern.add(button12);
        datenAendern.add(label12);
        datenAendern.add(RegistrierLayer.cbFamilienstand);
        datenAendern.add(button10);
        datenAendern.add(label15);
        datenAendern.add(textField10);
        datenAendern.add(button13);
        geburtsdatum.add(label5);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumTag);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumMonat);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumJahr);
        geburtsdatum.add(button3);
    }

    @Override
    public void addPanelsToFrame() {
        MasterController.fenster.add(einstellungen);
        MasterController.fenster.add(datenAendern);
        MasterController.fenster.add(geburtsdatum);
    }

    @Override
    public void removePanelsFromFrame() {
        MasterController.fenster.remove(einstellungen);
        MasterController.fenster.remove(datenAendern);
        MasterController.fenster.remove(geburtsdatum);
    }

    void vornameAendernWurdeAusgewaelt(){
        angemeldetePerson.setVorname(textField1.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "first_name",
                angemeldetePerson.getVorname());
    }
    void ortAendernWurdeAusgewaelt(){
        angemeldetePerson.setOrt(textField5.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "city",
                angemeldetePerson.getOrt());
    }
    void nachnameAendernWurdeAusgewaelt(){
        angemeldetePerson.setNachname(textField2.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "last_name",
                angemeldetePerson.getNachname());
    }
    void geburtsdatumAendernWurdeAusgewaelt(){
        angemeldetePerson.setGeburtsdatum(new BasicGeburtsdatum((int) RegistrierLayer.GeburtsdatumTag.getSelectedItem(),
                (int) RegistrierLayer.GeburtsdatumMonat.getSelectedItem(),
                (int) RegistrierLayer.GeburtsdatumJahr.getSelectedItem()));
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "birth_date",
                angemeldetePerson.getGeburtsdatum());
    }
    void passwortAendernWurdeAusgewaelt(){
        angemeldetePerson.setPasswort(textField3.getText());
        DATABASE.update_password_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                angemeldetePerson.getPasswort());
    }
    void anredeAendernWurdeAusgewaelt(){
        angemeldetePerson.setAnrede(RegistrierLayer.cbAnrede.getSelectedItem().toString());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "form_of_address",
                angemeldetePerson.getAnrede());
    }
    void plzAendernWurdeAusgewaelt(){
        angemeldetePerson.setPLZ(Integer.parseInt(textField4.getText()));
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "zip_code",
                angemeldetePerson.getPLZ());
    }
    void strasseAendernWurdeAusgewaelt(){
        angemeldetePerson.setStrasse(textField6.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "street",
                angemeldetePerson.getStrasse());
    }
    void hausnummerAendernWurdeAusgewaelt(){
        angemeldetePerson.setHausnummer(textField7.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "house_number",
                angemeldetePerson.getHausnummer());
    }
    void familienstandAendernWurdeAusgewaelt(){
        angemeldetePerson.setFamilienstand(RegistrierLayer.cbFamilienstand.getSelectedItem().toString());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "marital_status",
                angemeldetePerson.getFamilienstand());
    }
    void mailAendernWurdeAusgewaelt(){
        angemeldetePerson.setMailAdresse(textField9.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "email_address",
                angemeldetePerson.getMailAdresse());
    }
    void telefonnummerAendernWurdeAusgewaelt(){
        angemeldetePerson.setTelefonnummer(Long.parseLong(textField8.getText()));
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "phone_number",
                angemeldetePerson.getTelefonnummer());
    }
    void staatsangehoerigkeitAendernWurdeAusgewaelt(){
        angemeldetePerson.setStaatsangehoerigkeit(textField10.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),
                "nationality",
                angemeldetePerson.getStaatsangehoerigkeit());
    }
/**
* Wenn der Nutzer die Daten seiner Person ändert, ermöglicht diese Klasse es die Änderungen in der Datenbank zu speichern
**/

}
