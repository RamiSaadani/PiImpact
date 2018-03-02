/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerClient;

import Services.CrudArticle;
import Services.CrudEvaluation;
import Services.CrudEvenement;
import Services.CrudOffre;
import entities.Article;
import entities.Evenement;
import entities.Offre;
import java.io.FileInputStream;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author CePc
 */


public class ActualiteController implements Initializable {

     Article A = null ; 
     CrudEvaluation CEval = new CrudEvaluation() ; 
     CrudEvenement CE= new CrudEvenement();
     Evenement E = null ; 
     CrudOffre co= new CrudOffre() ; 
       Offre o = null ; 
    Alert alert = new Alert(Alert.AlertType.ERROR);  
   
    @FXML
    private ScrollPane scroll1;
    @FXML
    private ScrollPane scroll2;
    @FXML
    private ScrollPane scroll3;
    @FXML
    private ScrollPane scroll4;
    @FXML
    private WebView WebView1;

    CrudArticle CA=new CrudArticle();
    public  Node ListofAllArticle () throws SQLException {

        VBox root0 =new VBox(10) ; 

        ObservableList<Article> OB = FXCollections.observableArrayList() ; 
        OB = CA.displayAllArticle(); 
        
        for (int i=0; i < 4 ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
        root.setPadding(new Insets(5,5, 5,5));
        
       

        try {
        Article A = OB.get(i)   ;
        Image image = new Image("file:"+A.getAFFICHE_A());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
       
        
        //Titre
        Label Titre = new Label(A.getTITRE_A()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(A.getDESCRIPTION_A()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label Date = new Label("Edité le : " +A.getDate_a() + "par "+A.getEDITEUR_A()) ; 
        
        Label Type = new Label("Type :"+A.getTYPE_A()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+A.getID_ARTICLE());
           button.onActionProperty().set((event) -> {
            //    scroll1.setContent(AfficheArticle (button.getAccessibleText()));

           });
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Article", A.getID_ARTICLE())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(Date,Type) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println(e);
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
  
public  Node ListofAllEvent () throws SQLException {
        VBox root0 =new VBox(10) ; 
        ObservableList<Evenement> OB = FXCollections.observableArrayList() ; 
        OB = CE.displayAllEvenement() ; 
        
        for (int i=0; i < 4 ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
       root.setPadding(new Insets(5,5, 5,5));

        try {
       Evenement E = OB.get(i)   ;
     //   FileInputStream input = new FileInputStream("C:\\Users\\koussai\\Downloads\\19956753_117137918905954_6849634775297747404_o (1).png");
        Image image = new Image("file:"+E.getAFFICHE_E());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        
        
        //Titre
        Label Titre = new Label(E.getTITRE_E()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(E.getDESCRIPTION_E()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label DateDebut = new Label("Date début : " +E.getDATEDEBUT_E()) ; 
        DateDebut.setTextFill(Color.web("#17202A"));
        Label Lieu = new Label("Lieu : "+E.getLIEU_E()) ;
        Lieu.setTextFill(Color.web("#17202A"));
        Label Frais = new Label("Frais : " +E.getFRAIS_E()) ;
        Frais.setTextFill(Color.web("#17202A"));
        Label orga = new Label("Organisateur :"+E.getORGANISATEUR_E()) ;
        orga.setTextFill(Color.web("#17202A"));
        Label Type = new Label("Type :"+E.getTYPE_E()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+E.getID_EVENEMENT());
           button.onActionProperty().set((event) -> {
           //           scroll4.setContent(AfficheEvent (button.getAccessibleText()));

           });
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Evenement", E.getID_EVENEMENT())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(DateDebut,Lieu,Frais,orga,Type) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println(e);
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }

public  Node ListofAllOffre () throws SQLException {

        VBox root0 = new VBox(10) ; //spacing 10

        ObservableList<Offre> OB = FXCollections.observableArrayList() ; 
        OB = co.displayAllOffre();
        
        for (int i=0; i < 4 ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-background-color:  #2471A3"); 
        root.setPadding(new Insets(5,5, 5,5));

        try {

        FileInputStream input = null;
       // input=new FileInputStream("C:\\image\\logo.png");
        Image image = null;
        image=new Image("file:"+o.getAFFICHE_O());
        ImageView IMG = null;
        IMG=new ImageView(image);
        
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        Offre o = OB.get(i)   ;
        
        //Offre
        Label Titre = new Label(o.getTITRE_O()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#FDFEFE"));
       
        Label Des = new Label(o.getDESCRIPTION_O()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#FDFEFE"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
        Label Datedebut = new Label("Date debut : " +o.getDATEDEBUT_O()) ; 
        Datedebut.setTextFill(Color.web("#FDFEFE"));
        Label Datefin = new Label("Date fin : " +o.getDATEFIN_O()) ; 
        Datefin.setTextFill(Color.web("#FDFEFE"));
        Label prix = new Label("Prix : " +o.getNOUVEAU_PRIX()) ;
        prix.setTextFill(Color.web("#FDFEFE"));
        Label nomesp = new Label("Nom espace :"+o.getNom_es()) ;
        nomesp.setTextFill(Color.web("#FDFEFE"));

        //Voir details
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #FDFEFE"); 
           button.setTextFill(Color.web("#2471A3"));
           button.setAccessibleText(""+o.getID_OFFRE());
           button.onActionProperty().set((event) -> {
            
   //          scroll2.setContent(AfficheOffre(button.getAccessibleText()));
           });
           
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#FDFEFE"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Offre", o.getID_OFFRE())) ;
        MoyNote.setTextFill(Color.web("#FDFEFE"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(Datedebut,Datefin,prix,nomesp) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println("ex11 "+e); 
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
                scroll1.setContent(ListofAllArticle());
                scroll4.setContent(ListofAllEvent());
                scroll2.setContent(ListofAllOffre());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
         WebView embeddedWV = new WebView();
           WebEngine WebView = embeddedWV.getEngine();
           WebView1.getEngine().load("https://www.youtube.com/embed/Vo7QtHdeTbo?list=PL_C0q8jNRXJadoakiLVd8Prvu_HbZkKkm");
    }  

  
    
   
}
