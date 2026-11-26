package com.lostmypillow.jd2.dialogs;

import com.lostmypillow.jd2.structure.DialogInfo;
import com.lostmypillow.jd2.structure.DialogTypeInfo;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

@HttpExchange("/dialogs")
public interface DialogsClient {
    /**
     * Answers a dialog
     *
     * @param id dialog ID
     * @param data a stringified {@link Map} of dialog answer data
     */
    @PostExchange("/answer")
    void answer(
            @RequestParam("id") long id,
            @RequestParam("data") String data
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:Answer Dialog 0 first
    // ResponseCode 400 (Bad Request); Type:Invalid ID: 0

    @GetExchange("/get")
    Jd2Response<DialogInfo> get(
            @RequestParam("id") long id,
            @RequestParam("icon") boolean icon,
            @RequestParam("properties") boolean properties
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:Invalid ID: 0

    @GetExchange("/getTypeInfo")
    Jd2Response<DialogTypeInfo> getTypeInfo(@RequestParam("dialogType") String dialogType);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:Requested Type not found!

    @GetExchange("/list")
    Jd2Response<long[]> list();
}

