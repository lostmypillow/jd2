package com.lostmypillow.jd2.structure;

public record PluginConfigEntry(
    AbstractType abstractType,
    Object defaultValue,
    String docs,
    String enumLabel,
    String[][] enumOptions,
    String interfaceName,
    String key,
    String storage,
    String type,
    Object value
) {}
