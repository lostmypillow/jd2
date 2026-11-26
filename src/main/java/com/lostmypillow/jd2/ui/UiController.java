package com.lostmypillow.jd2.ui;

import com.lostmypillow.jd2.constants.Context;
import com.lostmypillow.jd2.structure.MyJDMenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ui")
public class UiController {
    private final UiClient uiClient;

    public UiController(UiClient uiClient) {
        this.uiClient = uiClient;
    }

    @GetMapping("/getMenu")
    public ResponseEntity<MyJDMenuItem> getMenu(@RequestParam("context") Context context) {
        return ResponseEntity.ok(uiClient.getMenu(context).data());
    }

    @PostMapping("/invokeAction")
    public ResponseEntity<Object> invokeAction(
            @RequestParam("context") Context context,
            @RequestParam("id") String id,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        return ResponseEntity.ok(uiClient.invokeAction(context, id, linkIds, packageIds).data());
    }
}

