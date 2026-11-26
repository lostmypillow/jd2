package com.lostmypillow.jd2.constants;

public enum Context {
    LGC("Linkgrabber Rightclick Context"),
    DLC("Downloadlist Rightclick Context");

    private final String description;

    Context(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
