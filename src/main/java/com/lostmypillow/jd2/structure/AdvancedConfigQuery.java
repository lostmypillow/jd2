package com.lostmypillow.jd2.structure;

public record AdvancedConfigQuery(
    String configInterface,
    Boolean defaultValues,
    Boolean description,
    Boolean enumInfo,
    Boolean includeExtensions,
    String pattern,
    Boolean values
) {}
