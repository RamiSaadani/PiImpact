/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Nidhal Bougatf
 */
public class Offre {
    
    int ID_OFFRE;
    int ID_ESPACE;
    String DESCRIPTION_O ;
    String TITRE_O;
    float ANCIEN_PRIX;
    float NOUVEAU_PRIX;
    Date DATEDEBUT_O;
    Date DATEFIN_O;
    String AFFICHE_O;
    String TYPE_O;

    public Offre(int ID_OFFRE, int ID_ESPACE, String DESCRIPTION_O, String TITRE_O, float ANCIEN_PRIX, float NOUVEAU_PRIX, Date DATEDEBUT_O, Date DATEFIN_O, String AFFICHE_O,String TYPE_O) {
        this.ID_OFFRE = ID_OFFRE;
        this.ID_ESPACE = ID_ESPACE;
        this.DESCRIPTION_O = DESCRIPTION_O;
        this.TITRE_O = TITRE_O;
        this.ANCIEN_PRIX = ANCIEN_PRIX;
        this.NOUVEAU_PRIX = NOUVEAU_PRIX;
        this.DATEDEBUT_O = DATEDEBUT_O;
        this.DATEFIN_O = DATEFIN_O;
        this.AFFICHE_O = AFFICHE_O;
        this.TYPE_O=TYPE_O;
    }

    public int getID_OFFRE() {
        return ID_OFFRE;
    }

    public void setID_OFFRE(int ID_OFFRE) {
        this.ID_OFFRE = ID_OFFRE;
    }

    public int getID_ESPACE() {
        return ID_ESPACE;
    }

    public void setID_ESPACE(int ID_ESPACE) {
        this.ID_ESPACE = ID_ESPACE;
    }

    public String getDESCRIPTION_O() {
        return DESCRIPTION_O;
    }

    public void setDESCRIPTION_O(String DESCRIPTION_O) {
        this.DESCRIPTION_O = DESCRIPTION_O;
    }

    public String getTITRE_O() {
        return TITRE_O;
    }

    public void setTITRE_O(String TITRE_O) {
        this.TITRE_O = TITRE_O;
    }

    public float getANCIEN_PRIX() {
        return ANCIEN_PRIX;
    }

    public void setANCIEN_PRIX(float ANCIEN_PRIX) {
        this.ANCIEN_PRIX = ANCIEN_PRIX;
    }

    public float getNOUVEAU_PRIX() {
        return NOUVEAU_PRIX;
    }

    public void setNOUVEAU_PRIX(float NOUVEAU_PRIX) {
        this.NOUVEAU_PRIX = NOUVEAU_PRIX;
    }

    public Date getDATEDEBUT_O() {
        return DATEDEBUT_O;
    }

    public void setDATEDEBUT_O(Date DATEDEBUT_O) {
        this.DATEDEBUT_O = DATEDEBUT_O;
    }

    public Date getDATEFIN_O() {
        return DATEFIN_O;
    }

    public void setDATEFIN_O(Date DATEFIN_O) {
        this.DATEFIN_O = DATEFIN_O;
    }

    public String getAFFICHE_O() {
        return AFFICHE_O;
    }

    public void setAFFICHE_O(String AFFICHE_O) {
        this.AFFICHE_O = AFFICHE_O;
    }
    
     public String getTYPE_O() {
        return TYPE_O;
    }

    public void setTYPE_O(String TYPE_O) {
        this.TYPE_O = TYPE_O;
    }

    @Override
    public String toString() {
        return "Offre{" + "ID_OFFRE=" + ID_OFFRE + ", ID_ESPACE=" + ID_ESPACE + ", DESCRIPTION_O=" + DESCRIPTION_O + ", TITRE_O=" + TITRE_O + ", ANCIEN_PRIX=" + ANCIEN_PRIX + ", NOUVEAU_PRIX=" + NOUVEAU_PRIX + ", DATEDEBUT_O=" + DATEDEBUT_O + ", DATEFIN_O=" + DATEFIN_O + ", AFFICHE_O=" + AFFICHE_O + ", TYPE_O=" + TYPE_O + '}';
    }

   
    
    
    
    
}
