package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.*;

import controller.GameEngine;
import database.DatabaseConnection;

/**
 * A Simple Graphical User Interface for the Six Equation Game.
 * 

 *
 */
public class GameGui extends JFrame implements ActionListener {
	public JLabel lblpassUser;
	
	private JLabel countdownLabel; 
	private Timer timer;
	private static final long serialVersionUID = -107785653906635L;
	

	/**
	 * Method that is called when a button has been pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int solution = Integer.parseInt(e.getActionCommand());
		boolean correct = myGame.checkSolution(solution);
		int level = myGame.getCurrentLevel();
		int score = myGame.getScore(lblpassUser.getText());

		if (correct) {
			System.out.println("Correct solution entered!");
			currentGame = myGame.nextGame();
			
			countdownLabel.setText("Timer: 00:20");
			
			// Stop the previous timer (if any)
	        if (timer != null && timer.isRunning()) {
	            timer.stop();
	        }
	        
			timer = startTimer(20);
			timer.start();
			
			ImageIcon ii = new ImageIcon(currentGame);
			questArea.setIcon(ii);
			//level++;
			infoArea.setText("Good!  Score: "+score+"    Level: "+level);
			
		} else {
			
			GameOver theGame = new GameOver();
			
			theGame.lbl_passUser.setText(lblpassUser.getText());
			theGame.setVisible(true); 
			dispose();
			timer.stop();
			
			try {
				Connection con= DatabaseConnection.createConnection();
		        Statement stm = con.createStatement();
				
		        String query="insert into score Values(?,?)";
				PreparedStatement ps=con.prepareStatement(query);
				String username = lblpassUser.getText();
				ps.setString(1, username);
				ps.setLong(2, score);
			
				if(ps.executeUpdate() > 0) {
					String query2 = "update registration set level=? where username=?";
		            PreparedStatement ps2 = con.prepareStatement(query2);
		            System.out.println("current level: " + level);
		    		System.out.println("current player: " + username);
		            ps2.setInt(1, level);
		            ps2.setString(2, username);
		            
		            if ( ps2.executeUpdate() > 0) {
		            	System.out.println("Updated level");
		            } else {
		            	System.out.println("Error while updating level");
		            }
				}
					
				ps.close();
				con.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	}

	JLabel questArea = null;
	GameEngine myGame = null;
	BufferedImage currentGame = null;
	JTextArea infoArea = null;
/**
 * Initializes the game. 
 * @param player
 */
	private void initGame(String player) {
		setSize(690, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("What is the missing value?");
		JPanel panel = new JPanel();
		

		myGame = new GameEngine(player);
		currentGame = myGame.nextGame();
		
		countdownLabel = new JLabel("Timer: 00:20");
		panel.add(countdownLabel);

		timer = startTimer(20);
		timer.start();

		infoArea = new JTextArea(1, 40);
		
		infoArea.setEditable(false);
		infoArea.setText("What is the value of the tomato? Level: 1 Score: 0");
		
		JScrollPane infoPane = new JScrollPane(infoArea);
		panel.add(infoPane);

		ImageIcon ii = new ImageIcon(currentGame);
		questArea = new JLabel(ii);
	    questArea.setSize(330, 600);
	    
		JScrollPane questPane = new JScrollPane(questArea);
		panel.add(questPane);

		for (int i = 0; i < 10; i++) {
			JButton btn = new JButton(String.valueOf(i));
			panel.add(btn);
			btn.addActionListener(this);
		}
		JButton exsit = new JButton("Exit");
		exsit.setBackground(new Color(128, 255, 255));
		exsit.setFont(new Font("Tahoma", Font.BOLD, 10));
		exsit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home theGame = new Home();
				theGame.lbl_passUser.setText(lblpassUser.getText());
				theGame.setVisible(true);
				timer.stop();
				dispose();
				
			}
		});
		exsit.setBounds(836, 23, 79, 21);
		panel.add(exsit);

		getContentPane().add(panel);
		
		lblpassUser = new JLabel("");
		lblpassUser.setForeground(new Color(255, 255, 255));
		panel.add(lblpassUser);
		panel.repaint();
		

	}
	
	
	public Timer startTimer(int seconds) {
		Timer timer = new Timer(1000, new ActionListener() {
            int totalSeconds = seconds;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the label with the current timer value
            	int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                countdownLabel.setText(String.format("Timer: %02d:%02d", minutes, seconds));

                // Increment the timer value
                totalSeconds--;

                if (totalSeconds < 0) {
                    ((Timer) e.getSource()).stop(); // Stop the timer when countdown reaches 0
                   
                    
                    GameOver theGame = new GameOver();
        			theGame.lbl_passUser.setText(lblpassUser.getText());
        			theGame.setVisible(true); 
        			dispose();
        			
                }
            }
        });
		return timer;
	}
	
/**
 * Default player is null. 
 */
	public GameGui() {
		super();
		initGame(null);
	}

	/**
	 * Use this to start GUI, e.g., after login.
	 * 
	 * @param player
	 */
	public GameGui(String player) {
		super();
		initGame(player);
	}

	/**
	 * Main entry point into the equation game. Can be used without login for testing. 
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args) {
		GameGui myGUI = new GameGui();
		myGUI.setVisible(true);

	}
}