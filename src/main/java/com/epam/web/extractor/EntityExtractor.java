package com.epam.web.extractor;

import java.util.Map;

public interface EntityExtractor<T> {
    Map<String, Object> parse(T entity);
}
