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

public class ChambreDAO {
    private ConnectionDB db;
    private PreparedStatement stmt;
    private String numCh;
    private String designCh;
    private String typeCh;
    private int prixCh;
    private ResultSet resultSet = null;
    
    public void setAttribut(String nc, String dc, String tc, int pc){
        numCh = nc;
        designCh = dc;
        typeCh = tc;
        prixCh = pc;
    }
    
    public void setnumCh(String nc){
        numCh = nc;
    }
    
    public void chambreCreate(){
        db = new ConnectionDB();
        String sql = "INSERT INTO chambre (numChambr,design,chambreType,prixNuit) VALUES(?,?,?,?);";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setString(1, numCh);
            stmt.setString(2, designCh);
            stmt.setString(3, typeCh);
            stmt.setInt(4, prixCh);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Element add succesfully");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
    }
    
    public ResultSet getAll(){
        db = new ConnectionDB();
        String sql = "SELECT * FROM chambre;";
        stmt = db.getPreparedStmt(sql);
        try{
            resultSet = stmt.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
        return resultSet;
    }
    
    public ResultSet getElement(String nc){
        db = new ConnectionDB();
        String sql = "SELECT * FROM chambre WHERE numChambr = ?;";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setString(1, nc);
            resultSet = stmt.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
        return resultSet;
    }
    
    public void update(String nc){
        db = new ConnectionDB();
        String sql = "UPDATE chambre SET design = ?,chambreType = ?, prixNuit = ? WHERE numChambr = ?;";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setString(1, designCh);
            stmt.setString(2, typeCh);
            stmt.setInt(3, prixCh);
            stmt.setString(4, nc);
            stmt.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
    }
    
    public void delete(String nc){
        db = new ConnectionDB();
        String sql = "DELETE FROM chambre WHERE numChambr = ?;";
        stmt = db.getPreparedStmt(sql);
        try{
            stmt.setString(1, nc);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Element delete succesfully");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        db.closeConnection();
    }
}
