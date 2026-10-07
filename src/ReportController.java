import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportController {
    private final ReportModel reportModel;
    private final MenuView menuView;
    private final JTextArea reportTextAreaView;
    private final GenerationView generationView;

    public ReportController(ReportModel reportModel, MenuView menuView, JTextArea reportTextAreaView, GenerationView generationView) {
        this.reportModel = reportModel;
        this.menuView = menuView;
        this.reportTextAreaView = reportTextAreaView;
        this.generationView = generationView;
    }

    public void bind() {
        menuView.setExitItemHandler(e -> System.exit(0));
        menuView.setSaveReportItemHandler(e -> saveReport());
        menuView.setSaveReportAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        generationView.setGenerateHandler(e -> generateReportInBackground());
    }

    private void generateReportInBackground() {
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int counter = 1;
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(300); // Symulacja długiej operacji
                    for(int j = 1; j <= 10; j++) {
                        publish(counter); // Przekazanie wartości do wątku EDT
                        counter += 1;
                    }
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int latestValue = chunks.get(chunks.size() - 1);
                generationView.setProgressValue(latestValue); // Bezpieczna zmiana w GUI
                for(int v : chunks) reportTextAreaView.append("\n[LOGGER] Zrealizowano krok: " + v);
            }

            @Override
            protected void done() {
                JOptionPane.showMessageDialog(null, "Operacja zakończona sukcesem!");
                generationView.setGenerateAvailability(true);
            }
        };

        generationView.setGenerateAvailability(false);
        generationView.setProgressValue(0);
        reportTextAreaView.append("\n\n[LOGGER] Nowy raport");
        worker.execute();
    }

    private void saveReport() {
        if(reportTextAreaView.getText().isBlank()) {
            final var warn = JOptionPane.showConfirmDialog(null, "Zawartość raportu jest pusta. Kliknij Ok aby kontynuować.", "Uwaga", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(warn != JOptionPane.OK_OPTION) return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz miejsce zapisu");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt"));

        int userSelection = fileChooser.showSaveDialog(menuView);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            final var result = reportModel.saveReport(fileToSave, reportTextAreaView.getText());
            if(result) JOptionPane.showMessageDialog(null, "Pomyślnie zapisano raport do pliku:\n" + fileToSave.getName());
            else JOptionPane.showMessageDialog(null, "Nie udało się zapisać raportu do pliku:\n" + fileToSave.getName(), "Zapis do pliku", JOptionPane.ERROR_MESSAGE);
        }
    }
}
