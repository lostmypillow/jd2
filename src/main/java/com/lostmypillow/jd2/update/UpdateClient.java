package com.lostmypillow.jd2.update;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/update")
public interface UpdateClient {
    /**
     * Checks whether an update is available
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @GetExchange("/isUpdateAvailable")
    Jd2Response<Boolean> isUpdateAvailable();

    /**
     * Restarts JDownloader and applies available updates
     */
    @PostExchange("/restartAndUpdate")
    void restartAndUpdate();

    /**
     * Runs an update check
     */
    @PostExchange("/runUpdateCheck")
    void runUpdateCheck();
}

