package org.infantaelena.ies.ad.ejercicios.tema8;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

public class Ejercicios8_1 {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create()) {

            MongoDatabase database = mongoClient.getDatabase("sample_training");
            System.out.println("Conectado a la base de datos remota con éxito");
            MongoCollection<Document> collection = database.getCollection("routes");

            //_81a(collection);
            //_81b(collection);
            //_81c(collection);
            //_81d(collection);
            //_81e(collection);

        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos remota: "
                    + e.getMessage());
        }
    }

    private static void _81e(MongoCollection<Document> collection) {
        DeleteResult deleteResult = collection.deleteOne(eq("_id", "65dccba9cbacde6128fa6476"));

        System.out.println("Documentos eliminados: " + deleteResult.getDeletedCount());

    }

    private static void _81d(MongoCollection<Document> collection) {

        Document newAirline = new Document("id", 1001)
                .append("name", "Global Skyways")
                .append("alias", "GS")
                .append("iata", "GS");
        UpdateResult updateResult = collection.updateOne(
                eq("_id", "65dccba9cbacde6128fa6476"),
                set("airline", newAirline));

        System.out.println("Documentos actualizados: " + updateResult.getModifiedCount());
    }

    private static void _81c(MongoCollection<Document> collection) {

        UpdateResult updateResult = collection.updateOne(
                eq("_id", "65dccba9cbacde6128fa6476"),
                set("dst_airport", "LHR"));

        System.out.println("Documentos actualizados: " + updateResult.getModifiedCount());
    }

    private static void _81b(MongoCollection<Document> collection) {
        Bson filter = gt("stops", 0);
        Bson projection = Projections.fields(Projections.include("src_airport", "dst_airport", "airline.name"), Projections.excludeId());

        List<Document> routes = collection.find(filter).projection(projection).into(new ArrayList<>());
        routes.forEach(route -> System.out.println(route.toJson()));
    }

    private static void _81a(MongoCollection<Document> collection) {
        Document airline = new Document("id", 999)
                .append("name", "Pan American World Airways")
                .append("alias", "PA")
                .append("iata", "PA");

        Document route = new Document("_id", "65dccba9cbacde6128fa6476")
                .append("airline", airline)
                .append("src_airport", "MAD")
                .append("dst_airport", "MIA")
                .append("codeshare", "")
                .append("stops", 2)
                .append("airplane", "Boeing 747-121");

        collection.insertOne(route);
    }
}
