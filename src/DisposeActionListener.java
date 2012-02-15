import java.awt.*;
import java.awt.event.*;

public class DisposeActionListener implements ActionListener {
    private Window window;
    public DisposeActionListener(Window w) {
	window = w;
    }
    public void actionPerformed(ActionEvent e) {
	window.dispose();
    }
}
