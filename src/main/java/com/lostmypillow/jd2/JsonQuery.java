package com.lostmypillow.jd2;

import tools.jackson.databind.ObjectMapper;

public interface JsonQuery {

    // Shared thread-safe mapper instance
    ObjectMapper MAPPER = new ObjectMapper();

    default String toJson() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to stringify query", e);
        }
    }
}