/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testbd;

/**
 *
 * @author Nidhal Bougatf
 */
public class TestBD {

  
    public static void main(String[] args) {
        
        static String url="jdbc:mysql://127.0.0.1/esprit";
    static String login="root";
    static String password="";
    
    static Connection cnx;
    
    public static void main(String[] args) {
        try {
            cnx=DriverManager.getConnection(url, login, password);
            Personne p = new Personne ("Ali","Bejaoui");
            Personne p1 = new Personne ("Ali1","Bejaoui1");
            CRUD_Personne crud= new CRUD_Personne();
            //crud.insertST(p);
           // crud.insertPST(p1);
            crud.deleteST(3);
            crud.displayAll().forEach(System.out::println);
            //ou
            List<Personne> l=crud.displayAll();
            for ( Personne i :l)
            {
                System.out.println(i);
            }
            Personne p2 = new Personne (5,"Haythem2","Salllami2");
            crud.updateSt(p2);
            
            
        } catch (SQLException ex) {
            System.out.println("erreur de connexion");
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
