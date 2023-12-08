package controller;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import database.DatabaseConnection;
import view.Home;
import view.LoginForm;

/**
 * Main class where the games are coming from.
 *
 */
public class GameEngine {
	
	String thePlayer = null;

    int level;  // Add a level variable
    int count = 0;

	/**
	 * Each player has their own game engine.
	 * 
	 * @param player
	 */
	public GameEngine(String player) {
		thePlayer = player;
		level = 1;
	}
	

	int counter = 0;
	int score;

	//public JLabel lblpassUser;
	GameServer theGames = new GameServer();
	Game current = null;


	/**
	 * Retrieves a game. This basic version only has two games that alternate.
	 */
	public BufferedImage nextGame() {
		current = theGames.getRandomGame();
		return current.getImage();

	}

	/**
	 * Checks if the parameter i is a solution to the game URL. If so, score is
	 * increased by one.
	 * 
	 * @param game
	 * @param i
	 * @return
	 */
	public boolean checkSolution( int i) {
		if (i == current.getSolution()) {
			score++;
			count++;
			System.out.println("level: " + level);
			System.out.println("count: " + count);
			if (count == 4) {
                level = 2;
//                updateLevelInDatabase();
//                score = 0;  // Reset the score after reaching 5 correct answers
            } else if (count == 10) {
            	level = 3;
//                updateLevelInDatabase();
//                score = 0;  
            } else if (count == 20) {
            	level = 4;
            }
			return true;
		} else {
			return false;
		}
	}
	
	public int getCurrentLevel() {
		return this.level;
	}

	private void updateLevelInDatabase() {
		// TODO Auto-generated method stub
		System.out.println("current level: " + level);
		System.out.println("current player: " + thePlayer);
		try {
            Connection con = DatabaseConnection.createConnection();
            String query = "update registration set level=? where username=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, level);
            ps.setString(2, thePlayer);

            int i = ps.executeUpdate();
            
            if (i > 0) {
            	System.out.println("Updated level");
            } else {
            	System.out.println("Error while updating level");
            }

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}


	/**
	 * Retrieves the score.
	 * 
	 * @param player
	 * @return
	 */
	public int getScore(String username) {
		try {
	         
	         Connection con= DatabaseConnection.createConnection();
	         Statement stm = con.createStatement();
	         String sql="select score from score where username='"+username+"';";
	         
	         ResultSet rs=stm.executeQuery(sql);
	         
	          score = rs.getInt(1);

	               
	        }catch (Exception e1) {
	         System.out.println(e1.getMessage());
	        }
		return score;
	}

}
