package programGUI;
import Database.*;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Database.Word;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JFormattedTextField;
import javax.swing.border.Border;

public class InsertDataWindow {

	private JFrame frame;
	private JTextField textFieldSubject;
	private JTextArea textAreaDescribe;
	private JTextField textFieldEnglish;
	private JTextField textFieldViet;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertDataWindow window = new InsertDataWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InsertDataWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		final JPanel addSubjectPanel = new JPanel();
		frame.getContentPane().add(addSubjectPanel, "name_1108396113544");
		addSubjectPanel.setLayout(null);
		addSubjectPanel.setVisible(true);
		
		final JPanel addWordPanel = new JPanel();
		frame.getContentPane().add(addWordPanel, "name_1111198421676");
		addWordPanel.setLayout(null);
		
		JLabel lblEnglish = new JLabel("Từ tiếng Anh");
		lblEnglish.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEnglish.setBounds(48, 39, 137, 50);
		addWordPanel.add(lblEnglish);
		
		textFieldEnglish = new JTextField();
		textFieldEnglish.setBounds(216, 43, 326, 50);
		addWordPanel.add(textFieldEnglish);
		textFieldEnglish.setBorder(null);
		textFieldEnglish.setColumns(10);
		
		JLabel lblExample = new JLabel("Câu ví dụ");
		lblExample.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblExample.setBounds(48, 127, 137, 50);
		addWordPanel.add(lblExample);
		
		final JTextArea textAreaExample = new JTextArea();
		textAreaExample.setWrapStyleWord(true);
		textAreaExample.setLineWrap(true);
		textAreaExample.setFont(new Font("Serif", Font.PLAIN, 20));
		textAreaExample.setBounds(216, 137, 326, 76);
		addWordPanel.add(textAreaExample);
		
		JLabel labelViet = new JLabel("Nghĩa tiếng Việt");
		labelViet.setFont(new Font("SansSerif", Font.BOLD, 20));
		labelViet.setBounds(48, 252, 137, 50);
		addWordPanel.add(labelViet);
		
		textFieldViet = new JTextField();
		textFieldViet.setColumns(10);
		textFieldViet.setBounds(216, 256, 326, 50);
		textFieldViet.setBorder(null);
		addWordPanel.add(textFieldViet);
		
		JButton btnBackToSubject = new JButton("Quay lại");
		btnBackToSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWordPanel.setVisible(false);
				addSubjectPanel.setVisible(true);
			}
		});
		btnBackToSubject.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnBackToSubject.setBounds(48, 367, 174, 58);
		addWordPanel.add(btnBackToSubject);
		
		JButton btnAddWord = new JButton("Thêm từ");
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e0) {
				if (textFieldEnglish.getText().trim().isEmpty() || textFieldViet.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ");
				}
				else {
					try {
						if (Word.add(textFieldEnglish.getText().toLowerCase(), textFieldViet.getText().toLowerCase(), textAreaExample.getText()) == 1) {
							JOptionPane.showMessageDialog(null, "Thêm từ thành công");
							textFieldViet.setText("");
							textAreaExample.setText("");
						}
						else {
							JOptionPane.showMessageDialog(null, "Từ đã có trong csdl");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddWord.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnAddWord.setBounds(368, 367, 174, 58);
		addWordPanel.add(btnAddWord);
		
		JLabel labelSubject = new JLabel("Nhập đề tài");
		labelSubject.setToolTipText("");
		labelSubject.setFont(new Font("Arial", Font.BOLD, 15));
		labelSubject.setBounds(45, 62, 113, 38);
		addSubjectPanel.add(labelSubject);
		
		textFieldSubject = new JTextField();
		textFieldSubject.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldSubject.setColumns(10);
		textFieldSubject.setBounds(193, 57, 307, 43);
		textFieldSubject.setBorder(null);
		addSubjectPanel.add(textFieldSubject);
		
		JLabel labelDescribe = new JLabel("Mô tả đề tài");
		labelDescribe.setFont(new Font("Arial", Font.BOLD, 15));
		labelDescribe.setBounds(45, 138, 113, 38);
		addSubjectPanel.add(labelDescribe);
		
		final JTextArea textAreaDescribe = new JTextArea();
		textAreaDescribe.setWrapStyleWord(true);
		textAreaDescribe.setRows(10);
		textAreaDescribe.setLineWrap(true);
		textAreaDescribe.setColumns(10);
		textAreaDescribe.setBounds(193, 138, 307, 143);
		addSubjectPanel.add(textAreaDescribe);
		
		JButton btnAddSubject = new JButton("Thêm");
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldSubject.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đề tài");
				}
				else {
					try {
						if (Theme.add(textFieldSubject.getText().trim().toLowerCase(), textAreaDescribe.getText()) == 1) {
							Word.theme = textFieldSubject.getText().toLowerCase();
							addSubjectPanel.setVisible(false);
							addWordPanel.setVisible(true);
							textFieldSubject.setText("");
						}
						else {
							JOptionPane.showMessageDialog(null, "Đã có đề tài này");

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddSubject.setFont(new Font("Arial", Font.BOLD, 18));
		btnAddSubject.setBounds(372, 322, 128, 38);
		addSubjectPanel.add(btnAddSubject);
		
		JButton btnBackToMain = new JButton("Quay lại");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				MainFrame.main(null);
			}
		});
		btnBackToMain.setFont(new Font("Arial", Font.BOLD, 18));
		btnBackToMain.setBounds(45, 322, 128, 38);
		addSubjectPanel.add(btnBackToMain);
		
	}
}
