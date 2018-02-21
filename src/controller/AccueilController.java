/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rami
 */


    

public class AccueilController implements Initializable {
    
    @FXML 
    private Label Gest ; 
    @FXML
    private AnchorPane holderPane;
    Stage primaryStage ;
    public AccueilController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    AnchorPane GestionMembre,GestionArticle,GestionEvent,GestionEspace,GestionOffre,GestionEval;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
             GestionMembre = FXMLLoader.load(getClass().getResource("/View/GestionMembre.fxml"));
             GestionArticle = FXMLLoader.load(getClass().getResource("/View/GestionArticle.fxml"));
             GestionEvent = FXMLLoader.load(getClass().getResource("/View/GestionEvent.fxml"));
             GestionEspace = FXMLLoader.load(getClass().getResource("/View/GestionEspace.fxml"));
             GestionOffre = FXMLLoader.load(getClass().getResource("/View/GestionOffre.fxml"));
             GestionEval = FXMLLoader.load(getClass().getResource("/View/GestionEval.fxml"));
           System.out.println("controller.AccueilController.initialize() 1 ");
             setNode(GestionMembre);
              Gest.setText("Gestionnaire des membres");
        } catch (IOException ex) {
             System.out.println("controller.AccueilController.initialize()"+ex);
        }
    }    
    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    @FXML
    private void GetGestionMembre(ActionEvent event) {
        setNode(GestionMembre);
        Gest.setText("Gestionnaire \n des membres");
    }

    @FXML
    private void GetGestionArticle(ActionEvent event) {
        setNode(GestionArticle);
        Gest.setText("Gestionnaire des Articles");
    }

    @FXML
    private void GetGestionEvents(ActionEvent event) {
        setNode(GestionEvent);
        Gest.setText("Gestionnaire des Evenements");
    }

    @FXML
    private void GetGestionEspace(ActionEvent event) {
        setNode(GestionEspace);
        Gest.setText("Gestionnaire des Espaces");
    }

    @FXML
    private void GetGestionOffre(ActionEvent event) {
        setNode(GestionOffre);
                Gest.setText("Gestionnaire des Offres");
    }


    
            @FXML
    private void GetGestionEval(ActionEvent event) {
        setNode(GestionEval);
         Gest.setText("Evaluations");
    }
}
