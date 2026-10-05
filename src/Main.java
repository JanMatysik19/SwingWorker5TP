import javax.swing.*;

public class Main {
    void main() {
        final var frame = new FrameView();
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}
