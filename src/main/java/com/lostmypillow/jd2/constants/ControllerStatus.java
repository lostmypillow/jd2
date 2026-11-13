package com.lostmypillow.jd2.constants;

public enum ControllerStatus {
    RUNNING("Extraction is currently running"),
    QUEUED("Archive is queued for extraction and will run as soon as possible"),
    NA("No controller assigned");

    private final String description;

    ControllerStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
