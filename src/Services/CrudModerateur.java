/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Moderateur;
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

public class CrudModerateur {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
    public void BanModerateur(Moderateur m) throws SQLException{
        String requete = "Update utilisateur set status=4 where id="+m.getId() ; ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
    public List<Moderateur>displayAll() throws SQLException{
        String requete="SELECT * FROM utilisateur where type='moderateur'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Moderateur> list = new ArrayList<>() ; 
        while(rs.next()){
        Moderateur m = new Moderateur(rs.getInt("1"),rs.getString("2"),rs.getString("3"),rs.getDate("4"),rs.getString("5"),rs.getInt("6"),rs.getInt("7"),rs.getFloat("8"),rs.getFloat("9"),rs.getString("10"),rs.getString("11"),"moderateur");
        list.add(m) ;
        }
        return list ;
    }
    public void UpdateModerateur(Moderateur m) throws SQLException{
        String requete="UPDATE utilisateur SET NOM=?, PRENOM=? ,DATENAISSANCE=? ,EMAIL=? ,STATUS=?, NUMTEL=?,TAILLE=?,POIDS=?,AVATAR=?,MDP=? WHERE id=?" ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, m.getNom());
        pst.setString(2, m.getPrenom());
        pst.setDate(3, m.getDate_naissance());
        pst.setString(4, m.getEmail());
        pst.setInt(4, m.getSTATUS());
        pst.setInt(5, m.getNum_tel());
        pst.setFloat(6, m.getTaille());
        pst.setFloat(7, m.getPoids());
        pst.setString(8, m.getAvatar());
        pst.setString(9, m.getMot_passe());
        pst.setInt(10, m.getId());
        pst.executeUpdate() ; 
       
    }
    
}
