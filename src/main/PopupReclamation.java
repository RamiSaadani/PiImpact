/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Services.CrudReclamation;
import entities.Reclamation;
import entities.Utilisateur;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Rami
 */
public class PopupReclamation {
    public static void displayPopupReclamation(Utilisateur c) throws FileNotFoundException
{
Stage popupwindow=new Stage();
      
popupwindow.initModality(Modality.APPLICATION_MODAL);
popupwindow.setTitle("Confirmation de Coach");
      
      
Label label1= new Label("Bonjour "+c.getNom()+" "+c.getPrenom()+", Vous voulez reclamer quelques chose ?");

label1.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
label1.setStyle("-fx-text-fill :  #0288D1 ;"); 

      
     
Button exit= new Button("Annuler");
Button EnvoyerReclamation= new Button("Envoyer Reclamation");

exit.setStyle("-fx-background-color :   #0288D1 ; -fx-text-fill :  #ffffff ;"); 
EnvoyerReclamation.setStyle("-fx-background-color :   #0288D1 ; -fx-text-fill :  #ffffff ;"); 
    
     

VBox layout= new VBox(10);
   HBox hlayout = new  HBox() ;
   TextField TxtType = new TextField() ;
   TxtType.setPromptText("Bref description ou type de reclamation");
   TxtType.setStyle("-fx-border-color :   #0288D1 ; -fx-text-fill :  black ;");
   TextArea TxtCommentaire = new TextArea() ;
   TxtCommentaire.setPromptText("Bref description ici");
   TxtType.setStyle("-fx-border-color :  #0288D1 ; -fx-text-fill :  black ;");
   hlayout.getChildren().addAll(EnvoyerReclamation ,exit) ;
   layout.setSpacing(20);
   hlayout.setSpacing(20);
   layout.setPadding(new Insets(40));
   hlayout.setAlignment(Pos.CENTER);
   layout.getChildren().addAll(label1 , TxtType ,TxtCommentaire , hlayout ) ;
     exit.setOnAction(e -> popupwindow.close());
     /*************************/
    CrudReclamation crudreclamation= new CrudReclamation() ;
    EnvoyerReclamation.setOnAction(e -> {
   if(TxtType.getText().equals("")||TxtCommentaire.getText().equals("")){
        Alert a = new  Alert(Alert.AlertType.ERROR) ; 
        a.setContentText("Veuillez remplir tous les champs");
        a.showAndWait();
}else{
   Reclamation r = new Reclamation(0, c.getId(), TxtType.getText(), TxtCommentaire.getText(), "nontraiter", Date.valueOf(LocalDate.now())); 
       try {
           crudreclamation.insertReclamation(r);
           Alert a = new  Alert(Alert.AlertType.INFORMATION) ; 
        a.setContentText("merci de nous contactez");
        a.showAndWait();
       } catch (SQLException ex) {
          Alert a = new  Alert(Alert.AlertType.ERROR) ; 
        a.setContentText("Ressayez plus tard");
        a.showAndWait();
       }
      
   }
   popupwindow.close();      
});
Scene scene1= new Scene(layout, 800, 350);
      
popupwindow.setScene(scene1);
      
popupwindow.showAndWait();
       
}
}
