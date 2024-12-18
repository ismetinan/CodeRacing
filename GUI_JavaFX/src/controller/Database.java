package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:coderacers.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " username TEXT NOT NULL "
                + ");";
        String sql2 = "CREATE TABLE IF NOT EXISTS leaderboard (" + 
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT," + 
                        "    username TEXT NOT NULL," + 
                        "    score INTEGER NOT NULL" + 
                        ");";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            
            System.out.println("Users table has been initialized.");

            stmt.execute(sql2);
            System.out.println("LeaderBoard init");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addUser(String username) {
        String sql = "INSERT INTO users(username) VALUES(?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            System.out.println("Username added: " + username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addScore(String username, int score) {
        String sql = "INSERT INTO leaderboard(username, score) VALUES(?, ?)";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            System.out.println("Score added for username: " + username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public static String getLatestUsername() {
        String sql = "SELECT username FROM users ORDER BY id DESC LIMIT 1";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
        public static List<String> getLeaderboard() {
        String sql = "SELECT username, score FROM leaderboard ORDER BY score DESC LIMIT 10";
        List<String> leaderboard = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nickname = rs.getString("username");
                int score = rs.getInt("score");
                leaderboard.add(nickname + " - " + score);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving leaderboard: " + e.getMessage());
        }
        return leaderboard;
    }

}
