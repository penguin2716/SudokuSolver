import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SavePanel extends JPanel {
    private boolean isSaved = false;
    private int saveMatrix[][] = new int[SudokuFrame.SIZE][SudokuFrame.SIZE];
    private JButton saveButton;
    private JButton loadButton;
    private JButton clearButton;

    private NumberButton button[][];

    public SavePanel(NumberButton b[][]) {
	button = b;
	setLayout(new FlowLayout());

	saveButton = new JButton("Save");
	loadButton = new JButton("Load");
	clearButton = new JButton("Clear");

	add(saveButton);
	add(loadButton);
	add(clearButton);

	saveButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    saveData();
		    resetButtonStatus(true);
		}
	    });

	loadButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    loadData();
		}
	    });

	clearButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clearData(saveMatrix);
		    resetButtonStatus(false);
		}
	    });

	resetButtonStatus(false);
    }

    private void saveData() {
	for(int y=0; y<SudokuFrame.SIZE; y++) {
	    for(int x=0; x<SudokuFrame.SIZE; x++) {
		saveMatrix[y][x] = button[y][x].getNumber();
	    }
	}
    }

    private void loadData() {
	for(int y=0; y<SudokuFrame.SIZE; y++) {
	    for(int x=0; x<SudokuFrame.SIZE; x++) {
		button[y][x].setNumber(saveMatrix[y][x]);
	    }
	}
    }

    private void clearData(int matrix[][]) {
	for(int y=0; y<SudokuFrame.SIZE; y++) {
	    for(int x=0; x<SudokuFrame.SIZE; x++) {
		matrix[y][x] = 0;
	    }
	}
    }

    private void resetButtonStatus(boolean saved) {
	isSaved = saved;
	saveButton.setEnabled(true);
	loadButton.setEnabled(saved);
	clearButton.setEnabled(saved);
    }

}

