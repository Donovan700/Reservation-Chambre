/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author micka
 */
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    
    Connection connection;
    
    String url = "jdbc:postgresql://localhost:5432/hotel_database";
    String user = "postgres";
    String password = "#Fianarants0a07";
    
    public void dbConnection(){
        
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage() + "First catch");
        }
        
        try{
            connection = DriverManager.getConnection(url,user,password);
            //JOptionPane.showMessageDialog(null, );
            
            System.out.println("Connected");
        }catch(SQLException ex){
            
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                //JOptionPane.showMessageDialog(null, "Connection closed");
                System.out.println("Connection closed");
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println(ex.getMessage());
        }
    }
    
    public ConnectionDB(){
        dbConnection();
    }
    
    public PreparedStatement getPreparedStmt(String sql){
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(sql);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Failed to prepare a statement");
        }
        return stmt;
    }
}
