package com.lostmypillow.jd2.downloadsv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.Reason;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DownloadsV2ClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DownloadsV2Client downloadsV2Client;

    @Test
    void cleanup() {
        assertDoesNotThrow(() -> downloadsV2Client.cleanup(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                Action.DELETE_ALL,
                Mode.REMOVE_LINKS_ONLY,
                SelectionType.NONE
        ));
    }

    @Test
    void forceDownload() {
        Jd2Response<Boolean> data = downloadsV2Client.forceDownload(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void getDownloadUrls() {
        Jd2Response<Map<String, List<Long>>> data = downloadsV2Client.getDownloadUrls(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                new String[]{"ORIGIN"}
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Map.class, data.data(), "data.data() should be an instance of Map");
    }

    @Test
    void getStopMark() {
        Jd2Response<Long> data = downloadsV2Client.getStopMark();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Long.class, data.data(), "data.data() should be an instance of Long");
    }

    @Test
    void getStopMarkedLink() {
        Jd2Response<DownloadLink> data = downloadsV2Client.getStopMarkedLink();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void getStructureChangeCounter() {
        Jd2Response<Long> data = downloadsV2Client.getStructureChangeCounter(0L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Long.class, data.data(), "data.data() should be an instance of Long");
    }

    @Test
    void moveLinks() {
        assertDoesNotThrow(() -> downloadsV2Client.moveLinks(Json.stringify(new long[]{}), -1L, -1L));
    }

    @Test
    void movePackages() {
        assertDoesNotThrow(() -> downloadsV2Client.movePackages(Json.stringify(new long[]{}), -1L));
    }

    @Test
    void movetoNewPackage() {
        assertDoesNotThrow(() -> downloadsV2Client.movetoNewPackage(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                "newPkg",
                "/downloads"
        ));
    }

    @Test
    void packageCount() {
        Jd2Response<Integer> data = downloadsV2Client.packageCount();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
    }

    @Test
    void queryLinks() {
        Jd2Response<List<DownloadLink>> data = downloadsV2Client.queryLinks(
                Json.stringify(new LinkQuery())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void queryPackages() {
        Jd2Response<List<FilePackage>> data = downloadsV2Client.queryPackages(
                Json.stringify(new PackageQuery())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void removeLinks() {
        assertDoesNotThrow(() -> downloadsV2Client.removeLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void removeStopMark() {
        assertDoesNotThrow(() -> downloadsV2Client.removeStopMark());
    }

    @Test
    void renameLink() {
        assertDoesNotThrow(() -> downloadsV2Client.renameLink(1L, "newName"));
    }

    @Test
    void renamePackage() {
        assertDoesNotThrow(() -> downloadsV2Client.renamePackage(1L, "newPackageName"));
    }

    @Test
    void resetLinks() {
        assertDoesNotThrow(() -> downloadsV2Client.resetLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void resumeLinks() {
        assertDoesNotThrow(() -> downloadsV2Client.resumeLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void setDownloadDirectory() {
        assertDoesNotThrow(() -> downloadsV2Client.setDownloadDirectory("/downloads", Json.stringify(new long[]{})));
    }

    @Test
    void setDownloadPassword() {
        Jd2Response<Boolean> data = downloadsV2Client.setDownloadPassword(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                "password"
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void setEnabled() {
        assertDoesNotThrow(() -> downloadsV2Client.setEnabled(
                true,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void setPriority() {
        assertDoesNotThrow(() -> downloadsV2Client.setPriority(
                Priority.DEFAULT,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void setStopMark() {
        assertDoesNotThrow(() -> downloadsV2Client.setStopMark(-1L, -1L));
    }

    @Test
    void splitPackageByHoster() {
        assertDoesNotThrow(() -> downloadsV2Client.splitPackageByHoster(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void startOnlineStatusCheck() {
        assertDoesNotThrow(() -> downloadsV2Client.startOnlineStatusCheck(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void unskip() {
        Jd2Response<Boolean> data = downloadsV2Client.unskip(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                Reason.MANUAL
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

