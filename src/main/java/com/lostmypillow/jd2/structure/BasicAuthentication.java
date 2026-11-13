package com.lostmypillow.jd2.structure;

public record BasicAuthentication(
    Long created,
    Boolean enabled,
    String hostmask,
    Long id,
    Long lastValidated,
    String password,
    Type type,
    String username
) {
    public enum Type {
        BASIC,
        FORM,
        AUTO
    }
}
