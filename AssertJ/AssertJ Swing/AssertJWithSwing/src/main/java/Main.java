import org.example.MainFrame;
import org.example.TextFieldFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextFieldFrame::new);
    }
}
