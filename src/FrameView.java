import javax.swing.*;
import java.awt.*;

public class FrameView extends JFrame {

    public FrameView() {
        setTitle("INF.04 - Generator Raportów");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 500, 400);
        setContentPane(new Content());
    }

    private class Content extends JPanel {
        private final MenuView menuView;
        private final ReportModel reportModel;
        private final JTextArea reportTextAreaView;
        private final GenerationView generationView;

        public Content() {
            setLayout(new BorderLayout());

            menuView = new MenuView();
            add(menuView, BorderLayout.NORTH);

            reportTextAreaView = new JTextArea();
            reportTextAreaView.setEditable(false);
            final var scrollTextArea = new JScrollPane(reportTextAreaView);
            add(scrollTextArea, BorderLayout.CENTER);

            generationView = new GenerationView();
            add(generationView, BorderLayout.SOUTH);

            reportModel = new ReportModel();
            final var controller = new ReportController(reportModel, menuView, reportTextAreaView, generationView);
            controller.bind();
        }
    }
}
