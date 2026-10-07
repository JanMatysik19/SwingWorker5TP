import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuView extends JMenuBar {
    private final JMenuItem saveReportItem, exitItem;

    public MenuView() {
        final var fileMenu = new JMenu("Plik");
        saveReportItem = new JMenuItem("Zapisz Raport");
        exitItem = new JMenuItem("Wyjście");
        fileMenu.add(saveReportItem);
        fileMenu.add(exitItem);
        add(fileMenu);
    }

    public void setExitItemHandler(ActionListener l) {
        exitItem.addActionListener(l);
    }

    public void setSaveReportItemHandler(ActionListener l) {
        saveReportItem.addActionListener(l);
    }

    public void setSaveReportAccelerator(KeyStroke keyStroke) {
        saveReportItem.setAccelerator(keyStroke);
    }
}
