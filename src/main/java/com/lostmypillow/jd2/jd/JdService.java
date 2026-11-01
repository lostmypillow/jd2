package com.lostmypillow.jd2.jd;

import com.lostmypillow.jd2.jd.records.JdApiResponse;
import org.springframework.stereotype.Service;

@Service
public class JdService {
    private final JdClient client;

    public JdService(JdClient client) {
        this.client = client;
    }

    private <T> T unwrap(JdApiResponse<T> response) {
        if (response == null || response.data() == null) {
            throw new IllegalStateException("Response body or data field is null.");
        }
        return response.data();
    }

    public int getCoreRevision() {
        return unwrap(client.getCoreRevision());
    }

    public long getUptime() {
        return unwrap(client.getUptime());
    }

    public long getVersion() {
        return unwrap(client.getVersion());
    }


}
