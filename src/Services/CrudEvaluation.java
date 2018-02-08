/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import entities.Evaluation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class CrudEvaluation {
  
Connection con= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ;
    
     public void insertSt(Evaluation e) throws SQLException{
         
        String requete = "INSERT INTO Evaluation (ID_EVALUATION,ID_UTILISATEUR,NOTES_EV,COMMENTAIRE_EV,OBJET_EV, TYPE_EV, ID_O) values"
                + "('"+e.getID_EVALUATION()+"','"+e.getID_UTILISATEUR()+"','"+e.getNOTES_EV()+"','"+e.getCOMMENTAIRE_EV()+"','"+e.getOBJET_EV()+"','"+ e.getTYPE_EV()+"','"+ e.getID_O()+"')" ;
                
        ste=con.createStatement() ;
        ste.executeUpdate(requete ); 
    }
    
     public List<Evaluation>display(String objett,String type,String nom) throws SQLException{
         if(objett.equals("offre"))
         {
                     String requete="SELECT AVG(NOTES_EV)FROM Evaluation WHERE id=(select  ID_OFFRE from offre where  titre_o='"+nom+"' and type_o='"+type+"') ";
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);
         }
         else if (objett.equals("espace"))
         {
        String requete="SELECT AVG(NOTES_EV)FROM Evaluation WHERE id=(select  ID_ESPACE from espace where  nom_es='"+nom+"' and type_es='"+type+"') ";
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);
         }
         
         else if(objett.equals("evenement")){
         
        String requete="SELECT AVG(NOTES_EV)FROM Evaluation WHERE id=(select  ID_EVENEMENT from evenement where  titre_e='"+nom+"'and type_e='"+type+"') ";
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);
         }
         
       
        List<Evaluation> list = new ArrayList<>() ; 
        while(rs.next()){
       Evaluation ev= new Evaluation(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
        list.add(ev) ;
        }
        return list ;
    }
      
     public void DeleteSt(Evaluation e) throws SQLException{
        String requete = "DELETE FROM Evaluation WHERE id="+e.getID_EVALUATION() ;
        ste=con.createStatement() ;
        ste.executeUpdate(requete); 
                }
      public void UpdateArticle(Evaluation e) throws SQLException{
        String requete="UPDATE Evaluation SET ID_EVALUATION=?,ID_UTILISATEUR=?,NOTES_EV=?, COMMENTAIRE_EV=?,OBJET_EV=?,TYPE_EV=?,ID_O=?" ;
       
        pst=con.prepareStatement(requete) ; 
        pst.setInt(1, e.getID_EVALUATION());
        pst.setInt(2, e.getID_UTILISATEUR());
        pst.setInt(3, e.getNOTES_EV());
        pst.setString(4, e.getCOMMENTAIRE_EV()); 
        pst.setString(5, e.getOBJET_EV());
        pst.setString(6, e.getTYPE_EV());
        pst.setInt(7, e.getID_O());
        pst.executeUpdate() ; 
       
    }
}

