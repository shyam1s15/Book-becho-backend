package com.bookbecho.infrastructure.core.serialization;

import com.bookbecho.infrastructure.core.api.DateAdapter;
import com.bookbecho.infrastructure.core.api.ExternalIdAdapter;
import com.bookbecho.infrastructure.core.api.JodaDateTimeAdapter;
import com.bookbecho.infrastructure.core.api.JodaMonthDayAdapter;
import com.bookbecho.infrastructure.core.api.LocalDateAdapter;
import com.bookbecho.infrastructure.core.api.LocalDateTimeAdapter;
import com.bookbecho.infrastructure.core.api.LocalTimeAdapter;
import com.bookbecho.infrastructure.core.api.OffsetDateTimeAdapter;
import com.bookbecho.infrastructure.core.api.ParameterListExclusionStrategy;
import com.bookbecho.infrastructure.core.api.ParameterListInclusionStrategy;
import com.bookbecho.infrastructure.core.domain.ExternalId;
import com.bookbecho.infrastructure.core.exception.UnsupportedParameterException;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Helper class for serialization of Java objects into JSON using Google's GSON.
 */
@Service
public final class GoogleGsonSerializerHelper {

    public Gson createGsonBuilderForPartialResponseFiltering(final boolean prettyPrint, final Set<String> responseParameters) {
        final ExclusionStrategy strategy = new ParameterListInclusionStrategy(responseParameters);

        final GsonBuilder builder = new GsonBuilder().addSerializationExclusionStrategy(strategy);
        registerTypeAdapters(builder);
        if (prettyPrint) {
            builder.setPrettyPrinting();
        }
        return builder.create();
    }

    public Gson createGsonBuilderWithParameterExclusionSerializationStrategy(final Set<String> supportedParameters,
                                                                             final boolean prettyPrint, final Set<String> responseParameters) {

        final Set<String> parameterNamesToSkip = new HashSet<>();

        if (!responseParameters.isEmpty()) {
            // strip out all known support parameters from expected response to
            // see if unsupported parameters requested for response.
            final Set<String> differentParametersDetectedSet = new HashSet<>(responseParameters);
            differentParametersDetectedSet.removeAll(supportedParameters);

            if (!differentParametersDetectedSet.isEmpty()) {
                throw new UnsupportedParameterException(new ArrayList<>(differentParametersDetectedSet));
            }

            parameterNamesToSkip.addAll(supportedParameters);
            parameterNamesToSkip.removeAll(responseParameters);
        }

        final ExclusionStrategy strategy = new ParameterListExclusionStrategy(parameterNamesToSkip);

        final GsonBuilder builder = new GsonBuilder().addSerializationExclusionStrategy(strategy);
        registerTypeAdapters(builder);
        if (prettyPrint) {
            builder.setPrettyPrinting();
        }
        return builder.create();
    }

    public String serializedJsonFrom(final Gson serializer, final Object[] dataObjects) {
        return serializer.toJson(dataObjects);
    }

    public String serializedJsonFrom(final Gson serializer, final Object singleDataObject) {
        return serializer.toJson(singleDataObject);
    }

    public static Gson createSimpleGson() {
        return createGsonBuilder().create();
    }

    public static GsonBuilder createGsonBuilder() {
        return createGsonBuilder(false);
    }

    public static GsonBuilder createGsonBuilder(final boolean prettyPrint) {
        final GsonBuilder builder = new GsonBuilder();
        registerTypeAdapters(builder);
        if (prettyPrint) {
            builder.setPrettyPrinting();
        }
        return builder;
    }

    public static void registerTypeAdapters(final GsonBuilder builder) {
        builder.registerTypeAdapter(java.util.Date.class, new DateAdapter());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        // NOTE: was missing, necessary for GSON serialization with JDK 17's restrictive module access
        builder.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter());
        builder.registerTypeAdapter(ZonedDateTime.class, new JodaDateTimeAdapter());
        builder.registerTypeAdapter(MonthDay.class, new JodaMonthDayAdapter());
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        builder.registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter());
        builder.registerTypeAdapter(ExternalId.class, new ExternalIdAdapter());
    }
}

