package programGUI;
import Database.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;

public class SubjectListFrame {

	private JFrame frame;
	Theme dataTheme = new Theme();
	private JTextField textField;
	private ArrayList<Theme> themeList;
	private JPanel panelListSubject;
	private static JPanel panelChoice;
	private static JPanel panelOpen;
	private JScrollPane scrollPane;
	private JButton btnGame;
	private JButton btnListWord;
	private JButton btnLearn;
	private static JLabel lblSubjectName;
	private ArrayList<JButton> buttonList;
	int i;
	private JLabel bg1;
	private JLabel bg2;

	/**
	 * Launch the application.
	 */
	
	public static void execute() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Word.theme = "";
					Question.theme = "";
					SubjectListFrame window = new SubjectListFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void executeBack() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubjectListFrame window = new SubjectListFrame();
					window.frame.setVisible(true);
					panelOpen.setVisible(false);
					panelChoice.setVisible(true);
					lblSubjectName.setText(LearnWordWindow.ToFirstUpper(Word.theme));
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
	public SubjectListFrame() throws SQLException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private void initialize() throws SQLException, IOException {
		themeList = Theme.getThemeList();
		buttonList = new ArrayList<JButton>();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds((MainFrame.r.width-550)/2, (MainFrame.r.height-650)/2, 550, 650);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image2\\thumbnail_burned.png"));
		frame.setTitle("Uh-Oh");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panelChoice = new JPanel();
		frame.getContentPane().add(panelChoice, "name_8957925332661");
		panelChoice.setLayout(null);
		 
		panelOpen = new JPanel();
		frame.getContentPane().add(panelOpen, "name_2303264168791");
		panelOpen.setLayout(null);
		
		panelListSubject = new TransparentPanel();
		panelListSubject.setBackground(new Color(0,0,0,0));
		panelListSubject.setLayout(null);
		panelListSubject.setBounds(0, 0, 350, 500);
		panelListSubject.setPreferredSize(new Dimension(0,100*themeList.size()));
		panelListSubject.setOpaque(false);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelListSubject);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		 
		scrollPane.setBounds(70, 137, 411, 400);
		panelOpen.add(scrollPane);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane.setViewportView(panelListSubject);
		 
		for (i = 0; i < themeList.size(); i++) {
			final JButton btnTmp = new ButtonSubject();
			btnTmp.setVisible(true);
			btnTmp.setLocation(0, 100*i);
			String tmp = themeList.get(i).getTheme();
			btnTmp.setToolTipText(themeList.get(i).getDes());
			if (tmp.length() > 1)  
                tmp = tmp.substring(0, 1).toUpperCase() + tmp.substring(1).toLowerCase() + "";  
            else  
                tmp = tmp.toUpperCase() + "";
			btnTmp.setText(tmp);
			btnTmp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Word.theme = btnTmp.getText().toLowerCase();
					Question.theme = btnTmp.getText().toLowerCase();
					Word.key = "";
					lblSubjectName.setText(btnTmp.getText());
					scrollPane.setVisible(false);
					panelOpen.setVisible(false);
					panelChoice.setVisible(true);
				}
			});	
			panelListSubject.add(btnTmp);

		 }
	    
	    btnLearn = new JButton("Học từ mới");
	    btnLearn.setFont(new Font("Serif", Font.PLAIN, 25));
	    btnLearn.setBounds(129, 264, 288, 79);
	    btnLearn.setContentAreaFilled(false);
	    btnLearn.setBorderPainted(false);
	    panelChoice.add(btnLearn);
	    btnLearn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!Word.isThemeEmpty()) {
						frame.setVisible(false);
						LearnWordWindow.execute();
					}
					else {
						JOptionPane.showMessageDialog(frame, "Đề tài "+ ListWordWindow.ToFirstUpper(Word.theme) + "\nkhông có từ nào", "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	    btnLearn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnLearn.setBorderPainted(true);
			}
			
			public void mouseExited(MouseEvent e) {
				btnLearn.setBorderPainted(false);
			}
		});
	    
	    btnListWord = new JButton("Danh sách từ");
	    btnListWord.setFont(new Font("Serif", Font.PLAIN, 25));
	    btnListWord.setContentAreaFilled(false);
	    btnListWord.setBorderPainted(false);
	    btnListWord.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.setVisible(false);
	    		ListWordWindow.execute();
	    	}
	    });
	    btnListWord.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnListWord.setBorderPainted(true);
			}
			
			public void mouseExited(MouseEvent e) {
				btnListWord.setBorderPainted(false);
			}
		});
	    btnListWord.setBounds(129, 359, 288, 79);
	    panelChoice.add(btnListWord);
	    
	    btnGame = new JButton("");
	    btnGame.setFont(new Font("Serif", Font.PLAIN, 25));
	    btnGame.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try {
					if (!Word.isThemeEmpty()) {
						frame.setVisible(false);
						GameWindow.execute();
					}
					else {
						JOptionPane.showMessageDialog(frame, "Đề tài "+ ListWordWindow.ToFirstUpper(Word.theme) + "\nkhông có từ nào", "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    });
	    btnGame.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnGame.setBorderPainted(true);
			}
			
			public void mouseExited(MouseEvent e) {
				btnGame.setBorderPainted(false);
			}
		});
	    btnGame.setBounds(129, 452, 288, 78);
	    btnGame.setContentAreaFilled(false);
	    btnGame.setBorderPainted(false);
	    panelChoice.add(btnGame);
	    btnGame.setText("Trò chơi");
	    
	    JButton btnSearch = new JButton("");
	    btnSearch.addActionListener(new SearchAction());
	    btnSearch.setBounds(434, 72, 40, 40);
	    panelOpen.add(btnSearch);
	    btnSearch.setContentAreaFilled(false);
	    
	    textField = new JTextField();
	    textField.setFont(new Font("SansSerif", Font.PLAIN, 13));
	    textField.addActionListener(new SearchAction());
	    textField.setLocation(79, 72);
	    textField.setSize(345, 40);
	    panelOpen.add(textField);
	    textField.setColumns(10);
	    
	    panelChoice.setVisible(false);
	    panelOpen.setVisible(true);
	    
	    
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				MainFrame.main(null);
			}
		});
		btnBack.setFont(new Font("Serif", Font.PLAIN, 16));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(47, 548, 89, 64);
		panelOpen.add(btnBack);
		
		ButtonHome btnHome = new ButtonHome(frame);
		panelOpen.add(btnHome);
		ButtonHome btnHome1 = new ButtonHome(frame);
		panelChoice.add(btnHome1);
		
		BufferedImage bufferBackground1 = ImageIO.read(getClass().getResource("/dsDeTai_quaylai.png"));
		ImageIcon image1 = new ImageIcon(bufferBackground1);
		bg1 = new JLabel(image1);
		bg1.setSize(544, 622);
		bg1.setLocation(0, 0);
		panelOpen.add(bg1);
		
		BufferedImage bufferBackground2 = ImageIO.read(getClass().getResource("/hoc_choi_coquaylai.png"));
		ImageIcon image2 = new ImageIcon(bufferBackground2);
		
		lblSubjectName = new JLabel("");
		lblSubjectName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectName.setForeground(Color.WHITE);
		lblSubjectName.setFont(new Font("Gentium Book Basic", Font.BOLD | Font.ITALIC, 45));
		lblSubjectName.setBounds(0, 172, 544, 90);
		panelChoice.add(lblSubjectName);
		
		JButton btnBackToList = new JButton("Quay lại");
		btnBackToList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Word.theme = "";
				//Question.theme = "";
				frame.setVisible(false);
				SubjectListFrame.execute();
			}
		});
		btnBackToList.setFont(new Font("Serif", Font.PLAIN, 16));
		btnBackToList.setContentAreaFilled(false);
		btnBackToList.setBorderPainted(false);
		btnBackToList.setBounds(41, 536, 94, 64);
		panelChoice.add(btnBackToList);
		bg2 = new JLabel(image2);
		bg2.setForeground(Color.BLACK);
		bg2.setSize(544, 622);
		bg2.setLocation(0, 0);
		panelChoice.add(bg2);
	}
	public class TransparentPanel extends JPanel {
	    {
	        setOpaque(false);
	    }
	    public void paintComponent(Graphics g) {
	        g.setColor(getBackground());
	        Rectangle r = g.getClipBounds();
	        g.fillRect(r.x, r.y, r.width, r.height);
	        super.paintComponent(g);
	    }
	}
	
	private class SearchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Theme.key = textField.getText().trim().toLowerCase();
    		try {
				themeList = Theme.searchThemeList();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		panelListSubject.removeAll();
    		panelListSubject.revalidate();
    		panelListSubject.repaint();
			panelListSubject.setPreferredSize(new Dimension(0, themeList.size()*100));

    		for (i = 0; i < themeList.size(); i++) {
    			final JButton btnTmp = new ButtonSubject();
    			btnTmp.setVisible(true);
    			btnTmp.setLocation(0, i*100);
    			String tmp = themeList.get(i).getTheme();
    			btnTmp.setToolTipText(themeList.get(i).getDes());
    			if (tmp.length() > 1)  
                    tmp = tmp.substring(0, 1).toUpperCase() + tmp.substring(1).toLowerCase() + "";  
                else  
                    tmp = tmp.toUpperCase() + "";
    			btnTmp.setText(tmp);
    			panelListSubject.add(btnTmp);
    			btnTmp.addActionListener(new ActionListener() {
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					// TODO Auto-generated method stub
    					Word.theme = btnTmp.getText().toLowerCase();
    					Question.theme = btnTmp.getText().toLowerCase();
						lblSubjectName.setText(btnTmp.getText());
						scrollPane.setVisible(false);
						panelOpen.setVisible(false);
						panelChoice.setVisible(true);
    				}
    			});	
    		 }
    	}
    }
	
	public class ButtonSubject extends JButton {
		{
		setFont(new Font("Serif", Font.BOLD, 23));
		setForeground(Color.WHITE);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setSize(411, 100);
		}
	}
	
	
}