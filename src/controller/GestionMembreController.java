/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudUtilisateur;
import entities.Utilisateur;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class GestionMembreController implements Initializable {
    @FXML
    private TableView<Utilisateur> TablViewList;
    @FXML
    private TableColumn<Utilisateur, String> UserNameColumn;
    @FXML
    private TableColumn<Utilisateur, String> EmailColumn;
    @FXML
    private TableColumn<Utilisateur, Integer> StatusColumn;
    @FXML
    private TableColumn<Utilisateur, String> TypeColumn;
    @FXML
    private TextField TxtSearch;
    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtGender;
    @FXML
    private TextField TxtNumtel;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtType;
    @FXML
    private Button filtre;
    @FXML
    private Button filtre2;
    @FXML
    private TextField TxtEmailModerateur;
    CrudUtilisateur c = new CrudUtilisateur() ;
    ObservableList<Utilisateur> data;

    public GestionMembreController() {
        try {
            this.data = FXCollections.observableArrayList(c.displayAllUsers());
        } catch (SQLException ex) {
            Logger.getLogger(GestionMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TablViewList.setItems(data);
    }    

    @FXML
    private void SelectData(MouseEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void Filtre(ActionEvent event) {
    }

    @FXML
    private void AddModerateur(ActionEvent event) {
    }
    
}
