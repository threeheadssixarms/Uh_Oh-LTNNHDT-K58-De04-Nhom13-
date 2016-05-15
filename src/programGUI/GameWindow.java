package programGUI;
import Database.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GameWindow {

	private JFrame frame;
	public JPanel panelMultipleChoice;
	public JPanel panelComplete;
	public JPanel panelEndGame;
	private JLabel lblMark;
	static int i;
	static int j;
	static int curQues;
	static int mark;
	static int maxQues;
	ArrayList<Question> quesList;
	private JLabel labelQuestionMC;
	private JButton btnNextC;
	private JButton btnAnswer0;
	private JButton btnAnswer1;
	private JButton btnAnswer2;
	private JButton btnAnswer3;
	private JButton btnAnswerTrue;
	private JLabel lblTime;
	private JLabel lblQuestionC;
	private JTextField textFieldAnswer;
	private int minutes;
	private int seconds;
	static String answerChosen;
	private long startTime;
	private long endTime;
	private JLabel bgChosen;
	private JLabel bgAnswer;
	private JLabel bg3;
	private JLabel bg2;
	private JLabel bg1;
	private JLabel lblNoQuesC;
	private JLabel lblNoQuesMC;
	private JLabel bgChange;
	/**
	 * Launch the application.
	 */
	public static void execute() {
		i  = 0;
		mark = 0;
		curQues  = 1;
		answerChosen = "";
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
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
	public GameWindow() throws SQLException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	
	
	private void initialize() throws SQLException, IOException {
		BufferedImage bufferChosen = ImageIO.read(getClass().getResource("/red.png"));
		ImageIcon imageChosen = new ImageIcon(bufferChosen);
		bgChosen = new JLabel(imageChosen);
		bgChosen.setBounds(0, 0, 0, 0);
		
		BufferedImage bufferAnswer = ImageIO.read(getClass().getResource("/green.png"));
		ImageIcon imageAnswer = new ImageIcon(bufferAnswer);
		bgAnswer = new JLabel(imageAnswer);
		bgAnswer.setBounds(0, 0, 0, 0);
		
		
		startTime = System.currentTimeMillis();
		
		quesList = Question.getQuestionList();
		maxQues = quesList.size();
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image2\\thumbnail_burned.png"));
		frame.setTitle("Uh-Oh");
		frame.setBounds((MainFrame.r.width-550)/2, (MainFrame.r.height-650)/2, 550, 650);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panelComplete = new JPanel();
		frame.getContentPane().add(panelComplete, "name_994528488738");
		panelComplete.setLayout(null);
		panelComplete.setVisible(true);
		
		
		panelEndGame = new JPanel();
		frame.getContentPane().add(panelEndGame, "name_6327022998073");
		panelEndGame.setLayout(null);
		
		JLabel lblResult = new JLabel("Kết quả");
		lblResult.setForeground(Color.WHITE);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 51));
		lblResult.setBounds(141, 186, 271, 90);
		panelEndGame.add(lblResult);
		
		lblMark = new JLabel();
		lblMark.setForeground(Color.WHITE);
		lblMark.setFont(new Font("GungsuhChe", Font.PLAIN, 40));
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		lblMark.setBounds(42, 423, 464, 96);
		panelEndGame.add(lblMark);
		
		lblTime = new JLabel();
		lblTime.setForeground(Color.WHITE);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("GungsuhChe", Font.PLAIN, 40));
		lblTime.setBounds(42, 301, 464, 96);
		panelEndGame.add(lblTime);
		
		lblNoQuesC = new JLabel("");
		lblNoQuesC.setForeground(Color.WHITE);
		lblNoQuesC.setFont(new Font("Serif", Font.BOLD, 20));
		lblNoQuesC.setBounds(62, 196, 123, 43);
		lblNoQuesC.setText("Câu " + curQues);
		panelComplete.add(lblNoQuesC);
		
	
		lblQuestionC = new JLabel("");
		lblQuestionC.setFont(new Font("Serif", Font.BOLD, 23));
		lblQuestionC.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionC.setText(quesList.get(i).getQuestion());
		lblQuestionC.setBounds(62, 255, 441, 61);
		panelComplete.add(lblQuestionC);
		
		textFieldAnswer = new JTextField();
		textFieldAnswer.setForeground(Color.WHITE);
		textFieldAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAnswer.setBorder(new LineBorder(new Color(92,232,215)));
		textFieldAnswer.setFont(new Font("Serif", Font.PLAIN, 23));
		textFieldAnswer.setBounds(62, 371, 425, 141);
		textFieldAnswer.setBackground(new Color(92,232,215));
		panelComplete.add(textFieldAnswer);
		textFieldAnswer.setColumns(10);
		
		JButton btnNextC = new JButton();
		btnNextC.setForeground(Color.WHITE);
		btnNextC.setFont(new Font("Serif", Font.BOLD, 20));
		btnNextC.setContentAreaFilled(false);
		btnNextC.setBorderPainted(false);
		btnNextC.setText("Tiếp");
		btnNextC.setBounds(318, 555, 190, 52);
		panelComplete.add(btnNextC);
		
		panelMultipleChoice = new JPanel();
		frame.getContentPane().add(panelMultipleChoice, "name_995795426270");
		panelMultipleChoice.setLayout(null);
		panelMultipleChoice.setVisible(false);
		
		labelQuestionMC = new JLabel();
		labelQuestionMC.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuestionMC.setFont(new Font("Serif", Font.BOLD, 25));
		labelQuestionMC.setText(quesList.get( i ).getQuestion());
		labelQuestionMC.setBounds(44, 184, 462, 57);
		panelMultipleChoice.add(labelQuestionMC);
		
		lblNoQuesMC = new JLabel("");
		lblNoQuesMC.setBounds(52, 130, 123, 43);
		panelMultipleChoice.add(lblNoQuesMC);
		lblNoQuesMC.setForeground(Color.WHITE);
		lblNoQuesMC.setText("Câu " + curQues);
		lblNoQuesMC.setFont(new Font("Serif", Font.BOLD, 20));
		
		btnAnswer0 = new JButton("");
		btnAnswer0.setFont(new Font("Serif", Font.PLAIN, 20));
		btnAnswer0.setBorderPainted(false);
		btnAnswer0.setContentAreaFilled(false);
		btnAnswer0.setText(quesList.get( i ).getAnswer0());
		btnAnswer0.setBounds(44, 283, 462, 57);
		panelMultipleChoice.add(btnAnswer0);
		
		btnAnswer1 = new JButton("");
		btnAnswer1.setFont(new Font("Serif", Font.PLAIN, 20));
		btnAnswer1.setBorderPainted(false);
		btnAnswer1.setContentAreaFilled(false);
		btnAnswer1.setText(quesList.get( i ).getAnswer1());
		btnAnswer1.setBounds(44, 347, 462, 57);
		panelMultipleChoice.add(btnAnswer1);
		
		btnAnswer2 = new JButton("");
		btnAnswer2.setFont(new Font("Serif", Font.PLAIN, 20));
		btnAnswer2.setBorderPainted(false);
		btnAnswer2.setContentAreaFilled(false);
		btnAnswer2.setText(quesList.get( i ).getAnswer2());
		btnAnswer2.setBounds(44, 410, 461, 57);
		panelMultipleChoice.add(btnAnswer2);
		
		btnAnswer3 = new JButton();
		btnAnswer3.setFont(new Font("Serif", Font.PLAIN, 20));
		btnAnswer3.setBorderPainted(false);
		btnAnswer3.setContentAreaFilled(false);
		btnAnswer3.setText(quesList.get( i ).getAnswer3());
		btnAnswer3.setBounds(45, 474, 461, 57);
		panelMultipleChoice.add(btnAnswer3);
		
		JButton btnNextMC = new JButton();
		btnNextMC.setForeground(Color.WHITE);
		btnNextMC.setFont(new Font("Serif", Font.BOLD, 20));
		btnNextMC.setContentAreaFilled(false);
		btnNextMC.setBorderPainted(false);
		btnNextMC.setText("Tiếp");
		btnNextMC.setBounds(310, 554, 189, 53);
		panelMultipleChoice.add(btnNextMC);
		
		btnAnswer0.addActionListener(new ActionAnswer(btnAnswer0, btnAnswer1, btnAnswer2, btnAnswer3));
		btnAnswer1.addActionListener(new ActionAnswer(btnAnswer1, btnAnswer0, btnAnswer2, btnAnswer3));
		btnAnswer2.addActionListener(new ActionAnswer(btnAnswer2, btnAnswer0, btnAnswer1, btnAnswer3));
		btnAnswer3.addActionListener(new ActionAnswer(btnAnswer3, btnAnswer0, btnAnswer1, btnAnswer2));
		
	
		btnNextC.addActionListener(new CNextAction());
		btnNextC.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(315, 551, 195, 60);
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
		btnNextMC.addActionListener(new MCNextAction(btnAnswer0, btnAnswer1, btnAnswer2, btnAnswer3));
		btnNextMC.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(307, 551, 195, 60);
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
		
		if (Math.random() < 0.5) {
			panelComplete.setVisible(false);
			panelMultipleChoice.setVisible(true);
		}
		else {
			panelMultipleChoice.setVisible(false);
			panelComplete.setVisible(true);
		}
		
		
		JButton btnQuitGameC = new JButton();
		btnQuitGameC.setForeground(Color.WHITE);
		btnQuitGameC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelComplete.setVisible(false);
				panelEndGame.setVisible(true);
				lblMark.setText(mark +"/"+curQues);
				endTime = System.currentTimeMillis() - startTime;
				minutes = (int) (endTime/(1000*60));
				seconds = (int) (endTime/1000) - minutes*60;
				lblTime.setText(minutes +":"+seconds);
			}
		});
		btnQuitGameC.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(43, 551, 195, 60);
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
		btnQuitGameC.setText("Nghỉ chơi");
		btnQuitGameC.setFont(new Font("Serif", Font.BOLD, 20));
		btnQuitGameC.setContentAreaFilled(false);
		btnQuitGameC.setBorderPainted(false);
		btnQuitGameC.setBounds(45, 555, 190, 52);
		panelComplete.add(btnQuitGameC);
	
		
		JButton btnQuitGameMC = new JButton();
		btnQuitGameMC.setForeground(Color.WHITE);
		btnQuitGameMC.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(49, 551, 195, 60);
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
		btnQuitGameMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMultipleChoice.setVisible(false);
				panelEndGame.setVisible(true);
				lblMark.setText(mark +"/"+curQues);
				endTime = System.currentTimeMillis() - startTime;
				minutes = (int) (endTime/(1000*60));
				seconds = (int) (endTime/1000) - minutes*60;
				lblTime.setText(minutes +":"+seconds);
			}
			
		});
		
		btnQuitGameMC.setText("Nghỉ chơi");
		btnQuitGameMC.setFont(new Font("Serif", Font.BOLD, 20));
		btnQuitGameMC.setContentAreaFilled(false);
		btnQuitGameMC.setBorderPainted(false);
		btnQuitGameMC.setBounds(52, 555, 190, 53);
		panelMultipleChoice.add(btnQuitGameMC);
		
		panelComplete.add(new ButtonHome(frame));
		panelMultipleChoice.add(new ButtonHome(frame));
		
		panelEndGame.add(new ButtonHome(frame));
		
		BufferedImage bufferBackground2 = ImageIO.read(getClass().getResource("/trac nghiem.png"));
		ImageIcon image2 = new ImageIcon(bufferBackground2);
		bg2 = new JLabel(image2);
		bg2.setSize(544, 622);
		bg2.setLocation(0, 0);
		panelMultipleChoice.add(bg2);
		
		BufferedImage bufferBackground1 = ImageIO.read(getClass().getResource("/gamedientu.png"));
		ImageIcon image1 = new ImageIcon(bufferBackground1);
		bg1 = new JLabel(image1);
		bg1.setSize(544, 622);
		bg1.setLocation(0, 0);
		panelComplete.add(bg1);
		
		bg2.add(bgChosen);
		
		JButton btnReplay = new JButton("Chơi lại");
		btnReplay.setForeground(Color.WHITE);
		btnReplay.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(40, 550, 195, 60);
				bg3.add(bgChange);
				bg3.revalidate();
				bg3.repaint();
			}
			public void mouseExited(MouseEvent e) {
				bg3.remove(bgChange);
				bg3.revalidate();
				bg3.repaint();
			}
		});
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				GameWindow.execute();
			}
		});
		btnReplay.setFont(new Font("Serif", Font.BOLD, 20));
		btnReplay.setBounds(42, 554, 190, 53);
		panelEndGame.add(btnReplay);
		btnReplay.setContentAreaFilled(false);
		btnReplay.setBorderPainted(false);
		
		JButton btnQuit = new JButton("Thoát trò chơi");
		btnQuit.setForeground(Color.WHITE);
		btnQuit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(315, 551, 195, 60);
				bg3.add(bgChange);
				bg3.revalidate();
				bg3.repaint();
			}
			public void mouseExited(MouseEvent e) {
				bg3.remove(bgChange);
				bg3.revalidate();
				bg3.repaint();
			}
		});
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SubjectListFrame.executeBack();
			}
		});
		btnQuit.setFont(new Font("Serif", Font.BOLD, 20));
		btnQuit.setBounds(319, 554, 190, 53);
		panelEndGame.add(btnQuit);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		
		BufferedImage bufferBackground3 = ImageIO.read(getClass().getResource("/endgame.png"));
		ImageIcon image3 = new ImageIcon(bufferBackground3);
		bg3 = new JLabel(image3);
		bg3.setSize(544, 622);
		bg3.setLocation(0, 0);
		panelEndGame.add(bg3);
		
		BufferedImage bf = ImageIO.read(getClass().getResource("/nutbam.png"));
		ImageIcon imageChange = new ImageIcon(bf);
		bgChange = new JLabel(imageChange);
	}
	
	private class ActionAnswer implements ActionListener {
		private JButton btnThis;
		private JButton btn1;
		private JButton btn0;
		private JButton btn2;
		
		public ActionAnswer(JButton btnThis, JButton btn0, JButton btn1, JButton btn2) {
			this.btnThis = btnThis;
			this.btn0 = btn0;
			this.btn1 = btn1;
			this.btn2 = btn2;
		}
		public void actionPerformed(ActionEvent e) {
			btnThis.setForeground(Color.white);
			btn0.setForeground(Color.black);
			btn1.setForeground(Color.black);
			btn2.setForeground(Color.black);
			answerChosen = btnThis.getText();
			bgChosen.setBounds(btnThis.getX(), btnThis.getY(), btnThis.getWidth(), btnThis.getHeight());
			bg2.add(bgChosen);
			bg2.revalidate();
			bg2.repaint();
		}
		
	}
	
	private class NextQuestion implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (curQues < maxQues) {
				curQues = curQues+ 1;
				if (Math.random() < 0.5 ) {
					i = i + 1;
					lblNoQuesMC.setText("Câu " + curQues);
					bg2.remove(bgAnswer);
					bg2.remove(bgChosen);
					panelMultipleChoice.revalidate();
					panelMultipleChoice.repaint();
					panelComplete.setVisible(false);
					panelMultipleChoice.setVisible(true);
					labelQuestionMC.setText(quesList.get( i ).getQuestion());
					btnAnswer0.setForeground(Color.black);
					btnAnswer1.setForeground(Color.black);
					btnAnswer2.setForeground(Color.black);
					btnAnswer3.setForeground(Color.black);
					btnAnswer0.setText(quesList.get( i ).getAnswer0());
					btnAnswer1.setText(quesList.get( i ).getAnswer1());
					btnAnswer2.setText(quesList.get( i ).getAnswer2());
					btnAnswer3.setText(quesList.get( i ).getAnswer3());
					answerChosen = "";
				}
				else {
					i = i + 1;
					panelMultipleChoice.setVisible(false);
					panelComplete.setVisible(true);
					lblNoQuesC.setText("Câu " + curQues);
					lblQuestionC.setText(quesList.get(i).getQuestion());
					textFieldAnswer.setText("");
				}
			}
			else {
				panelComplete.setVisible(false);
				panelMultipleChoice.setVisible(false);
				panelEndGame.setVisible(true);
				lblMark.setText(mark + "/" +  curQues);
				endTime = System.currentTimeMillis() - startTime;
				minutes = (int) (endTime/(1000*60));
				seconds = (int) (endTime/1000) - minutes*60;
				lblTime.setText(minutes +":"+seconds);
			}
		}
	}
	
	private class MCNextAction extends NextQuestion {
		private JButton btn3;
		private JButton btn1;
		private JButton btn0;
		private JButton btn2;
		
		public MCNextAction (JButton btn0, JButton btn1, JButton btn2, JButton btn3) {
			this.btn3 = btn3;
			this.btn0 = btn0;
			this.btn1 = btn1;
			this.btn2 = btn2;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (btn0.getText().equals(quesList.get(i).getAnswerTrue()) ) {
				bgAnswer.setBounds(44, 283, 462, 57);
				btn0.setForeground(Color.white);
			}
			if (btn1.getText().equals(quesList.get(i).getAnswerTrue()) ) {
				bgAnswer.setBounds(44, 347, 462, 57);
				btn1.setForeground(Color.white);
			}
			if (btn2.getText().equals(quesList.get(i).getAnswerTrue()) ) {
				bgAnswer.setBounds(44, 410, 461, 57);
				btn2.setForeground(Color.white);
			}
			if (btn3.getText().equals(quesList.get(i).getAnswerTrue()) ) {
				bgAnswer.setBounds(45, 474, 461, 57);
				btn3.setForeground(Color.white);
			}
			if (bgAnswer.getY() == bgChosen.getY()) 
				bg2.remove(bgChosen);
			bg2.add(bgAnswer);
			bg2.revalidate();
			bg2.repaint();
			if (answerChosen.equalsIgnoreCase(quesList.get(i).getAnswerTrue())) {
				mark ++;
				JOptionPane.showMessageDialog(frame, "Chính xác", "Kết quả", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Chưa chính xác", "Kết quả", JOptionPane.PLAIN_MESSAGE);

			}
			super.actionPerformed(e);
		}
	}
	
	private class CNextAction extends NextQuestion {
		public void actionPerformed(ActionEvent e) {
			if (textFieldAnswer.getText().equalsIgnoreCase(quesList.get(i).getAnswerTrue())) {
				mark = mark + 1;
				JOptionPane.showMessageDialog(frame, "Chính xác", "Kết quả", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Chưa chính xác\nĐáp án là: " + quesList.get(i).getAnswerTrue(), "Kết quả", JOptionPane.PLAIN_MESSAGE);
			}
			super.actionPerformed(e);
		}
	}
}
