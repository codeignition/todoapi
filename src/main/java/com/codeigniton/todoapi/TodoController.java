package com.codeigniton.todoapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by sinister on 07/04/15.
 */

public class TodoController {

    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ObjectId.class, new ObjectIdSerializer());

        get("/", (req, res) -> "Hello World");

        post("/todos", (request, response) -> {
            Map<String,String> params = new Gson().fromJson(request.body(),  new TypeToken<Map<String,String>>(){}.getType());

            String title = params.get("title");
            Todo todo = new Todo(title);

            TodoDAO todoDAO = new TodoDAO();
            todoDAO.save(todo);

            response.status(201);
            Gson gson = gsonBuilder.create();
            response.type("application/json");
            return gson.toJson(todo);
        });

        get("/todos/:id", (request, response) -> {
            TodoDAO todoDAO = new TodoDAO();
            Todo todo = todoDAO.get(new ObjectId(request.params(":id")));
            if (todo != null) {
                response.status(200);
                Gson gson = gsonBuilder.create();
                response.type("application/json");
                return gson.toJson(todo);
            } else {
                response.status(404);
                return "Todo not found";
            }
        });

        put("/todos/:id", (request, response) -> {
            Map<String, String> params = new Gson().fromJson(request.body(), new TypeToken<Map<String,String>>(){}.getType());

            String title = params.get("title");
            TodoDAO todoDAO = new TodoDAO();
            Todo todo = todoDAO.get(new ObjectId(request.params(":id")));
            if (todo != null) {
                todo.setTitle(title);
                todoDAO.save(todo);
                response.status(200);
                Gson gson = gsonBuilder.create();
                response.type("application/json");
                return gson.toJson(todo);
            } else {
                response.status(404);
                return "Todo not found";
            }
        });

        delete("/todos/:id", (request, response) -> {
            TodoDAO todoDAO = new TodoDAO();
            Todo todo = todoDAO.get(new ObjectId(request.params(":id")));
            if (todo != null) {
                todoDAO.delete(todo);
                response.status(200);
                return "Todo deleted";
            } else {
                response.status(404);
                return "Todo not found";
            }
        });

        get("/todos", (request, response) -> {
            TodoDAO todoDAO = new TodoDAO();
            List<Todo> todos = todoDAO.find().asList();
            response.status(200);
            Gson gson = gsonBuilder.create();
            response.type("application/json");
            return gson.toJson(todos);
        });
    }
}
