package com.lostmypillow.jd2.structure;

public record Extension(
    String configInterface,
    String description,
    Boolean enabled,
    String iconKey,
    String id,
    Boolean installed,
    String name
) {}
