package programGUI;
import Database.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LearnWordWindow {

	private JFrame frame;
	private Word learnWord;
	private int anhViet = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearnWordWindow window = new LearnWordWindow();
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
	public LearnWordWindow() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {	
		learnWord = new Word();
		learnWord.learn();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel learnWordPanel = new JPanel();
		frame.getContentPane().add(learnWordPanel, "name_736900089916");
		learnWordPanel.setLayout(null);
		
		JLabel labelNewWord = new JLabel("Từ mới");
		labelNewWord.setFont(new Font("Arial", Font.PLAIN, 16));
		labelNewWord.setBounds(66, 49, 127, 31);
		learnWordPanel.add(labelNewWord);
		
		final JLabel labelWord = new JLabel();
		labelWord.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelWord.setBounds(66, 91, 328, 49);
		learnWordPanel.add(labelWord);
		if (anhViet == 0) {
			labelWord.setText(learnWord.getEn());
		}
		else {
			labelWord.setText(learnWord.getVn());
		}
		
		final JLabel labelResult = new JLabel();
		labelResult.setVerticalAlignment(SwingConstants.TOP);
		labelResult.setFont(new Font("Times New Roman", Font.BOLD, 15));
		labelResult.setBounds(62, 226, 351, 117);
		learnWordPanel.add(labelResult);
		
		final JButton buttonShowExample = new JButton("Hiện ví dụ");
		buttonShowExample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				labelResult.setText(learnWord.getEg());
			}
		});
		buttonShowExample.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonShowExample.setBounds(66, 180, 106, 35);
		learnWordPanel.add(buttonShowExample);
		
		final JButton buttonShowMeaning = new JButton("Hiện nghĩa");
		buttonShowMeaning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anhViet == 0) {
					labelResult.setText(learnWord.getVn());
				}
				else {
					labelResult.setText(learnWord.getEn());
				}
			}
		});
		buttonShowMeaning.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonShowMeaning.setBounds(288, 180, 106, 35);
		learnWordPanel.add(buttonShowMeaning);
		
		
		JButton buttonSwap = new JButton("Anh-Việt");
		buttonSwap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anhViet == 0) {
					anhViet = 1;
					labelWord.setText(learnWord.getVn());
					labelResult.setText("");
				}
				else {
					anhViet = 0;
					labelWord.setText(learnWord.getEn());
					labelResult.setText("");
				}
			}
		});
		buttonSwap.setBounds(367, 11, 106, 35);
		learnWordPanel.add(buttonSwap);
		
		JButton btnNextword = new JButton("Tiếp theo");
		btnNextword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					learnWord.learn();
					if (anhViet == 0) {
						labelWord.setText(learnWord.getEn());
					}
					else {
						labelWord.setText(learnWord.getVn());
					}
					labelResult.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNextword.setBounds(325, 361, 120, 31);
		learnWordPanel.add(btnNextword);
	}
}
