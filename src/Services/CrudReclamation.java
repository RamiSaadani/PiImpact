/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rami
 */

public class CrudReclamation {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
    public void ChangerStatusToTraite(Reclamation r) throws SQLException{
        String requete = "Update reclamation set ETAT_R='traite'" ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
   
     public List<Reclamation>displayAllNonTraiter() throws SQLException{
        String requete="SELECT * FROM reclamation where ETAT_R='nontraite'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>() ; 
        while(rs.next()){
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        list.add(r) ;
        }
        return list ;
    }
     public List<Reclamation>displayAll() throws SQLException{
        String requete="SELECT * FROM reclamation" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>() ; 
        while(rs.next()){
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        list.add(r) ;
        }
        return list ;
    }
    public List<Reclamation> findById(int id) throws SQLException{
         String requete="SELECT * FROM reclamation where ID_RECLAMATION="+id ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>() ; 
        while(rs.next()){
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        list.add(r) ;
        }
        return list ;
       
    }
    public void InsertReclamation(Reclamation r) throws SQLException{
        String requete="Insert into Reclamation ( type_rec, commentaire_rec, etat_rec, date_rec , id_utilisateur) values (? ,? ,? ,?,?) " ;
        pst=cnx.prepareStatement(requete);
                
     
        pst.setString(1, r.getTYPE_R());
        pst.setString(2, r.getCOMMENTAIRE_R());
        pst.setString(3, r.getETAT_R());
        pst.setDate(4, r.getDATE_R());
        pst.setInt(5, r.getID_UTILISATEUR());

        pst.executeUpdate() ;
   
}
}
