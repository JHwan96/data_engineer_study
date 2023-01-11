package org.de.mongodb.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test2");
        MongoCollection<Document> collection = mongoDatabase.getCollection("movies");
        Document document = collection.find(eq("title", "The Favourite")).first();
        System.out.println(document.toJson());
    }
}
