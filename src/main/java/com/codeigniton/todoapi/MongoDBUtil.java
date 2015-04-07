package com.codeigniton.todoapi;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by sinister on 5/29/14.
 */

public class MongoDBUtil {
    private static int port;
    private static String host;
    private static MongoClient connection = null;
    private static Morphia morphia = null;
    private static String dbName;

    static {
        host = System.getenv("TODOAPI_DB_HOST");
        if (host == null) {
            host = "localhost";
        }

        String envport = System.getenv("TODOAPI_PORT");
        if (envport == null){
            port = 27017;
        } else {
            port = Integer.parseInt(envport);
        }

        dbName = System.getenv("TODOAPI_NAME");
        if (dbName == null) {
            dbName = "todoapi";
        }
    }

    public static MongoClient getConnection() {
        if (connection == null) {
            try {
                connection = new MongoClient(host, port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Morphia getMorphia() {
        if (morphia == null) {
            morphia = new Morphia();
            morphia.map(Todo.class);
        }
        return morphia;
    }

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static String getDbName() {
        return dbName;
    }
}
