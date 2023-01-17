package org.de.mongodb.update;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.de.mongodb.model.Product;

import java.time.format.DateTimeFormatter;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Updates.mul;
import static com.mongodb.client.model.Projections.fields;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {
        CodecProvider pojoCoderProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCoderProvider));

        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("de-mongodb");
        MongoCollection<Product> collection = mongoDatabase.getCollection("products",Product.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        UpdateResult updateResult = collection.updateMany(fields(regex("name", "shoes"),
                                    gt("price", 10000)),
                mul("price", 0.9)
        );
        if(updateResult.wasAcknowledged()){
            System.out.println("modified count : "+updateResult.getModifiedCount());
        }
    }
}
