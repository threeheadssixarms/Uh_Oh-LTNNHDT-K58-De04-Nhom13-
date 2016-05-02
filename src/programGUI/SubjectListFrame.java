package programGUI;
import Database.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Scrollbar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class SubjectListFrame {

	private JFrame frame;
	Theme dataTheme = new Theme();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubjectListFrame window = new SubjectListFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public SubjectListFrame() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		ArrayList<Theme> themeList = Theme.getThemeList();
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		int i;
		for (i = 0; i < themeList.size(); i++) {
			JButton btnTmp = new JButton();
			btnTmp.setVisible(true);
			String tmp = themeList.get(i).getTheme();
			String.valueOf(tmp.charAt(0)).toUpperCase();
			btnTmp.setText(tmp);
			buttonList.add(btnTmp);
		}
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(500,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		 final JPanel panelChoice = new JPanel();
		 frame.getContentPane().add(panelChoice, "name_8957925332661");
		 panelChoice.setLayout(null);
		
		JPanel panelListSubject = new JPanel();
		panelListSubject.setLayout(null);
		panelListSubject.setPreferredSize(new Dimension(panelListSubject.getWidth(),panelListSubject.getHeight()));
		final JScrollPane scrollPane = new JScrollPane(panelListSubject);
			for (i = 0; i < buttonList.size(); i++) {
				final JButton btnTmp = new JButton();
				btnTmp.setVisible(true);
				btnTmp.setBounds(200, 50*i, 100, 50);
				btnTmp.setText(themeList.get(i).getTheme());
				panelListSubject.add(btnTmp);
				btnTmp.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						LearnData.theme = btnTmp.getText();
						scrollPane.setVisible(false);
						panelChoice.setVisible(true);
					}
				});
				}
	    //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(panelListSubject);
        
        
	    frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
	    
	    JButton btnLearn = new JButton("Học từ mới");
	    btnLearn.setBounds(142, 63, 180, 80);
	    panelChoice.add(btnLearn);
	    btnLearn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LearnWordWindow.main(null);
			}
		});
	    
	    JButton btnGame = new JButton("Chơi game");
	    btnGame.setBounds(142, 220, 180, 80);
	    panelChoice.add(btnGame);
	    btnGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GameWindow.main(null);
			}
		});
	    
	    frame.setVisible(true);
		panelChoice.setVisible(false);
	    scrollPane.setVisible(true);
	}
}