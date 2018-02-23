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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class CrudEvaluation {

    Connection con = DataSource.getInstance().getConnection();
    Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public void insertEvaluation(Evaluation e) throws SQLException {

        String requete = "INSERT INTO Evaluation (ID_EVALUATION,ID_UTILISATEUR,NOTES_EV,COMMENTAIRE_EV,OBJET_EV, TYPE_EV, ID_O, DATE_EVALUATION) values"
                + "('" + e.getID_EVALUATION() + "','" + e.getID_UTILISATEUR() + "','" + e.getNOTES_EV() + "','" + e.getCOMMENTAIRE_EV() + "','" + e.getOBJET_EV() + "','" + e.getTYPE_EV() + "','" + e.getID_O() + "','" + e.getDATE_EVALUATION() + "')";

        ste = con.createStatement();
        ste.executeUpdate(requete);
    }

    public ObservableList<Evaluation> DisplayAll() throws SQLException {
        String requete = "SELECT  * from evaluation ";
        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

    public ObservableList<Evaluation> searchByNomU(String nom) throws SQLException {
        String requete = "select * FROM Evaluation,utilisateur WHERE  utilisateur.nom Like '%" + nom + "%' and  evaluation.ID_UTILISATEUR = utilisateur.ID_UTILISATEUR";
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

    public List<Evaluation> SearchByNom(String objett, String type, String nom) throws SQLException {
        if (objett.equals("offre")) {
            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id=(select  ID_OFFRE from offre where  titre_o='" + nom + "' and type_o='" + type + "') ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
        } else if (objett.equals("espace")) {
            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id=(select  ID_ESPACE from espace where  nom_es='" + nom + "' and type_es='" + type + "') ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
        } else if (objett.equals("evenement")) {

            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id=(select  ID_EVENEMENT from evenement where  titre_e='" + nom + "'and type_e='" + type + "') ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
        }
        List<Evaluation> list = new ArrayList<>();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

    public ObservableList<Evaluation> SearchByType(String type) throws SQLException {

        String requete = "select  * from evaluation " + type + " ";
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);

        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

    public ObservableList<Evaluation> SearchByNomU_Type(String type, String nom) throws SQLException {
        try {
            String requete = "SELECT * FROM evaluation INNER JOIN utilisateur ON evaluation.ID_UTILISATEUR = utilisateur.ID_UTILISATEUR where utilisateur.nom Like '%" + nom + "%' AND evaluation.TYPE_EV= " + type + " ";
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);

            
        } catch (Exception e) {
            System.out.println("Services.CrudEvaluation.SearchByNomU_Type()"+e);
        }
        
        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

    public void DeleteEvaluation(Evaluation e) throws SQLException {
        String requete = "DELETE FROM Evaluation WHERE id=" + e.getID_EVALUATION();
        ste = con.createStatement();
        ste.executeUpdate(requete);
    }
//      public void UpdateEvaluation(Evaluation e) throws SQLException{
//        String requete="UPDATE Evaluation SET ID_EVALUATION=?,ID_UTILISATEUR=?,NOTES_EV=?, COMMENTAIRE_EV=?,OBJET_EV=?,TYPE_EV=?,ID_O=? where " ;
//       
//        pst=con.prepareStatement(requete) ; 
//        pst.setInt(1, e.getID_EVALUATION());
//        pst.setInt(2, e.getID_UTILISATEUR());
//        pst.setInt(3, e.getNOTES_EV());
//        pst.setString(4, e.getCOMMENTAIRE_EV()); 
//        pst.setString(5, e.getOBJET_EV());
//        pst.setString(6, e.getTYPE_EV());
//        pst.setInt(7, e.getID_O());
//        pst.executeUpdate() ; 
//       
//    }
    public List<Evaluation> DisplayAll2() throws SQLException {
        String requete = "SELECT  * from evaluation ";
        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        List<Evaluation> list = new ArrayList<>();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

}
