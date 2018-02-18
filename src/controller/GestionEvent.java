/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudEvenement;
import entities.Evenement;
import entities.Utilisateur;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author koussai
 */
public class GestionEvent implements Initializable {

    @FXML 
    private ImageView AfficheIMG ;
    @FXML
    private TextField Titre;
    @FXML
    private TextArea Description;
    @FXML
    private TextField FilePath;
    @FXML
    private Button UploadFile;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private TextField Lieu;
    private TextField Duree;
    @FXML
    private TextField Frais;
    @FXML
    private TextField Organisateur;
    @FXML
    private TextField Contact;
    @FXML
    private ComboBox<String> Type;
    @FXML
    private Button AddEvent;
    @FXML
    private Button EditEvent;
    @FXML
    private Button EditEvenet;
    @FXML
    private TextField Chercher;
    @FXML
    private Button chercher;
    @FXML
    private CheckBox Rando;
    @FXML
    private CheckBox voyage;
    @FXML
    private CheckBox Camp;
    @FXML
    private CheckBox marathon;
    @FXML
    private CheckBox autre;
    @FXML
    private Button filtre;
    @FXML
    private TableView<Evenement> eventList;
    @FXML
    private TableColumn<Evenement, Integer> EventID;
    @FXML
    private TableColumn<Evenement, Integer> UserID;
    @FXML
    private TableColumn<Evenement, String> TITRE;
    @FXML
    private TableColumn<Evenement, String> DESCRIPTION;
    @FXML
    private TableColumn<Evenement, Date> DATEDEBUT;
    @FXML
    private TableColumn<Evenement, Date> DATEFIN;
    @FXML
    private TableColumn<Evenement, String> DUREE;
    @FXML
    private TableColumn<Evenement, String> FRAIS;
    @FXML
    private TableColumn<Evenement, String> ORGANISATEUR;
    @FXML
    private TableColumn<Evenement, String> CONTACT;
    @FXML
    private TableColumn<Evenement, String> AFFICHE;
    @FXML
    private TableColumn<Evenement, String> TYPE;
    @FXML
    private TableColumn<Evenement, String> LIEU;
   
    public int ID_Event = 0 ; 
    CrudEvenement CE = new CrudEvenement() ; 
    Evenement E  = null ; 
    String TitreEvent  = "" ;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
         UpdateList() ; 
         try { eventList.setItems(CE.displayAllEvenement()); } 
         catch (SQLException ex) {System.out.println("Combox : " + ex);  }
         ObservableList Combo =  FXCollections.observableArrayList("Randonn�","Voyage","Voyage","Marathon","Autre") ; 
         Type.setItems(Combo);       
         
       eventList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                        Evenement SelectedEvenet = eventList.getItems().get(eventList.getSelectionModel().getSelectedIndex());
                        ID_Event  = SelectedEvenet.getID_EVENEMENT() ;
                        Titre.setText(SelectedEvenet.getTITRE_E());
                         TitreEvent  = Titre.getText();
                        Description.setText(SelectedEvenet.getDESCRIPTION_E());
                        FilePath.setText(SelectedEvenet.getAFFICHE_E());
                        FilePath.setText(SelectedEvenet.getAFFICHE_E());
                        DateDebut.valueProperty().setValue(SelectedEvenet.getDATEDEBUT_E().toLocalDate());
                        DateFin.valueProperty().setValue(SelectedEvenet.getDATEFIN_E().toLocalDate());
                        Lieu.setText(SelectedEvenet.getLIEU_E());
                        Duree.setText(""+SelectedEvenet.getDUREE_E());
                        Frais.setText(""+ SelectedEvenet.getFRAIS_E());
                        Organisateur.setText(SelectedEvenet.getORGANISATEUR_E());
                        Contact.setText(SelectedEvenet.getCONTACT_E());
                        Type.getSelectionModel().select(SelectedEvenet.getTYPE_E()) ;
            }           
        });
         
    }    

    public  void UpdateList() {
                
        EventID.setCellValueFactory(new  PropertyValueFactory<>("ID_EVENEMENT"));
        UserID.setCellValueFactory(new  PropertyValueFactory<>("ID_UTILISATEUR"));
        TITRE.setCellValueFactory(new  PropertyValueFactory<>("TITRE_E"));
        DESCRIPTION.setCellValueFactory(new  PropertyValueFactory<>("DESCRIPTION_E"));
        AFFICHE.setCellValueFactory(new  PropertyValueFactory<>("AFFICHE_E"));
        DATEDEBUT.setCellValueFactory(new  PropertyValueFactory<>("DATEDEBUT_E"));
        DATEFIN.setCellValueFactory(new  PropertyValueFactory<>("DATEFIN_E"));
        FRAIS.setCellValueFactory(new  PropertyValueFactory<>("FRAIS_E"));
        LIEU.setCellValueFactory(new  PropertyValueFactory<>("LIEU_E"));
        DUREE.setCellValueFactory(new  PropertyValueFactory<>("DUREE_E"));
       ORGANISATEUR.setCellValueFactory(new  PropertyValueFactory<>("ORGANISATEUR_E"));
        CONTACT.setCellValueFactory(new  PropertyValueFactory<>("CONTACT_E"));
        TYPE.setCellValueFactory(new  PropertyValueFactory<>("TYPE_E"));
    }

    
    @FXML
    private void UplaodImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            FilePath.setText(file.getAbsolutePath());
            
            Image img = new Image("file:"+file.getAbsolutePath());
            AfficheIMG.imageProperty().set(img); 
        
    }

    @FXML
    private void AjouterEvenet(ActionEvent event) throws SQLException {
        
        if ("".equals(Titre.getText()) ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Le titre est un champ obligatoire !");
            alert.showAndWait();
        } else if ("".equals(Description.getText()) ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("La description est un champ obligatoire !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateDebut.getValue().toEpochDay() ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur Date");
            alert.setHeaderText("Attention");
            alert.setContentText("la date d'�venement doit etre superieur � la date courant !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateFin.getValue().toEpochDay() || DateFin.getValue().toEpochDay()<DateDebut.getValue().toEpochDay()  ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la date du fin de l'evenement !");
            alert.showAndWait();
        }else if ( "".equals(Duree.getText()) ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la dur�e de l'evenemenemt !");
            alert.showAndWait();  
        } else if ( "".equals(Frais.getText()) ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la dur�e de l'evenemenemt !");
            alert.showAndWait();  
        }else if ( "".equals(Organisateur.getText()) ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie le nom de  l'organisateur  !");
            alert.showAndWait();  
        }
        else if ( "".equals(Contact.getText()) ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie les contacts  !");
            alert.showAndWait();  
        }else if ((Type.getSelectionModel().getSelectedIndex())== 0 ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie chois  !");
            alert.showAndWait();  
        }
        else {
        
            try {
                E = new  Evenement( Titre.getText(), Description.getText(), FilePath.getText() , java.sql.Date.valueOf(DateDebut.getValue()) , java.sql.Date.valueOf(DateFin.getValue()), Lieu.getText(),Integer.parseInt(Duree.getText()) , Float.parseFloat(Frais.getText()), Organisateur.getText(), Contact.getText(), Type.getValue());
                E.setID_EVENEMENT(ID_Event);
                E.setID_UTILISATEUR(20);
                CE.InsertEvenement(E);
                eventList.setItems(CE.displayAllEvenement());
                 vider () ; 
            } catch (SQLException ex) {
               Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Erreur d'insertion");
                alert.setHeaderText("Attention");
                alert.setContentText("Exception "+ex);
                alert.showAndWait();
            }
         }
       
    }

    @FXML
    private void EditEvenet(ActionEvent event) throws SQLException {
       try {  
        E = new  Evenement( Titre.getText(), Description.getText(), FilePath.getText() , java.sql.Date.valueOf(DateDebut.getValue()) , java.sql.Date.valueOf(DateFin.getValue()), Lieu.getText(),Integer.parseInt(Duree.getText()) , Float.parseFloat(Frais.getText()), Organisateur.getText(), Contact.getText(), Type.getValue());
        E.setID_UTILISATEUR(20);
        E.setID_EVENEMENT(ID_Event);  
         CE.UpdateEvenement(E,ID_Event);
      
      eventList.setItems(CE.displayAllEvenement());
       vider () ; 
       } catch (SQLException ex) { System.err.println("EditEvenet " +ex );    }
    }

    @FXML
    private void DeletEvent(ActionEvent event) throws SQLException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation la suppression");
        alert.setHeaderText("Confirmer");
        alert.setContentText("Vous-etes sur de supprimer l'evenement "+TitreEvent+" ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
             CE.DeleteEvenement(ID_Event);
        }
         
        try {        
           eventList.setItems(CE.displayAllEvenement());
            vider () ; 
        } catch (SQLException ex) { }
       
    }

    @FXML
    private void Search(ActionEvent event) {
         try {        
           eventList.setItems(CE.Search(Chercher.getText()));
        } catch (SQLException ex) {
             System.out.println(ex);
        } 
    }

    @FXML
    private void Filtre(ActionEvent event) {
        String Type = "''" ; 
        if(Rando.isSelected()) { Type+=" or TYPE_E ='Randonn�'" ;}
        if(voyage.isSelected()) { Type+="or TYPE_E ='Voyage'";}
        if(Camp.isSelected()) { Type+=" or TYPE_E ='Camping'";}
        if(marathon.isSelected()) { Type+=" or TYPE_E = 'Marathon'";}
        if(autre.isSelected()) { Type+=" or TYPE_E = 'Autre'";}
     
        
        try {
           eventList.setItems(CE.displayFiltreEvenement(Type));
        } catch (SQLException ex) { System.out.println(ex);
        } 
        

    }
    
    
    
    public void vider () {
    E = null ; 
    Titre.setText(""); 
    Description.setText("");  
    FilePath.setText("");
    DateDebut.setValue(null);  
    DateFin.setValue(null);  
    Lieu.setText("");
    Duree.setText("");
    Frais.setText("");
    Organisateur.setText("");
    Contact.setText("");
    Type.setValue("");
    Type.setPromptText("Type");
    AfficheIMG.imageProperty().set(null); 
    }

    @FXML
    private void SlectTable(MouseEvent event) {
    }
}

