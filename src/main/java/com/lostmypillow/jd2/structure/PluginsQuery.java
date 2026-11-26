package com.lostmypillow.jd2.structure;

public record PluginsQuery(
    Boolean pattern,
    Boolean version
) {
    public PluginsQuery() {
        this(null, null);
    }
}
