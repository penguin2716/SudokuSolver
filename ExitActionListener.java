import java.awt.*;
import java.awt.event.*;

public class ExitActionListener implements ActionListener {
    private int retval;
    public ExitActionListener(int ret) {
	ret = retval;
    }
    public void actionPerformed(ActionEvent e) {
	System.exit(retval);
    }
}
