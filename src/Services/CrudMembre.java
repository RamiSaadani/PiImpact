/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Membre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.MessagingException;

/**
 *
 * @author Rami
 */

public class CrudMembre {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
    public void BanMembre(Membre m) throws SQLException{
        String requete = "Update utilisateur set status=4 where ID_UTILISATEUR="+m.getId() ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
    public List<Membre>displayAll() throws SQLException{
        String requete="SELECT * FROM utilisateur where type='membre'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Membre> list = new ArrayList<>() ; 
        while(rs.next()){
        Membre m = new Membre(rs.getInt("1"),rs.getString("2"),rs.getString("3"),rs.getDate("4"),rs.getString("5"),rs.getInt("6"),rs.getInt("7"),rs.getFloat("8"),rs.getFloat("9"),rs.getString("10"),rs.getString("11"),"membre");
        list.add(m) ;
        }
        return list ;
    }
    public void UpdateMembre(Membre m) throws SQLException{
        String requete="UPDATE utilisateur SET NOM=?, PRENOM=? ,DATENAISSANCE=? ,EMAIL=? ,STATUS=?, NUMTEL=?,TAILLE=?,POIDS=?,AVATAR=?,MDP=? WHERE id=?" ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, m.getNom());
        pst.setString(2, m.getPrenom());
        pst.setDate(3, m.getDate_naissance());
        pst.setString(4, m.getEmail());
        pst.setInt(5, m.getSTATUS());
        pst.setInt(6, m.getNum_tel());
        pst.setFloat(7, m.getTaille());
        pst.setFloat(8, m.getPoids());
        pst.setString(9, m.getAvatar());
        pst.setString(10, m.getMot_passe());
        pst.setInt(11, m.getId());
        pst.executeUpdate() ; 
       
    }
    public Membre FindMembreById(int id) throws SQLException{
        String requete="SELECT * FROM utilisateur where ID_UTILISATEUR="+id ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Membre m = new Membre(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"coach");
        
        return m ;
        }
        return null ;
    }
        public List<Membre> FindMembreByNameOrLastname(String name) throws SQLException{
        String requete="SELECT * FROM utilisateur where (NOM='"+name+"' OR PRENOM='"+name+"') AND Type='membre'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Membre> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Membre m  = new Membre(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"membre");
        
        list.add(m) ;
        }
        return list ;
    }
     public List<Membre> filtre(List<Membre> list  , int i){
        return list.stream().filter(e->e.getSTATUS()==i).collect(Collectors.toList()) ;
    }
        public Membre AdminAuthentification(String email , String passwd) throws SQLException{
        String requete="SELECT * FROM utilisateur where EMAIL='"+email+"' AND MDP='"+passwd+"' AND STATUS=1 AND TYPE='membre'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Membre m  = new Membre(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"membre");
        return m ;
        }
        return null ;
    }
        public void InsertMembre(Membre m) throws SQLException{
        String requete="Insert into utilisateur (NOM , PRENOM , DATENAISSANCE , EMAIL , STATUS , NUMTEL , TAILLE , POIDS , AVATAR , MDP,TYPE) values (?, ? ,? ,? ,?, ?,?,?,?,?,'membre') " ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, m.getNom());
        pst.setString(2, m.getPrenom());
        pst.setDate(3, m.getDate_naissance());
        pst.setString(4, m.getEmail());
        pst.setInt(5, m.getSTATUS());
        pst.setInt(6, m.getNum_tel());
        pst.setFloat(7, m.getTaille());
        pst.setFloat(8, m.getPoids());
        pst.setString(9, m.getAvatar());
        pst.setString(10, m.getMot_passe());
        pst.executeUpdate() ; 
        MailService ms = new MailService() ; 
    try {
        ms.SendEmailModOrMembre(m);
    } catch (MessagingException ex) {
        ex.printStackTrace();
    }
       
    }
        public static void main(String[] args) {
        Membre m = new Membre(1, "rami","saadani", null, "rami.saadani.1@esprit.tn", 2, 25475, 2.5f  , 6.7f, null, null, "membre") ;
        CrudMembre c = new CrudMembre() ; 
    try {
        c.InsertMembre(m);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        }
}
