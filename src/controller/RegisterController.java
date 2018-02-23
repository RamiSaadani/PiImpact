/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import Services.CrudMembre;
import Services.CrudUtilisateur;
import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.* ;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class RegisterController implements Initializable {
    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtEmail;
    @FXML
    private PasswordField TxtPass;
    @FXML
    private RadioButton RadBtnHomme;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton RadBtnFemme;
    @FXML
    private DatePicker DateNaissance;
    @FXML
    private Button BtnSignup;
    Stage primarystage ;

    public RegisterController(Stage primarystage) {
        this.primarystage = primarystage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Signin(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginGUI.fxml")) ; 
       loader.setController(new LoginGUIController(primarystage));
       
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primarystage.setTitle("Connexion");
        primarystage.setScene(scene);
        primarystage.show();
    }
    
    @FXML
    private void Signup(ActionEvent event) throws IOException, SQLException {
        if(TxtNom.equals("")||TxtPrenom.equals("")||TxtEmail.equals("")||TxtPass.equals("")||(gender.getSelectedToggle() == null)||(DateNaissance.getValue()==null)){
             Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Veuillez remplir tous les champs");
             a.showAndWait();
        }else{
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText().toLowerCase();
            CrudUtilisateur crudutilisateur = new CrudUtilisateur() ;
            if(crudutilisateur.FindByEmail(TxtEmail.getText())==null){
            Membre m = new Membre(0, TxtNom.getText(), TxtPrenom.getText(), java.sql.Date.valueOf(DateNaissance.getValue()), toogleGroupValue, TxtEmail.getText()  , TxtPass.getText()) ;
            CrudMembre s = new CrudMembre() ; 
            try {
                s.InsertMembre(m);
            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("adresse email non reconuu");
             a.showAndWait();
            }
            }else{
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("adresse email existe deja connectez vous");
            a.showAndWait();
            }
        }
}
}
