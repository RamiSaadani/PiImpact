/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import Services.CrudCoach;
import Services.CrudMembre;
import Services.CrudUtilisateur;
import entities.Coach;
import entities.Membre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class RegisterCoachController implements Initializable {
    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtEmail;
    @FXML
    private PasswordField TxtPass;
    @FXML
    private DatePicker DateNaissance;
    @FXML
    private RadioButton RadBtnHomme;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton RadBtnFemme;
    @FXML
    private TextArea TxtNiveauCoach;
    @FXML
    private Button BtnSignup;
    String filepath="" ;
    Stage primaryStage ;

    public RegisterCoachController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void joinPic(ActionEvent event) {
        FileChooser fs = new FileChooser() ; 
        File selectedfile = fs.showOpenDialog(null) ;
        if(selectedfile!=null){
            filepath=selectedfile.getAbsolutePath() ;
        }
    }

    @FXML
    private void Signin(MouseEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginGUI.fxml")) ; 
       loader.setController(new LoginGUIController(primaryStage));
       
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Connexion");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
     @FXML
    private void Signup(ActionEvent event) throws IOException, SQLException {
        if(TxtNom.equals("")||TxtPrenom.equals("")||TxtEmail.equals("")||TxtPass.equals("")||(gender.getSelectedToggle() == null)||(DateNaissance.getValue()==null)||TxtNiveauCoach.equals("")||filepath.equals("")){
             Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Veuillez remplir tous les champs");
             a.showAndWait();
        }else{
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText().toLowerCase();
            CrudUtilisateur crudutilisateur = new CrudUtilisateur() ;
            if(crudutilisateur.FindByEmail(TxtEmail.getText())==null){
                Coach m = new Coach(0, TxtNom.getText(), TxtPrenom.getText(), java.sql.Date.valueOf(DateNaissance.getValue()), toogleGroupValue, TxtEmail.getText()  , TxtPass.getText(),filepath,0,TxtNiveauCoach.getText()) ;
                CrudCoach s = new CrudCoach(); 
            s.InsertCoach(m);
            }else{
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("adresse email existe deja connectez vous");
            a.showAndWait();
            }
        }
}
    
}
