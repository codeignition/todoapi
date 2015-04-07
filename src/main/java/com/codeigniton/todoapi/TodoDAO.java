package com.codeigniton.todoapi;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by sinister on 07/04/15.
 */

public class TodoDAO extends BasicDAO<Todo, ObjectId> {
    public TodoDAO() {
        super(MongoDBUtil.getConnection(), MongoDBUtil.getMorphia(), MongoDBUtil.getDbName());
    }
}
