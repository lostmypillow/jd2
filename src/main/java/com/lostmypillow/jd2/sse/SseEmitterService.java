package com.lostmypillow.jd2.sse;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseEmitterService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter createEmitter() {
        var emitter = new SseEmitter(1_800_000L);

        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));
        emitter.onError((ex) -> this.emitters.remove(emitter));

        return emitter;
    }

    @EventListener
    public void handleDomainEvent(SseDomainEvent event) {
        List<SseEmitter> deadEmitters = new ArrayList<>();

        for (var emitter : emitters) {
            try {
                SseEmitter.SseEventBuilder sseEvent = SseEmitter.event()
                        .name(event.namespace())
                        .data(event.data());

                emitter.send(sseEvent);
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        }

        this.emitters.removeAll(deadEmitters);
    }
}
