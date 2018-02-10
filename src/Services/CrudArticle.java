/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import entities.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CePc
 */
public class CrudArticle {
Connection con= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ;
    
     public void insertSt(Article a) throws SQLException{
         
        String requete = "INSERT INTO Article (ID_UTILISATEUR,TITRE_A,DESCRIPTION_A,EDITEUR_A,TYPE_A) values"
                + "('"+a.getID_UTILISATEUR()+"','"+a.getDESCRIPTION_A()+"','"+a.getEDITEUR_A()+"','"+a.getTITRE_A()+"','"+a.getTYPE_A()+"')" ;
                
        ste=con.createStatement() ;
        ste.executeUpdate(requete ); 
    }
    
     public List<Article>displayAll() throws SQLException{
        String requete="SELECT * FROM Article" ;
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Article> list = new ArrayList<>() ; 
        while(rs.next()){
        Article a = new Article(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
        list.add(a) ;
        }
        return list ;
    }
     public void DeleteSt(int id) throws SQLException{
        String requete = "DELETE FROM Article WHERE id_ARTICLE="+id ;
        ste=con.createStatement() ;
        ste.executeUpdate(requete); 
                }
      public void UpdateArticle(Article a) throws SQLException{
        String requete="UPDATE Article SET TITRE_A=?, DESCRIPTION_A=?, EDITEUR_A=?, TYPE_A=? WHERE ID_ARTICLE=?" ;
       
        pst=con.prepareStatement(requete) ; 
        pst.setString(1, a.getTITRE_A());
        pst.setString(2, a.getDESCRIPTION_A());
        pst.setString(3, a.getEDITEUR_A());
        pst.setString(4, a.getTYPE_A());
        pst.setInt(5, a.getID_ARTICLE());
        pst.executeUpdate() ; 
       
    }
      
      public static void main(String[] args) throws SQLException {
          Article a =new Article( 1, "sport","sante","test","test");
        CrudArticle b=new CrudArticle();
        //b.insertSt(a);
        //b.DeleteSt(2);
        b.UpdateArticle(a);
        //b.displayAll().forEach(System.out::println);
        
    }
}
