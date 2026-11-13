package com.lostmypillow.jd2.structure;

import java.util.List;

public record MenuStructure(
    List<MenuStructure> children,
    String icon,
    String id,
    String name,
    Type type
) {
    public enum Type {
        CONTAINER,
        ACTION,
        LINK,
        SEPARATOR
    }
}
