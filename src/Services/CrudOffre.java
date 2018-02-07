/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import entities.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nidhal Bougatf
 */
public class CrudOffre {
    Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
    
    public List<Offre>displayAll() throws SQLException{
        String requete="SELECT * FROM Offre" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt("1"),rs.getInt("2"),rs.getString("3"),rs.getString("4"),rs.getFloat("5"),rs.getFloat("6"),rs.getDate("7"),rs.getDate("8"),rs.getString("9"));
        list.add(o) ;
        }
        return list ;
    }
    
    public void InsertOffre(Offre o) throws SQLException
    {
        
        String req="INSERT INTO  offre (ID_OFFRE, ID_ESPACE, DESCRIPTION_O, TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O) "
                + "VALUES ('"+o.getID_OFFRE()+"','"+o.getID_ESPACE()+"','"+o.getDESCRIPTION_O()+"','"+o.getTITRE_O()+"','"+o.getANCIEN_PRIX()+"','"+o.getNOUVEAU_PRIX()+"','"+o.getDATEDEBUT_O()+"','"+o.getDATEFIN_O()+"','"+o.getAFFICHE_O()+"')";
        ste=cnx.createStatement();  
        ste.executeUpdate(req); 
    }
    
    
    
    public void UpdateOffre(Offre o) throws SQLException
    {
        String req="UPDATE offre  SET ID_OFFRE='"+o.getID_OFFRE()+"',ID_ESPACE='"+o.getID_ESPACE()+"',DESCRIPTION_O='"+o.getDESCRIPTION_O()+"',TITRE_O='"+o.getTITRE_O()+"',ANCIEN_PRIX='"+o.getANCIEN_PRIX()+"',NOUVEAU_PRIX='"+o.getNOUVEAU_PRIX()+"',DATEDEBUT_O='"+o.getDATEDEBUT_O()+"',DATEFIN_O='"+o.getDATEFIN_O()+"',AFFICHE_O='"+o.getAFFICHE_O()+"' WHERE ID_OFFRE='"+o.getID_OFFRE()+"' ";
        ste=cnx.createStatement();  
        ste.executeUpdate(req);
        
    }
    
    public void DeleteOffre(int id) throws SQLException
    {
        String req="Delete from Offre where ID_OFFRE="+id; 
        ste=cnx.createStatement();  
        ste.executeUpdate(req);    
    }
    
    public List<Offre>searchOffreByNameEspace(String nom) throws SQLException{
        String requete="SELECT * FROM Offre where ID_OFFRE= (Select ID_ESPACE from espace where nom_es='"+nom+"')" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt("1"),rs.getInt("2"),rs.getString("3"),rs.getString("4"),rs.getFloat("5"),rs.getFloat("6"),rs.getDate("7"),rs.getDate("8"),rs.getString("9"));
        list.add(o) ;
        }
        return list ;
    }
    
    
    
    
    
    
    
    
}
