
public class Espace {
    private int ID_ESPACE;
    private int ID_UTILISATEUR ;
    private String nomEspace;
    private String ADRESSE_ES;
    private String EMAIL_ES;
    private String NUMTEL_ES;
    private String FACEBOOK_ES;
    private String LOGO_ES;

    
    
    
   
    
    public Espace()
    {
        
    }

    public Espace(int ID_ESPACE, String nomEspace, String ADRESSE_ES, String EMAIL_ES, String NUMTEL_ES, String FACEBOOK_ES, String LOGO_ES) {
        this.ID_ESPACE = ID_ESPACE;
       
        this.nomEspace = nomEspace;
        this.ADRESSE_ES = ADRESSE_ES;
        this.EMAIL_ES = EMAIL_ES;
        this.NUMTEL_ES = NUMTEL_ES;
        this.FACEBOOK_ES = FACEBOOK_ES;
        this.LOGO_ES = LOGO_ES;
    }

    @Override
    public String toString() {
        return "Espace{" + "ID_ESPACE=" + ID_ESPACE + ", ID_UTILISATEUR=" + ID_UTILISATEUR + ", nomEspace=" + nomEspace + ", ADRESSE_ES=" + ADRESSE_ES + ", EMAIL_ES=" + EMAIL_ES + ", NUMTEL_ES=" + NUMTEL_ES + ", FACEBOOK_ES=" + FACEBOOK_ES + ", LOGO_ES=" + LOGO_ES + '}';
    }
    

    public Espace(String nomEspace,String ADRESSE_ES, String EMAIL_ES, String NUMTEL_ES, String FACEBOOK_ES, String LOGO_ES) {
        this.nomEspace = nomEspace;
        this.ADRESSE_ES = ADRESSE_ES;
        this.EMAIL_ES = EMAIL_ES;
        this.NUMTEL_ES = NUMTEL_ES;
        this.FACEBOOK_ES = FACEBOOK_ES;
        this.LOGO_ES = LOGO_ES;
    }
    public Espace(int ID_ESPACE, String nomEspace,String ADRESSE_ES) 
    {
        this.ID_ESPACE=ID_ESPACE;
        this.nomEspace = nomEspace;
        this.ADRESSE_ES = ADRESSE_ES;

    }

    public int getID_ESPACE() {
        return ID_ESPACE;
    }

    public void setID_ESPACE(int ID_ESPACE) {
        this.ID_ESPACE = ID_ESPACE;
    }

    public int getID_UTILISATEUR() {
        return ID_UTILISATEUR;
    }

    public void setID_UTILISATEUR(int ID_UTILISATEUR) {
        this.ID_UTILISATEUR = ID_UTILISATEUR;
    }

    public String getNomEspace() {
        return nomEspace;
    }

    public void setNomEspace(String nomEspace) {
        this.nomEspace = nomEspace;
    }

    public String getADRESSE_ES() {
        return ADRESSE_ES;
    }

    public void setADRESSE_ES(String ADRESSE_ES) {
        this.ADRESSE_ES = ADRESSE_ES;
    }

    public String getEMAIL_ES() {
        return EMAIL_ES;
    }

    public void setEMAIL_ES(String EMAIL_ES) {
        this.EMAIL_ES = EMAIL_ES;
    }

    public String getNUMTEL_ES() {
        return NUMTEL_ES;
    }

    public void setNUMTEL_ES(String NUMTEL_ES) {
        this.NUMTEL_ES = NUMTEL_ES;
    }

    public String getFACEBOOK_ES() {
        return FACEBOOK_ES;
    }

    public void setFACEBOOK_ES(String FACEBOOK_ES) {
        this.FACEBOOK_ES = FACEBOOK_ES;
    }

    public String getLOGO_ES() {
        return LOGO_ES;
    }

    public void setLOGO_ES(String LOGO_ES) {
        this.LOGO_ES = LOGO_ES;
    }



    
    
}