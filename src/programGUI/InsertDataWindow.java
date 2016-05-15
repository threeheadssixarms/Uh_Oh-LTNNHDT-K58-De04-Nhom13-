package programGUI;
import Database.*;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Database.Word;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFormattedTextField;
import javax.swing.border.Border;

import java.awt.Color;

public class InsertDataWindow {

	private JFrame frame;
	private static JPanel addSubjectPanel;
	private static JPanel addWordPanel;
	private JTextField textFieldSubject;
	private JTextArea textAreaDescribe;
	private JTextField textFieldEnglish;
	private JTextField textFieldViet;
	private static int back;
	private JLabel bg1;
	private JLabel bg2;
	private JLabel bgChange;
	/**
	 * Launch the application.
	 */
	
	public static void execute() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertDataWindow window = new InsertDataWindow();
					window.frame.setVisible(true);
					back = 0;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void addWord() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertDataWindow window = new InsertDataWindow();
					window.frame.setVisible(true);
					addWordPanel.setVisible(true);
					addSubjectPanel.setVisible(false);
					back = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public InsertDataWindow() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame("Uh-Oh");
		frame.setBounds((MainFrame.r.width-550)/2, (MainFrame.r.height-650)/2, 550, 650);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image2\\thumbnail_burned.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setResizable(false);
		
		
		addSubjectPanel = new JPanel();
		frame.getContentPane().add(addSubjectPanel, "name_1108396113544");
		addSubjectPanel.setLayout(null);
		addSubjectPanel.setVisible(true);
		
		addWordPanel = new JPanel();
		frame.getContentPane().add(addWordPanel, "name_1111198421676");
		addWordPanel.setLayout(null);
		
		JLabel lblEnglish = new JLabel("Từ tiếng Anh");
		lblEnglish.setForeground(Color.WHITE);
		lblEnglish.setFont(new Font("Serif", Font.BOLD, 20));
		lblEnglish.setBounds(48, 92, 150, 50);
		addWordPanel.add(lblEnglish);
		
		textFieldEnglish = new JTextField();
		textFieldEnglish.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldEnglish.setBounds(53, 139, 455, 65);
		addWordPanel.add(textFieldEnglish);
		textFieldEnglish.setBorder(null);
		textFieldEnglish.setColumns(10);
		
		JLabel lblExample = new JLabel("Câu ví dụ");
		lblExample.setForeground(Color.WHITE);
		lblExample.setFont(new Font("Serif", Font.BOLD, 20));
		lblExample.setBounds(48, 327, 137, 42);
		addWordPanel.add(lblExample);
		
		final JTextArea textAreaExample = new JTextArea();
		textAreaExample.setWrapStyleWord(true);
		textAreaExample.setLineWrap(true);
		textAreaExample.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textAreaExample.setBounds(48, 371, 460, 144);
		addWordPanel.add(textAreaExample);
		
		JLabel labelViet = new JLabel("Nghĩa tiếng Việt");
		labelViet.setForeground(Color.WHITE);
		labelViet.setFont(new Font("Serif", Font.BOLD, 20));
		labelViet.setBounds(48, 210, 187, 42);
		addWordPanel.add(labelViet);
		
		textFieldViet = new JTextField();
		textFieldViet.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldViet.setColumns(10);
		textFieldViet.setBounds(50, 255, 455, 65);
		textFieldViet.setBorder(null);
		addWordPanel.add(textFieldViet);
		
		JButton btnBackToSubject = new JButton("Quay lại");
		btnBackToSubject.setForeground(Color.WHITE);
		btnBackToSubject.setContentAreaFilled(false);
		btnBackToSubject.setBorderPainted(false);
		btnBackToSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (back == 0) {
					addWordPanel.setVisible(false);
					addSubjectPanel.setVisible(true);
				}
				else {
					frame.setVisible(false);
					ListWordWindow.execute();
				}
			}
		});
		btnBackToSubject.setFont(new Font("Serif", Font.BOLD, 20));
		btnBackToSubject.setBounds(45, 556, 189, 52);
		btnBackToSubject.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(39, 553, 200, 60);
				bg2.add(bgChange);
				bg2.revalidate();
				bg2.repaint();
			}
			public void mouseExited(MouseEvent e) {
				bg2.remove(bgChange);
				bg2.revalidate();
				bg2.repaint();
			}
		});			
		addWordPanel.add(btnBackToSubject);
		
		JButton btnAddWord = new JButton("Thêm từ");
		btnAddWord.setForeground(Color.WHITE);
		btnAddWord.setContentAreaFilled(false);
		btnAddWord.setBorderPainted(false);
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e0) {
				if (textFieldEnglish.getText().trim().isEmpty() || textFieldViet.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ");
				}
				else {
					try {
						if (Word.add(textFieldEnglish.getText().toLowerCase(), textFieldViet.getText().toLowerCase(), textAreaExample.getText()) == 1) {
							JOptionPane.showMessageDialog(frame, "Thêm từ thành công");
							textFieldViet.setText("");
							textAreaExample.setText("");
						}
						else {
							JOptionPane.showMessageDialog(frame, "Từ đã có trong csdl");
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
		btnAddWord.setFont(new Font("Serif", Font.BOLD, 20));
		btnAddWord.setBounds(319, 557, 189, 52);
		btnAddWord.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(313, 553, 200, 60);
				bg2.add(bgChange);
				bg2.revalidate();
				bg2.repaint();
			}
			public void mouseExited(MouseEvent e) {
				bg2.remove(bgChange);
				bg2.revalidate();
				bg2.repaint();
			}
		});					
		addWordPanel.add(btnAddWord);
		
		JLabel labelSubject = new JLabel("Nhập đề tài");
		labelSubject.setForeground(Color.WHITE);
		labelSubject.setToolTipText("");
		labelSubject.setFont(new Font("Serif", Font.BOLD, 20));
		labelSubject.setBounds(45, 138, 113, 38);
		addSubjectPanel.add(labelSubject);
		
		textFieldSubject = new JTextField();
		textFieldSubject.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldSubject.setColumns(10);
		textFieldSubject.setBounds(55, 187, 432, 48);
		textFieldSubject.setBorder(null);
		addSubjectPanel.add(textFieldSubject);
		
		JLabel labelDescribe = new JLabel("Mô tả đề tài");
		labelDescribe.setForeground(Color.WHITE);
		labelDescribe.setFont(new Font("Serif", Font.BOLD, 20));
		labelDescribe.setBounds(45, 242, 113, 38);
		addSubjectPanel.add(labelDescribe);
		
		final JTextArea textAreaDescribe = new JTextArea();
		textAreaDescribe.setBackground(Color.WHITE);
		textAreaDescribe.setWrapStyleWord(true);
		textAreaDescribe.setRows(10);
		textAreaDescribe.setLineWrap(true);
		textAreaDescribe.setColumns(10);
		textAreaDescribe.setBounds(55, 291, 432, 231);
		addSubjectPanel.add(textAreaDescribe);
		
		JButton btnAddSubject = new JButton("Thêm");
		btnAddSubject.setForeground(Color.WHITE);
		btnAddSubject.setBorderPainted(false);
		btnAddSubject.setContentAreaFilled(false);
		btnAddSubject.setFocusPainted(false);
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldSubject.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đề tài");
				}
				else {
					try {
						if (Theme.add(textFieldSubject.getText().trim().toLowerCase(), textAreaDescribe.getText()) == 1) {
							frame.setTitle("Thêm từ");
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
		btnAddSubject.setFont(new Font("Serif", Font.BOLD, 20));
		btnAddSubject.setBounds(310, 557, 189, 52);
		btnAddSubject.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(304, 553, 200, 60);
				bg1.add(bgChange);
				bg1.revalidate();
				bg1.repaint();
			}
			public void mouseExited(MouseEvent e) {
				bg1.remove(bgChange);
				bg1.revalidate();
				bg1.repaint();
			}
		});		
		addSubjectPanel.add(btnAddSubject);
		
		JButton btnBackToMain = new JButton("Quay lại");
		btnBackToMain.setForeground(Color.WHITE);
		btnBackToMain.setBorderPainted(false);
		btnBackToMain.setContentAreaFilled(false);
		btnBackToMain.setFocusPainted(false);
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				MainFrame.main(null);
			}
		});
		btnBackToMain.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(45, 553, 200, 60);
				bg1.add(bgChange);
				bg1.revalidate();
				bg1.repaint();
			}
			public void mouseExited(MouseEvent e) {
				bg1.remove(bgChange);
				bg1.revalidate();
				bg1.repaint();
			}
		});	
		btnBackToMain.setFont(new Font("Serif", Font.BOLD, 20));
		btnBackToMain.setBounds(51, 556, 189, 52);
		addSubjectPanel.add(btnBackToMain);
		
		ButtonHome btnHome = new ButtonHome(frame);
		ButtonHome btnHome1 = new ButtonHome(frame);
		addSubjectPanel.add(btnHome);
		addWordPanel.add(btnHome1);
		
		BufferedImage bufferBackground = ImageIO.read(getClass().getResource("/themdetai.png"));
		ImageIcon image = new ImageIcon(bufferBackground);
		bg1 = new JLabel(image);
		bg1.setSize(544, 622);
		bg1.setLocation(0, 0);
		addSubjectPanel.add(bg1);
		
		BufferedImage bufferBackground2 = ImageIO.read(getClass().getResource("/themtu.png"));
		ImageIcon image2 = new ImageIcon(bufferBackground2);
		bg2 = new JLabel(image2);
		bg2.setFont(new Font("Serif", Font.BOLD, 15));
		bg2.setSize(544, 622);
		bg2.setLocation(0, 0);
		addWordPanel.add(bg2);
		
		BufferedImage bf = ImageIO.read(getClass().getResource("/nutbam.png"));
		ImageIcon image1 = new ImageIcon(bf);
		bgChange = new JLabel(image1);
		//bgChange.setBounds(45, 553, 200, 60);
		//bg1.add(bgChange);
	}
}
