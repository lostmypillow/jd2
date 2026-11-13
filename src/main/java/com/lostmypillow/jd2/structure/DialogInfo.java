package com.lostmypillow.jd2.structure;

import java.util.Map;

public record DialogInfo(
    Map<String, String> properties,
    String type
) {}
