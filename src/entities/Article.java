/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author CePc
 */
public class Article {
    int ID_ARTICLE;
    int ID_UTILISATEUR;
    String TITRE_A;
    String DESCRIPTION_A;
    String EDITEUR_A;
    String TYPE_A;

    public Article(int ID_ARTICLE, int ID_UTILISATEUR, String TITRE_A, String DESCRIPTION_A, String EDITEUR_A, String TYPE_A) {
        this.ID_ARTICLE = ID_ARTICLE;
        this.ID_UTILISATEUR = ID_UTILISATEUR;
        this.TITRE_A = TITRE_A;
        this.DESCRIPTION_A = DESCRIPTION_A;
        this.EDITEUR_A = EDITEUR_A;
        this.TYPE_A = TYPE_A;
    }

    public Article( int ID_UTILISATEUR, String TITRE_A, String DESCRIPTION_A, String EDITEUR_A, String TYPE_A) {
        
        this.ID_UTILISATEUR = ID_UTILISATEUR;
        this.TITRE_A = TITRE_A;
        this.DESCRIPTION_A = DESCRIPTION_A;
        this.EDITEUR_A = EDITEUR_A;
        this.TYPE_A = TYPE_A;
    }

    @Override
    public String toString() {
        return "Article{" + "ID_ARTICLE=" + ID_ARTICLE + ", ID_UTILISATEUR=" + ID_UTILISATEUR + ", TITRE_A=" + TITRE_A + ", DESCRIPTION_A=" + DESCRIPTION_A + ", EDITEUR_A=" + EDITEUR_A + ", TYPE_A=" + TYPE_A + '}';
    }


    public int getID_ARTICLE() {
        return ID_ARTICLE;
    }

    public void setID_ARTICLE(int ID_ARTICLE) {
        this.ID_ARTICLE = ID_ARTICLE;
    }

    public int getID_UTILISATEUR() {
        return ID_UTILISATEUR;
    }

    public void setID_UTILISATEUR(int ID_UTILISATEUR) {
        this.ID_UTILISATEUR = ID_UTILISATEUR;
    }

    public String getTITRE_A() {
        return TITRE_A;
    }

    public void setTITRE_A(String TITRE_A) {
        this.TITRE_A = TITRE_A;
    }

    public String getDESCRIPTION_A() {
        return DESCRIPTION_A;
    }

    public void setDESCRIPTION_A(String DESCRIPTION_A) {
        this.DESCRIPTION_A = DESCRIPTION_A;
    }

    public String getEDITEUR_A() {
        return EDITEUR_A;
    }

    public void setEDITEUR_A(String EDITEUR_A) {
        this.EDITEUR_A = EDITEUR_A;
    }

    public String getTYPE_A() {
        return TYPE_A;
    }

    public void setTYPE_A(String TYPE_A) {
        this.TYPE_A = TYPE_A;
    }
    
}
