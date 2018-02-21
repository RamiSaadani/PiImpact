/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudEvaluation;
import entities.Evaluation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private LineChart<?, ?> chart1;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private LineChart<?, ?> chart2;
    @FXML
    private CheckBox EvalEvent;
    @FXML
    private CheckBox EvalArticle;
    @FXML
    private CheckBox EvalEspace;
    @FXML
    private CheckBox EvalOffre;
    @FXML
    private TextField NomUser;
    @FXML
    private CheckBox EvaEspace;
    @FXML
    private CheckBox EvaArticle;
    @FXML
    private CheckBox EvaEvent;
    @FXML
    private CheckBox EvaOffre;
    
    
    
     private ObservableList<Evaluation> list_evaluation = FXCollections.observableArrayList();;
 //private final Stage primaryStage;
    
     Alert alert=new Alert(Alert.AlertType.WARNING);
      CrudEvaluation CE = new CrudEvaluation();
 
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
        
        TableColumn   _IdEval = new TableColumn("ID_EVALUATION");
        TableColumn _IdUtilisateur= new TableColumn("ID_UTILISATEUR");
        TableColumn _NotesEv = new TableColumn("NOTES_EV");
        TableColumn _CommentaireEv = new TableColumn("COMMENTAIRE_EV");
        TableColumn _ObjetEv= new TableColumn("OBJET_EV");
        TableColumn  _TypeEv= new TableColumn("TYPE_EV");
        TableColumn  _IdObj= new TableColumn("ID_O");
      
             
             
          _IdEval.setCellValueFactory(new PropertyValueFactory<>("ID_EVALUATION"));
        _IdUtilisateur.setCellValueFactory(new PropertyValueFactory<>("ID_UTILISATEUR"));
        _NotesEv.setCellValueFactory(new PropertyValueFactory<>("NOTES_EV"));
        _CommentaireEv.setCellValueFactory(new PropertyValueFactory<>("COMMENTAIRE_EV"));
        _ObjetEv.setCellValueFactory(new PropertyValueFactory<>("OBJET_EV"));
        _TypeEv.setCellValueFactory(new PropertyValueFactory<>("TYPE_EV"));
        _IdObj.setCellValueFactory(new PropertyValueFactory<>("ID_O"));
        TableEval.getColumns().addAll( _IdEval,_IdUtilisateur,_NotesEv, _CommentaireEv, _ObjetEv, _TypeEv ,_IdObj);
       
 
UpdateListe();


        
    } 
    
//    
//     public EvaluationController(Stage primaryStage) 
//    {
//        this.primaryStage = primaryStage;
//    }
//    
    @FXML
      public void UpdateListe(){
           
                CrudEvaluation CE = new CrudEvaluation();     
        try 
        {
              
             
            TableEval.setItems( CE.DisplayAll());
              
        } 
        catch (SQLException ex) 
        {
           
        }
         }

    @FXML
    private void Chercher(ActionEvent event) {
        
         if ("".equals(chercherUti.getText())) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
        a.setContentText("Veuillez saisir un utilisateur! ");
        a.showAndWait();
            
        }
          else
        {
            String uti = chercherUti.getText() ;          
            CrudEvaluation CE = new CrudEvaluation();

            
        list_evaluation = FXCollections.observableArrayList();
        try {
           TableEval.setItems(CE.searchByNomU(chercherUti.getText())); 
        } catch (SQLException ex) {
         
        }
        
    }
    }

    @FXML
    private void filtrer(ActionEvent event) throws SQLException {
        
         String type =  "''" ; 
       
        if(EvalEvent.isSelected()){type+=" OR TYPE_EV = 'evenement' ";}
        if(EvalArticle.isSelected()){type+=" OR TYPE_EV = 'Article' ";} 
        if(EvalEspace.isSelected()){type+=" OR TYPE_EV = 'Espace' ";}
        if(EvalOffre.isSelected()){type+=" OR TYPE_EV = 'Offre' ";}        
        try {
            
            TableEval.setItems(CE.SearchByType(type))  ;
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        
   
    }
    

    @FXML
    private void filtrerUti(ActionEvent event) throws SQLException {
        
        
          
            String uti = chercherUti.getText() ;          
            String type =  "''" ; 
            
        list_evaluation = FXCollections.observableArrayList();
        try {
           TableEval.setItems(CE.SearchByNomU_Type(type, uti) ); 
        } catch (SQLException ex) {
         
        }
        
        
       
         
       
  
         


    }
    
}
