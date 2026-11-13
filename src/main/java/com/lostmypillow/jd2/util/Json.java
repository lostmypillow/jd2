package com.lostmypillow.jd2.util;

import tools.jackson.databind.ObjectMapper; // or com.fasterxml.jackson.databind.ObjectMapper

public final class Json {

    // Thread-safe, single shared instance
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Json() {} // Prevent instantiation

    public static String stringify(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to stringify object to JSON", e);
        }
    }
}