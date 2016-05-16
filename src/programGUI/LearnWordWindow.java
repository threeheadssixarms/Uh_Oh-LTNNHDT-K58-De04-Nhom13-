package programGUI;
import Database.*;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.swing.JTextArea;

import java.awt.Color;
import java.io.IOException;
import java.awt.Toolkit;

public class LearnWordWindow {

	private JFrame frame;
	private Word learnWord;
	private int anhViet = 0;
	private int exam = 0;
	private String tmpEng;
	private String tmpViet;
	private String tmpExample;
	private JPanel learnWordPanel;
	private JTextArea textArea;
	private JLabel bg1;
	private JLabel bgChange;
	/**
	 * Launch the application.
	 */
	
	public static void execute() {
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
	 * @throws IOException 
	 */
	public LearnWordWindow() throws SQLException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private void initialize() throws SQLException, IOException {	
		learnWord = new Word("","","");
		learnWord.learn();
		
		frame = new JFrame("Uh-Oh");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image2\\thumbnail_burned.png"));
		frame.setBounds((MainFrame.r.width-550)/2, (MainFrame.r.height-650)/2, 550, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setResizable(false);
		
		learnWordPanel = new JPanel();
		frame.getContentPane().add(learnWordPanel, "name_736900089916");
		learnWordPanel.setLayout(null);
		
		final JButton btnWord = new JButton("");
		btnWord.setBorderPainted(false);
		btnWord.setFocusable(false);
		btnWord.setContentAreaFilled(false);
		btnWord.setForeground(Color.WHITE);
		tmpViet = this.ToFirstUpper(learnWord.getVn());
		tmpEng = this.ToFirstUpper(learnWord.getEn());
		btnWord.setText(tmpEng);
		btnWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anhViet == 0) {
					btnWord.setText(tmpViet);
					anhViet = 1;
				}
				else {
					btnWord.setText(tmpEng);
					anhViet = 0;
				}
			}
		});
		
		final JButton btnShowExample = new JButton("Hiện ví dụ");
		btnShowExample.setForeground(Color.WHITE);
		btnShowExample.setBorderPainted(false);
		btnShowExample.setFocusPainted(false);
		btnShowExample.setBackground(new Color(92,232,215));
		tmpExample = ToFirstUpper(learnWord.getEg());
		btnShowExample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				learnWordPanel.remove(btnShowExample);
				learnWordPanel.revalidate();
				learnWordPanel.repaint();
			}
		});
		btnShowExample.setFont(new Font("Serif", Font.BOLD, 20));
		btnShowExample.setBounds(57, 235, 439, 81);
		btnShowExample.addMouseListener(new MouseInAndOut(btnShowExample));
		learnWordPanel.add(btnShowExample);
		
		
		btnWord.setFont(new Font("Gentium Book Basic", Font.PLAIN, 40));
		btnWord.setBounds(57, 346, 440, 174);
		btnWord.addMouseListener(new MouseInAndOut(btnWord));
		learnWordPanel.add(btnWord);
		
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		btnBack.setForeground(Color.white);
		btnBack.setFont(new Font("Serif", Font.BOLD, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Word.rs = null;
				frame.setVisible(false);
				SubjectListFrame.executeBack();
			}
		});
		btnBack.setBounds(28, 551, 226, 63);
		btnBack.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(40, 552, 200, 60);
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
		learnWordPanel.add(btnBack);
		
		JButton btnNextword = new JButton("Tiếp theo");
		btnNextword.setBorderPainted(false);
		btnNextword.setContentAreaFilled(false);
		btnNextword.setFocusPainted(false);
		btnNextword.setForeground(Color.WHITE);
		btnNextword.setFont(new Font("Serif", Font.BOLD, 20));
		btnNextword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exam = 0;
				anhViet = 0;
				learnWordPanel.remove(textArea);
				learnWordPanel.remove(bg1);
				learnWordPanel.revalidate();
				learnWordPanel.repaint();
				try {
					learnWord.learn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tmpEng = ToFirstUpper(learnWord.getEn());
				tmpViet = ToFirstUpper(learnWord.getVn());
				tmpExample = ToFirstUpper(learnWord.getEg());
				textArea.setText(tmpExample);
				learnWordPanel.add(btnShowExample);
				learnWordPanel.add(textArea);
				learnWordPanel.add(bg1);
				btnWord.setText(tmpEng);
				learnWordPanel.revalidate();
				learnWordPanel.repaint();
			}
		});
		btnNextword.setBounds(310, 556, 189, 52);
		btnNextword.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(304, 552, 200, 60);
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
		learnWordPanel.add(btnNextword);
		learnWordPanel.add(new ButtonHome(frame));
		
		textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Serif", Font.BOLD, 20));
		textArea.setBackground(new Color(92,232,215));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setText(tmpExample);
		textArea.setBounds(57, 235, 439, 81);
		learnWordPanel.add(textArea);
		
		BufferedImage bufferBackground = ImageIO.read(getClass().getResource("/hoc tu.png"));
		ImageIcon image = new ImageIcon(bufferBackground);
		bg1 = new JLabel(image);
		bg1.setSize(544, 622);
		bg1.setLocation(0, 0);
		learnWordPanel.add(bg1);
		
		BufferedImage bf = ImageIO.read(getClass().getResource("/nutbam.png"));
		ImageIcon image1 = new ImageIcon(bf);
		bgChange = new JLabel(image1);
		bgChange.setBounds(304, 552, 200, 60);
		
	}
	
	public static String ToFirstUpper(String tmp) {
		if (tmp.length() == 0) return null;
		String result;
		if (tmp.length() > 1)  
            result = tmp.substring(0, 1).toUpperCase() + tmp.substring(1).toLowerCase() + "";  
        else  
            result = tmp.toUpperCase() + "";
		return result;
	}
}
