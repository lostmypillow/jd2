package com.lostmypillow.jd2.structure;

public record ExtensionQuery(
    Boolean configInterface,
    Boolean description,
    Boolean enabled,
    Boolean iconKey,
    Boolean installed,
    Boolean name,
    String pattern
) {
    public ExtensionQuery() {
        this(null, null, null, null, null, null, null);
    }
}
