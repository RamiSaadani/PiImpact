/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudMembre;
import Services.CrudUtilisateur;
import entities.Membre;
import entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class GestionInformationMembreController implements Initializable {

    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtPoids;
    @FXML
    private TextField TxtTaille;
    @FXML
    private RadioButton RadBtnHomme;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton RadBtnFemme;
    @FXML
    private DatePicker DateNaissance;
    @FXML
    private VBox Hboxx;
    @FXML
    private TextField TxtCurrentPass;
    @FXML
    private TextField TxtNewPass;
    @FXML
    private TextField TxtNewPass2;
    @FXML
    private ImageView ProfilePic;
    Utilisateur CurrentUser= LoginGUIController.CurrentUser ;
    String filepath="" ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      Hboxx.setVisible(false);
      TxtNom.setText(CurrentUser.getNom());
      TxtPrenom.setText(CurrentUser.getPrenom());
      TxtPoids.setText(CurrentUser.getPoids()+"");
      TxtTaille.setText(CurrentUser.getTaille()+"");
      DateNaissance.setValue((CurrentUser.getDate_naissance().toLocalDate()));
      RadBtnHomme.setSelected(true);
      if(CurrentUser.getGender().equals("femme")){
      RadBtnFemme.setSelected(true);
      }
      
      if(!CurrentUser.getAvatar().equals("NULL")){
      
        try { 
            FileInputStream ff = new  FileInputStream(CurrentUser.getAvatar()) ;
            Image img = new Image(ff) ;
            ProfilePic.setImage(img);
            
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        }
        
      }
      
      
    }    

    @FXML
    private void SavingInformation(ActionEvent event) {
        if(!TxtNom.getText().equals("")){TxtNom.setStyle("-fx-border-color :  #2471A3 ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : black ;"); }
             if(!TxtPrenom.getText().equals("")){TxtPrenom.setStyle("-fx-border-color :  #2471A3 ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : black ;");}
             if(!TxtPoids.getText().equals("")){TxtPrenom.setStyle("-fx-border-color :  #2471A3 ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : black ;");}
             if(!TxtTaille.getText().equals("")){TxtTaille.setStyle("-fx-border-color :  #2471A3 ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : black ;");}
             if(DateNaissance.getValue()!=null){DateNaissance.setStyle("-fx-border-color : white ; ") ;}
        if(TxtNom.getText().equals("")||TxtPrenom.getText().equals("")||(DateNaissance.getValue()==null)||TxtTaille.getText().equals("")||TxtTaille.getText().equals("")){
           
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Veuillez remplir tous les champs");
             a.showAndWait();
             if(TxtNom.getText().equals("")){TxtNom.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0;");}
             if(TxtPrenom.getText().equals("")){TxtPrenom.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0;");}
             if(TxtPoids.getText().equals("")){TxtPoids.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0;");}
             if(TxtTaille.getText().equals("")){TxtTaille.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0;");}
             if(DateNaissance.getValue()==null){DateNaissance.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0;") ;}
           
        }else {
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText().toLowerCase();
            CrudUtilisateur crudutilisateur = new CrudUtilisateur() ;
           
            CurrentUser.setNom(TxtNom.getText());
            CurrentUser.setPrenom(TxtPrenom.getText());
            CurrentUser.setPoids(Float.parseFloat(TxtPoids.getText()));
            CurrentUser.setTaille(Float.parseFloat(TxtTaille.getText()));
            CurrentUser.setDate_naissance(java.sql.Date.valueOf(DateNaissance.getValue()));
            CurrentUser.setGender(toogleGroupValue);
            CrudUtilisateur crd = new CrudUtilisateur() ;
            
            try {
                crd.UpdateUser(CurrentUser);
            } catch (SQLException ex) {
                Logger.getLogger(GestionInformationMembreController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
                
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Ajout avec Succes");
             a.showAndWait();
            }
            }
           
        
 
    

    @FXML
    private void AfficherMotdepasse(ActionEvent event) {
        Hboxx.setVisible(true);
    }

    @FXML
    private void SavingPassword(ActionEvent event) throws SQLException {
        if(TxtCurrentPass.getText().equals("")||TxtNewPass.getText().equals("")||TxtCurrentPass.getText().equals("")){
        Alert a = new  Alert(Alert.AlertType.ERROR) ; 
        a.setContentText("Inserez le mot de passe");
        a.showAndWait() ;
        }else{
        if(CurrentUser.getMot_passe().equals(TxtCurrentPass.getText())){
        if(TxtNewPass.getText().equals(TxtNewPass2.getText())){
                        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        CurrentUser.setMot_passe(TxtNewPass2.getText());
        CrudUtilisateur ccrd = new CrudUtilisateur() ;
        ccrd.UpdateUser(CurrentUser);
         Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Mot de passe changé");
             a.showAndWait();
             TxtCurrentPass.setText("");
             TxtNewPass.setText("");
             TxtNewPass2.setText("");
        }
        }
        }
    }

    @FXML
    private void ChangePicture(MouseEvent event) {
        FileChooser fs = new FileChooser() ; 
        File selectedfile = fs.showOpenDialog(null) ;
        if(selectedfile!=null){
            filepath=selectedfile.getAbsolutePath() ;
        }
        CurrentUser.setAvatar(filepath);
        CrudUtilisateur crd = new CrudUtilisateur() ;
            
            try {
            FileInputStream ff = new  FileInputStream(CurrentUser.getAvatar()) ;
            Image img = new Image(ff) ;
            ProfilePic.setImage(img);
                crd.UpdateUser(CurrentUser);
            } catch (Exception e ) {
                Alert a = new  Alert(Alert.AlertType.ERROR) ; 
                a.setContentText("Il y a un erreur");
                a.showAndWait() ;
        
            }
        
        
    }

    @FXML
    private void CheckPasswordwithTxPass(KeyEvent event) {
        if(TxtNewPass2.getText().equals(TxtNewPass.getText())){
       TxtNewPass2.setStyle("-fx-border-color :  red ; -fx-border-width :  0 0 2px 0 ;");
        }else {
      TxtPrenom.setStyle("-fx-border-color :  #2471A3 ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : black ;");

        }
    }
    
}
