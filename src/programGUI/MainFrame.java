package programGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class MainFrame {

	private JFrame frmProgram;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProgram = new JFrame();
		frmProgram.setTitle("Program");
		frmProgram.setBounds(100, 100, 349, 354);
		frmProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAddSubject = new JButton("Thêm đề tài");
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProgram.setVisible(false);
				InsertDataWindow.main(null);
			}
		});
		btnAddSubject.setFont(new Font("Serif", Font.BOLD, 19));
		btnAddSubject.setBounds(98, 59, 154, 54);
		btnAddSubject.setBackground(SystemColor.activeCaption);
		btnAddSubject.setForeground(Color.BLACK);
		
		JButton btnLearnWords = new JButton("Danh sách đề tài");
		btnLearnWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProgram.setVisible(false);
				SubjectListFrame.main(null);
			}
		});
		btnLearnWords.setFont(new Font("Serif", Font.BOLD, 19));
		btnLearnWords.setBounds(98, 139, 154, 54);
		frmProgram.getContentPane().setLayout(null);
		frmProgram.getContentPane().add(btnAddSubject);
		frmProgram.getContentPane().add(btnLearnWords);
		
		JButton btnGame = new JButton("");
		btnGame.setText("Trò chơi");
		btnGame.setFont(new Font("Serif", Font.BOLD, 19));
		btnGame.setBounds(98, 211, 154, 54);
		frmProgram.getContentPane().add(btnGame);
		  btnGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					frmProgram.setVisible(false);
					GameWindow.main(null);
				}
			});
	}
}
