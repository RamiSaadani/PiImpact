/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;



import connexion.DataSource;
import entities.Espace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author ELYES
 */
public class CrudEspace { 
    Connection cnx = DataSource.getInstance().getConnection();
    Statement ste;
    PreparedStatement pst;
    ResultSet rs;
    
   

    public void insertEspace(Espace p) throws SQLException
    {
        String req = "insert into espace ( ID_UTILISATEUR,NOM_ES ,ADRESSE_ES, EMAIL_ES , NUMTEL_ES , FACEBOOK_ES , LOGO_ES,TYPE_ES ) values ('"+p.getID_UTILISATEUR()+"',,'"+p.getNomEspace()+"','"+p.getADRESSE_ES()+"','"+p.getEMAIL_ES()+"','"+p.getNUMTEL_ES()+"','"+p.getFACEBOOK_ES()+"','"+p.getLOGO_ES()+"','"+p.getTYPE_ES()+"')";
        ste = cnx.createStatement();
        ste.executeUpdate(req);
    }
    
    
    
    public void deleteEspace(int id) throws SQLException
    {
        String req = "Delete from espace where ID_ESPACE= "+id;
        ste =cnx.createStatement();
        ste.executeUpdate(req);
    }
    
    public List<Espace> displayAllEspace() throws SQLException
    {
        String requete = "select *  from espace";
        ste = cnx.createStatement();
        rs=ste.executeQuery(requete);
        List<Espace> List= new ArrayList<>();
        while(rs.next())
        {
            Espace p = new Espace(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
            List.add(p);
        }
        return List;
    }
    
    public void updateEspace(Espace p) throws SQLException
    {
        String req= "update espace set NOM_ES= ? , ADRESSE_ES = ? ,EMAIL_ES=?,NUMTEL_ES=?,FACEBOOK_ES=?,LOGO_ES=?,TYPE_ES=?  where ID_ESPACE= ?";
        pst = cnx .prepareStatement(req);
        pst.setString(1, p.getNomEspace());
        pst.setString(2, p.getADRESSE_ES());
         pst.setString(3, p.getEMAIL_ES());
         pst.setInt(4, p.getNUMTEL_ES());
         pst.setString(5, p.getFACEBOOK_ES());
         pst.setString(6, p.getLOGO_ES());
         pst.setString(7, p.getTYPE_ES());
        pst.setInt(8, p.getID_ESPACE());
        pst.executeUpdate();
    }
    
    
    public List<Espace> searchByType(String type)throws SQLException
    {
        String requete="SELECT * FROM Espace where ID_ESPACE= '"+type+"'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Espace> list = new ArrayList<>() ; 
        while(rs.next()){
        Espace o = new Espace(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
        list.add(o) ;
        }
        return list ;
    }
    
    
     public static void main(String[] args) {
        
        CrudEspace co = new CrudEspace();
        try
        {
            
            co.displayAllEspace().forEach(System.out::println);
            System.out.println("By type = sport");
            co.searchByType("sport").forEach(System.out::println);
        }
        catch (SQLException ex) {
            System.out.println("erreur de connexion");
           
        }
        
        
        
            
    }

    
}
