package org.de.mongodb.transaction;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {
        CodecProvider pojoCoderProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codecRegistry = fromRegistries(getDefaultCodecRegistry(),
                                                    fromProviders(pojoCoderProvider));

        String uri = "mongodb://localhost:27017/?replicaSet=rs0";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("de-mongodb").withCodecRegistry(codecRegistry);

        ClientSession clientSession = mongoClient.startSession();

        TransactionOptions transactionOptions = TransactionOptions.builder()
                .readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.LOCAL)
                .writeConcern(WriteConcern.MAJORITY)
                .build();

//        TransactionBody transactionBody = () -> {
        clientSession.startTransaction(transactionOptions);
            MongoCollection<Document> coll1 = mongoDatabase.getCollection("foo");
            MongoCollection<Document> coll2 = mongoDatabase.getCollection("bar");

            Map<String, Object> input1 = new HashMap<>();
            int id = new Random().nextInt();
            int value = new Random().nextInt();
            System.out.println("Test input: "+id+" : " + value);
            input1.put("_id", id);
            input1.put("field", value);

            Map<String, Object> input2 = new HashMap<>();
            input2.put("_id", id+1);
            input2.put("field", value+1);

            coll1.insertOne(clientSession, new Document(input1));
            coll1.insertOne(clientSession, new Document(input2));
            coll2.insertOne(clientSession, new Document(input1));
//            coll1.insertOne(clientSession, new Document(input1));

            clientSession.commitTransaction();
//            return "inserted into collection in different database";
//        };
//
//        clientSession.withTransaction(transactionBody, transactionOptions);
    }
}
