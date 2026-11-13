package com.lostmypillow.jd2.dialogs;

import com.lostmypillow.jd2.structure.DialogInfo;
import com.lostmypillow.jd2.structure.DialogTypeInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dialogs")
public class DialogsController {
    public final DialogsClient client;

    public DialogsController(DialogsClient client) {
        this.client = client;
    }

    @PostMapping("/answer")
    public ResponseEntity<Void> answer(
            @RequestParam("id") long id,
            @RequestParam("data") String data
    ) {
        client.answer(id, data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<DialogInfo> get(
            @RequestParam("id") long id,
            @RequestParam("icon") boolean icon,
            @RequestParam("properties") boolean properties
    ) {
        return ResponseEntity.ok(client.get(id, icon, properties).data());
    }

    @GetMapping("/getTypeInfo")
    public ResponseEntity<DialogTypeInfo> getTypeInfo(@RequestParam("dialogType") String dialogType) {
        return ResponseEntity.ok(client.getTypeInfo(dialogType).data());
    }

    @GetMapping("/list")
    public ResponseEntity<long[]> list() {
        return ResponseEntity.ok(client.list().data());
    }
}

