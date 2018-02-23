/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudEvaluation;
import Services.CrudUtilisateur;
import connexion.DataSource;
import entities.Evaluation;
import entities.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionEvalController implements Initializable {

    @FXML
    private TableView<Evaluation> TableEval;
    @FXML
    private TextField chercherUti;
    @FXML
    private BarChart<String, Number> chart5;
    @FXML
    private RadioButton EvalEvent;
    @FXML
    private RadioButton EvalArticle;
    @FXML
    private RadioButton EvalEspace;
    @FXML
    private RadioButton EvalOffre;
    @FXML
    private TextField NomUser;
    @FXML
    private RadioButton EvaEspace;
    @FXML
    private RadioButton EvaArticle;
    @FXML
    private RadioButton EvaEvent;
    @FXML
    private RadioButton EvaOffre;

    private ObservableList<Evaluation> list_evaluation = FXCollections.observableArrayList();
    ;
 //private final Stage primaryStage;
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
    CrudEvaluation CE = new CrudEvaluation();

    private ObservableList<BarChart.Data> ObArrL_Evals;
    @FXML
    private ToggleGroup filter1;
    @FXML
    private RadioButton EvalNone;
    @FXML
    private RadioButton EvaNone;
    @FXML
    private ToggleGroup filter2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UpdateListe();

        TableColumn _IdEval = new TableColumn("ID_EVALUATION");
        TableColumn _IdUtilisateur = new TableColumn("ID_UTILISATEUR");
        TableColumn _NotesEv = new TableColumn("NOTES_EV");
        TableColumn _CommentaireEv = new TableColumn("COMMENTAIRE_EV");
        TableColumn _ObjetEv = new TableColumn("OBJET_EV");
        TableColumn _TypeEv = new TableColumn("TYPE_EV");
        TableColumn _IdObj = new TableColumn("ID_O");

        _IdEval.setCellValueFactory(new PropertyValueFactory<>("ID_EVALUATION"));
        _IdUtilisateur.setCellValueFactory(new PropertyValueFactory<>("ID_UTILISATEUR"));
        _NotesEv.setCellValueFactory(new PropertyValueFactory<>("NOTES_EV"));
        _CommentaireEv.setCellValueFactory(new PropertyValueFactory<>("COMMENTAIRE_EV"));
        _ObjetEv.setCellValueFactory(new PropertyValueFactory<>("OBJET_EV"));
        _TypeEv.setCellValueFactory(new PropertyValueFactory<>("TYPE_EV"));
        _IdObj.setCellValueFactory(new PropertyValueFactory<>("ID_O"));
        TableEval.getColumns().addAll(_IdEval, _IdUtilisateur, _NotesEv, _CommentaireEv, _ObjetEv, _TypeEv, _IdObj);



        this.LoadChart("génerale", "");

    }

    public void LoadChart(String chartName, String type) {
        String requete = "select Distinct Extract(YEAR from DATE_EVALUATION) as year From Evaluation " + type + "";
        chart5.setTitle("Evaluation " + chartName);

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName("Evaluation");

        try {
            Connection connexion = DataSource.getInstance().getConnection();
            ResultSet rs = connexion.createStatement().executeQuery(requete);
            while (rs.next()) {
                String req;
                if (type.equals("")) {
                    req = "select count(*) as number From Evaluation where "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                } else {
                    req = "select count(*) as number From Evaluation " + type + " and "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                }
                ResultSet rs1 = connexion.createStatement().executeQuery(req);
                rs1.next();
                series.getData().add(new XYChart.Data(rs.getString(1), rs1.getInt(1)));
            }
            chart5.getData().clear();
            chart5.getData().add(series);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    
//     public GestionEvalController(Stage primaryStage) 
//    {
//        this.primaryStage = primaryStage;
//    }
//  
    @FXML
    public void UpdateListe() {

        CrudEvaluation CE = new CrudEvaluation();
        try {

            TableEval.setItems(CE.DisplayAll());

        } catch (SQLException ex) {
            Logger.getLogger(GestionEvalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Chercher(ActionEvent event) {

        if ("".equals(chercherUti.getText())) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir un utilisateur! ");
            a.showAndWait();

        } else {
            String uti = chercherUti.getText();
            CrudEvaluation CE = new CrudEvaluation();

            list_evaluation = FXCollections.observableArrayList();
            try {
                TableEval.setItems(CE.searchByNomU(chercherUti.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(GestionEvalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void filtrer(ActionEvent event) throws SQLException {
        String type = "";
        String evalName = "génerale";
        if (EvalEvent.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'evenement' ";
            evalName = "evenement";
        }
        if (EvalArticle.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'article' ";
            evalName = "article";
        }
        if (EvalEspace.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'espace' ";
            evalName = "espace";
        }
        if (EvalOffre.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'offre' ";
            evalName = "offre";
        }
        try {
            TableEval.setItems(CE.SearchByType(type));
            this.LoadChart(evalName, type);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void filtrerUti(ActionEvent event) throws SQLException {

        String uti = NomUser.getText();
         String type = "";
//        String typeis = "";
        String evalName = "génerale";
        list_evaluation = FXCollections.observableArrayList();
        
        
               if (EvaEvent.isSelected() ) {
            type +=" WHERE EVALUATION.TYPE_EV ='evenement'";
            evalName = "evenement";
        } 
        if (EvaArticle.isSelected()) {
            type +=" WHERE EVALUATION.TYPE_EV ='article' ";
            evalName = "article";
        }
        if (EvaEspace.isSelected()) {
            type += " WHERE EVALUATION.TYPE_EV = 'espace' ";
            evalName = "espace";
        }
        if (EvaOffre.isSelected()) {
            type += " WHERE EVALUATION.TYPE_EV ='offre' ";
            evalName = "offre"; }
        try {
            ObservableList<Evaluation> mylist = FXCollections.observableArrayList(test(NomUser.getText(), evalName)) ;
            
            TableEval.setItems(mylist);
             
               this.LoadChart(evalName, type);
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvalController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    private List<Evaluation>list;
    public List<Evaluation> test(String nom, String type) throws SQLException{
       List<Evaluation> list2;
       CrudEvaluation a = new CrudEvaluation() ;
 List<Evaluation> list = new ArrayList<>();
       
       list = a.DisplayAll2();
      
        CrudUtilisateur t = new CrudUtilisateur() ;
        List<Utilisateur> users = t.FindUserByNameOrLastname(nom) ;
        System.out.println(type);
 list2 = (List<Evaluation>) list.stream().filter(e->e.getTYPE_EV().equals(type)).collect(Collectors.toList());
        System.out.println(list2);
        if (list2!=null&& users!=null) {
            
            
       
 boolean testtt=false ;
    for(Evaluation e : list2){
       
    for(Utilisateur u : users){
    if(e.getID_UTILISATEUR()==u.getId()){
        System.out.println(e.getID_UTILISATEUR());
                System.out.println(u.getId());
                System.out.println("aaaaaaaaaakzejvkzerjzer");
    testtt=true ;
    }
    if(testtt==false){
    list2.remove(e) ;
    }
    }
    }
     
 
 return list2 ;
    }
        return null ;
      }         

    @FXML
    private void Reinitialiser(ActionEvent event) {
        
        UpdateListe();
        chercherUti.clear();
        NomUser.clear();
        
        
    }
    
}
