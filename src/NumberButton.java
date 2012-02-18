import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberButton extends JButton implements MouseListener, MouseWheelListener{
    private int Number;
    public NumberButton() {
	super();
	setForeground(Color.black);
	setFont(new Font("Arial", Font.BOLD, 30));
	setNumber(0);
	this.addMouseListener(this);
	this.addMouseWheelListener(this);
    }

    public void setNumber(int num, boolean complete) {
	if(num >= 0 && num < 10) {
	    Number = num;
	}
	if (Number == 0) {
	    setText("");
	}
	else {
	    setText(Integer.toString(Number));
	}
	if(complete == true) {
	    setForeground(Color.red);
	}
	else {
	    setForeground(Color.black);
	}
    }

    public void setNumber(int num) {
	setNumber(num, false);
    }

    public void resetColor() {
	setForeground(Color.black);
    }

    public int getNumber() {
	return Number;
    }

    public void clear() {
	setNumber(0);
	setForeground(Color.black);
    }

    public void incrementNumber() {
	setNumber((Number+1)%10, false);
    }
    public void decrementNumber() {
	setNumber((Number+9)%10, false);
    }
    public void mouseWheelMoved(MouseWheelEvent e) {
	if(e.getWheelRotation() < 0) {
	    incrementNumber();
	}
	else {
	    decrementNumber();
	}
    }
    public void mouseClicked(MouseEvent e) {
	switch(e.getButton()) {
	case MouseEvent.BUTTON1:
	    incrementNumber();
	    break;
	case MouseEvent.BUTTON3:
	    decrementNumber();
	    break;
	case MouseEvent.BUTTON2:
	    clear();
	    break;
	default:
	}
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
    