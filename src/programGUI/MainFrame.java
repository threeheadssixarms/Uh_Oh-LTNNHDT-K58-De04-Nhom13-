package programGUI;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame {

	private JFrame frmProgram;
	public static Dimension r;

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
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public MainFrame() throws IOException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void initialize() throws IOException, SQLException {
		r = Toolkit.getDefaultToolkit().getScreenSize();
		
		frmProgram = new JFrame();
		frmProgram.setIconImage(Toolkit.getDefaultToolkit().getImage("image2\\thumbnail_burned.png"));
		frmProgram.setTitle("Uh-Oh");
		frmProgram.setBounds((r.width-550)/2, (r.height-650)/2, 550, 650);
		frmProgram.setResizable(false);
		frmProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgram.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelMain = new JPanel();
		frmProgram.getContentPane().add(panelMain, "name_2876111040791");
		panelMain.setLayout(null);
		
		
		final JButton btnAddSubject = new JButton("Thêm đề tài");
		
		btnAddSubject.setForeground(new Color(11, 107, 105));
		btnAddSubject.setBounds(133, 250, 279, 74);
		btnAddSubject.setBorderPainted(false);
		btnAddSubject.setContentAreaFilled(false);
		btnAddSubject.setFont(new Font("Serif", Font.BOLD, 23));
		
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProgram.setVisible(false);
				InsertDataWindow.execute();
			}
		});
		btnAddSubject.addMouseListener(new MouseInAndOut(btnAddSubject));
		
		panelMain.add(btnAddSubject);

		
		final JButton btnSubjectList = new JButton("Danh sách đề tài");
		btnSubjectList.setBounds(133, 359, 279, 74);
		btnSubjectList.setForeground(new Color(11, 107, 105));
		btnSubjectList.setFont(new Font("Serif", Font.BOLD, 23));
		btnSubjectList.setBorderPainted (false);
		btnSubjectList.setContentAreaFilled (false);
		
		btnSubjectList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProgram.setVisible(false);
				SubjectListFrame.execute();
			}
		});
		btnSubjectList.addMouseListener(new MouseInAndOut(btnSubjectList));
		panelMain.add(btnSubjectList);

		
		final JButton btnExit = new JButton("");
		btnExit.setBounds(133, 464, 279, 74);
		btnExit.setForeground(new Color(11, 107, 105));
		btnExit.setText("Thoát");
		btnExit.setFont(new Font("Serif", Font.BOLD, 23));
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		
		btnExit.addMouseListener(new MouseInAndOut(btnExit));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgram.setVisible(false);
			}
		});
		
		panelMain.add(btnExit);
		
		
		BufferedImage bufferBackground = ImageIO.read(getClass().getResource("/MainFrame.png"));
		ImageIcon image = new ImageIcon(bufferBackground);
		JLabel bg1 = new JLabel(image);
		bg1.setSize(544, 622);
		bg1.setLocation(0, 0);
		panelMain.add(bg1);
	}
}
