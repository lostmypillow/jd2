package com.lostmypillow.jd2.extraction;

import com.lostmypillow.jd2.structure.ArchiveSettings;
import com.lostmypillow.jd2.structure.ArchiveStatus;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

@HttpExchange("/extraction")
public interface ExtractionClient {
    @PostExchange("/addArchivePassword")
    void addArchivePassword(@RequestParam("password") String password);

    @PostExchange("/cancelExtraction")
    Jd2Response<Boolean> cancelExtraction(@RequestParam("controllerID") long controllerID);

    /**
     * Gets archive info for links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @return a {@link Jd2Response} containing a {@link List} of {@link ArchiveStatus}
     */
    @PostExchange("/getArchiveInfo")
    Jd2Response<List<ArchiveStatus>> getArchiveInfo(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );

    /**
     * Gets archive settings
     *
     * @param archiveIds a stringified {@code String[]} of archive IDs
     * @return a {@link Jd2Response} containing a {@link List} of {@link ArchiveSettings}
     */
    @PostExchange("/getArchiveSettings")
    Jd2Response<List<ArchiveSettings>> getArchiveSettings(@RequestParam("archiveIds") String archiveIds);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @GetExchange("/getQueue")
    Jd2Response<List<ArchiveStatus>> getQueue();

    /**
     * Sets archive settings
     *
     * @param archiveId archive ID
     * @param archiveSettings a stringified {@link ArchiveSettings} object
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/setArchiveSettings")
    Jd2Response<Boolean> setArchiveSettings(
            @RequestParam("archiveId") String archiveId,
            @RequestParam("archiveSettings") String archiveSettings
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Starts extraction immediately for links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @return a {@link Jd2Response} containing a map of archive IDs to extraction status
     */
    @PostExchange("/startExtractionNow")
    Jd2Response<Map<String, Boolean>> startExtractionNow(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
}

