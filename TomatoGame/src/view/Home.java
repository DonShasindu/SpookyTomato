package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class Home extends JFrame {

	private JPanel contentPane;
	public JLabel lbl_passUser;
	public JLabel lbl_passLevel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	
	public Home() {
		setTitle("Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton game = new JButton("");
		game.setIcon(new ImageIcon(Home.class.getResource("/res/PlayBtn.png")));
		game.setBackground(new Color(128, 128, 128));
		game.setFont(new Font("Snap ITC", Font.BOLD, 45));
		game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameGui theGame = new GameGui();
				theGame.lblpassUser.setText(lbl_passUser.getText());
				theGame.setVisible(true); 
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("High Score");
		btnNewButton.setIcon(null);
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HighScore highScore = new HighScore();
                highScore.lbl_passUser.setText(lbl_passUser.getText());
                highScore.displayLeaderboard();
                highScore.displayUserHighScore();
                highScore.setVisible(true);
                dispose();
            }
        });
		
		JLabel lblLevel = new JLabel("");
		lblLevel.setBounds(670, 10, 57, 40);
		contentPane.add(lblLevel);
		
		lbl_passLevel = new JLabel("");
		System.out.println("db level: " + lbl_passLevel.getText());
		lblLevel.setText(lbl_passLevel.getText());
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Welcome,");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(438, 10, 74, 40);
		contentPane.add(lblNewLabel_1);
		
		lbl_passUser = new JLabel("");
		lbl_passUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_passUser.setBounds(507, 10, 115, 40);
		contentPane.add(lbl_passUser);
		btnNewButton.setBounds(747, 10, 100, 40);
		contentPane.add(btnNewButton);
		game.setBounds(811, 364, 151, 75);
		contentPane.add(game);
		
		JButton logout = new JButton("Log out");
		logout.setIcon(null);
		logout.setBackground(new Color(128, 255, 255));
		logout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginForm theGame = new LoginForm(); 
				theGame.setVisible(true); 
				dispose();
			}
		});
		logout.setBounds(876, 10, 100, 40);
		contentPane.add(logout);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/res/imgHomeBg.png")));
		lblNewLabel.setBounds(0, 0, 986, 474);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(657, 10, 57, 40);
		contentPane.add(lblNewLabel_2);
	}
	
}
