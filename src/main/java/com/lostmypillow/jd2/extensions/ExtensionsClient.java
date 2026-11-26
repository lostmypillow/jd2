package com.lostmypillow.jd2.extensions;

import com.lostmypillow.jd2.structure.Extension;
import com.lostmypillow.jd2.structure.ExtensionQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/extensions")
public interface ExtensionsClient {
    @PostExchange("/install")
    Jd2Response<Boolean> install(@RequestParam("id") String id);

    @GetExchange("/isEnabled")
    Jd2Response<Boolean> isEnabled(@RequestParam("classname") String classname);

    @GetExchange("/isInstalled")
    Jd2Response<Boolean> isInstalled(@RequestParam("id") String id);

    /**
     * Lists extensions
     *
     * @param query a stringified {@link ExtensionQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link Extension}
     */
    @PostExchange("/list")
    Jd2Response<List<Extension>> list(@RequestParam("query") String query);

    @PostExchange("/setEnabled")
    Jd2Response<Boolean> setEnabled(
            @RequestParam("classname") String classname,
            @RequestParam("b") boolean b
    );
}

