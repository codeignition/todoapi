package com.codeigniton.todoapi;

import com.google.gson.*;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by sinister on 07/04/15.
 */
public class ObjectIdSerializer implements JsonSerializer<ObjectId> {
    @Override
    public JsonElement serialize(ObjectId value, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(value.toString());
    }
}
