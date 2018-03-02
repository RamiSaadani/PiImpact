/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Time;
import java.sql.Date ; 
import java.time.LocalDate;

/**
 *
 * @author ELYES
 */
public class Cours {
    private int id_cours;
    private int id_espace;
    private int id_coach;
    
    private LocalDate date_cours;
    private String hr_debut;
    private String hr_fin;
    private String titre;
    
    public Cours()
    {
        
    }
        public Cours(int id_cours, int id_espace, String titre  ,String hr_debut, String hr_fin,LocalDate date_cours) {
        this.id_cours = id_cours;
        this.id_espace = id_espace;
        
        this.date_cours = date_cours;
        this.hr_debut = hr_debut;
        this.hr_fin = hr_fin;
        this.titre=titre;
    }

    public Cours(int id_cours, int id_espace, int id_coach, LocalDate date_cours, String hr_debut, String hr_fin) {
        this.id_cours = id_cours;
        this.id_espace = id_espace;
        this.id_coach = id_coach;
        this.date_cours = date_cours;
        this.hr_debut = hr_debut;
        this.hr_fin = hr_fin;
    }

    public Cours(int id_espace, int id_coach, LocalDate date_cours, String hr_debut, String hr_fin) {
        this.id_espace = id_espace;
        this.id_coach = id_coach;
        this.date_cours = date_cours;
        this.hr_debut = hr_debut;
        this.hr_fin = hr_fin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    
    

    public int getId_cours() {
        return id_cours;
    }
    public String getId_coursstr()
    {
        String x = ""+id_cours;
        return x;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public int getId_espace() {
        return id_espace;
    }

    public void setId_espace(int id_espace) {
        this.id_espace = id_espace;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public LocalDate getDate_cours() {
        return date_cours;
    }
  /*  public LocalDate getLocalDate()
    {
        Date.valu
    }*/
    public void setDate_cours(LocalDate date_cours) {
        this.date_cours = date_cours;
    }

    public String getHr_debut() {
        return hr_debut;
    }

    public void setHr_debut(String hr_debut) {
        this.hr_debut = hr_debut;
    }

    public String getHr_fin() {
        return hr_fin;
    }

    public void setHr_fin(String hr_fin) {
        this.hr_fin = hr_fin;
    }

    @Override
    public String toString() {
        return "Cours{" + "id_cours=" + id_cours + ", id_espace=" + id_espace + ", id_coach=" + id_coach + ", date_cours=" + date_cours + ", hr_debut=" + hr_debut + ", hr_fin=" + hr_fin + ", titre=" + titre + '}';
    }
    
    
    
    
    
}
