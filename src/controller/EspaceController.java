/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Cours;
import Entite.Espace;
import Services.CrudCours;
import Services.CrudEspace;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JComboBox;

/**
 * FXML Controller class
 *
 * @author Karray
 */
public class EspaceController implements Initializable {
    @FXML
    TableView<Espace> table;

    @FXML
    private TableColumn<Espace, String> nomcl;
    @FXML
    private TableColumn<Espace, String> adressecl;
    @FXML
    private TableColumn<Espace, String> mailcl;
    @FXML
    private TableColumn<Espace, Integer> NumCl;
    @FXML
    private TableColumn<Espace, String> facebookcl;
    @FXML
    private TableColumn<Espace, String> logocl;
    @FXML
    private TableColumn<Espace, String> typecl;
    @FXML
   // private TableColumn<Espace, String> idEspacecl;
    //@FXML
    private Button Deletebtn;
    @FXML
    private TextField idtf;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField adressetf;
    @FXML
    private TextField mailtf;
    @FXML
    private TextField numtel;
    @FXML
    private TextField logotf;
    @FXML
    private TextField typetf;
    @FXML
    private TextField facebooktf;
    @FXML
    private Button modifbtn;
    @FXML
    private Button ajoutbtn;
    @FXML
    private TextField searchtf;
//voir value de chaque ligne get value
    //faire un set de chaque ligne
  //  public Map<Integer, String> MapComboEspace;
    ///obervableMap
    private Map<Integer, String> clientVoitureLoue;
    final ObservableList<String> ComboType = FXCollections.observableArrayList();              //classes d'affichages JavaFX qui ont besoin d'être informées de tous les changements faits sur la liste
     ObservableList<String> ComboHeure_debut = FXCollections.observableArrayList();   
    final ObservableList<String> ComboHeure_fin = FXCollections.observableArrayList();
    //final 
    final ObservableList<String> ComboEspace = FXCollections.observableArrayList();
 //   final ObservableList<Integer> ComboEspace = FXCollections.observableArrayList();
    public ImageView imageView;
    public Image i;
    @FXML
    private Button chercherbtn;
    @FXML
    private VBox VboxInterfaceSuppModif;
    @FXML
    private VBox VboxInterfaceAjout;
    @FXML
    private ImageView ImLogo;
    @FXML
    private VBox VboxInterfaceAjoutCours;
    @FXML
    private Button addCoursbtn;
    @FXML
    private TableView<Cours> tableCours;
    @FXML
    private TableColumn<Cours, String> titreCourscl;
    @FXML
    private TableColumn<Cours, String> Hrdebutcl;
    @FXML
    private TableColumn<Cours, String> HrFincl;
    @FXML
    private TableColumn<Cours, Date> DateCourscl;
    private TextField idEspacetf;
    private TextField id_courstf;
    @FXML
    private VBox VboxInterfaceSuppModifCours;
    @FXML
    private TextField id_espacetf;
    @FXML
    private TextField Id_courstf;
    @FXML
    private TextField TitreCourstf;
    @FXML
    private DatePicker date_cours;
    private ComboBox<?> heure_debut_comboModif;
    private ComboBox<?> heure_fin_comboModif;
    @FXML
    private Button modifbtnCours;
    @FXML
    private Button DeletebtnCours;
    @FXML
    private TextField Heure_debutTF;
    @FXML
    private TextField heure_finTF;
    @FXML
    private ComboBox<String> combo_hr_debut_1 ;
    @FXML
    private ComboBox<String> combo_hr_fin_1;
    @FXML
    private Button GenerePDF;
    @FXML
    private Button Renitialiser;
   
    
    
   
    
   
    
    /**
     * Initializes the controller class.
     */
    
    
   
    
    
    
    
    
    @Override
    
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
        CrudEspace ser = new CrudEspace();
        CrudCours serCours = new CrudCours();
        RemplirComboBoxHeureFin();
         RemplirComboBoxHeureDebut();
        combo_hr_debut_1.setItems(ComboHeure_debut);
        combo_hr_fin_1.setItems(ComboHeure_fin);
        
        setCelluleValue();
        setCelluleValueTableCours();
       
     // idEspacetf.setOpacity(0);
     // Id_courstf.setOpacity(0);
      
        try {
            //serCours.displayAllObsCours();
            System.out.println(serCours.displayAllObsCours());
            tableCours.getItems().addAll(serCours.displayAllObsCours());
        } catch (SQLException ex) {
            
        }
        tableCours.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              
                    

    
                Cours SelectedCours = tableCours.getItems().get(tableCours.getSelectionModel().getSelectedIndex());
                combo_hr_debut_1.setItems(ComboHeure_debut);
        combo_hr_fin_1.setItems(ComboHeure_fin);
                id_espacetf.setText(""+SelectedCours.getId_espace());
                Id_courstf.setText(""+SelectedCours.getId_cours());
                TitreCourstf.setText(""+SelectedCours.getTitre());
                
                combo_hr_debut_1.setValue(SelectedCours.getHr_debut());
                 combo_hr_fin_1.setValue(SelectedCours.getHr_fin());
                 
                date_cours.setValue(SelectedCours.getDate_cours());
//                Heure_debutTF.setText(SelectedCours.getHr_debut());
      //          heure_finTF.setText(SelectedCours.getHr_fin());
               // ComboHeireDebut.//=setItems(ComboHeure_debut);
               
                
                
            }
        });
    
        try {
            
            table.getItems().addAll(ser.displayAllObs());
            
            } catch (SQLException ex) {
            
        }
           table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
              Espace SelectedEspace = table.getItems().get(table.getSelectionModel().getSelectedIndex());
              nomtf.setText(SelectedEspace.getNomEspace());
              adressetf.setText(SelectedEspace.getADRESSE_ES());
              mailtf.setText(SelectedEspace.getEMAIL_ES());
              
              logotf.setText(SelectedEspace.getLOGO_ES());
              i = new Image("file:"+SelectedEspace.getLOGO_ES() );
        ImLogo.imageProperty().set(i);
              typetf.setText(SelectedEspace.getTYPE_ES());
              idtf.setText(SelectedEspace.getID_ESPACESTR());
              //idtf.isCache();
              numtel.setText(SelectedEspace.getNUMTEL_ESStr());
              facebooktf.setText(SelectedEspace.getFACEBOOK_ES());
             // facebook
            }
        });
       
       
            
        
               
        
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        ObservableList<Espace> allEspaces,SelectedEspace;
        allEspaces=table.getItems();
        SelectedEspace=table.getSelectionModel().getSelectedItems();
        System.out.println(SelectedEspace);
        
        SelectedEspace.forEach(allEspaces::remove);
        int x = Integer.parseInt(idtf.getText());
         CrudEspace ser = new CrudEspace();
        /* if(nomtf.getText().isEmpty())
         {
             
             AlertBox.display("jkj", "jkjhkl");
         }*/
         ser.deleteST(x);
         clearFields();
        
        
        
        
        
    }
    
        @FXML
    private void modifierCours(ActionEvent event) throws SQLException {
            System.out.println("yuypop");
        //date_cours
      //   combo_hr_debut_1.setItems(ComboHeure_debut);
        //combo_hr_fin_1.setItems(ComboHeure_fin);
       if(( combo_hr_debut_1.getValue()!=null)&&(combo_hr_fin_1.getValue()!=null))
                         {
                     String x1 = (String)combo_hr_debut_1.getSelectionModel().getSelectedItem();
                     String x2 = (String)combo_hr_fin_1.getSelectionModel().getSelectedItem();
                     String heure_debutstr = x1.substring(0, 2);
                 String heure_fin_str = x2.substring(0, 2);
                  int HD=Integer.parseInt(heure_debutstr);
                     int HF=Integer.parseInt(heure_fin_str);
                     if(HD>=HF)
                         {
                             combo_hr_debut_1.setStyle("-fx-border-color : red ;");
                             combo_hr_fin_1.setStyle("-fx-border-color : red ;");
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Heures invalides");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                             
                         }
                     else
                     {
                         System.out.println(Date.valueOf(date_cours.getValue()));
 Cours c =new Cours(Integer.parseInt(Id_courstf.getText()), Integer.parseInt(id_espacetf.getText()),  TitreCourstf.getText(), ((String) combo_hr_debut_1.getSelectionModel().getSelectedItem()), ((String)combo_hr_fin_1.getSelectionModel().getSelectedItem()),date_cours.getValue());
           System.out.println(TitreCourstf.getText());         
//  Cours c= new Cours (Integer.parseInt(idtf.getText()),nomtf.getText(),adressetf.getText(),mailtf.getText(),Integer.parseInt(numtel.getText()),facebooktf.getText(),logotf.getText(),typetf.getText());
combo_hr_debut_1.setStyle("-fx-border-color : white ;");
                             combo_hr_fin_1.setStyle("-fx-border-color : white ;");      
CrudCours ser = new CrudCours();
      ser.updateST(c);
      refreshTableCours();
      clearFieldsCours();
                     }
                         
                   /*  else if(ValiderDateCours(date_cours)==false)
                         {
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Date invalide");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                         }*/
                         }
        
    /*   if((Heure_debutTF.getText()!=null)&&(heure_finTF.getText()!=null))
        {
           // if((ValiderHeureDFin(heure_finTF)==true) && (ValiderHeureDebut(Heure_debutTF))==true)
          //  {
                 String x1=Heure_debutTF.getText().substring(0, 2);
                 String x2=heure_finTF.getText().substring(0, 2);
                  int HD=Integer.parseInt(x1);
                     int HF=Integer.parseInt(x2);
                     if(HD>=HF)
                     {
                          Heure_debutTF.setStyle("-fx-border-color : red ;");
                             heure_finTF.setStyle("-fx-border-color : red ;");
                              Alert alert = new Alert(Alert.AlertType.WARNING);
                               alert.setContentText("Heures invalides");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                 //    }
            }
            else if((Heure_debutTF.getText()==null)||(heure_finTF.getText()==null))
            {
                 Heure_debutTF.setStyle("-fx-border-color : red ;");
                             heure_finTF.setStyle("-fx-border-color : red ;");
                              Alert alert = new Alert(Alert.AlertType.WARNING);
                               alert.setContentText("Heures invalides");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                
            }
            
        }
   /*    else
       {
            Cours c =new Cours(Integer.parseInt(Id_courstf.getText()), Integer.parseInt(id_espacetf.getText()),  TitreCourstf.getText(), Heure_debutTF.getText(), heure_finTF.getText(), Date.valueOf(date_cours.getValue()));
           System.out.println(TitreCourstf.getText());         
//  Cours c= new Cours (Integer.parseInt(idtf.getText()),nomtf.getText(),adressetf.getText(),mailtf.getText(),Integer.parseInt(numtel.getText()),facebooktf.getText(),logotf.getText(),typetf.getText());
      ServiceCours ser = new ServiceCours();
      ser.updateST(c);
    //  clearFields();
    //  refreshTable();
    //   }
       /* if((Heure_debutTF.getText()!=null)&&(heure_finTF.getText()!=null))
             
                         {
            if((ValiderHeureDFin(heure_finTF)==true) && (ValiderHeureDebut(Heure_debutTF)))
            {
                             //  String x1 = (String)heure_debut_combo.getSelectionModel().getSelectedItem();
                    // String x2 = (String)heure_fin_combo.getSelectionModel().getSelectedItem();
                    String x1=Heure_debutTF.getText().substring(0, 2);
                 String x2=heure_finTF.getText().substring(0, 2);
                  int HD=Integer.parseInt(x1);
                     int HF=Integer.parseInt(x2);
                     if(HD>=HF)
                         {
                             Heure_debutTF.setStyle("-fx-border-color : red ;");
                             heure_finTF.setStyle("-fx-border-color : red ;");
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Heures invalides");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                             
                         }
            }
        /*
                     else if(ValiderDateCours(date_cours)==false)
                         {
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Date invalide");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                         }
        */
        
    }

    @FXML
    private void supprimerCours(ActionEvent event) throws SQLException {
                ObservableList<Cours> allEspaces,SelectedEspace;
        allEspaces=tableCours.getItems();
        SelectedEspace=tableCours.getSelectionModel().getSelectedItems();
        System.out.println(SelectedEspace);
        
        SelectedEspace.forEach(allEspaces::remove);
        int x = Integer.parseInt(Id_courstf.getText());
         CrudCours ser = new CrudCours();
        /* if(nomtf.getText().isEmpty())
         {
             
             AlertBox.display("jkj", "jkjhkl");
         }*/
         ser.deleteST(x);
         clearFieldsCours();
        
        
        
        
        
    }
   
   

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
        Espace P= new Espace (Integer.parseInt(idtf.getText()),nomtf.getText(),adressetf.getText(),mailtf.getText(),Integer.parseInt(numtel.getText()),facebooktf.getText(),logotf.getText(),typetf.getText());
      CrudEspace ser = new CrudEspace();
      ser.updateST(P);
      clearFields();
      refreshTable();
     

    
    
    }
        @FXML
    private void ajouterCours(ActionEvent event) throws SQLException {
        CrudEspace ser = new CrudEspace();
        VboxInterfaceSuppModif.setVisible(false);
        VboxInterfaceAjoutCours.setVisible(true);
            Cours c = new Cours();
            ///////////////////////////////////faire soit nouvel interface ou faire le planning des cours pour chaque espace
            /////ajouter nouvel categorie pour les cours 
            /////////////faire le remplissage des combobox
            /////////////faire l alertes sur les cours
        Label HD_label = new Label();
        //copier
        ComboBox heure_debut_combo = new ComboBox(ComboHeure_debut);
        RemplirComboBoxHeureDebut();
        heure_debut_combo.setMaxHeight(30);
        HBox HeureDebutHBox = new HBox(7);
        HeureDebutHBox.setPadding(new Insets(5,5,5,5));
        HeureDebutHBox.getChildren().addAll(HD_label,heure_debut_combo); 
        
        Label HF_label = new Label();
        ComboBox heure_fin_combo = new ComboBox(ComboHeure_fin);
          
        RemplirComboBoxHeureFin();
        heure_debut_combo.setMaxHeight(30);
        HBox HeureFinHBox = new HBox(17);
        HeureFinHBox.setPadding(new Insets(5,5,5,5));
        HeureFinHBox.getChildren().addAll(HF_label,heure_fin_combo); 
      //  ObservableMap<Integer,ObservableValue> ComboMapespace=FXCollections.observableMap(ser.EspaceDonneeCombo());
       // ComboBox ComboEspaceJecrois = new ComboBox();
       /* for(String s :ComboMapespace.values())
        {
            
        }*/
     //   ComboBox EspaceCoursJcomboBox = new ComboBox();
        
        
        
           /* HashMap<Integer,String> map = ser.EspaceDonneeCombo();
        for(String s:map.values())
        {
            EspaceCoursJcomboBox.addItem(s);
        }
        */
        Label Date_cours_label = new Label();
        DatePicker date_cours_datePicker = new DatePicker();
        date_cours_datePicker.setPromptText("Date du cours");
        HBox date_picker = new HBox(18);
        date_picker.getChildren().addAll(Date_cours_label,date_cours_datePicker);
        
        Label Titrelabel = new Label();    
        TextField Titretf = new TextField();
        Titretf.setPromptText("Titre du cours:");
        HBox TitreHbox = new HBox(5);
        TitreHbox.setPadding(new Insets(5,5,5,5)); 
        TitreHbox.getChildren().addAll(Titrelabel,Titretf); 
        
       
        Label Espace_label = new Label();
        ComboBox EspaceComboCours = new ComboBox(ser.displayIDesp());
            System.out.println((String)EspaceComboCours.getSelectionModel().getSelectedItem());
       
        EspaceComboCours.valueProperty();
        EspaceComboCours.setMaxHeight(30);
        HBox EspaceCours = new HBox(7);
        EspaceCours.setPadding(new Insets(5,5,5,5));
        EspaceCours.getChildren().addAll(Espace_label,EspaceComboCours); 
        
        Button addCoursbtn = new Button("Ajouter");
        Button annulbtn = new Button("Annuler");
        HBox PannelBoutons = new HBox(7);
        PannelBoutons.setPadding(new Insets(5,5,5,5));
        PannelBoutons.getChildren().addAll(addCoursbtn,annulbtn); 
        
        
        
       /* Titretf.setPromptText("Espace :");
        HBox TitreHbox = new HBox(5);
        TitreHbox.setPadding(new Insets(5,5,5,5));
        TitreHbox.getChildren().addAll(Titrelabel,Titretf); */
        
        
        HD_label.setText("Heure debut:");
        HF_label.setText("Heure fin:");
        Titrelabel.setText("Titre cours:");
        Date_cours_label.setText("Date cours:");
        Espace_label.setText("Espace:");
        
        
        VboxInterfaceAjoutCours.setPadding(new Insets(10,10,10,10));
        VboxInterfaceAjoutCours.getChildren().addAll(HeureDebutHBox,HeureFinHBox,date_picker,TitreHbox,EspaceCours,PannelBoutons);
               annulbtn.setOnAction(e->{
                VboxInterfaceAjoutCours.setVisible(false);
                ComboHeure_debut.clear();
                ComboHeure_fin.clear();
                try {
                ser.displayIDesp().clear();
                } catch (SQLException ex) {
                
                }
                VboxInterfaceAjoutCours.getChildren().clear();
                VboxInterfaceSuppModif.setVisible(true);
                });
       
                 addCoursbtn.setOnAction(e->{
                     if((heure_debut_combo.getValue()!=null)&&(heure_fin_combo.getValue()!=null))
                         {
                     String x1 = (String)heure_debut_combo.getSelectionModel().getSelectedItem();
                     String x2 = (String)heure_fin_combo.getSelectionModel().getSelectedItem();
                     String heure_debutstr = x1.substring(0, 2);
                 String heure_fin_str = x2.substring(0, 2);
                  int HD=Integer.parseInt(heure_debutstr);
                     int HF=Integer.parseInt(heure_fin_str);
                     if(HD>=HF)
                         {
                             heure_debut_combo.setStyle("-fx-border-color : red ;");
                             heure_fin_combo.setStyle("-fx-border-color : red ;");
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Heures invalides");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                             
                         }
                     else if(ValiderDateCours(date_cours_datePicker)==false)
                         {
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Date invalide");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                         }
                   /*  else if((heure_debut_combo.getValue()==null)||(heure_fin_combo.getValue()==null)||(date_cours_datePicker.getValue()==null)||Titretf.getText().isEmpty())
                         {
                             
                         }*/
                     else
                         {
                             String x = (String)EspaceComboCours.getSelectionModel().getSelectedItem();
                 String ID = x.substring(1, 2);
                 String ID2 = x.substring(1, 3);

                  if(ValiderIDespace2(ID)==true)
                {
                    int id_espace=Integer.parseInt(ID);
                                      // int id_espace=Integer.parseInt(ID2); 
                    c.setId_espace(id_espace);
                    c.setHr_debut((String)heure_debut_combo.getSelectionModel().getSelectedItem());
                    c.setHr_fin((String)heure_fin_combo.getSelectionModel().getSelectedItem());
                     c.setDate_cours(date_cours_datePicker.getValue() );
                    c.setTitre(Titretf.getText());
                //    c.setDate_cours(date_cours_datePicker.get);
                               CrudCours crud = new CrudCours();
       
               try {
                   crud.insertPST(c);
               } catch (SQLException ex) {
                   
               }
               try {
                   refreshTable();
                   refreshTableCours();
               } catch (SQLException ex) {
                   
               }
                               VboxInterfaceAjoutCours.setVisible(false);
                ComboHeure_debut.clear();
                ComboHeure_fin.clear();
                try {
                ser.displayIDesp().clear();
                } catch (SQLException ex) {
                
                }
                VboxInterfaceAjoutCours.getChildren().clear();
                VboxInterfaceSuppModif.setVisible(true);
                    
                 
                    //System.out.println("Tu as terminer");
                 /*   System.out.println(x.substring(1, 2));
            System.out.println(Integer.parseInt(x.substring(1, 2)));*/
                }
            else if(ValiderIDespace2(ID2)==true)
                 {
                    int id_espace=Integer.parseInt(ID2); 
                    c.setId_espace(id_espace);
                    c.setHr_debut((String)heure_debut_combo.getSelectionModel().getSelectedItem());
                    c.setHr_fin((String)heure_fin_combo.getSelectionModel().getSelectedItem());
                     c.setDate_cours(date_cours_datePicker.getValue() );
                    c.setTitre(Titretf.getText());
                //    c.setDate_cours(date_cours_datePicker.get);
                               CrudCours crud = new CrudCours();
       
               try {
                   crud.insertPST(c);
               } catch (SQLException ex) {
                   
               }
               try {
                   refreshTableCours();
               } catch (SQLException ex) {
                   
               }
                    
                 }
                             
                         }
                         }
                     else if((heure_debut_combo.getValue()==null) ||   (heure_fin_combo.getValue()==null)||(Titretf.getText().isEmpty()) || (date_cours_datePicker.getValue()==null)||(EspaceComboCours.getValue()==null) )
                     {
                             heure_debut_combo.setStyle("-fx-border-color : red ;");
                             heure_fin_combo.setStyle("-fx-border-color : red ;");
                             Titretf.setStyle("-fx-border-color : red ;");
                             date_cours_datePicker.setStyle("-fx-border-color : red ;");
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Verifiez vos champs");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                             
                     }
                         
              /*   if((heure_debut_combo.getValue()!=null)&&(heure_fin_combo.getValue()!=null))
                         {
                  String x1 = (String)heure_debut_combo.getSelectionModel().getSelectedItem();
                  String x2 = (String)heure_fin_combo.getSelectionModel().getSelectedItem();
                  
                  
                 String ID1 = x1.substring(0, 2);
                 String ID2 = x2.substring(0, 2);
                     
                     int HD=Integer.parseInt(ID1);
                     int HF=Integer.parseInt(ID2);
                     
                     if(HD>=HF)
                         {
                              Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Heures invalides");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                         }
                         
                           
                         }
                 
                 else if((heure_debut_combo.getValue()==null)||(heure_fin_combo.getValue()==null))
                         {
                             
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                              // alert.setTitle("Champs vide");
                             alert.setContentText("Remplissez les champs");
                             alert.setWidth(45);
                       
                             alert.showAndWait(); 
                             System.out.println("Continue");
                         }*/
               //  else
               //  {
                         else{
                     String x = (String)EspaceComboCours.getSelectionModel().getSelectedItem();
                 String ID = x.substring(1, 2);
                 String ID2 = x.substring(1, 3);

                  if(ValiderIDespace2(ID)==true)
                {
                    int id_espace=Integer.parseInt(ID);
                                      // int id_espace=Integer.parseInt(ID2); 
                    c.setId_espace(id_espace);
                    c.setHr_debut((String)heure_debut_combo.getSelectionModel().getSelectedItem());
                    c.setHr_fin((String)heure_fin_combo.getSelectionModel().getSelectedItem());
                     c.setDate_cours(date_cours_datePicker.getValue());
                    c.setTitre(Titretf.getText());
                //    c.setDate_cours(date_cours_datePicker.get);
                               CrudCours crud = new CrudCours();
       
               try {
                   crud.insertPST(c);
               } catch (SQLException ex) {
                   
               }
               try {
                   refreshTable();
                   refreshTableCours();
               } catch (SQLException ex) {
                   
               }
                               VboxInterfaceAjoutCours.setVisible(false);
                ComboHeure_debut.clear();
                ComboHeure_fin.clear();
                try {
                ser.displayIDesp().clear();
                } catch (SQLException ex) {
                
                }
                VboxInterfaceAjoutCours.getChildren().clear();
                VboxInterfaceSuppModif.setVisible(true);
                    
                 
                    //System.out.println("Tu as terminer");
                 /*   System.out.println(x.substring(1, 2));
            System.out.println(Integer.parseInt(x.substring(1, 2)));*/
                }
            else if(ValiderIDespace2(ID2)==true)
                 {
                    int id_espace=Integer.parseInt(ID2); 
                    c.setId_espace(id_espace);
                    c.setHr_debut((String)heure_debut_combo.getSelectionModel().getSelectedItem());
                    c.setHr_fin((String)heure_fin_combo.getSelectionModel().getSelectedItem());
                     c.setDate_cours(date_cours_datePicker.getValue() );
                    c.setTitre(Titretf.getText());
                //    c.setDate_cours(date_cours_datePicker.get);
                               CrudCours crud = new CrudCours();
       
               try {
                   crud.insertPST(c);
               } catch (SQLException ex) {
                   
               }
               try {
                   refreshTable();
               } catch (SQLException ex) {
                   
               }
                    
                 }
            
            
                     }
                // }
        //  System.out.println(Integer.parseInt(x.substring(1, 2)));
   //     System.out.println((String)EspaceComboCours.getSelectionModel().getSelectedItem());
        });
        
        
       
  /*     annulbtn.setOnAction(e->{
            VboxInterfaceAjout.setVisible(false);
   //     TypeC 
   ComboType.clear();
          VboxInterfaceAjout.getChildren().clear();
           VboxInterfaceSuppModif.setVisible(true);
       });*/
       
     //   VboxInterfaceAjout.getChildren().addAll(ch1,ch2,ch3,imageView,ch4,ch5,ch6,ch7,ch8);
        //faire le combobox
            
            
            
            
            
            
    }
    
        @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        VboxInterfaceSuppModif.setVisible(false);
        
         VboxInterfaceAjout.setVisible(true);
        Espace p = new Espace();
        
         Label Nomlabel = new Label();    
        TextField nomtf = new TextField();
        nomtf.setPromptText("Nom espace");
        HBox ch1 = new HBox(5);
        ch1.setPadding(new Insets(5,5,5,5));
        ch1.getChildren().addAll(Nomlabel,nomtf); 
        
        
        Label Adresselabel = new Label();
        TextField Adressetf = new TextField();
        Adressetf.setPromptText("Adresse");
        HBox ch2 = new HBox(34);
        ch2.setPadding(new Insets(5,5,5,5));
        ch2.getChildren().addAll(Adresselabel,Adressetf); 
        
        Label Maillabel = new Label();
        TextField Mailtf = new TextField();
        Mailtf.setPromptText("E-mail");
        HBox ch3 = new HBox(44);
        ch3.setPadding(new Insets(5,5,5,5));
        ch3.getChildren().addAll(Maillabel,Mailtf); 
        
        Button Uploadbtn = new Button("Upload");
         Label Logolabel = new Label();
         TextField Logotf = new TextField();
         Logotf.setOpacity(0);
       // TextField Logotf = new TextField();
        Logotf.setMaxWidth(5);
     //   Logotf.setPromptText("Logo");
        imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        Uploadbtn.setOnAction(e->{ 
            //**************************************************IMAGE
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir l'affiche");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String pathh = selectedFile.getAbsolutePath();
        
        
        if (pathh.equals("")) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Veuilez selectionner une affiche !!");
            a.showAndWait();
        }
        else
        {
            Logotf.setText(pathh);
        }
       // Image img = new Image ("file:"+selectedFile.getAbsolutePath());
       i = new Image("file:"+selectedFile.getAbsolutePath());
        imageView.imageProperty().set(i);
        
        
        });
        
       
         
        HBox ch4 = new HBox(54);
         HBox upload = new HBox(5);
         upload.getChildren().addAll(Uploadbtn);
        ch4.setPadding(new Insets(1,1,1,1));
        
        ch4.getChildren().addAll(Logolabel,upload,Logotf); 
        
        
        Label Typelabel = new Label();
        ComboBox TypeC = new ComboBox(ComboType);
        RemplirComboBox();
        TypeC.setMaxHeight(30);
       
        HBox ch5 = new HBox(5);
        ch5.setPadding(new Insets(5,5,5,5));
        ch5.getChildren().addAll(Typelabel,TypeC); 
        
       
      // TextField Typetf = new TextField();
      //  Typetf.setPromptText("Type");
      /*  HBox ch5 = new HBox(5);
        ch5.setPadding(new Insets(5,5,5,5));
        ch5.getChildren().addAll(Typelabel,Typetf); */
        
        Label Facebooklabel = new Label();
        TextField Facebooktf = new TextField();
        Facebooktf.setPromptText("Facebook");
        HBox ch6 = new HBox(24);
        ch6.setPadding(new Insets(5,5,5,5));
        ch6.getChildren().addAll(Facebooklabel,Facebooktf); 
        
        Label Numlabel = new Label();
        TextField Numtf = new TextField();
        Numtf.setPromptText("Numero de tel°");
        HBox ch7 = new HBox(7);
        ch7.setPadding(new Insets(5,5,5,5));
        ch7.getChildren().addAll(Numlabel,Numtf); 
        
       Button addbtn = new Button("Ajouter");
         Button annulbtn = new Button("Annuler");
          //#2471A3
         addbtn.setTextFill(Paint.valueOf("#ffffff"));
       //  addbtn.setStyle("#2471A3");
         addbtn.setStyle("fx-background-color:#ffffff");
         
         HBox ch8 = new HBox(7);
        ch8.setPadding(new Insets(5,5,5,5));
        ch8.getChildren().addAll(addbtn,annulbtn); 
        
        Nomlabel.setText("Nom espace:");
        Adresselabel.setText("Adresse:");
        Maillabel.setText("E-mail:");
        Logolabel.setText("Logo:");
        Typelabel.setText("Type espace:");
        Facebooklabel.setText("Facebook:");
        Numlabel.setText("Numero de tel°:");
        
     //  VBox layout = new VBox(10);
        VboxInterfaceAjout.setPadding(new Insets(10,10,10,10));
         
       VboxInterfaceAjout.getChildren().addAll(ch1,ch2,ch3,imageView,ch4,ch5,ch6,ch7,ch8);
       
       annulbtn.setOnAction(e->{
            VboxInterfaceAjout.setVisible(false);
   //     TypeC 
   ComboType.clear();
          VboxInterfaceAjout.getChildren().clear();
           VboxInterfaceSuppModif.setVisible(true);
       });
       
        
       addbtn.setOnAction(e ->{
           if((nomtf.getText().isEmpty())|| (Adressetf.getText().isEmpty()) || (Numtf.getText().isEmpty()) || (TypeC.getValue()==null) || (Facebooktf.getText().isEmpty()) ||(Mailtf.getText().isEmpty()))
           {
               nomtf.setStyle("-fx-border-color : red ;");
               Adressetf.setStyle("-fx-border-color : red ;");
               TypeC.setStyle("-fx-border-color : red ;");
               Facebooktf.setStyle("-fx-border-color : red ;");
               Mailtf.setStyle("-fx-border-color : red ;");
               Numtf.setStyle("-fx-border-color : red ;");
               
               //nomtf.setStyle("-fx-background-color : red ;");
               Alert alert = new Alert(Alert.AlertType.WARNING);
               // alert.setTitle("Champs vide");
               alert.setContentText("Remplissez les champ invalides");
               alert.setWidth(45);
                       
               alert.showAndWait();
           }
           else if(Adressetf.getText().isEmpty())
           {
               Adressetf.setStyle("-fx-border-color : red ;");
               Alert alert = new Alert(Alert.AlertType.WARNING);
              // alert.setTitle("Champs vide");
              alert.setContentText("Remplissez le champ adresse");
               alert.setWidth(45);
                       
               alert.showAndWait();
               
           }
           else if(ValiderNum(Numtf)==false)
           {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              // alert.setTitle("Champs vide");
              alert.setContentText("Numero invalide");
               alert.setWidth(45);
                       
               alert.showAndWait(); 
           }
           else if(ValiderEmail(Mailtf)==false)
           {
                Alert alert = new Alert(Alert.AlertType.WARNING);
              // alert.setTitle("Champs vide");
              alert.setContentText("Adresse mail invalide");
               alert.setWidth(45);
                       
               alert.showAndWait(); 
               
           }
           
         
           
                   
           else{
           String aString = Numtf.getText(); 
        int x = Integer.parseInt(aString); 
        
        p.setNomEspace(nomtf.getText());
        p.setADRESSE_ES(Adressetf.getText());
        p.setEMAIL_ES(Mailtf.getText());
        p.setFACEBOOK_ES(Facebooktf.getText());
        p.setLOGO_ES(Logotf.getText() );
        p.setTYPE_ES((String)TypeC.getSelectionModel().getSelectedItem());///Comtype recupere le champ de combo
               System.out.println( TypeC.getSelectionModel().getSelectedItem().hashCode());
      TypeC.getSelectionModel().getSelectedItem().toString();
        p.setNUMTEL_ES(x);
        
        VboxInterfaceAjout.setVisible(false);
   //     TypeC 
   ComboType.clear();
          VboxInterfaceAjout.getChildren().clear();
           VboxInterfaceSuppModif.setVisible(true);
           CrudEspace crud = new CrudEspace();
       
               try {
                   crud.insertPST(p);
               } catch (SQLException ex) {
                   
               }
               try {
                   refreshTable();
               } catch (SQLException ex) {
                   
               }
                  }
           //return p;
       });
          
        /*  addbtn.setOnAction(e ->{
          
          });*/
        
      /*  PannelSuppModif.setVisible(false);
        
        Label Adresselabel = new Label();
        TextField Adressetf = new TextField();
        Adressetf.setPromptText("Adresse");
        HBox ch2 = new HBox(34);
        ch2.setPadding(new Insets(5,5,5,5));
        ch2.getChildren().addAll(Adresselabel,Adressetf); 
        
         VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
         Button addbtn = new Button("Ajouter");
          layout.getChildren().addAll(ch2,addbtn);
       // PannelBoutons.setVisible(false);*/
     /*   AjoutInterface AI = new AjoutInterface();
        
       
        ServiceEspace crud = new ServiceEspace();
       
        crud.insertPST(AI.DisplayInterface());
        refreshTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Espace ajoutée avec succés");
        alert.showAndWait();
        clearFields();*/
        
      /*  try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAddPerson.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
              
    } catch(Exception e) {
       e.printStackTrace();
      }
        String aString = numtel.getText(); 
        int x = Integer.parseInt(aString); 
       
        Espace p = new Espace(nomtf.getText() ,adressetf.getText() , mailtf.getText() , x ,facebooktf.getText() , logotf.getText(),  typetf.getText());
        
        ServiceEspace crud = new ServiceEspace();
       
        crud.insertPST(p);
        refreshTable();
        clearFields();

     */
        
    }
    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        CrudEspace crud = new CrudEspace();
        CrudCours crud2 = new CrudCours();
        crud.SearchByNom(searchtf.getText());
      //  crud.SearchByType();
         setCelluleValue();
         System.out.println(crud.SearchByNom(searchtf.getText()));
         table.setItems(crud.SearchByNom(searchtf.getText()));
          table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
              Espace SelectedEspace = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                try {
                    crud2.SearchById(SelectedEspace.getID_ESPACE());
                    setCelluleValueTableCours();
                    System.out.println(crud2.SearchById(SelectedEspace.getID_ESPACE()));
                    tableCours.setItems(crud2.SearchById(SelectedEspace.getID_ESPACE()));
                    // facebook
                } catch (SQLException ex) {
                    
                }
            }
        });
        // refreshTableSearch();
      
      
    
        try {
            table.getItems().addAll(crud.SearchByType(searchtf.getText()));
            
            } catch (SQLException ex) {
            
        }
        
        
        
    }
    
     public void RemplirComboBox()//rendre la methode qui recupére de la table type(nouvel table)
    {
        ComboType.add("Salle de sport");
       // ComboType.set(1, "Salle de sport");
        //System.out.println(ComboType.getSelectionModel().getSelectedItem());
        ComboType.add("Salle de beauté");
        ComboType.add("Spa");
        ComboType.add("Centre de therapie");
        
        
        
    }
     public void RemplirComboBoxHeureDebut()//rendre la methode qui recupére de la table type(nouvel table)
    {
        ComboHeure_debut.add("08:00");
        ComboHeure_debut.add("09:00");
        ComboHeure_debut.add("10:00");
        ComboHeure_debut.add("11:00");
        ComboHeure_debut.add("12:00");
        ComboHeure_debut.add("13:00");
        ComboHeure_debut.add("14:00");
        ComboHeure_debut.add("15:00");
        ComboHeure_debut.add("16:00");
        ComboHeure_debut.add("17:00");
        ComboHeure_debut.add("18:00");
        ComboHeure_debut.add("19:00");
        ComboHeure_debut.add("20:00");
        ComboHeure_debut.add("21:00");
        ComboHeure_debut.add("22:00");
       
        
        
        
        
    } 
         public boolean ValiderDateCours(DatePicker d)
    {
        Calendar now = Calendar.getInstance();
        int yearNow=now.get(Calendar.YEAR);
        int yeard=d.getValue().getYear();
        int monthNow=now.get(Calendar.MONTH);
        int monthd=d.getValue().getMonthValue();
        int dayNow=now.get(Calendar.DAY_OF_MONTH);
        int dayd=d.getValue().getDayOfMonth();
        if(yearNow<yeard ) //&& monthNow>=monthd &&dayNow>=dayd
        {
            //faire archivage
            return true;
        }
        else if((yearNow==yeard)&&(monthNow<monthd))
        {
            return true;
        }
        else if((yearNow==yeard)&&(monthNow==monthd)&&(dayNow<dayd))
        {
            return true;
        } 
        else if((yearNow==yeard)&&(monthNow==monthd)&&(dayNow==dayd))
        {
            return true;
        }   

        
       
        return false;
    }
          public void RemplirComboBoxHeureFin()//rendre la methode qui recupére de la table type(nouvel table)
    {
        ComboHeure_fin.add("09:00");
        ComboHeure_fin.add("10:00");
        ComboHeure_fin.add("11:00");
        ComboHeure_fin.add("12:00");
        ComboHeure_fin.add("13:00");
        ComboHeure_fin.add("14:00");
        ComboHeure_fin.add("15:00");
        ComboHeure_fin.add("16:00");
        ComboHeure_fin.add("17:00");
        ComboHeure_fin.add("18:00");
        ComboHeure_fin.add("19:00");
        ComboHeure_fin.add("20:00");
        ComboHeure_fin.add("21:00");
        ComboHeure_fin.add("22:00");     
    }  
    public void RemplirComboBoxEspace()//rendre la methode qui recupére de la table type(nouvel table)
    {
        
       
        ComboHeure_fin.add("09:00");
        ComboHeure_fin.add("10:00");
        ComboHeure_fin.add("11:00");
        ComboHeure_fin.add("12:00");
        ComboHeure_fin.add("13:00");
        ComboHeure_fin.add("14:00");
        ComboHeure_fin.add("15:00");
        ComboHeure_fin.add("16:00");
        ComboHeure_fin.add("17:00");
        ComboHeure_fin.add("18:00");
        ComboHeure_fin.add("19:00");
        ComboHeure_fin.add("20:00");
        ComboHeure_fin.add("21:00");
        ComboHeure_fin.add("22:00");     
    }  
    public int ValiderHeure(String X)
    {
        if(X!=null)
        {
            int HD=Integer.parseInt(X);
            return HD;
            
        }
      return 0;  
    }
    
    public boolean ValiderEmail(TextField tx)
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(tx.getText());
        if(m.find() && m.group().equals(tx.getText()))
        {
            return true;
        }
        return false;
    }
public boolean ValiderIDespace2(String ID)
        {
            Pattern p =Pattern.compile("[0-9]");
            Pattern p1 =Pattern.compile("[0-9]{2}");
             Matcher m=p.matcher(ID);
             Matcher m1=p1.matcher(ID);
             
                 if(m.find() && m.group().equals(ID))
                 {
                     return true;
                    /* System.out.println(Integer.parseInt(ID));
                     System.out.println(ID);*/
                 }
                 else if(m1.find() && m1.group().equals(ID))
                 {
                     return true;
                    /* System.out.println(Integer.parseInt(ID));
                     System.out.println(ID);*/
                 }
                 return false;
            
        }
    public boolean ValiderNum(TextField tx)
    {
        Pattern p = Pattern.compile("(2|5|7|9)?[0-9]{7}");
        Matcher m = p.matcher(tx.getText());
        if(m.find() && m.group().equals(tx.getText()))
        {
            return true;
        }
        return false;
        
    }
        public boolean ValiderHeureDebut(TextField tx)
    {
        Pattern p = Pattern.compile("[8-21]");
        Matcher m = p.matcher(tx.getText());
        if(m.find() && m.group().equals(tx.getText()))
        {
            return true;
        }
        return false;
        
    }
                public boolean ValiderHeureDFin(TextField tx)
    {
        Pattern p = Pattern.compile("[9-22][:][00]");
        Matcher m = p.matcher(tx.getText());
        if(m.find() && m.group().equals(tx.getText()))
        {
            return true;
        }
        return false;
        
    }
    
    
    
    
    public void refreshTable() throws SQLException
    {
        CrudEspace ser = new CrudEspace();
        ser.displayAllObs().clear();
     table.setItems(ser.displayAllObs());
    }
        public void refreshTableCours() throws SQLException
    {
        CrudCours ser = new CrudCours();
        ser.displayAllObsCours().clear();
     tableCours.setItems(ser.displayAllObsCours());
    }
    public void refreshTableSearch() throws SQLException
    {
       CrudEspace ser = new CrudEspace();
       ser.displayAllObs().clear();
       ser.SearchByNom(searchtf.getText()).clear();
       table.setItems(ser.SearchByType(searchtf.getText()));
          
    }
        public void refreshTableSearchCours() throws SQLException
    {
       CrudEspace ser = new CrudEspace();
       ser.displayAllObs().clear();
       ser.SearchByType(typetf.getText()).clear();
       table.setItems(ser.SearchByType(typetf.getText()));
          
    }
    public void clearFields()
    {
        idtf.clear();
          nomtf.clear();
         adressetf.clear();
         mailtf.clear();
         logotf.clear();
        typetf.clear();
        numtel.clear();
        facebooktf.clear();
        ImLogo.imageProperty().set(null);
        
    }
     public void clearFieldsCours()
    {
       // Id_courstf.getText()), Integer.parseInt(id_espacetf.getText()),  TitreCourstf.getText(), Heure_debutTF.getText(), heure_finTF.getText(),date_cours.getValue()
        Id_courstf.clear();
         id_espacetf.clear();
         TitreCourstf.clear();
         combo_hr_debut_1.setValue(null); 
         combo_hr_fin_1.setValue(null);
   
       // Heure_debutTF.clear();
       //  heure_finTF.clear();
      date_cours.setValue(null);
        numtel.clear();
        facebooktf.clear();
        ImLogo.imageProperty().set(null);
        
    }

   public void setCelluleValue()
   {
       nomcl.setCellValueFactory(new PropertyValueFactory<>("nomEspace"));

     // idEspacecl.setCellValueFactory(new PropertyValueFactory<>("ADRESSE_ES"));
     adressecl.setCellValueFactory(new PropertyValueFactory<>("ADRESSE_ES"));
      mailcl.setCellValueFactory(new PropertyValueFactory<>("ADRESSE_ES"));

      NumCl.setCellValueFactory(new PropertyValueFactory<>("NUMTEL_ES"));
      facebookcl.setCellValueFactory(new PropertyValueFactory<>("FACEBOOK_ES"));
      logocl.setCellValueFactory(new PropertyValueFactory<>("LOGO_ES"));
      typecl.setCellValueFactory(new PropertyValueFactory<>("TYPE_ES"));
      
       
   }
      public void setCelluleValueTableCours()
   {
      // nomcl.setCellValueFactory(new PropertyValueFactory<>("nomEspace"));

     // idEspacecl.setCellValueFactory(new PropertyValueFactory<>("ADRESSE_ES"));
     titreCourscl.setCellValueFactory(new PropertyValueFactory<>("titre"));
     Hrdebutcl.setCellValueFactory(new PropertyValueFactory<>("hr_debut"));

      HrFincl.setCellValueFactory(new PropertyValueFactory<>("hr_fin"));
      DateCourscl.setCellValueFactory(new PropertyValueFactory<>("date_cours"));
    //  logocl.setCellValueFactory(new PropertyValueFactory<>("LOGO_ES"));
    //  typecl.setCellValueFactory(new PropertyValueFactory<>("TYPE_ES"));
      
       
   }
 /*@FXML
    private TableColumn<Cours, String> nomEspaceCourscl;
    @FXML
    private TableColumn<Cours, String> titreCourscl;
    @FXML
    private TableColumn<Cours, String> Hrdebutcl;
    @FXML
    private TableColumn<Cours, String> HrFincl;
    @FXML
    private TableColumn<Cours, Date> DateCourscl;

*/

    @FXML
    private void GenererPDF(ActionEvent event) throws FileNotFoundException, DocumentException, IOException, SQLException {
   /*  Document document = new Document();
    
      PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ELYES\\Desktop\\Liste_espace.pdf"));
      document.open();
      document.add(new Paragraph("Hello World"));
 
   
    document.close();*/
   CrudEspace crud = new CrudEspace();
    PdfPTable table = new PdfPTable(6);
   for(Espace v : crud.displayAll())
    {
        
      
      //On créer l'objet cellule.
      PdfPCell cell;
      
      cell = new PdfPCell(new Phrase(v.getNomEspace(),FontFactory.getFont("Comic Sans MS",12)));
      cell.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell);
      PdfPCell cell1;
      
      cell1 = new PdfPCell(new Phrase(v.getADRESSE_ES(),FontFactory.getFont("Comic Sans MS",12)));
      cell1.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell1.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell1);
            PdfPCell cell2;
      
      cell2 = new PdfPCell(new Phrase(v.getEMAIL_ES(),FontFactory.getFont("Comic Sans MS",12)));
      cell2.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell2.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell2);
            
      PdfPCell cell3;
      
      cell3 = new PdfPCell(new Phrase(v.getFACEBOOK_ES(),FontFactory.getFont("Comic Sans MS",12)));
      cell3.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell3.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell3);
            PdfPCell cell4;
      
      cell4 = new PdfPCell(new Phrase((""+v.getNUMTEL_ES()),FontFactory.getFont("Comic Sans MS",12)));
      cell4.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell4.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell4);
                  PdfPCell cell5;
      
      cell5 = new PdfPCell(new Phrase("Logo",FontFactory.getFont("Comic Sans MS",12)));
      cell5.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell5.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell5);
        System.out.println(v);
    }
          Document doc =new Document();
        
          PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\ELYES\\Desktop\\Liste_espace.pdf"));
          doc.open();
          doc.add(new Paragraph("Liste des espaces"));
          doc.add(table);
         // PdfPTable table = new PdfPTable(6);
         // table.setWidthPercentage(100);
         
             doc.close();
             Desktop.getDesktop().open(new File("C:\\Users\\ELYES\\Desktop\\Liste_espace.pdf"));
    }
    public static PdfPTable premierTableau()
  {
      //On créer un objet table dans lequel on intialise ça taille.
      PdfPTable table = new PdfPTable(6);
      
      //On créer l'objet cellule.
      PdfPCell cell;
      
      cell = new PdfPCell(new Phrase("Nom espace",FontFactory.getFont("Comic Sans MS",12)));
      cell.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell);
      PdfPCell cell1;
      
      cell1 = new PdfPCell(new Phrase("Adresse espace",FontFactory.getFont("Comic Sans MS",12)));
      cell1.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell1.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell1);
            PdfPCell cell2;
      
      cell2 = new PdfPCell(new Phrase("Email",FontFactory.getFont("Comic Sans MS",12)));
      cell2.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell2.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell2);
            
      PdfPCell cell3;
      
      cell3 = new PdfPCell(new Phrase("Facebook",FontFactory.getFont("Comic Sans MS",12)));
      cell3.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell3.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell3);
            PdfPCell cell4;
      
      cell4 = new PdfPCell(new Phrase("Numero tel°",FontFactory.getFont("Comic Sans MS",12)));
      cell4.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell4.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell4);
                  PdfPCell cell5;
      
      cell5 = new PdfPCell(new Phrase("Logo",FontFactory.getFont("Comic Sans MS",12)));
      cell5.setHorizontalAlignment(Element.ALIGN_BASELINE);
      cell5.setBackgroundColor(BaseColor.GRAY);
    
      table.addCell(cell5);
 
/*     cell = new PdfPCell(new Phrase("Fusion de 2 cellules de la première colonne"));
      cell.setRowspan(2);
      table.addCell(cell);
 
      //contenu du tableau.
      table.addCell("Colonne 1; Cellule 1");
      table.addCell("Colonne 1; Cellule 2");
      table.addCell("Colonne 2; Cellule 1");
      table.addCell("Colonne 2; Cellule 2");*/
      
      return table;  
  }

    @FXML
    private void Renitialiser(ActionEvent event) throws SQLException {
        searchtf.clear();
        refreshTableCours();
        refreshTable();
        
    }





    

    

    

    
    
    
}
