package com.lostmypillow.jd2.constants;

public enum ArchiveFileStatus {
    COMPLETE("File is available for extraction"),
    INCOMPLETE("File exists, but is incomplete"),
    MISSING("File does not exist");

    private final String description;

    ArchiveFileStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
