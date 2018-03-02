/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entite.Espace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import connexion.DataSource;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;

/**
 *
 * @author ELYES
 */
public class CrudEspace {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    ResultSet rs;
    
    public CrudEspace() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public Map<Integer,String> EspaceDonneeCombo() throws SQLException
    {
        HashMap<Integer,String>map= new HashMap<>();
        String requete = "select ID_ESPACE,NOM_ES   from espace";
        ste = con.createStatement();
         rs=ste.executeQuery(requete);
         while(rs.next())
         {
             map.put(rs.getInt(1),rs.getString(2));
         }
        return map; 
    }
        public ObservableList<String> displayIDesp() throws SQLException
    {
        String requete = "select ID_ESPACE,NOM_ES ,ADRESSE_ES   from espace";
        ste = con.createStatement();
        
        
        rs=ste.executeQuery(requete);
        
        ObservableList<String> List= FXCollections.observableArrayList();
        while(rs.next())
        {
            
            
            List.add(" "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
            String X = ""+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3);
            
            System.out.println(X.substring(0, 1));
          System.out.println(Integer.parseInt(X.substring(0, 1)));
        }
        return List;
    }
    
        public void insertPST(Espace p) throws SQLException
    {
        String req = "insert into espace ( NOM_ES ,ADRESSE_ES, EMAIL_ES , NUMTEL_ES , FACEBOOK_ES , LOGO_ES,TYPE_ES ) values (?,?,?,?,?,?,?)";
      PreparedStatement  pst = con.prepareStatement(req);//attente des parametre
        pst.setString(1, p.getNomEspace());
        pst.setString(2, p.getADRESSE_ES());
        pst.setString(3, p.getEMAIL_ES());
        pst.setInt(4, p.getNUMTEL_ES());
        pst.setString(5, p.getFACEBOOK_ES());
        pst.setString(6, p.getLOGO_ES());
        pst.setString(7, p.getTYPE_ES());
       
       // ste = connexion.createStatement();
        pst.executeUpdate();
    }
       // ObservableList<Object>
        public List<Espace> displayAll() throws SQLException
    {
        String requete = "select *  from espace";
        ste = con.createStatement();
        
        rs=ste.executeQuery(requete);
        
        List<Espace> List= new ArrayList<>();
        while(rs.next())
        {
            Espace p = new Espace(rs.getString(3),rs.getString(4));
            List.add(p);
        }
        return List;
    }
        public int VerifAdresseEspaceObs(String Nom,String Adresse) throws SQLException
        {
            String requete = "select NOM_ES,ADRESSE_ES from espace where NOM_ES= '"+Nom+"' and ADRESSE_ES= '"+Adresse+"'";
            ste = con.createStatement();
        
        
        rs=ste.executeQuery(requete);
       // rs=ste.executeQuery(requete);
        
        return rs.getRow();
        }
                public ObservableList<Espace> displayAllObs() throws SQLException
    {
        String requete = "select *  from espace";
        ste = con.createStatement();
        
        
        rs=ste.executeQuery(requete);
        
        ObservableList<Espace> List= FXCollections.observableArrayList();;
        while(rs.next())
        {
            Espace p = new Espace(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
            List.add(p);
        }
        return List;
    }
       public ObservableList<Espace> SearchByNom(String objet) throws SQLException 
       {

        String requete = "select  * from espace where NOM_ES like '" + objet + "' ";
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);

        ObservableList<Espace> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Espace ev = new Espace(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
            list.add(ev);
        }
        return list;
    }
       
                 public ObservableList<Espace> SearchByType(String type) throws SQLException
    {
        String requete = "select *  from espace where TYPE_ES = '"+type+"'";
        ste = con.createStatement();
        
        rs=ste.executeQuery(requete);
        
        ObservableList<Espace> List= FXCollections.observableArrayList();;
        while(rs.next())
        {
            Espace p = new Espace(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
            List.add(p);
        }
        return List;
    }
                    public void deleteST(int id) throws SQLException
    {
        String req = "Delete from espace where ID_ESPACE= "+id;
        ste =con.createStatement();
        ste.executeUpdate(req);
    }
        public ObservableList<Espace> getEspace()
        {
            ObservableList<Espace>espaces= FXCollections.observableArrayList();
            Espace e = new Espace("test","test","test",468468,"test","test","test");
            espaces.add(e);
            
            return espaces;
        }
        public void updateST(Espace p) throws SQLException
    {
        String req= "update espace set NOM_ES= ? , ADRESSE_ES = ? ,EMAIL_ES=?,NUMTEL_ES=?,FACEBOOK_ES=?,LOGO_ES=?,TYPE_ES=? where ID_ESPACE= ?";
        PreparedStatement  pst = con.prepareStatement(req);
      //  pst = con .prepareStatement(req);
        pst.setString(1, p.getNomEspace());
        pst.setString(2, p.getADRESSE_ES());
         pst.setString(3, p.getEMAIL_ES());
         pst.setInt(4, p. getNUMTEL_ES());
         pst.setString(5, p.getFACEBOOK_ES());
         pst.setString(6, p.getLOGO_ES());
         pst.setString(7, p.getTYPE_ES());
         
         
         
         
         
        pst.setInt(8, p.getID_ESPACE());
       // ste = connexion.createStatement();
        pst.executeUpdate();
        
    }
    
}
