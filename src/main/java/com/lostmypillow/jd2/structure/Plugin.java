package com.lostmypillow.jd2.structure;

public record Plugin(
    AbstractType abstractType,
    String className,
    Object defaultValue,
    String displayName,
    String docs,
    String enumLabel,
    String[][] enumOptions,
    String interfaceName,
    String key,
    String pattern,
    String storage,
    String type,
    Object value,
    String version
) {}
