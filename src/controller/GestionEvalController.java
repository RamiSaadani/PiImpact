/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudEvaluation;

import connexion.DataSource;
import entities.Evaluation;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Timer;
import java.util.TimerTask;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionEvalController implements Initializable {

    @FXML
    private TableView<Evaluation> TableEval;
    @FXML
    private TextField chercherUti;
    @FXML
    private BarChart<String, Number> chart5;
    @FXML
    private RadioButton EvalEvent;
    @FXML
    private RadioButton EvalArticle;
    @FXML
    private RadioButton EvalEspace;
    @FXML
    private RadioButton EvalOffre;
    private TextField NomUser;

    private ObservableList<Evaluation> list_evaluation = FXCollections.observableArrayList();
    ;
 //private final Stage primaryStage;
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
    CrudEvaluation CE = new CrudEvaluation();

    private ObservableList<BarChart.Data> ObArrL_Evals;
    @FXML
    private ToggleGroup filter1;
    @FXML
    private RadioButton EvalNone;
    private TextField NomObj;
    @FXML
    private RadioButton Event;
    @FXML
    private RadioButton article;
    @FXML
    private RadioButton esp;
    @FXML
    private ComboBox<?> ComboType;
    ObservableList Combo1 = FXCollections.observableArrayList();
    ObservableList Combo2 = FXCollections.observableArrayList();
    ObservableList Combo3 = FXCollections.observableArrayList();
    @FXML
    private ToggleGroup filter2;

    Timer timer = new Timer();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UpdateListe();

        TableColumn _IdEval = new TableColumn("ID_EVALUATION");
        TableColumn _IdUtilisateur = new TableColumn("ID_UTILISATEUR");
        _IdEval.setVisible(false);
        _IdUtilisateur.setVisible(false);
       // TableColumn _Nom_uti = new TableColumn("NOM_UTILISATEUR");
        TableColumn _NotesEv = new TableColumn("NOTES_EV");
        TableColumn _CommentaireEv = new TableColumn("COMMENTAIRE_EV");
        TableColumn _ObjetEv = new TableColumn("OBJET_EV");
        TableColumn _TypeEv = new TableColumn("TYPE_EV");
        TableColumn _IdObj = new TableColumn("ID_O");
        _IdObj.setVisible(false);

        _IdEval.setCellValueFactory(new PropertyValueFactory<>("ID_EVALUATION"));

        _IdUtilisateur.setCellValueFactory(new PropertyValueFactory<>("ID_UTILISATEUR"));
        _NotesEv.setCellValueFactory(new PropertyValueFactory<>("NOTES_EV"));
        _CommentaireEv.setCellValueFactory(new PropertyValueFactory<>("COMMENTAIRE_EV"));
        _ObjetEv.setCellValueFactory(new PropertyValueFactory<>("OBJET_EV"));
        _TypeEv.setCellValueFactory(new PropertyValueFactory<>("TYPE_EV"));
        _IdObj.setCellValueFactory(new PropertyValueFactory<>("ID_O"));
     //   _Nom_uti.setCellValueFactory(new PropertyValueFactory<>("NOM_UTILISATEUR"));
     
        TableEval.getColumns().addAll(_IdEval, _IdUtilisateur, _NotesEv, _CommentaireEv, _ObjetEv, _TypeEv, _IdObj);

        this.LoadChart("génerale", "");

        Combo1 = FXCollections.observableArrayList("Randonné", "Voyage", "Camping", "Marathon", "Autre");

        Combo2 = FXCollections.observableArrayList("salle de sport", "spa", "Centre de therapie", "salle de beaute");

        Combo3 = FXCollections.observableArrayList("sport", "sante", "nutrition", "medicne", "therapie", "citation", "horoscope", " pshcologie", "motivation");
        
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    MaNotification maNotification = new MaNotification();
                    try {
                        maNotification.s = AfficherHeures();
                    } catch (SQLException ex) {
                        
                    }
                    maNotification.start();
                }
            }, 5000,60*60*1000);
        
    }
//pour extraire year et type du BD 

    public void LoadChart(String chartName, String type) {
        String requete = "select Distinct Extract(YEAR from DATE_EVALUATION) as year From Evaluation " + type + "";
        chart5.setTitle("Evaluation " + chartName);

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName("Evaluation");

        try {
            Connection connexion = DataSource.getInstance().getConnection();
            ResultSet rs = connexion.createStatement().executeQuery(requete);
            while (rs.next()) {
                String req;
                if (type.equals("")) {
                    req = "select count(*) as number From Evaluation where "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                } else {
                    req = "select count(*) as number From Evaluation " + type + " and "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                }
                ResultSet rs1 = connexion.createStatement().executeQuery(req);
                rs1.next();
                series.getData().add(new XYChart.Data(rs.getString(1), rs1.getInt(1)));
            }
            chart5.getData().clear();
            chart5.getData().add(series);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void LoadChart2(String chartName, String objet) {
        String requete = " select Distinct Extract(YEAR from DATE_EVALUATION) as year From Evaluation " + objet + "";
        chart5.setTitle("Evaluation " + chartName);

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName("Evaluation");
        
        try {
            Connection connexion = DataSource.getInstance().getConnection();
            ResultSet rs = connexion.createStatement().executeQuery(requete);
            while (rs.next()) {
                String req;
                if (objet.equals("")) {
                    req = "select count(*) as number From Evaluation where   "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                } else {
                    req = "select count(*) as number From Evaluation " + objet + " and "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                }
                ResultSet rs1 = connexion.createStatement().executeQuery(req);
                rs1.next();
                series.getData().add(new XYChart.Data(rs.getString(1), rs1.getInt(1)));
            }
//            XYChart.Series<String, Number> series2 = new XYChart.Series();
//            for(Evaluation item : list){
//            series2.getData().add(new XYChart.Data(item, item.getTYPE_EV()));
//            }
            chart5.getData().clear();
            chart5.getData().add(series);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void UpdateListe() {

        CrudEvaluation CE = new CrudEvaluation();
        try {

            TableEval.setItems(CE.DisplayAll());

        } catch (SQLException ex) {
            
        }
    }

    @FXML
    private void Chercher(ActionEvent event) {

        if ("".equals(chercherUti.getText())) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir un utilisateur! ");
            a.showAndWait();

        } else {

            CrudEvaluation CE = new CrudEvaluation();

            list_evaluation = FXCollections.observableArrayList();
        }
        try {
            TableEval.setItems(CE.searchByNomU(chercherUti.getText()));

        } catch (SQLException ex) {
           
        }

    }

    @FXML
    private void filtrer(ActionEvent event) throws SQLException {
        String type = "";
        String evalName = "génerale";

        if (EvalEvent.isSelected()) {
            type += "WHERE OBJET_EV  = 'Evenement'  ";

            evalName = "evenement";
        }
        if (EvalArticle.isSelected()) {
            type += "WHERE OBJET_EV = 'Article' ";
            evalName = "article";
        }
        if (EvalEspace.isSelected()) {
            type += "WHERE OBJET_EV = 'Espace' ";
            evalName = "espace";
        }
        if (EvalOffre.isSelected()) {
            type += "WHERE OBJET_EV = 'Offre' ";
            evalName = "offre";
        }
        try {
            TableEval.setItems(CE.SearchByObjet(type));
            this.LoadChart(evalName, type);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void filtrerObj(ActionEvent event) {

        String objet = "";
        String evalname = "génerale";
        String type = (String) ComboType.getValue();

        if (Event.isSelected()) {
            objet += "where OBJET_EV= 'Evenement' and TYPE_EV = '" + type + "' ";
            evalname = type;
        }

        if (article.isSelected()) {
            objet += "where OBJET_EV= 'Article'  and TYPE_EV = '" + type + "' ";
            evalname = type;
        }

        if (esp.isSelected()) {
            objet += "where OBJET_EV= 'Espace' and TYPE_EV = '" + type + "' ";
            evalname = type;
        }

        try {
            TableEval.setItems(CE.SearchByObjetType(objet, type));
            this.LoadChart2(evalname, objet);
        } catch (SQLException ex) {
           
        }
    }

    @FXML
    private void Reinitialiser(ActionEvent event) {

        String evalname = "génerale";
        UpdateListe();
        chercherUti.clear();
        LoadChart(evalname, "");

    }

    public int AfficherHeures() throws SQLException {

        int req = 0;
        String requete = " SELECT count(*) FROM evaluation WHERE DAY( DATE_EVALUATION ) = DAY(CURDATE()) AND MONTH(DATE_EVALUATION)= MONTH(CURDATE())AND YEAR(DATE_EVALUATION)= YEAR(CURDATE()) ";
        try {
            Connection connexion = DataSource.getInstance().getConnection();
            ResultSet rs = connexion.createStatement().executeQuery(requete);

//             new MyTimerTask().run();
            while (rs.next()) {
                req = rs.getInt(1);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return req;
    }

    class MaNotification extends Thread {

        public int s = 0;

        @Override
        public void run() {
//        
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(EvaluationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            Notifications notificationBuilder = Notifications.create()
                    .title("Alerte ")
                    .text("Nombre d'evaluations d'aujourd'hui est: " + s)
                    .graphic(null)
                    .hideAfter(Duration.hours(6))
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("afficher notification");

                        }
                    });
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    notificationBuilder.show();
//                }
//            });

        }
    }


    @FXML
    private void RemplirEvent(ActionEvent event) {
        if (Event.isSelected()) {
            ComboType.setItems(Combo1);
        }
    }

    @FXML
    private void RemplorArt(ActionEvent event) {
        if (article.isSelected()) {
            ComboType.setItems(Combo3);
        }
    }

    @FXML
    private void RemplirEsp(ActionEvent event) {
        if (esp.isSelected()) {
            ComboType.setItems(Combo2);
        }
    }

}
