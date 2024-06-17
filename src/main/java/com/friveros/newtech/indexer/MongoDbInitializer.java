package com.friveros.newtech.indexer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.bson.Document;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MongoDbInitializer {

    private static final Logger LOGGER = Logger.getLogger(MongoDbInitializer.class.getName());

    @Inject
    MongoClient mongoClient;

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Configurando índices para MongoDB...");
        setupContractIndexes();
        setupHistoryIndexes();
    }

    private void setupContractIndexes() {
        MongoCollection<Document> contractCollection = mongoClient.getDatabase("contracts").getCollection("contracts");
        contractCollection.createIndex(Indexes.ascending("createdAt"));
        LOGGER.info("Índice para 'createdAt' creado.");
        contractCollection.createIndex(Indexes.ascending("active"));
        LOGGER.info("Índice para 'active' creado.");
        contractCollection.createIndex(Indexes.ascending("startAt"));
        LOGGER.info("Índice para 'startAt' creado.");
        contractCollection.createIndex(Indexes.ascending("endAt"));
        LOGGER.info("Índice para 'endAt' creado.");
        contractCollection.createIndex(Indexes.ascending("startAt", "endAt"));
        LOGGER.info("Índice compuesto para 'startAt' y 'endAt' creado.");
        contractCollection.createIndex(Indexes.ascending("inspectingProsecutorId"));
        LOGGER.info("Índice para 'inspectingProsecutorId' creado.");
        contractCollection.createIndex(Indexes.ascending("residentId"));
        LOGGER.info("Índice para 'residentId' creado.");
        contractCollection.createIndex(Indexes.ascending("clientId"));
        LOGGER.info("Índice para 'clientId' creado.");
        contractCollection.createIndex(Indexes.ascending("deliveryClientId"));
        LOGGER.info("Índice para 'deliveryClientId' creado.");

    }
    private void setupHistoryIndexes() {
        MongoCollection<Document> contractCollection = mongoClient.getDatabase("contracts").getCollection("history");
        contractCollection.createIndex(Indexes.ascending("changedAt"));
        LOGGER.info("Índice para 'changedAt' creado.");
        contractCollection.createIndex(Indexes.ascending("changedBy"));
        LOGGER.info("Índice para 'changedBy' creado.");
        contractCollection.createIndex(Indexes.ascending("changedIdBy"));
        LOGGER.info("Índice para 'changedIdBy' creado.");
        contractCollection.createIndex(Indexes.ascending("changedBy","changedIdBy"));
        LOGGER.info("Índice para 'changedBy' & 'changedIdBy' creado.");
        contractCollection.createIndex(Indexes.ascending("action"));
        LOGGER.info("Índice para 'action' creado.");
        contractCollection.createIndex(Indexes.geo2dsphere("point"));
        LOGGER.info("Índice para 'coords' creado.");
        contractCollection.createIndex(Indexes.ascending("contractId"));
        LOGGER.info("Índice para 'contractId' creado.");

    }
}
