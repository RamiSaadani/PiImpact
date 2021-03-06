package ControllerClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.LoginGUIController;
import entities.Utilisateur;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.util.Duration;
import main.PopupReclamation;

/**
 * FXML Controller class
 *
 * @author koussai
 */
public class AcceuilController implements Initializable {


    AnchorPane EventClient,ArticleClient,EspaceClient,OffreClient,ActualiteClient,Bot,GestionEditProfile;
   @FXML
    public   Label UserName;
      
   
    @FXML
     public  ImageView AVATARUSER;
    @FXML
    private Button BtnActualite;
    @FXML
    private Button BtnArticle;
    @FXML
    private Button BtnEspace;
    @FXML
    private Button BtnEvent;
    @FXML
    private Button BtnOffre;
    @FXML
    private Button BtnBot;
    @FXML
    private ScrollPane AncoMain;
    Stage primaryStage;

    public AcceuilController(Stage primaryStage) {
      this.primaryStage = primaryStage;
    }
    
    /**
     * Initializes the controller class.
     */
    
     private void setNode(Node node) {
        
        AncoMain.setContent(node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {     
             
            EventClient =  FXMLLoader.load(getClass().getResource("/ViewClient/Evenement.fxml"));
            EspaceClient = FXMLLoader.load(getClass().getResource("/ViewClient/Espace.fxml"));
            
            OffreClient = FXMLLoader.load(getClass().getResource("/ViewClient/Offre.fxml"));
            ActualiteClient = FXMLLoader.load(getClass().getResource("/ViewClient/Actualite.fxml"));
            GestionEditProfile= FXMLLoader.load(getClass().getResource("/View/GestionInformationMembre.fxml"));
            ArticleClient = FXMLLoader.load(getClass().getResource("/ViewClient/Article.fxml"));
            Bot = FXMLLoader.load(getClass().getResource("/View/ChatBotHelp.fxml"));
            
              Image image = new Image("file:"+LoginGUIController.CurrentUser.getAvatar());
              UserName.setText(LoginGUIController.CurrentUser.getNom() +" "+LoginGUIController.CurrentUser.getPrenom());
              AVATARUSER.setImage(image);
            } catch (IOException ex) {
                System.out.println("ControllerClient.AcceuilController.initialize()"+ex);
          }
        Timer timer = new Timer();
timer.schedule(new TimerTask()
{
    @Override
    public void run()
    {
        // TODO do your thing
        System.out.println(LoginGUIController.CurrentUser.getNom()+LoginGUIController.CurrentUser.getPrenom());
        UserName.setText(LoginGUIController.CurrentUser.getNom() +" "+LoginGUIController.CurrentUser.getPrenom());
        AVATARUSER.setImage(new Image("file:"+LoginGUIController.CurrentUser.getAvatar()));

    }
}, 0, 1000);
    }    

    @FXML
    private void GetAcceuil(ActionEvent event) {
        setNode(ActualiteClient);
        BtnActualite.setStyle("-fx-background-color:  #086A87");
        BtnActualite.setTextFill(Color.web("#FBFCFC"));
        //
        BtnArticle.setStyle("-fx-background-color:  #FFFFFF");
        BtnArticle.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvent.setTextFill(Color.web("#2471A3"));
        BtnOffre.setStyle("-fx-background-color:  #FFFFFF");
        BtnOffre.setTextFill(Color.web("#2471A3"));
        BtnBot.setStyle("-fx-background-color:  #FFFFFF");
        BtnBot.setTextFill(Color.web("#2471A3"));
    }

    @FXML
    private void GetArticle(ActionEvent event) {
    
    setNode(ArticleClient);
        BtnArticle.setStyle("-fx-background-color:  #086A87");
        BtnArticle.setTextFill(Color.web("#FBFCFC"));
        //
        BtnActualite.setStyle("-fx-background-color:  #FFFFFF");
        BtnActualite.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvent.setTextFill(Color.web("#2471A3"));
        BtnOffre.setStyle("-fx-background-color:  #FFFFFF");
        BtnOffre.setTextFill(Color.web("#2471A3"));
        BtnBot.setStyle("-fx-background-color:  #FFFFFF");
        BtnBot.setTextFill(Color.web("#2471A3"));
    
    }
    
    

    @FXML
    private void GetEspace(ActionEvent event) {
        setNode(EspaceClient);
        BtnEspace.setStyle("-fx-background-color:  #086A87");
        BtnEspace.setTextFill(Color.web("#FBFCFC"));
        //
        BtnActualite.setStyle("-fx-background-color:  #FFFFFF");
        BtnActualite.setTextFill(Color.web("#2471A3"));
        BtnArticle.setStyle("-fx-background-color:  #FFFFFF");
        BtnArticle.setTextFill(Color.web("#2471A3"));
        BtnEvent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvent.setTextFill(Color.web("#2471A3"));
        BtnOffre.setStyle("-fx-background-color:  #FFFFFF");
        BtnOffre.setTextFill(Color.web("#2471A3"));
        BtnBot.setStyle("-fx-background-color:  #FFFFFF");
        BtnBot.setTextFill(Color.web("#2471A3"));
    }

    @FXML
    private void GetEvenement(ActionEvent event) {
    setNode(EventClient);
        BtnEvent.setStyle("-fx-background-color:  #086A87");
        BtnEvent.setTextFill(Color.web("#FBFCFC"));
        //
        BtnActualite.setStyle("-fx-background-color:  #FFFFFF");
        BtnActualite.setTextFill(Color.web("#2471A3"));
        BtnArticle.setStyle("-fx-background-color:  #FFFFFF");
        BtnArticle.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnOffre.setStyle("-fx-background-color:  #FFFFFF");
        BtnOffre.setTextFill(Color.web("#2471A3"));
        BtnBot.setStyle("-fx-background-color:  #FFFFFF");
        BtnBot.setTextFill(Color.web("#2471A3"));
    }

    @FXML
    private void GetOffre(ActionEvent event) {
        setNode(OffreClient);
        BtnOffre.setStyle("-fx-background-color:  #086A87");
        BtnOffre.setTextFill(Color.web("#FBFCFC"));
        //
        BtnActualite.setStyle("-fx-background-color:  #FFFFFF");
        BtnActualite.setTextFill(Color.web("#2471A3"));
        BtnArticle.setStyle("-fx-background-color:  #FFFFFF");
        BtnArticle.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvent.setTextFill(Color.web("#2471A3"));
        BtnBot.setStyle("-fx-background-color:  #FFFFFF");
        BtnBot.setTextFill(Color.web("#2471A3"));
    }


    @FXML
    private void GetAide(ActionEvent event) {
          setNode(Bot);
        BtnBot.setStyle("-fx-background-color:  #086A87");
        BtnBot.setTextFill(Color.web("#FBFCFC"));
        //
        BtnActualite.setStyle("-fx-background-color:  #FFFFFF");
        BtnActualite.setTextFill(Color.web("#2471A3"));
        BtnArticle.setStyle("-fx-background-color:  #FFFFFF");
        BtnArticle.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvent.setTextFill(Color.web("#2471A3"));
        BtnOffre.setStyle("-fx-background-color:  #FFFFFF");
        BtnOffre.setTextFill(Color.web("#2471A3"));
    }
 
  @FXML
    private void ProfileEdit(MouseEvent event) {
        setNode(GestionEditProfile);
    }
    @FXML
    private void Lougout(MouseEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginGUI.fxml")) ; 
       loader.setController(new LoginGUIController(primaryStage));
       
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Connexion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
      @FXML
    private void Reclamer(MouseEvent event) throws FileNotFoundException {
          PopupReclamation pr = new PopupReclamation() ; 
          pr.displayPopupReclamation(LoginGUIController.CurrentUser);
    }
   
    
      

    
    
}
