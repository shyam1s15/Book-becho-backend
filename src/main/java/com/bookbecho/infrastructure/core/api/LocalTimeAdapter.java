package com.bookbecho.infrastructure.core.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalTime;

/**
 * Serializer for Java Local Time {@link LocalTime} that returns the time in array format to match previous Jackson
 * functionality.
 */
public class LocalTimeAdapter implements JsonSerializer<LocalTime> {

    @Override
    @SuppressWarnings("unused")
    public JsonElement serialize(final LocalTime src, final Type typeOfSrc, final JsonSerializationContext context) {
        JsonArray array = null;
        if (src != null) {
            array = new JsonArray();
            array.add(new JsonPrimitive(src.getHour()));
            array.add(new JsonPrimitive(src.getMinute()));
            array.add(new JsonPrimitive(src.getSecond()));
        }
        return array;
    }
}
