package edu.escuelaing.arep.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.escuelaing.arep.model.Message;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class MongoDBConnection {
    MongoClientURI mongoURI;
    MongoClient mongoClient;

    public MongoDBConnection (){
        mongoURI= new MongoClientURI("mongodb+srv://Admin:Admin@cluster0.bvczb.mongodb.net/RoundRobinService?retryWrites=true&w=majority");
        mongoClient= new MongoClient(mongoURI);
    }

    public ArrayList<Message> getMessage(){
        MongoDatabase mongoDatabase = mongoClient.getDatabase("RoundRobinService");
        MongoCollection<Document> mongoCollection=mongoDatabase.getCollection("Messages");
        ArrayList<Document>documents= new ArrayList<>();
        ArrayList<Message>messages= new ArrayList<>();
        FindIterable findIterable = mongoCollection.find();
        findIterable.into(documents);
        int doc=documents.size()-10;
        if (doc<0){doc=0;}
        for (int document = doc;document<documents.size();document++){
            System.out.println(documents.get(document).get("message"));
            if (documents.get(document).get("message") != null && documents.get(document).get("date") != null) {
                messages.add(new Message((String) documents.get(document).get("message"), (Date) documents.get(document).get("date")));
            }
        }
        return messages;
    }

    public void postMessage(Message message) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase("DockeryAWS");
        MongoCollection<Document> mongoCollection=mongoDatabase.getCollection("mensajes");
        Document document =new Document();
        document.put("message",message.getMessage());
        document.put("date",message.getDate());
        mongoCollection.insertOne(document);
    }

}