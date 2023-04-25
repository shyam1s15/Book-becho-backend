package com.bookbecho.infrastructure.core.serialization;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

/**
 * A Google GSON implementation of contract.
 *
 * It serializes all fields of any Java {@link Object} passed to it.
 */
@Component
public final class CommandProcessingResultJsonSerializer {

    private final Gson gson;

    public CommandProcessingResultJsonSerializer() {
        final GsonBuilder builder = new GsonBuilder();
        GoogleGsonSerializerHelper.registerTypeAdapters(builder);
        builder.serializeNulls();
        this.gson = builder.create();
    }

    public String serialize(final Object result) {
        String returnedResult = null;
        final String serializedResult = this.gson.toJson(result);
        if (!"null".equalsIgnoreCase(serializedResult)) {
            returnedResult = serializedResult;
        }
        return returnedResult;
    }
}
