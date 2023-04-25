package com.bookbecho.infrastructure.core.domain;

import com.bookbecho.infrastructure.core.exception.PlatformInternalServerException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 * ExternalId Value object
 */
@Getter
@EqualsAndHashCode
public class ExternalId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;
    private static final ExternalId empty = new ExternalId();
    private final String value;

    private ExternalId() {
        this.value = null;
    }

    public ExternalId(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("error.external.id.cannot.be.blank");
        }
        this.value = value;
    }

    /**
     * @return Create a new ExternalId object where value is a newly generated UUID
     */
    public static ExternalId generate() {
        return new ExternalId(UUID.randomUUID().toString());
    }

    /**
     * @return Create and return an empty ExternalId object
     */
    public static ExternalId empty() {
        return empty;
    }

    /**
     * @return whether value is null for the ExternalId object (return true if value is null)
     */
    public boolean isEmpty() {
        return value == null;
    }

    /**
     * Throws exception if value was not set (value is null) for this object
     *
     * @throws PlatformInternalServerException
     *             if value was not set (value is null) for this object
     */
    public void throwExceptionIfEmpty() {
        if (isEmpty()) {
            throw new PlatformInternalServerException("error.external.id.is.not.set", "Internal state violation: External id is not set");
        }
    }
}
