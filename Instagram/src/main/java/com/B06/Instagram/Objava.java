package com.B06.Instagram;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class Objava {
    private String datum;
    private String ime;
    private String prezime;
    private String username;

    public Integer getVideo_ID() {
        return video_ID;
    }

    private Integer video_ID;

    public Objava(String datum, String ime, String prezime, String username, Integer video_ID){
        this.datum = datum;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.video_ID = video_ID;
    }

    public void dodajObjavu(){
        MongoClientURI uri = new MongoClientURI("mongodb://instagram:instagram1@ds123658.mlab.com:23658/instagramdb");
        MongoClient mC = new MongoClient(uri);
        MongoDatabase mD = mC.getDatabase("instagramdb");
        MongoCollection<Document> tabela = mD.getCollection("Objava");

        Document objava = new Document("Video_ID", video_ID)
                .append("Ime", ime)
                .append("Prezime", prezime)
                .append("Username", username)
                .append("Datum", datum);

        tabela.insertOne(objava);
    }

}
