import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuFrame extends JFrame{

    public static int SIZE = 9;
    public static int BOX = 3;
    private final NumberButton button[][] = new NumberButton[SIZE][SIZE];

    private void makeMenuBar() {
	JMenuBar menubar = new JMenuBar();
	this.setJMenuBar(menubar);
	JMenu fileMenu = new JMenu("File");
	menubar.add(fileMenu);
	JMenuItem exitAction = fileMenu.add("Exit");
	exitAction.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    System.exit(0);
		}
	    });

    }

    public SudokuFrame(String title) {
	super(title);
	this.setSize(500,550);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	this.setLocationByPlatform(true);

	makeMenuBar();

	JPanel mainPanel = new JPanel();
	add(mainPanel, BorderLayout.CENTER);

	GridLayout gl = new GridLayout(BOX, BOX);
	gl.setHgap(10);
	gl.setVgap(10);
	mainPanel.setLayout(gl);

	for(int y=0; y<SIZE; y++) {
	    for(int x=0; x<SIZE; x++) {
		button[y][x] = new NumberButton();
	    }
	}

	for(int y=0; y<BOX; y++) {
	    for(int x=0; x<BOX; x++) {
		JPanel p = new JPanel();
		mainPanel.add(p);
		p.setLayout(new GridLayout(BOX,BOX));
		for(int v=0; v<BOX; v++) {
		    for(int h=0; h<BOX; h++) {
			p.add(button[y*3+v][x*3+h]);
		    }
		}
	    }
	}

	JPanel bottomPanel = new JPanel();
	bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 10));
	this.add(bottomPanel, BorderLayout.SOUTH);
	JButton clearButton = new JButton("Clear");
	JButton solveButton = new JButton("Solve");
	HelpButton helpButton = new HelpButton("Help", this);
	bottomPanel.add(clearButton);
	bottomPanel.add(solveButton);
	bottomPanel.add(helpButton);

	clearButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
			    button[y][x].clear();
			}
		    }
		}
	    });

	solveButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    SudokuSolver solver = new SudokuSolver();
		    int matrix[][] = new int[SIZE][SIZE];
		    for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
			    matrix[y][x] = button[y][x].getNumber();
			}
		    }
		    solver.solve(matrix);
		    for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
			    button[y][x].setNumber(matrix[y][x]);
			}
		    }

		}
	    });

    }
}

class HelpButton extends JButton implements ActionListener {
    private JFrame frame;
    public HelpButton(String text, JFrame f) {
	super(text);
	this.addActionListener(this);
	frame = f;
    }

    public void actionPerformed(ActionEvent e) {
	SudokuHelpDialog dialog = new SudokuHelpDialog(frame, "Help");
	dialog.setVisible(true);
    }

}

class SudokuHelpDialog extends JDialog {
    public SudokuHelpDialog(Frame f, String title) {
	super(f, title);
	this.setSize(300,200);
	Point p = f.getLocation();
	this.setLocation(p.x+100, p.y+100);
	this.setLocationByPlatform(true);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setLayout(new GridLayout(3,1));
	JLabel label[] = new JLabel[3];
	label[0] = new JLabel("* Left Click, Scroll Up: Increase");
	label[1] = new JLabel("* Right Click, Scroll Down: Decrease");
	label[2] = new JLabel("* Middle Click: Clear");
	this.add(label[0]);
	this.add(label[1]);
	this.add(label[2]);
    }
}
