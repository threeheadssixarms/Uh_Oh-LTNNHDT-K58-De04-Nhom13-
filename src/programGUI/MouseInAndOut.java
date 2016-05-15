package programGUI;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MouseInAndOut extends MouseAdapter{
	JButton comp;
	public MouseInAndOut(JButton btn) {
		this.comp = btn;
	}
	public void mouseEntered(MouseEvent arg0) {
		comp.setBorderPainted(true);
	}
	public void mouseExited(MouseEvent e) {
		comp.setBorderPainted(false);
	}
}
