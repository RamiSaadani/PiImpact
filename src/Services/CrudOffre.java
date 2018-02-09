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
    
    
    public List<Offre> displayAllOffre() throws SQLException{
        String requete="SELECT * FROM Offre" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    
    public void InsertOffre(Offre o) throws SQLException
    {
        
        String req="INSERT INTO  offre (ID_OFFRE, ID_ESPACE, DESCRIPTION_O, TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O,TYPE_O) "
                + "VALUES ('"+o.getID_OFFRE()+"','"+o.getID_ESPACE()+"','"+o.getDESCRIPTION_O()+"','"+o.getTITRE_O()+"','"+o.getANCIEN_PRIX()+"','"+o.getNOUVEAU_PRIX()+"','"+o.getDATEDEBUT_O()+"','"+o.getDATEFIN_O()+"','"+o.getAFFICHE_O()+"','"+o.getTYPE_O()+"')";
        ste=cnx.createStatement();  
        ste.executeUpdate(req); 
    }
    
    
    
    public void UpdateOffre(Offre o) throws SQLException
    {
        String req="UPDATE offre  SET ID_OFFRE='"+o.getID_OFFRE()+"',ID_ESPACE='"+o.getID_ESPACE()+"',DESCRIPTION_O='"+o.getDESCRIPTION_O()+"',TITRE_O='"+o.getTITRE_O()+"',ANCIEN_PRIX='"+o.getANCIEN_PRIX()+"',NOUVEAU_PRIX='"+o.getNOUVEAU_PRIX()+"',DATEDEBUT_O='"+o.getDATEDEBUT_O()+"',DATEFIN_O='"+o.getDATEFIN_O()+"',AFFICHE_O='"+o.getAFFICHE_O()+"',TYPE_O='"+o.getTYPE_O()+"' WHERE ID_OFFRE='"+o.getID_OFFRE()+"' ";
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
        String requete="SELECT * FROM Offre where ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nom+"')" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    
    public List<Offre>searchOffreByIdEspace(int id) throws SQLException{
        String requete="SELECT * FROM Offre where ID_ESPACE= '"+id+"'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    public List<Offre>displayOffreByDateFin() throws SQLException{
        String requete="SELECT * FROM Offre Order By (DATEFIN_O) ASC" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    public List<Offre>displayOffreByDateDebut() throws SQLException{
        String requete="SELECT * FROM Offre Order By (DATEDEBUT_O) ASC" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    
    public List<Offre>displayOffreByType(String type) throws SQLException{
        String requete="SELECT * FROM Offre WHERE type_o='"+type+"'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    
    
    public List<Offre>displayOffreExpire() throws SQLException{
        String requete="SELECT * FROM Offre where  CURRENT_TIMESTAMP  > DATEFIN_O " ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    public List<Offre>displayOffreValable() throws SQLException{
        String requete="SELECT * FROM Offre where  CURRENT_TIMESTAMP  < DATEFIN_O " ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getString(10));
        list.add(o) ;
        }
        return list ;
    }
    
    
    
    public static void main(String[] args) {
        
        CrudOffre co = new CrudOffre();
        try
        {
            
            co.displayAllOffre().forEach(System.out::println);
            System.out.println("Nom espace = consequatur");
           co.searchOffreByNameEspace("consequatur").forEach(System.out::println);
           System.out.println("Id Espace = 5");
            co.searchOffreByIdEspace(5).forEach(System.out::println);
            System.out.println("DateFin");
            co.displayOffreByDateFin().forEach(System.out::println);
            System.out.println("DateDebut");
            co.displayOffreByDateDebut().forEach(System.out::println);
            System.out.println("Offres Expire ");
            co.displayOffreExpire().forEach(System.out::println);
            System.out.println("Offres Valable ");
            co.displayOffreValable().forEach(System.out::println);
            System.out.println("By type = sport");
            co.displayOffreByType("sport").forEach(System.out::println);
        }
        catch (SQLException ex) {
            System.out.println("erreur de connexion");
           
        }
        
        
            
    }
    
    
    
    
    
    
}
