package com.lostmypillow.jd2.contentv2;

import com.lostmypillow.jd2.structure.IconDescriptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contentV2")
public class ContentV2Controller {
    public final ContentV2Client client;

    public ContentV2Controller(ContentV2Client client) {
        this.client = client;
    }

    @GetMapping("/getFavIcon")
    public ResponseEntity<Void> getFavIcon(@RequestParam("hostername") String hostername) {
        client.getFavIcon(hostername);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getFileIcon")
    public ResponseEntity<Void> getFileIcon(@RequestParam("filename") String filename) {
        client.getFileIcon(filename);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getIcon")
    public ResponseEntity<Void> getIcon(
            @RequestParam("key") String key,
            @RequestParam("size") int size
    ) {
        client.getIcon(key, size);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getIconDescription")
    public ResponseEntity<IconDescriptor> getIconDescription(@RequestParam("key") String key) {
        return ResponseEntity.ok(client.getIconDescription(key).data());
    }
}

