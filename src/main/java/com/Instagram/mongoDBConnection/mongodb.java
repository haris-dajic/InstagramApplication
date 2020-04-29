package com.Instagram.mongoDBConnection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class mongodb {


    public static void Konektuj(Integer fileId, String ime, String prezime, String username){

        MongoClientURI uri = new MongoClientURI("mongodb://instagram:instagram1@ds123658.mlab.com:23658/instagramdb");
        MongoClient mC = new MongoClient(uri);
        MongoDatabase mD = mC.getDatabase("instagramdb");
        MongoCollection<Document> tabela = mD.getCollection("Objava");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        tabela.insertOne(new Document("File_Id", fileId)
                .append("Ime", ime)
                .append("Prezime", prezime)
                .append("Username", username)
                .append("Datum", dateFormat.format(date)));
    }

    public static ArrayList<Document> getDataMongo(){

        MongoClientURI uri = new MongoClientURI("mongodb://instagram:instagram1@ds123658.mlab.com:23658/instagramdb");
        MongoClient mC = new MongoClient(uri);
        MongoDatabase mD = mC.getDatabase("instagramdb");
        MongoCollection<Document> tabela = mD.getCollection("Objava");

        ArrayList<Document> podaci = tabela.find().into(new ArrayList<Document>());
        return podaci;
    }
}
