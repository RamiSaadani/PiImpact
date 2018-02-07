/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Services.CrudArticle;


/**
 *
 * @author CePc
 */
public class ArticleTest {
       public static void main(String[] args) {
 
 CrudArticle Article=new CrudArticle();
 try{
     Article.displayAll();
 }
 catch(Exception e)
 {
     e.printStackTrace();
 }
    }
}
