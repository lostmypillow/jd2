package com.lostmypillow.jd2.structure;

import java.util.List;

public record MyJDMenuItem(
    List<MenuStructure> children,
    String icon,
    String id,
    String name,
    MenuStructure.Type type
) {}
