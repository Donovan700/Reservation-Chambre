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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Date;



public class ReservationDAO {
    private ConnectionDB db;
    private PreparedStatement stmt;
    
    private int idReserve;
    private String numChambr;
    private int nbrJour;
    private Date dateReserve;
    private Date dateEntree;
    private String nomClient;
    private String mail;
    private String telephone;
    
    private ResultSet resultSet = null;
    
    public void setAttribut(String nc, int nbJ,Date dateRev,Date dateEn, String nomC, String ml, String tel){
        numChambr = nc;
        nbrJour = nbJ;
        dateReserve = dateRev;
        dateEntree = dateEn;
        nomClient = nomC;
        mail = ml;
        telephone = tel;
    }
    
    
    
    public void setidReserve(int iR){
        idReserve = iR;
    }
    
    public void create(){
        db = new ConnectionDB();
        String sql = "INSERT INTO reserver (numChambr,dateReserve,dateEntree,nbrJour,nomClient,mail,telephone) values (?,?,?,?,?,?,?);";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setString(1, numChambr);
            stmt.setDate(2, dateReserve);
            stmt.setDate(3, dateEntree);
            stmt.setInt(4, nbrJour);
            stmt.setString(5, nomClient);
            stmt.setString(6, mail);
            stmt.setString(7, telephone);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Element add succesfully");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
    }
    
    public ResultSet getAll(){
        db = new ConnectionDB();
        String sql = "SELECT * FROM reserver;";
        stmt = db.getPreparedStmt(sql);
        try{
            resultSet = stmt.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
        return resultSet;
    }
    
    public ResultSet getElement(int iR){
        db = new ConnectionDB();
        String sql = "select * from reserver where idReserve = ?;";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setInt(1, iR);
            resultSet = stmt.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
        return resultSet;
    }
    
    public void update(int iR){
        db = new ConnectionDB();
        String sql = "UPDATE reserver SET numChambr = ?, dateReserve = ?,dateEntree = ?,nbrJour = ?,nomClient = ?,mail = ?,telephone = ? WHERE idReserve = ?;";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setString(1, numChambr);
            stmt.setDate(2, dateReserve);
            stmt.setDate(3, dateEntree);
            stmt.setInt(4, nbrJour);
            stmt.setString(5, nomClient);
            stmt.setString(6, mail);
            stmt.setString(7, telephone);
            stmt.setInt(8, iR);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
    }
    
    public void delete(int iR){
        db = new ConnectionDB();
        String sql = "DELETE FROM reserver WHERE idReserve = ?;";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setInt(1, iR);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Element delete succesfully");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
    }
}
