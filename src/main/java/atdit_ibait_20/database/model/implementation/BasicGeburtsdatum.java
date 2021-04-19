package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Geburtsdatum;

public class BasicGeburtsdatum implements Geburtsdatum {
    private final int GeburtsdatumTag;
    private final int GeburtsdatumMonat;
    private final int GeburtsdatumJahr;

    public BasicGeburtsdatum(int geburtsdatumTag, int geburtsdatumMonat, int geburtsdatumJahr) {
        GeburtsdatumTag = geburtsdatumTag;
        GeburtsdatumMonat = geburtsdatumMonat;
        GeburtsdatumJahr = geburtsdatumJahr;
    }


    @Override
    public int getGeburtsdatumTag() {
        return this.GeburtsdatumTag;
    }

    @Override
    public int getGeburtsdatumMonat() {
        return this.GeburtsdatumMonat;
    }

    @Override
    public int getGeburtsdatumJahr() {
        return this.GeburtsdatumJahr;
    }
}
