package edu.escuelaing.arep;

import static spark.Spark.*;
import com.google.gson.Gson;
import edu.escuelaing.arep.model.Message;
import edu.escuelaing.arep.persistence.MongoDBConnection;

public class LogApp {
    public static void main( String[] args ) {
        port(getPort());
        MongoDBConnection mongoDB = new MongoDBConnection();
        get("/arqempresarial", (req, res) -> {
            res.status(200);
            res.type("application/json");
            return new Gson().toJson(mongoDB.getMessage());
        });
        post("/arqempresarial", (req, res) -> {
            Message a = new Message(req.body());
            mongoDB.postMessage(a);
            return null;
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
