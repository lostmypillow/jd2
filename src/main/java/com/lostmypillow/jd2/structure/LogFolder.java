package com.lostmypillow.jd2.structure;

public record LogFolder(
    Long created,
    Boolean current,
    Long lastModified
) {}
