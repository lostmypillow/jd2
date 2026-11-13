package com.lostmypillow.jd2.structure;

import java.util.Map;

public record ArchiveStatus(
    /**
     * ID to adress the archive. Used for example for extraction/getArchiveSettings?[,,...]
     */
    String archiveId,
    String archiveName,
    /**
     * -1 or the controller ID if any controller is active. Used in cancelExtraction?
     */
    Long controllerId,
    /**
     * The status of the controller
     */
    ControllerStatus controllerStatus,
    /**
     * Map Keys: Filename of the Part-File. Values: ArchiveFileStatus
     * Example:
     * {
     * "archive.part1.rar":"COMPLETE",
     * "archive.part2.rar":"MISSING"
     * }
     */
    Map<String, ArchiveFileStatus> states,
    /**
     * Type of the archive. e.g. "GZIP_SINGLE", "RAR_MULTI","RAR_SINGLE",....
     */
    String type
) {}
