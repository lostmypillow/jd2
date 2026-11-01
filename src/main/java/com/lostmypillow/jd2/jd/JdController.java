package com.lostmypillow.jd2.jd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdController {
    private final JdService service;

    public JdController(JdService service) {
        this.service = service;
    }

    @GetMapping("/jd/revision")
    public int revision() {
        return this.service.getCoreRevision();
    }

    @GetMapping("/jd/version")
    public long version() {
        return this.service.getVersion();
    }

    @GetMapping("/jd/uptime")
    public long uptime() {
        return this.service.getUptime();
    }


}
