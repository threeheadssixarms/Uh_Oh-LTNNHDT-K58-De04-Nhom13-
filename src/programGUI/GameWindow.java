package programGUI;
import Database.*;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameWindow {

	private JFrame frame;
	public JPanel panelMultipleChoice;
	public JPanel panelComplete;
	public JPanel panelEndGame;
	static int i;
	static int j;
	static int curQues;
	static int mark;
	ArrayList<MultipleChoiceData> multiList;
	ArrayList<LearnData> completeList;
	ArrayList<Question> quesList;
	private JLabel labelQuestionMC;
	private JButton btnNextC;
	private JButton btnAnswer0;
	private JButton btnAnswer1;
	private JButton btnAnswer2;
	private JButton btnAnswer3;
	
	private JLabel lblQuestionC;
	private JTextField textFieldAnswer;

	static String answerChosen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	 */
	public GameWindow() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() throws SQLException {
		multiList = MultipleChoiceData.getQuestionList();
		completeList = LearnData.getQuestionList();
		quesList = Question.getQuestionList();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel panelComplete = new JPanel();
		frame.getContentPane().add(panelComplete, "name_994528488738");
		panelComplete.setLayout(null);
		panelComplete.setVisible(true);
		
		
		final JPanel panelEndGame = new JPanel();
		frame.getContentPane().add(panelEndGame, "name_6327022998073");
		panelEndGame.setLayout(null);
		
		final JLabel lblMark = new JLabel();
		lblMark.setBounds(81, 90, 329, 160);
		panelEndGame.add(lblMark);
		panelEndGame.setVisible(false);
		
		final JLabel lblQuestionC = new JLabel("");
		//lblQuestionC.setText(completeList.get(j).getEn());
		lblQuestionC.setText(quesList.get(i).getQuestion());
		lblQuestionC.setBounds(51, 46, 298, 108);
		panelComplete.add(lblQuestionC);
		
		textFieldAnswer = new JTextField();
		textFieldAnswer.setBounds(51, 212, 371, 56);
		panelComplete.add(textFieldAnswer);
		textFieldAnswer.setColumns(10);
		
		JButton btnNextC = new JButton();
		btnNextC.setText("Tiếp");
		btnNextC.setBounds(333, 402, 89, 56);
		panelComplete.add(btnNextC);
		
		final JPanel panelMultipleChoice = new JPanel();
		frame.getContentPane().add(panelMultipleChoice, "name_995795426270");
		panelMultipleChoice.setLayout(null);
		panelMultipleChoice.setVisible(false);
		
		final JLabel labelQuestionMC = new JLabel();
		labelQuestionMC.setFont(new Font("Times New Roman", Font.BOLD, 25));
		//labelQuestionMC.setText(multiList.get( i ).getQuestion());
		labelQuestionMC.setText(quesList.get( i ).getQuestion());
		labelQuestionMC.setBounds(44, 77, 332, 98);
		panelMultipleChoice.add(labelQuestionMC);
		
		final JButton btnAnswer0 = new JButton("");
		//btnAnswer0.setText(multiList.get( i ).getAnswer0());
		btnAnswer0.setText(quesList.get( i ).getAnswer0());
		btnAnswer0.setBounds(44, 261, 135, 53);
		panelMultipleChoice.add(btnAnswer0);
		
		final JButton btnAnswer1 = new JButton("");
		//btnAnswer1.setText(multiList.get( i ).getAnswer1());
		btnAnswer1.setText(quesList.get( i ).getAnswer1());
		btnAnswer1.setBounds(336, 261, 135, 53);
		panelMultipleChoice.add(btnAnswer1);
		
		final JButton btnAnswer2 = new JButton("");
		//btnAnswer2.setText(multiList.get( i ).getAnswer2());
		btnAnswer2.setText(quesList.get( i ).getAnswer2());
		btnAnswer2.setBounds(44, 392, 135, 53);
		panelMultipleChoice.add(btnAnswer2);
		
		final JButton btnAnswer3 = new JButton();
		//btnAnswer3.setText(multiList.get( i ).getAnswer3());
		btnAnswer3.setText(quesList.get( i ).getAnswer3());
		btnAnswer3.setBounds(336, 392, 135, 53);
		panelMultipleChoice.add(btnAnswer3);
		
		JButton btnNextMC = new JButton();
		btnNextMC.setText("Tiếp");
		btnNextMC.setBounds(382, 527, 107, 45);
		panelMultipleChoice.add(btnNextMC);
		
		btnAnswer0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				answerChosen = btnAnswer0.getText();
			}
		});
		
		btnAnswer1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				answerChosen = btnAnswer1.getText();
			}
		});
		
		btnAnswer2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				answerChosen = btnAnswer2.getText();
			}
		});
		
		btnAnswer3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				answerChosen = btnAnswer3.getText();
			}
		});
		
		btnNextC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				if (textFieldAnswer.getText().equalsIgnoreCase(quesList.get(i).getAnswerTrue())) {
					mark = mark + 1;
					System.out.print("đúng");
				}
				else {
					System.out.print("sai");
				}
				System.out.println(mark);
				if (curQues < 10) {
					curQues = curQues+ 1;
					if (Math.random() < 0.5 ) {
						i = i + 1;
						panelComplete.setVisible(false);
						panelMultipleChoice.setVisible(true);
						/*
						labelQuestionMC.setText(multiList.get( i ).getQuestion());
						btnAnswer0.setText(multiList.get( i ).getAnswer0());
						btnAnswer1.setText(multiList.get( i ).getAnswer1());
						btnAnswer2.setText(multiList.get( i ).getAnswer2());
						btnAnswer3.setText(multiList.get( i ).getAnswer3());
						*/
						labelQuestionMC.setText(quesList.get( i ).getQuestion());
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
						//lblQuestionC.setText(completeList.get(j).getEn());
						lblQuestionC.setText(quesList.get(i).getQuestion());
						textFieldAnswer.setText("");
					}
				}
				else {
					panelComplete.setVisible(false);
					panelMultipleChoice.setVisible(false);
					panelEndGame.setVisible(true);
					lblMark.setText(mark + "/" +  curQues);
				}
			}
		});
		btnNextMC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//if (answerChosen.equals(multiList.get(i).getAnswerTrue())) {
				if (answerChosen.equals(quesList.get(i).getAnswerTrue())) {
					mark = mark + 1;
					System.out.print("đúng");
				}
				else {
					System.out.print("sai");
				}
				System.out.println(mark);
				if (curQues < 10) {
					curQues = curQues+ 1;
					if (Math.random() < 0.5 ) {
						i = i + 1;
						panelComplete.setVisible(false);
						panelMultipleChoice.setVisible(true);
						/*
						labelQuestionMC.setText(multiList.get( i ).getQuestion());
						btnAnswer0.setText(multiList.get( i ).getAnswer0());
						btnAnswer1.setText(multiList.get( i ).getAnswer1());
						btnAnswer2.setText(multiList.get( i ).getAnswer2());
						btnAnswer3.setText(multiList.get( i ).getAnswer3());
						*/
						labelQuestionMC.setText(quesList.get( i ).getQuestion());
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
						//lblQuestionC.setText(completeList.get(i).getEn());
						lblQuestionC.setText(quesList.get(i).getQuestion());
						textFieldAnswer.setText("");
					}
				}
				else {
					panelComplete.setVisible(false);
					panelMultipleChoice.setVisible(false);
					panelEndGame.setVisible(true);
					lblMark.setText(mark + "/10");
				}
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
		
	}
}
