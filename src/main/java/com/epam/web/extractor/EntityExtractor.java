package com.epam.web.extractor;

import java.util.Map;

/**
 * The {@code EntityExtractor<T>} interface represents the implementation of writing object fields to the {@link Map}.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface EntityExtractor<T> {
    /**
     * Writing object fields to the map
     *
     * @param entity the current entity
     */
    Map<String, Object> parse(T entity);
}
