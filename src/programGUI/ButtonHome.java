package programGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonHome extends JButton{
	JFrame frameOwn;
	
	public ButtonHome(JFrame frame) {
		this.frameOwn = frame;
	}
	{
		setBorderPainted(false);
		setSize(56, 72);
		setLocation(431, 0);
		setContentAreaFilled(false);
		addActionListener(new ReturnHome());
		addMouseListener(new MouseInAndOut(this));
	}
	
	
	private class ReturnHome implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			frameOwn.setVisible(false);
			MainFrame.main(null);
		}
	}
}

