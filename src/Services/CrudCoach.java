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

/**
 *
 * @author Rami
 */

public class CrudCoach {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
    public void BanMembre(Coach c) throws SQLException{
        String requete = "Update utilisateur set status=4 where id="+c.getId() ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
    public List<Coach>displayAll() throws SQLException{
        String requete="SELECT * FROM utilisateur where type='coach'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Coach> list = new ArrayList<>() ; 
        while(rs.next()){
        Coach c = new Coach(rs.getInt("1"),rs.getString("2"),rs.getString("3"),rs.getDate("4"),rs.getString("5"),rs.getInt("6"),rs.getInt("7"),rs.getFloat("8"),rs.getFloat("9"),rs.getString("10"),rs.getString("11"),"coach",rs.getString("13"),rs.getString("14"),rs.getInt("15"));
        list.add(c) ;
        }
        return list ;
    }
    public void UpdatePersonne(Coach c) throws SQLException{
        String requete="UPDATE utilisateur SET NOM=?, PRENOM=? ,DATENAISSANCE=? ,EMAIL=? ,STATUS=?, NUMTEL=?,TAILLE=?,POIDS=?,AVATAR=?,MDP=? , NIVEAU_COACH=? , CERTIF_COACH=?, NOTE_COACH=? WHERE id=?" ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, c.getNom());
        pst.setString(2, c.getPrenom());
        pst.setDate(3, c.getDate_naissance());
        pst.setString(4, c.getEmail());
        pst.setInt(4, c.getSTATUS());
        pst.setInt(5, c.getNum_tel());
        pst.setFloat(6, c.getTaille());
        pst.setFloat(7, c.getPoids());
        pst.setString(8, c.getAvatar());
        pst.setString(9, c.getMot_passe());
        pst.setString(10, c.getNIVEAU_COACH());
        pst.setString(11, c.getCERTIF_COACH());
        pst.setInt(12, c.getNOTE_COACH());
        pst.executeUpdate() ; 
       
    }
}
