import java.io.File;
import java.io.FileWriter;

public class ReportModel {
    public boolean saveReport(File file, String report) {
        var fileToSave = file;
        if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
        }
        System.out.println("Zapisywanie do: " + fileToSave.getAbsolutePath());
        try {
            FileWriter fw = new FileWriter(fileToSave);
            fw.write(report);
            fw.close();
            System.out.println("Zapisano");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
