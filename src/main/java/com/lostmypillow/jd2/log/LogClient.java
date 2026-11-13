package com.lostmypillow.jd2.log;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LogFolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/log")
public interface LogClient {
    @GetExchange("/getAvailableLogs")
    Jd2Response<List<LogFolder>> getAvailableLogs();

    /**
     * Sends log files
     *
     * @param logFolders a stringified {@code LogFolder[]} of log folders
     * @return a {@link Jd2Response} containing the log ID {@link String}
     */
    @PostExchange("/sendLogFile")
    Jd2Response<String> sendLogFile(@RequestParam("logFolders") String logFolders);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter
}

