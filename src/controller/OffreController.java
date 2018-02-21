/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import Services.CrudOffre;
import entities.Offre;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nidhal Bougatf
 */
public class OffreController implements Initializable {
    
    

    @FXML
    private javafx.scene.control.Button btnappliquer;
    @FXML
    private javafx.scene.control.Button reinitialiser;
    @FXML
    private javafx.scene.control.Button annuler;
    @FXML
    private javafx.scene.control.Button enregistrer;
    @FXML
    private ComboBox<String> nomespace;
    
    private ObservableList<String> myespaces=FXCollections
            .observableArrayList() ;
    private ObservableList<String> myespacess=FXCollections
            .observableArrayList() ;
    
    private int idHolder;
    @FXML
    private javafx.scene.control.TextField titre;
    @FXML
    private javafx.scene.control.TextField pathaffiche;
    @FXML
    private javafx.scene.control.TextArea description;
    @FXML
    private Slider ancienprix;


    @FXML
    private Slider nouveauprix;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
   
    @FXML
    private javafx.scene.control.Button btnupload;

    
    ObservableList<String> myData = FXCollections
            .observableArrayList("Promotion", "Offre");
    
    
    @FXML
    private javafx.scene.control.Button btnajouter;
    @FXML
    private javafx.scene.control.Button btnmodifier;
    @FXML
    private javafx.scene.control.Button btnsupprimer;
    @FXML
    private TableView<Offre> tableoffre;

    
    @FXML
    private javafx.scene.control.TextField titreoffrerech;
    @FXML
    private javafx.scene.control.Button btnchercher;
    @FXML
    private ComboBox<String> nomespacerech;
    @FXML
    private ComboBox<String> disponibilite;

    @FXML
    private Slider prixminrech;
    @FXML
    private ImageView viewaffiche;
    @FXML
    private Slider prixmaxrech;
    
    private ObservableList<String> mydispo=FXCollections
            .observableArrayList("Tous","Disponible","Epuise") ;
    
    private ObservableList<Offre> list_promotion= FXCollections.observableArrayList();
    
    boolean selected=false;
    
        TableColumn _nomesp = new TableColumn("Nom espace");
        TableColumn _titre = new TableColumn("Titre");
        TableColumn _description = new TableColumn("Description");
        TableColumn _ancprix = new TableColumn("Ancien prix");
        TableColumn _nvprix = new TableColumn("Nouveau prix");
        TableColumn _startingDate = new TableColumn("Date debur");
        TableColumn _endingDate = new TableColumn("Date fin");
        TableColumn _affiche = new TableColumn("Affiche");
        TableColumn _test = new TableColumn("TEST");//********************************************************
        
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        annuler.setVisible(false);
        enregistrer.setVisible(false);

            ancienprix.setMin(0);
            ancienprix.setMax(500);
            ancienprix.setValue(250);
            ancienprix.setShowTickLabels(true);
            ancienprix.setShowTickMarks(true);
            ancienprix.setMajorTickUnit(50);
            ancienprix.setMinorTickCount(5);
            ancienprix.setBlockIncrement(5);
            
            nouveauprix.setMin(0);
            nouveauprix.setMax(500);
            nouveauprix.setValue(250);
            nouveauprix.setShowTickLabels(true);
            nouveauprix.setShowTickMarks(true);
            nouveauprix.setMajorTickUnit(50);
            nouveauprix.setMinorTickCount(5);
            nouveauprix.setBlockIncrement(5);
            
            prixminrech.setMin(0);
            prixminrech.setMax(500);
            prixminrech.setValue(0);
            prixminrech.setShowTickLabels(true);
            prixminrech.setShowTickMarks(true);
            prixminrech.setMajorTickUnit(50);
            prixminrech.setMinorTickCount(5);
            prixminrech.setBlockIncrement(5);
            
            prixmaxrech.setMin(0);
            prixmaxrech.setMax(500);
            prixmaxrech.setValue(0);
            prixmaxrech.setShowTickLabels(true);
            prixmaxrech.setShowTickMarks(true);
            prixmaxrech.setMajorTickUnit(50);
            prixmaxrech.setMinorTickCount(5);
            prixmaxrech.setBlockIncrement(5);

            
            buildNomEspace();
            nomespace.setValue("Select Espace");
            nomespace.setItems(myespaces);
            
            
            nomespacerech.setValue("Tous");
            nomespacerech.setItems(myespaces);
            
            disponibilite.setValue("Tous");
            disponibilite.setItems(mydispo);
            
            
            _nomesp.setCellValueFactory(new PropertyValueFactory<>("nom_es"));
            _titre.setCellValueFactory(new PropertyValueFactory<>("TITRE_O"));
            _description.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTION_O"));
            _ancprix.setCellValueFactory(new PropertyValueFactory<>("ANCIEN_PRIX"));
            _nvprix.setCellValueFactory(new PropertyValueFactory<>("NOUVEAU_PRIX"));
            _startingDate.setCellValueFactory(new PropertyValueFactory<>("DATEDEBUT_O"));
            _endingDate.setCellValueFactory(new PropertyValueFactory<>("DATEFIN_O"));
            _affiche.setCellValueFactory(new PropertyValueFactory<>("AFFICHE_O"));
            tableoffre.getColumns().addAll(_nomesp,_titre,_description,_ancprix,_nvprix,_startingDate,_endingDate,_affiche);
            buildOffreTable();
            
            
            tableoffre.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Offre> observable,Offre oldValue,Offre newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    getPromotionInfo(newValue.getID_OFFRE());
                });
       
    }    

     private void buildOffreTable() 
     {
            CrudOffre co = new CrudOffre();     
        try 
        {
            tableoffre.setItems(co.displayAllOffre());
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(OffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        selected=false;
    }
     
     
     
     
      public void buildNomEspace()
    {
         CrudOffre co = new CrudOffre();
        try {
            
             myespaces =FXCollections.observableArrayList(co.getAllEspace());
             
            
        } catch (SQLException ex) {
            
        }
    }
      
     
    @FXML
    private void ajouter(ActionEvent event) throws IOException, SQLException 
    {

        if(testsaisie())
        {
            
            buildOffreTable(); 
            String nomesp = nomespace.getValue() ; 
            String titr = titre.getText() ;
            String desc = description.getText();
            Double ancprix=ancienprix.getValue();
            Double nvprix=nouveauprix.getValue();
            LocalDate datedeb=datedebut.getValue();
            LocalDate datef=datefin.getValue();
            String aff=pathaffiche.getText();
            CrudOffre co = new CrudOffre();
            Offre f = new Offre(nomesp,titr,desc,ancprix,nvprix,datedeb,datef,aff);
            co.InsertOffre(f);
            Alert a = new Alert(Alert.AlertType.INFORMATION) ; 
            a.setContentText("Offre ajouté avec succés !!");
            a.showAndWait();
            buildOffreTable(); 
            clearsaisie();
        }
        

    }
    public void clearsaisie()
    {
            
            titre.clear() ;
            description.clear();
            ancienprix.setValue(250);
            nouveauprix.setValue(250);         
            datedebut.setValue(null);
            datefin.setValue(null);
            pathaffiche.clear();

            nomespace.setValue("Select Espace");
            nomespace.setItems(myespaces);
            
            nomespacerech.setValue("Tous");
            nomespacerech.setItems(myespaces);
            viewaffiche.setImage(null);
    }
    
    public boolean testsaisie()
    {
         String nomesp = nomespace.getValue() ; 
            String titr = titre.getText() ;
            String desc = description.getText();
            Double ancprix=ancienprix.getValue();
            Double nvprix=nouveauprix.getValue();
            LocalDate datedeb=datedebut.getValue();
            LocalDate datef=datefin.getValue();
            String aff=pathaffiche.getText();

        boolean res=false;
        
            if ( nomesp.equals("Select Espace") )
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez selctioner le nom de l'espace ! ");
                a.showAndWait();
            }
            else if ( titr.equals("") )
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter un titre ! ");
                a.showAndWait();
            }
            else if (desc.equals(""))
             {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter une description ! ");
                a.showAndWait();
            } 
            else if(nvprix>=ancprix)
            {
                 Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Le nouveau prix doit etre inferieur à l'ancien prix ! ");
                a.showAndWait();  
            }
            else if(datedeb==null)
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter la date de debut ! ");
                a.showAndWait(); 
            }
            else if(datef== null)
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter la date de fin ! ");
                a.showAndWait(); 
            }
            else if(datef.compareTo(datedeb) <=0)
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez verifier les dates ! ");
                a.showAndWait(); 
            }
            else if (aff.equals(""))
             {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez selectionner une affiche ! ");
                a.showAndWait();
            }   
            else
                res=true;          
        
        
        return res;
    }
    
    @FXML
    public void upload(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir l'affiche");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
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
            pathaffiche.setText(pathh);
        }
        Image img = new Image ("file:"+selectedFile.getAbsolutePath()) ;
        viewaffiche.imageProperty().set(img);
        

    }
    
    public void chercher(ActionEvent event) throws IOException, SQLException 
    {
        
        if ("".equals(titreoffrerech.getText())) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Veuillez saisir un titre ! ");
            a.showAndWait();
            
        }
        else
        {
            String titr = titreoffrerech.getText() ;          
            CrudOffre co = new CrudOffre();

        list_promotion = FXCollections.observableArrayList();
        try {
            co.searchOffreByTitre(titr).forEach(e -> { list_promotion.add(e);});
        } catch (SQLException ex) {
            
        }
      
        tableoffre.getItems().clear();
        tableoffre.getItems().addAll(list_promotion);

    
        }
    }
    
    public void appliquer(ActionEvent event) throws IOException, SQLException 
    {
        Double ancprix=prixminrech.getValue();
        Double nvprix=prixmaxrech.getValue();
        String nomespa = nomespacerech.getValue() ;
        String dispoesp = disponibilite.getValue() ;
        
        if(ancprix !=0 || nvprix !=0)
        {
           if(nvprix<=ancprix)
            {
                 Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Le prix minimal doit etre inferieur au prix maximal ! ");
                a.showAndWait();  
            }
            else
            {        
                CrudOffre co = new CrudOffre();

                list_promotion = FXCollections.observableArrayList();
                try {
                    co.searchOffreMultiple(nomespa,dispoesp,ancprix,nvprix).forEach(e -> { list_promotion.add(e);});
                } catch (SQLException ex) {
                    
                }

                tableoffre.getItems().clear();
                tableoffre.getItems().addAll(list_promotion);
                
            } 
        }
        else
        {
            CrudOffre co = new CrudOffre();

                list_promotion = FXCollections.observableArrayList();
                try {
                    co.searchOffreWithoutprix(nomespa,dispoesp).forEach(e -> { list_promotion.add(e);});
                } catch (SQLException ex) {
                    
                }

                tableoffre.getItems().clear();
                tableoffre.getItems().addAll(list_promotion);
            
        }
        

        
    }
    
    public void reinitialiser(ActionEvent event)
    {
            prixminrech.setMin(0);
            prixminrech.setMax(500);
            prixminrech.setValue(0);
            prixminrech.setShowTickLabels(true);
            prixminrech.setShowTickMarks(true);
            prixminrech.setMajorTickUnit(50);
            prixminrech.setMinorTickCount(5);
            prixminrech.setBlockIncrement(5);
            
            prixmaxrech.setMin(0);
            prixmaxrech.setMax(500);
            prixmaxrech.setValue(0);
            prixmaxrech.setShowTickLabels(true);
            prixmaxrech.setShowTickMarks(true);
            prixmaxrech.setMajorTickUnit(50);
            prixmaxrech.setMinorTickCount(5);
            prixmaxrech.setBlockIncrement(5);
   
            buildNomEspace();
            nomespacerech.setItems(myespaces);
            nomespacerech.setValue("Tous");
            
            disponibilite.setItems(mydispo);
            disponibilite.setValue("Tous");
            buildOffreTable();
        
    }
    public void supprimer(ActionEvent event) throws SQLException
    {
        
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Aucun objet selectionné");
            alert.setContentText("Veuillez selectionner une offre à supprimer !");
            alert.showAndWait();
        } 
        else 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppresion");
            alert.setContentText("Voulez vous vraiment supprimer cette offre ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
                CrudOffre co = new CrudOffre();
                co.DeleteOffre(idHolder);
                buildOffreTable();
                
            }
        }
    }
    
    public void modifier(ActionEvent event) throws SQLException
    {
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Aucun objet selectionné");
            alert.setContentText("Veuillez selectionner une offre à modifier !");
            alert.showAndWait();
        } 
        else 
        {
            
            annuler.setVisible(true);
            enregistrer.setVisible(true);
            btnmodifier.setVisible(false);
            btnajouter.setVisible(false);
            btnsupprimer.setVisible(false);
            
            
            CrudOffre co = new CrudOffre();
            Offre result =  co.getOffreById(idHolder);
            nomespace.getSelectionModel().select(co.getNomEspaceID(result.getID_ESPACE()));
            titre.setText(result.getTITRE_O()); 
            description.setText(result.getDESCRIPTION_O());
            ancienprix.setValue(result.getANCIEN_PRIX());
            nouveauprix.setValue(result.getNOUVEAU_PRIX());
            datedebut.setValue(result.getDATEDEBUT_O());
            datefin.setValue(result.getDATEFIN_O());
            pathaffiche.setText(result.getAFFICHE_O());
            
        }
        
    }

    private void getPromotionInfo(Integer id_offre) 
    {
        selected = true;
       idHolder = id_offre;
        
    }
    
    public void annuler (ActionEvent event)
    {
        clearsaisie();
        annuler.setVisible(false);
        enregistrer.setVisible(false);
        btnmodifier.setVisible(true);
        btnajouter.setVisible(true);
        btnsupprimer.setVisible(true);
        
        
    }
    public void enregistrer(ActionEvent event) throws SQLException
    {
       
        CrudOffre co = new CrudOffre();
        String nomesp = nomespace.getValue() ; 
        String titr = titre.getText() ;
        String desc = description.getText();
        Double ancprix=ancienprix.getValue();
        Double nvprix=nouveauprix.getValue();
        LocalDate datedeb=datedebut.getValue();
        LocalDate datef=datefin.getValue();
        String aff=pathaffiche.getText();
        Offre result =  co.getOffreById(idHolder);
        int idesp = result.getID_ESPACE();
        Offre f = new Offre(idHolder,idesp,titr,desc,ancprix,nvprix,datedeb,datef,aff);
         co.UpdateOffre(f);

         
         Alert a = new Alert(Alert.AlertType.INFORMATION) ; 
            a.setContentText("La modification a été faite !");
            a.showAndWait();
         buildOffreTable();

        clearsaisie();
        annuler.setVisible(false);
        enregistrer.setVisible(false);
        btnmodifier.setVisible(true);
        btnajouter.setVisible(true);
        btnsupprimer.setVisible(true);
        viewaffiche.setImage(null);
        
        
    }
    
}
