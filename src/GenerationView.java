import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GenerationView extends JPanel {
    private final JProgressBar progressBar;
    private final JButton generateBtn;

    public GenerationView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        progressBar = new JProgressBar(0, 100);
        add(progressBar);
        add(Box.createRigidArea(new Dimension(25, 0)));

        generateBtn = new JButton("Generuj Raport w Tle");
        add(generateBtn);
    }

    public void setProgressValue(int progressValue) {
        progressBar.setValue(progressValue);
    }

    public void setGenerateHandler(ActionListener l) {
        generateBtn.addActionListener(l);
    }

    public void setGenerateAvailability(boolean enabled) {
        generateBtn.setEnabled(enabled);
    }
}
