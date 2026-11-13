package com.lostmypillow.jd2.sse;

// e.g., "orders", "users", "system-alerts"
public record SseDomainEvent(String namespace, Object data) {
}
