/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Coach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Rami
 */

public class CrudCoach {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
    public void BanCoach(Coach c) throws SQLException{
        String requete = "Update utilisateur set status=4 where ID_UTILISATEUR="+c.getId() ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
    public List<Coach>displayAll() throws SQLException{
        String requete="SELECT * FROM utilisateur where type='coach'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Coach> list = new ArrayList<>() ; 
        while(rs.next()){
        Coach c = new Coach(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"coach",rs.getString("NIVEAU_COACH"),rs.getString("CERTIF_COACH"),rs.getInt("NOTE_COACH"));
        list.add(c) ;
        }
        return list ;
    }
    
          public Coach FindById(int id) throws SQLException{
        String requete="SELECT * FROM utilisateur where ID_UTILISATEUR="+id ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Coach c = new Coach(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"coach",rs.getString("NIVEAU_COACH"),rs.getString("CERTIF_COACH"),rs.getInt("NOTE_COACH"));
        
        return c ;
        }
        return null ;
    }
        public List<Coach> FindByNameOrlastname(String name) throws SQLException{
        String requete="SELECT * FROM utilisateur where (NOM='"+name+"' OR PRENOM='"+name+"') AND Type='coach'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Coach> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Coach c = new Coach(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"coach",rs.getString("NIVEAU_COACH"),rs.getString("CERTIF_COACH"),rs.getInt("NOTE_COACH"));
        
        list.add(c) ;
        }
        return list ;
    }
    
    public void UpdateCoach(Coach c) throws SQLException{
        String requete="UPDATE utilisateur SET NOM=?, PRENOM=? ,DATENAISSANCE=? ,EMAIL=? ,STATUS=?, NUMTEL=?,TAILLE=?,POIDS=?,AVATAR=?,MDP=? , NIVEAU_COACH=? , CERTIF_COACH=?, NOTE_COACH=? WHERE ID_UTILISATEUR=?" ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, c.getNom());
        pst.setString(2, c.getPrenom());
        pst.setDate(3, c.getDate_naissance());
        pst.setString(4, c.getEmail());
        pst.setInt(5, c.getSTATUS());
        pst.setInt(6, c.getNum_tel());
        pst.setFloat(7, c.getTaille());
        pst.setFloat(8, c.getPoids());
        pst.setString(9, c.getAvatar());
        pst.setString(10, c.getMot_passe());
        pst.setString(11, c.getNIVEAU_COACH());
        pst.setString(12, c.getCERTIF_COACH());
        pst.setInt(13, c.getNOTE_COACH());
        pst.setInt(14, c.getId());
        pst.executeUpdate() ; 
       
    }
    //filtrer selon le status du compte
    public List<Coach> filtre(List<Coach> list  , int i){
        return list.stream().filter(e->e.getSTATUS()==i).collect(Collectors.toList()) ;
    }
   
}
