package com.lostmypillow.jd2.structure;

import java.util.List;

/**
 * Leave Boolean values empty or set them to null if you want JD to use the default value
 */
public record ArchiveSettings(
    String archiveId,
    Boolean autoExtract,
    String extractPath,
    String finalPassword,
    List<String> passwords,
    Boolean removeDownloadLinksAfterExtraction,
    Boolean removeFilesAfterExtraction
) {}
