/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Book;

/**
 *
 * @author camilo.posadaa
 */
@Stateless
@Named
public class BookSessionBean {
    //Creo el punto de inyeccion 
    @Inject Book book;
    //Creo la collecion
    private DBCollection bookCollection;
    //llamo a la incializacion de la BD NoSQL
    @PostConstruct
    private void initDB(){
        Mongo mongo = null;
        try {
            mongo = new Mongo("ds033133.mongolab.com",33133);
        }catch(UnknownHostException ex){
            Logger.getLogger(BookSessionBean.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        DB db = mongo.getDB("librosdb");
        boolean auth = db.authenticate("cposdaa", "--".toCharArray());   
        if(auth){
            bookCollection = db.getCollection("books");
            //valido si la colleccion existe si no la crea
            if(bookCollection == null){
                bookCollection = db.createCollection("books", null);
            }
            
        }else{
            System.out.println("Login failed");
        }
    
    }
    
    //metodo para isertar 
    public void createBook(){
        //crea un nuevo docuimento
        BasicDBObject doc = book.toDBObject();
        //Inserto el documento el la coleccion 
        bookCollection.insert(doc);
        
    }
    
    
    //Metodo para listar
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<Book>();
        //creamos un nuevo cursor para recorrer el documento
        DBCursor cursor = bookCollection.find();
        while(cursor.hasNext()){
            DBObject dbo = cursor.next();
            books.add(Book.fromDBObject(dbo));
        }
        return books;
    }
    
    
}