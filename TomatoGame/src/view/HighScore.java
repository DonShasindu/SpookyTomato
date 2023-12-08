package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class HighScore extends JFrame {

	protected static final AbstractButton tblData = null;
	private JPanel contentPane;
	public JLabel lbl_passUser;
	private JTable tbl;
	private JTable HighScore;
	private JScrollPane scrollPane_1;
	private JButton btnNewButton;
	private JLabel lblTitle;
	private JLabel lblYourHighScore;

	/**
	 * Launch the application.
	 */
	public void displayLeaderboard() {
        try {
            Connection con = DatabaseConnection.createConnection();
            Statement stm = con.createStatement();
            String sql = "SELECT s.username, MAX(s.score) AS highest_score FROM score s GROUP BY s.username ORDER BY highest_score DESC limit 3;";

            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) tbl.getModel();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++)
                colName[i] = rsmd.getColumnName(i + 1);
            model.setColumnIdentifiers(colName);

            String username, score;
            while (rs.next()) {
                username = rs.getString(1);
                score = rs.getString(2);
                String[] row = { username, score };
                model.addRow(row);
            }
            stm.close();
            con.close();

        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }
	public void displayUserHighScore() {
        try {
            Connection con = DatabaseConnection.createConnection();
            Statement stm = con.createStatement();
            String sql = "SELECT MAX(score) AS highest_score FROM score where username='" + lbl_passUser.getText() + "';";

            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) HighScore.getModel();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++)
                colName[i] = rsmd.getColumnName(i + 1);
            model.setColumnIdentifiers(colName);

            String userScore;
            while (rs.next()) {
                userScore = rs.getString(1);
                String[] row = { userScore };
                model.addRow(row);
            }
            stm.close();
            con.close();

        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }
	



	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HighScore frame = new HighScore();
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
	public HighScore() {
		
		
		setTitle("High Score");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon(HighScore.class.getResource("/res/btnHome.png")));
		btnBack.setBounds(784, 399, 100, 40);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home theGame = new Home();
				theGame.lbl_passUser.setText(lbl_passUser.getText());
				theGame.setVisible(true); 
				dispose();
			}
		});
		
		lblYourHighScore = new JLabel("Your  High  score");
		lblYourHighScore.setFont(new Font("Verdana", Font.BOLD, 13));
		lblYourHighScore.setBounds(769, 291, 125, 29);
		contentPane.add(lblYourHighScore);
		
		lblTitle = new JLabel("Leaderboard");
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 13));
		lblTitle.setBounds(784, 152, 100, 19);
		contentPane.add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(710, 187, 242, 76);
		contentPane.add(scrollPane);
		
		tbl = new JTable();
		scrollPane.setViewportView(tbl);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(769, 330, 125, 48);
		contentPane.add(scrollPane_1);
		
		HighScore = new JTable();
		scrollPane_1.setViewportView(HighScore);
		
		
		JButton refresh = new JButton("");
		refresh.setFont(new Font("Tahoma", Font.PLAIN, 10));
		refresh.addActionListener(new ActionListener() {
			public void addRefreshButtonActionListener(ActionListener listener) {
		        refresh.addActionListener(listener);
		    }
//			public void actionPerformed(ActionEvent e) {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
				

		});
		refresh.setBounds(810, 207, 19, 21);
		contentPane.add(refresh);
		contentPane.add(btnBack);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void addBtnNewButtonActionListener(ActionListener listener) {
		        btnNewButton.addActionListener(listener);
		    }


			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnNewButton.setBounds(839, 207, 19, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HighScore.class.getResource("/res/imgHighScoreBg.png")));
		lblNewLabel.setBounds(0, 0, 996, 474);
		contentPane.add(lblNewLabel);
		
		lbl_passUser = new JLabel("");
		lbl_passUser.setBounds(46, 388, 45, 13);
		contentPane.add(lbl_passUser);
	}
}
