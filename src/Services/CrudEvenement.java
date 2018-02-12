/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author koussai
 */
public class CrudEvenement {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
ResultSet rs ; 
    

 public void InsertEvenement(Evenement E) throws SQLException
    {    
        String req="INSERT INTO evenement(ID_EVENEMENT, ID_UTILISATEUR, TITRE_E, DESCRIPTION_E, AFFICHE_E, DATEDEBUT_E, DATEFIN_E, LIEU_E, DUREE_E, FRAIS_E, ORGANISATEUR_E, CONTACT_E,TYPE_E) "
                + "VALUES ('"+E.getID_EVENEMENT()+"','"+E.getID_UTILISATEUR()+"','"+E.getTITRE_E()+"','"+E.getDESCRIPTION_E()+"','"+E.getAFFICHE_E()+"','"
                +E.getDATEDEBUT_E()+"','"+E.getDATEFIN_E()+"','"+E.getLIEU_E()+"','"+E.getDUREE_E()+"','"+E.getFRAIS_E()+"','"+E.getORGANISATEUR_E()+"','"+E.getCONTACT_E()+"','"+E.getTYPE_E()+"')";
        ste=cnx.createStatement();  
        ste.executeUpdate(req); 
    }

 public void UpdateEvenement(Evenement E,int id) throws SQLException{
        String requete="UPDATE evenement SET ID_UTILISATEUR='"+E.getID_UTILISATEUR()+"',TITRE_E='"+E.getTITRE_E()+"',DESCRIPTION_E='"+E.getDESCRIPTION_E()+"',AFFICHE_E='"+E.getAFFICHE_E()+"',DATEDEBUT_E='"+E.getDATEDEBUT_E()+"',DATEFIN_E='"+E.getDATEFIN_E()+"',LIEU_E='"+E.getLIEU_E()+"',DUREE_E='"+E.getDUREE_E()+"',FRAIS_E='"+E.getFRAIS_E()+"',ORGANISATEUR_E='"+E.getORGANISATEUR_E()+"',CONTACT_E='"+E.getCONTACT_E()+"',TYPE_E='"+E.getTYPE_E()+"' where ID_EVENEMENT="+id  ;
        ste=cnx.createStatement() ; 
        ste.executeUpdate(requete) ; 
       
    }
 
 public void DeleteEvenement(int id) throws SQLException
    {
        String req="Delete from evenement where ID_EVENEMENT="+id; 
        ste=cnx.createStatement();  
        ste.executeUpdate(req);    
    }
    
    public List<Evenement> displayAllEvenement() throws SQLException{
        String requete="SELECT * FROM evenement" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Evenement> list = new ArrayList<>() ; 
        while(rs.next()){                   
        Evenement E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getFloat(10),rs.getString(11),rs.getString(12),rs.getString(13));
        list.add(E) ;
        }
        return list ;
    }


    }
    


