package programGUI;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.JMenuBar;

public class LearnWord extends JFrame {

	private JPanel contentPane;
	private JLabel lblWord;
	private JLabel lblMeaning;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearnWord frame = new LearnWord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LearnWord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWord = new JLabel();
		lblWord.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblWord.setBounds(60, 53, 328, 49);
		contentPane.add(lblWord);
		
		lblMeaning = new JLabel();
		lblMeaning.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMeaning.setBounds(60, 188, 351, 177);
		contentPane.add(lblMeaning);
		
		JLabel lblNewWord = new JLabel("T\u1EEB m\u1EDBi");
		lblNewWord.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewWord.setBounds(60, 11, 127, 31);
		contentPane.add(lblNewWord);
		
		JButton btnShowExam = new JButton("Hi\u1EC7n v\u00ED d\u1EE5");
		btnShowExam.setFont(new Font("Arial", Font.PLAIN, 13));
		btnShowExam.setBounds(60, 131, 106, 35);
		contentPane.add(btnShowExam);
		
		JButton btnShowMeaning = new JButton("Hi\u1EC7n ngh\u0129a");
		btnShowMeaning.setFont(new Font("Arial", Font.PLAIN, 13));
		btnShowMeaning.setBounds(282, 131, 106, 35);
		contentPane.add(btnShowMeaning);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnShowExam, btnShowMeaning}));
	}
}
