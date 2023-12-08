package controller;
import java.sql.*;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import database.DatabaseConnection;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Signup {
	User signup;
	public Signup(User signUp) {
		this.signup=signUp;
	}
	public void saveSignupToDatabese() {
		try {
			Connection con=DatabaseConnection.createConnection();
			String query="insert into registration(username,age,password)"+"Values('"+signup.username+"','"+signup.age+"','"+signup.password+"')"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println("Error"+e.getLocalizedMessage());
		}
	}
	

}
