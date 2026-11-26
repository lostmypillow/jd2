package com.lostmypillow.jd2.ui;

import com.lostmypillow.jd2.constants.Context;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.MyJDMenuItem;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/ui")
public interface UiClient {
    /**
     * Get the custom menu structure for the desired context
     *
     * @param context the {@link Context}
     * @return a {@link Jd2Response} containing a {@link MyJDMenuItem}
     */
    @GetExchange("/getMenu")
    Jd2Response<MyJDMenuItem> getMenu(@RequestParam("context") Context context);
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
    // ResponseCode 404 (Not Found); Type:Not Found

    /**
     * Invoke a menu action on our selection and get the results.
     *
     * @param context the {@link Context}
     * @param id action identifier
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @return a {@link Jd2Response} containing an {@link Object}
     */
    @PostExchange("/invokeAction")
    Jd2Response<Object> invokeAction(
            @RequestParam("context") Context context,
            @RequestParam("id") String id,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
    // ResponseCode 404 (Not Found); Type:Not Found
}

