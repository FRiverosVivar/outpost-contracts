package com.friveros.newtech.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

@ApplicationScoped
public class SequenceGeneratorService {
    @Inject
    MongoClient mongoClient;

    public long getNextSequence(String sequenceName) {
        MongoCollection<Document> countersCollection = mongoClient.getDatabase("pages").getCollection("counters");
        Document counter = countersCollection.findOneAndUpdate(
                new Document("_id", sequenceName),
                new Document("$inc", new Document("seq", 1)),
                new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true)
        );
        return (counter.getInteger("seq")).longValue();
    }
}