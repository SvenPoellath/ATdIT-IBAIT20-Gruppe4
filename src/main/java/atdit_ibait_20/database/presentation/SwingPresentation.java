package atdit_ibait_20.database.presentation;

public interface SwingPresentation {
    public static void setStrings() {}
    public void setFrame();
    void setLayout();
    void addListeners();
    void addComponentsToPanels();
    void addPanelsToFrame();
    void removePanelsFromFrame();
}
