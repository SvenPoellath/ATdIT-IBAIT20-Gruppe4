package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VertragHinzufuegenTest {
    Person person;
    VertragHinzufuegen Vertrag = new VertragHinzufuegen(person);
    Object buchungsArtObjektMonat = App.resourceBundle.getString("monthly");
    Object buchungsArtObjektJahr = App.resourceBundle.getString("yearly");
    int ZweiTage = 2;
    int ExpectedZweiTage = (ZweiTage + 1)*50;
    int FuenfTage = 5;
    int ExpectedFuenfTage = (FuenfTage + 1)*50;

    @Test
    public void setPreisTestMonat() {
        Assertions.assertEquals(300, Vertrag.setPreisForLuggageInsurance(buchungsArtObjektMonat));
    }

    @Test
    public void setPreisTestJahr() {
        Assertions.assertEquals(3000, Vertrag.setPreisForLuggageInsurance(buchungsArtObjektJahr));
    }

    @Test
    public void setPreisPerTripZweiTageTest(){
        Assertions.assertEquals(ExpectedZweiTage, Vertrag.setPreisForLuggageInsurancePerTrip(ZweiTage));
    }

    @Test
    public void setPreisPerTripFuenfTageTest(){
        Assertions.assertEquals(ExpectedFuenfTage, Vertrag.setPreisForLuggageInsurancePerTrip(FuenfTage));
    }

    @Test
    public void eingegebeneIBANPruefenTest(){

    }

}