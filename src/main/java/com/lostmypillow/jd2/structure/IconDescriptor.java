package com.lostmypillow.jd2.structure;

import java.util.List;
import java.util.Map;

public record IconDescriptor(
    String cls,
    String key,
    Map<String, Object> prps,
    List<IconDescriptor> rsc
) {}
