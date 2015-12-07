/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import javax.enterprise.inject.Model;
import javax.validation.constraints.Size;

/**
 *
 * @author camilo.posadaa
 */
@Model

public class Book {
    
    @Size(min=1 , max=20)
    private String name;
    @Size(min=1 , max=20)
    private String author;
    @Size(min=1 , max=6)
    private String year;
    @Size(min=1 , max=20)
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
    //Coloca java a objetos bson
    public BasicDBObject toDBObject(){
        BasicDBObject document = new BasicDBObject();
        document.put("name",name);
        document.put("year",year );
        document.put("language",language );
        document.put("author", author );
        return document;
    }
    //coloca de bson a java
    public static Book fromDBObject(DBObject document){
        Book b = new Book();
        b.name =(String)document.get("name");
        b.year = (String)document.get("year");
        b.language = (String)document.get("language");
        b.author = (String)document.get("author");
        return b;
    }
}
