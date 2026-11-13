package com.lostmypillow.jd2.structure;

import com.lostmypillow.jd2.JsonQuery;
import com.lostmypillow.jd2.util.Json;

public record AddLinksQuery (
    Boolean assignJobID,
    Boolean autoExtract,
    Boolean autostart,
    String[] dataURLs,
    Boolean deepDecrypt,
    String destinationFolder,
    String downloadPassword,
    String extractPassword,
    String links,
    Boolean overwritePackagizerRules,
    String packageName,
    Priority priority,
    String sourceUrl
) {
    public AddLinksQuery(String links) {
        this(
                true,
                false,
                false,
                null,
                false,
                "/app",
                "",
                "",
                links,
                false,
                "packageName",
                Priority.HIGH,
                links
        );
    }
}
