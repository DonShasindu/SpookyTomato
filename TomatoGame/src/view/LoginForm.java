package view;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseConnection;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.*;

public class LoginForm extends JFrame {
	private static final long serialVersionUID = -6921462126880570161L;

	private JPanel contentPane;
	private JTextField txuser;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txuser = new JTextField();
		txuser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txuser.setForeground(new Color(255, 255, 255));
		txuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupForm theGame = new SignupForm(); 
				theGame.setVisible(true); 
				dispose();
			}
		});
		btnNewButton.setBounds(856, 410, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblNewLabel_3.setBounds(703, 235, 90, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Don't have an account?");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(729, 414, 117, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("User Name:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblNewLabel_2.setBounds(703, 144, 85, 13);
		contentPane.add(lblNewLabel_2);
		txuser.setBackground(new Color(0, 0, 0));
		txuser.setBounds(703, 168, 250, 38);
		contentPane.add(txuser);
		txuser.setColumns(10);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pass.setForeground(new Color(255, 255, 255));
		pass.setBackground(new Color(0, 0, 0));
		pass.setBounds(703, 258, 250, 38);
		contentPane.add(pass);
		
		JButton blogin = new JButton("Log in");
		blogin.setForeground(new Color(0, 0, 0));
		blogin.setBackground(new Color(255, 255, 255));
		blogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		blogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
			         
			         Connection con= DatabaseConnection.createConnection();
			         Statement stm = con.createStatement();
			         String sql="select * from registration where username='"+txuser.getText()+"'and password='"+pass.getText()+"';";
			         
			         ResultSet rs=stm.executeQuery(sql);
			         
			         if (rs.next()) {
			          dispose();

			          String username = rs.getString("username");
			          String level = String.valueOf(rs.getInt("level"));
			          Home home = new Home();
			          home.setVisible(true);
			          home.lbl_passUser.setText(username);
			          home.lbl_passLevel.setText(level);
			          
			         }
			         else {
			          JOptionPane.showMessageDialog(LoginForm.this,"Username or Password Error..!");
			          //txt_password.setText("");
			         }
			                
			        }catch (Exception e1) {
			         System.out.println(e1.getMessage());
			        }
			   }
		
						
			
		});
		blogin.setBounds(752, 333, 164, 38);
		contentPane.add(blogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginForm.class.getResource("/res/imgLoginBg.png")));
		lblNewLabel.setBounds(0, 0, 986, 463);
		contentPane.add(lblNewLabel);
	}
}
