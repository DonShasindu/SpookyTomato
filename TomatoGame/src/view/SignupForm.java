package view;
import model.User;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Signup;
import database.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SignupForm extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField age;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupForm frame = new SignupForm();
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
	public SignupForm() {
		setTitle("Sign up form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 986, 473);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(840, 435, 85, 21);
		panel.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnLogin.setBackground(new Color(192, 192, 192));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginForm theGame = new LoginForm(); 
				theGame.setVisible(true); 
				dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Already have an account?");
		lblNewLabel_1.setBounds(706, 439, 129, 13);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		username = new JTextField();
		username.setBounds(717, 182, 216, 30);
		panel.add(username);
		username.setBackground(new Color(255, 255, 255));
		username.setColumns(10);
		
		JLabel txtUsername = new JLabel("User name:");
		txtUsername.setBounds(717, 159, 95, 13);
		panel.add(txtUsername);
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel txtAge = new JLabel("Age:");
		txtAge.setBounds(717, 222, 95, 21);
		panel.add(txtAge);
		txtAge.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		age = new JTextField();
		age.setBounds(717, 253, 216, 30);
		panel.add(age);
		age.setBackground(new Color(255, 255, 255));
		age.setColumns(10);
		
		JLabel txtPassword = new JLabel("Password:");
		txtPassword.setBounds(717, 299, 95, 13);
		panel.add(txtPassword);
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		password = new JPasswordField();
		password.setBounds(717, 322, 216, 30);
		panel.add(password);
		password.setBackground(new Color(255, 255, 255));
		
		JButton signup = new JButton("Sign Up");
		signup.setBounds(756, 376, 154, 39);
		panel.add(signup);
		signup.setForeground(new Color(255, 255, 255));
		signup.setBackground(new Color(128, 128, 128));
		signup.setFont(new Font("Tahoma", Font.BOLD, 20));
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					User signIn = new User(username.getText(),age.getText(),password.getText());
				    Signup save = new Signup(signIn);
				    save.saveSignupToDatabese();
				    JOptionPane.showMessageDialog(null, "Sign in Successfull..! Go back to Login..!");
				    username.setText("");
					age.setText("");
					password.setText("");
				    
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 986, 473);
		lblNewLabel.setIcon(new ImageIcon(SignupForm.class.getResource("/res/imgSignupBg.png")));
		panel.add(lblNewLabel);
	}
}
