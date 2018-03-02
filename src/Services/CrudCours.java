/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Cours;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import connexion.DataSource;

/**
 *
 * @author ELYES
 */
public class CrudCours {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    ResultSet rs;
    
    public CrudCours() 
    {
        try 
        {
            ste = con.createStatement();
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }

    }
       public void insertPST(Cours c) throws SQLException
    {
        String req = "insert into cours ( id_espace, titre , hr_debut  , hr_fin , date_cours  ) values (?,?,?,?,?)";
      PreparedStatement  pst = con.prepareStatement(req);//attente des parametre
        pst.setInt(1, c.getId_espace());
        pst.setString(2, c.getTitre());
        pst.setString(3, c.getHr_debut());
        pst.setString(4, c.getHr_fin());
        pst.setDate(5, Date.valueOf(c.getDate_cours()));
       
       
       // ste = connexion.createStatement();
        pst.executeUpdate();
    }
     public void updateST(Cours p) throws SQLException
    {
        String req= "update cours set id_cours = ? ,  titre =?,hr_debut =?,hr_fin =?,date_cours =? where id_cours= ?";
        PreparedStatement  pst = con.prepareStatement(req);
      //  pst = con .prepareStatement(req);
      
        pst.setInt(1, p.getId_cours());
        
         pst.setString(2, p.getTitre());
         pst.setString(3, p.getHr_debut());
         pst.setString(4, p.getHr_fin());
        
         pst.setDate(5, Date.valueOf(p.getDate_cours()));
         
         
         
         
         
        pst.setInt(6, p.getId_cours());
       // ste = connexion.createStatement();
        pst.executeUpdate();
        
    }
      public void deleteST(int id) throws SQLException
    {
        String req = "Delete from cours where id_cours = "+id;
        ste =con.createStatement();
        ste.executeUpdate(req);
    }
       public ObservableList<Cours> SearchById(int id) throws SQLException 
       {
           String requete = "select  * from cours where id_espace  = "+id;
        System.out.println(requete);

       
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);

        ObservableList<Cours> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Cours ev = new Cours(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getString(6),rs.getDate(7).toLocalDate());
            list.add(ev);
        }
        return list;
    }
     
             public ObservableList<Cours> displayAllObsCours() throws SQLException
    {
        String requete = "select *  from cours";
        ste = con.createStatement();
        
        
        rs=ste.executeQuery(requete);
        
        ObservableList<Cours> List= FXCollections.observableArrayList();;
        while(rs.next())
        {
          //  LocalDate x= Date.valueOf(rs.getDate(7));
           Cours c =new Cours(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getString(6),rs.getDate(7).toLocalDate());
            List.add(c);
         
        }
        return List;
    }

    
    
}
