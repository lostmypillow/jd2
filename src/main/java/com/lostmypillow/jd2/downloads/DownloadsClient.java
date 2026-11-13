package com.lostmypillow.jd2.downloads;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.DownloadLink;
import com.lostmypillow.jd2.structure.FilePackage;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/downloads")
public interface DownloadsClient {
    @PostExchange("/disableLinks")
    Jd2Response<Boolean> disableLinks(
            @RequestParam("linkIds") List<String> linkIds,
            @RequestParam(name = "packageIds", required = false) List<String> packageIds
    );

    @PostExchange("/enableLinks")
    Jd2Response<Boolean> enableLinks(
            @RequestParam("linkIds") List<String> linkIds,
            @RequestParam(name = "packageIds", required = false) List<String> packageIds
    );

    @PostExchange("/forceDownload")
    Jd2Response<Boolean> forceDownload(
            @RequestParam("linkIds") List<String> linkIds,
            @RequestParam(name = "packageIds", required = false) List<String> packageIds
    );

    @PostExchange("/getChildrenChanged")
    Jd2Response<Long> getChildrenChanged(
            @RequestParam("structureWatermark") Long structureWatermark
    );

    @GetExchange("/getJDState")
    Jd2Response<String> getJDState();

    @PostExchange("/moveLinks")
    Jd2Response<Boolean> moveLinks(
            @RequestParam("query") String query
    );
    //    query is stringified APIQuery

    @PostExchange("/movePackages")
    Jd2Response<Boolean> movePackages(
            @RequestParam("query") String query
    );
    //    query is stringified APIQuery

    @GetExchange("/packageCount")
    Jd2Response<Integer> packageCount();

    @PostExchange("/pause")
    Jd2Response<Boolean> pause(@RequestParam("value") Boolean value);

    @GetExchange("/queryLinks")
    Jd2Response<List<DownloadLink>> queryLinks(@RequestParam("queryParams") String queryParams);
    //    queryParams is stringified APIQuery

    @GetExchange("/queryPackages")
    Jd2Response<List<FilePackage>> queryPackages(@RequestParam("queryParams") String queryParams);
    //    queryParams is stringified APIQuery

    @PostExchange("/removeLinks")
    Jd2Response<Boolean> removeLinks(
            @RequestParam("linkIds") List<String> linkIds,
            @RequestParam(name = "packageIds", required = false) List<String> packageIds
    );

    @PostExchange("/renamePackage")
    Jd2Response<Boolean> renamePackage(
            @RequestParam("packageId") Long packageId,
            @RequestParam("newName") String newName
    );

    @PostExchange("/resetLinks")
    Jd2Response<Boolean> resetLinks(
            @RequestParam("linkIds") List<String> linkIds,
            @RequestParam(name = "packageIds", required = false) List<String> packageIds
    );

    @GetExchange("/speed")
    Jd2Response<Integer> speed();

    @PostExchange("/start")
    Jd2Response<Boolean> start();

    @PostExchange("/stop")
    Jd2Response<Boolean> stop();

}
