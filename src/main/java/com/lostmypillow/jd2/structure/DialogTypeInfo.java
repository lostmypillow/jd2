package com.lostmypillow.jd2.structure;

import java.util.Map;

public record DialogTypeInfo(
    Map<String, String> in,
    Map<String, String> out
) {}
