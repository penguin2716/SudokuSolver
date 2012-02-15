import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberButton extends JButton implements MouseListener, MouseWheelListener{
    private int Number;
    public NumberButton() {
	super();
	setFont(new Font("Arial", Font.BOLD, 30));
	setNumber(0);
	this.addMouseListener(this);
	this.addMouseWheelListener(this);
    }

    public void setNumber(int num) {
	if(num >= 0 && num < 10) {
	    Number = num;
	}
	if (Number == 0) {
	    setText("");
	}
	else {
	    setText(Integer.toString(Number));
	}
    }
    public int getNumber() {
	return Number;
    }

    public void clear() {
	setNumber(0);
    }

    public void incrementNumber() {
	setNumber((Number+1)%10);
    }
    public void decrementNumber() {
	setNumber((Number+9)%10);
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
	    setNumber(0);
	    break;
	default:
	}
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
    