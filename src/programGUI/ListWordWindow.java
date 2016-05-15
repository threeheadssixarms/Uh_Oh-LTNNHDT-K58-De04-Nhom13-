package programGUI;
import Database.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import programGUI.SubjectListFrame.ButtonSubject;
import programGUI.SubjectListFrame.TransparentPanel;
import Database.Word;

public class ListWordWindow {

	private JFrame frame;
	private JPanel changeWordPanel;
	private JPanel panelOpen;
	private JPanel panelListWord;
	private ArrayList<Word> wordList;
	private JScrollPane scrollPane;
	private int i;
	private String delete;
	private JTextField textFieldEnglish;
	private JTextField textFieldViet;
	private JTextArea textAreaExample;
	private JTextField textFieldSearch;
	private Word changeWord;
	private JLabel bgChange;
	private JLabel bg1;
	private JLabel bg2;

	/**
	 * Launch the application.
	 */
	
	
	public static void execute() {
		System.out.println(Word.theme);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListWordWindow window = new ListWordWindow();
					window.frame.setVisible(true);
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
	public ListWordWindow() throws IOException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void initialize() throws IOException, SQLException {
		changeWord = new Word("","","");
		delete = "";
		frame = new JFrame("Uh-Oh");
		frame.setBounds((MainFrame.r.width-550)/2, (MainFrame.r.height-650)/2, 550, 650);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image2\\thumbnail_burned.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setResizable(false);
		
		wordList = Word.searchWordList();
		
		final JPanel changeWordPanel = new JPanel();
		frame.getContentPane().add(changeWordPanel, "name_1111198421676");
		changeWordPanel.setLayout(null);
		
		JLabel lblEnglish = new JLabel("Từ tiếng Anh");
		lblEnglish.setForeground(Color.WHITE);
		lblEnglish.setFont(new Font("Serif", Font.BOLD, 20));
		lblEnglish.setBounds(48, 92, 150, 50);
		changeWordPanel.add(lblEnglish);
		
		textFieldEnglish = new JTextField();
		textFieldEnglish.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldEnglish.setBounds(53, 139, 455, 65);
		changeWordPanel.add(textFieldEnglish);
		textFieldEnglish.setBorder(null);
		textFieldEnglish.setColumns(10);
		
		JLabel lblExample = new JLabel("Câu ví dụ");
		lblExample.setForeground(Color.WHITE);
		lblExample.setFont(new Font("Serif", Font.BOLD, 20));
		lblExample.setBounds(48, 327, 137, 42);
		changeWordPanel.add(lblExample);
		
		textAreaExample = new JTextArea();
		textAreaExample.setWrapStyleWord(true);
		textAreaExample.setLineWrap(true);
		textAreaExample.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textAreaExample.setBounds(48, 371, 460, 144);
		changeWordPanel.add(textAreaExample);
		
		JLabel labelViet = new JLabel("Nghĩa tiếng Việt");
		labelViet.setForeground(Color.WHITE);
		labelViet.setFont(new Font("Serif", Font.BOLD, 20));
		labelViet.setBounds(48, 210, 187, 42);
		changeWordPanel.add(labelViet);
		
		textFieldViet = new JTextField();
		textFieldViet.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldViet.setColumns(10);
		textFieldViet.setBounds(50, 255, 455, 65);
		textFieldViet.setBorder(null);
		changeWordPanel.add(textFieldViet);
		
		JButton btnBackToSubject = new JButton("Quay lại");
		btnBackToSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Word.key = "";
				frame.setVisible(false);
				ListWordWindow.execute();
			}
		});
		btnBackToSubject.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(40, 553, 200, 60);
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
		btnBackToSubject.setForeground(Color.WHITE);
		btnBackToSubject.setContentAreaFilled(false);
		btnBackToSubject.setBorderPainted(false);
		btnBackToSubject.setFont(new Font("Serif", Font.BOLD, 20));
		btnBackToSubject.setBounds(46, 555, 189, 53);
		changeWordPanel.add(btnBackToSubject);
		
		JButton btnUpdateWord = new JButton("Lưu");
		btnUpdateWord.setForeground(Color.WHITE);
		btnUpdateWord.setContentAreaFilled(false);
		btnUpdateWord.setBorderPainted(false);
		btnUpdateWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e0) {
				if (textFieldEnglish.getText().trim().isEmpty() || textFieldViet.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ");
				}
				else {
					try {
						if(changeWord.update(textFieldEnglish.getText().trim().toLowerCase(), textFieldViet.getText().trim().toLowerCase(),
								textAreaExample.getText().trim()) == false) {
							JOptionPane.showMessageDialog(frame, "Từ này đã có trong csdl");
						}
						else {
							JOptionPane.showMessageDialog(frame, "Sửa từ thành công");

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnUpdateWord.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				bgChange.setBounds(314, 553, 200, 60);
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
		
		btnUpdateWord.setFont(new Font("Serif", Font.BOLD, 20));
		btnUpdateWord.setBounds(319, 555, 189, 53);
		changeWordPanel.add(btnUpdateWord);
		
		panelOpen = new JPanel();
		frame.getContentPane().add(panelOpen, "name_2303264168791");
		panelOpen.setLayout(null);
		
		panelListWord = new TransparentPanel();
		panelListWord.setBackground(new Color(0,0,0,0));
		panelListWord.setLayout(null);
		panelListWord.setBounds(0, 0, 350, 500);
		panelListWord.setPreferredSize(new Dimension(0,55*wordList.size()+55));
		panelListWord.setOpaque(false);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelListWord);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		final JButton btnAddWord = new JButton();
		btnAddWord.setBounds(0, 0, 411, 55);
		btnAddWord.setText("Thêm từ");
		btnAddWord.setContentAreaFilled(false);
		btnAddWord.setBorder(new LineBorder(Color.white));
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				InsertDataWindow.addWord();
			}
		});
		btnAddWord.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnAddWord.setBorder(new LineBorder(Color.orange));
			}
			public void mouseExited(MouseEvent e) {
				btnAddWord.setBorder(new LineBorder(Color.white));
			} 
		});
		panelListWord.add(btnAddWord);
		
		for (i = 0; i < wordList.size(); i ++) {
			final JLabel lblTmpEn = new JLabel();
			lblTmpEn.setText(ToFirstUpper(wordList.get(i).getEn()));
			lblTmpEn.setSize(145,55);
			lblTmpEn.setLocation(0, 55*i+55);
			lblTmpEn.setFont(new Font("Serif", Font.PLAIN, 15));
			lblTmpEn.setBorder(BorderFactory.createLineBorder(Color.white));
			lblTmpEn.setHorizontalAlignment(SwingConstants.CENTER);
			final JLabel lblTmpVn = new JLabel();
			lblTmpVn.setText(ToFirstUpper(wordList.get(i).getVn()));
			lblTmpVn.setSize(145,55);
			lblTmpVn.setLocation(145, 55*i+55);
			lblTmpVn.setBorder(BorderFactory.createLineBorder(Color.white));
			lblTmpVn.setHorizontalAlignment(SwingConstants.CENTER);
			lblTmpVn.setFont(new Font("Serif", Font.PLAIN, 15));
			final JButton btnTmpChange = new JButton();
			btnTmpChange.setBorder(BorderFactory.createLineBorder(Color.white));
			btnTmpChange.setSize(55,55);
			btnTmpChange.setLocation(290, 55*i+55);
			btnTmpChange.setContentAreaFilled(false);
			btnTmpChange.setIcon(new ImageIcon("image2\\change.png"));
			final String tmpEg = wordList.get(i).getEg();
			btnTmpChange.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0){
					
					textFieldEnglish.setText(lblTmpEn.getText());
					textFieldViet.setText(lblTmpVn.getText());
					textAreaExample.setText(tmpEg);
					changeWord.setEn(lblTmpEn.getText().toLowerCase());
					changeWord.setVn(lblTmpVn.getText().toLowerCase());
					changeWord.setEg(tmpEg);
					panelOpen.setVisible(false);
					changeWordPanel.setVisible(true);
				}
			});
			btnTmpChange.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent arg0) {
					btnTmpChange.setBorder(new LineBorder(Color.orange));
				}
				public void mouseExited(MouseEvent e) {
					btnTmpChange.setBorder(new LineBorder(Color.white));
				} 
			});
			final JButton btnTmpDelete = new JButton();
			btnTmpDelete.setBorder(BorderFactory.createLineBorder(Color.white));
			btnTmpDelete.setSize(55,55);
			btnTmpDelete.setLocation(345, 55*i+55);
			btnTmpDelete.setIcon(new ImageIcon("image2\\bin.png"));
			btnTmpDelete.setContentAreaFilled(false);
			btnTmpDelete.addActionListener(new DeleteAction(lblTmpEn.getText().toLowerCase()));
			btnTmpDelete.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent arg0) {
					btnTmpDelete.setBorder(new LineBorder(Color.orange));
				}
				public void mouseExited(MouseEvent e) {
					btnTmpDelete.setBorder(new LineBorder(Color.white));
				} 
			});
			panelListWord.add(lblTmpEn);
			panelListWord.add(lblTmpVn);
			panelListWord.add(btnTmpChange);
			panelListWord.add(btnTmpDelete);
		}
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new SearchAction());
		btnSearch.setBounds(434, 72, 40, 40);
		panelOpen.add(btnSearch);
		btnSearch.setContentAreaFilled(false);
		
		scrollPane.setBounds(70, 137, 411, 400);
		panelOpen.add(scrollPane);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane.setViewportView(panelListWord);
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.setFont(new Font("Serif", Font.PLAIN, 16));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(47, 548, 89, 64);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Word.key = "";
				frame.setVisible(false);
				SubjectListFrame.executeBack();
			}
		});
		panelOpen.add(btnBack);
		
		panelOpen.add(new ButtonHome(frame));
		changeWordPanel.add(new ButtonHome(frame));
		
		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("SansSerif", Font.PLAIN, 13));
		textFieldSearch.setLocation(79, 72);
		textFieldSearch.setSize(345, 40);
		textFieldSearch.addActionListener(new SearchAction());
		panelOpen.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		BufferedImage bufferBackground1 = ImageIO.read(getClass().getResource("/dsDeTai_quaylai.png"));
		ImageIcon image1 = new ImageIcon(bufferBackground1);
		bg1 = new JLabel(image1);
		bg1.setSize(544, 622);
		bg1.setLocation(0, 0);
		panelOpen.add(bg1);
		panelOpen.setVisible(true);
		changeWordPanel.setVisible(false);

		BufferedImage bufferBackground2 = ImageIO.read(getClass().getResource("/themtu.png"));
		ImageIcon image2 = new ImageIcon(bufferBackground2);
		bg2 = new JLabel(image2);
		bg2.setFont(new Font("Serif", Font.BOLD, 15));
		bg2.setSize(544, 622);
		bg2.setLocation(0, 0);
		changeWordPanel.add(bg2);
		
		BufferedImage bf = ImageIO.read(getClass().getResource("/nutbam.png"));
		ImageIcon imageChange = new ImageIcon(bf);
		bgChange = new JLabel(imageChange);
		//bgChange.setBounds(314, 553, 200, 60);
		//bg2.add(bgChange);
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
	
	public static String ToFirstUpper(String tmp) {
		if (tmp.length() == 0) return null;
		String result;
		if (tmp.length() > 1)  
            result = tmp.substring(0, 1).toUpperCase() + tmp.substring(1).toLowerCase() + "";  
        else  
            result = tmp.toUpperCase() + "";
		return result;
	}
	
	private class SearchAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Word.key = textFieldSearch.getText().toLowerCase();
			try {
				wordList = Word.searchWordList();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			panelListWord.removeAll();
			panelListWord.revalidate();
			panelListWord.repaint();
			panelListWord.setPreferredSize(new Dimension(0, wordList.size()*55 + 55));
			final JButton btnAddWord = new JButton();
			btnAddWord.setBounds(0, 0, 411, 55);
			btnAddWord.setText("Thêm từ");
			btnAddWord.setContentAreaFilled(false);
			btnAddWord.setBorder(new LineBorder(Color.white));
			btnAddWord.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					InsertDataWindow.addWord();
				}
			});
			btnAddWord.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent arg0) {
					btnAddWord.setBorder(new LineBorder(Color.orange));
				}
				public void mouseExited(MouseEvent e) {
					btnAddWord.setBorder(new LineBorder(Color.white));
				} 
			});
			panelListWord.add(btnAddWord);
			for (i = 0; i < wordList.size(); i ++) {
				final JLabel lblTmpEn = new JLabel();
				lblTmpEn.setText(ToFirstUpper(wordList.get(i).getEn()));
				lblTmpEn.setSize(145,55);
				lblTmpEn.setLocation(0, 55*i+55);
				lblTmpEn.setBorder(BorderFactory.createLineBorder(Color.white));
				lblTmpEn.setHorizontalAlignment(SwingConstants.CENTER);
				final JLabel lblTmpVn = new JLabel();
				lblTmpVn.setText(ToFirstUpper(wordList.get(i).getVn()));
				lblTmpVn.setSize(145,55);
				lblTmpVn.setLocation(145, 55*i+55);
				lblTmpVn.setBorder(BorderFactory.createLineBorder(Color.white));
				lblTmpVn.setHorizontalAlignment(SwingConstants.CENTER);
				final JButton btnTmpChange = new JButton();
				btnTmpChange.setBorder(BorderFactory.createLineBorder(Color.white));
				btnTmpChange.setSize(55,55);
				btnTmpChange.setLocation(290, 55*i+55);
				btnTmpChange.setContentAreaFilled(false);
				btnTmpChange.setIcon(new ImageIcon("image\\change.png"));
				final String tmpEg = wordList.get(i).getEg();
				btnTmpChange.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0){
						
						textFieldEnglish.setText(lblTmpEn.getText());
						textFieldViet.setText(lblTmpVn.getText());
						textAreaExample.setText(tmpEg);
						changeWord.setEn(lblTmpEn.getText().toLowerCase());
						changeWord.setVn(lblTmpVn.getText().toLowerCase());
						changeWord.setEg(tmpEg);
						panelOpen.setVisible(false);
						changeWordPanel.setVisible(true);
					}
				});
				btnTmpChange.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent arg0) {
						btnTmpChange.setBorder(new LineBorder(Color.orange));
					}
					public void mouseExited(MouseEvent e) {
						btnTmpChange.setBorder(new LineBorder(Color.white));
					} 
				});
				final JButton btnTmpDelete = new JButton();
				btnTmpDelete.setBorder(BorderFactory.createLineBorder(Color.white));
				btnTmpDelete.setSize(55,55);
				btnTmpDelete.setLocation(345, 55*i+55);
				btnTmpDelete.setIcon(new ImageIcon("image\\bin.png"));
				btnTmpDelete.setContentAreaFilled(false);
				btnTmpDelete.addActionListener(new DeleteAction(lblTmpEn.getText().toLowerCase()));
				btnTmpDelete.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent arg0) {
						btnTmpDelete.setBorder(new LineBorder(Color.orange));
					}
					public void mouseExited(MouseEvent e) {
						btnTmpDelete.setBorder(new LineBorder(Color.white));
					}
				});
				panelListWord.add(lblTmpEn);
				panelListWord.add(lblTmpVn);
				panelListWord.add(btnTmpChange);
				panelListWord.add(btnTmpDelete);
			}
		}
	}
	
	private class DeleteAction extends SearchAction {
		private String delete;
		public DeleteAction(String del) {
			this.delete = del;
		}
		public void actionPerformed(ActionEvent e) {
			String[] options = {"Có", "Không"};
			int choice  = JOptionPane.showOptionDialog(frame, "Bạn có muốn xóa từ " + this.delete + "\nkhỏi cơ sở dữ liệu không?", "Xóa", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (choice == JOptionPane.YES_OPTION) {
				try {
					Word.delete(this.delete);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				super.actionPerformed(e);
			}
		}
	}
}	

