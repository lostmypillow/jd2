package com.lostmypillow.jd2.structure;

import java.util.List;

public record CnlQuery(
    String comment,
    String crypted,
    String dir,
    String jk,
    String key,
    String orgReferrer,
    String orgSource,
    String packageName,
    List<String> passwords,
    Boolean permission,
    String referrer,
    String source,
    String urls
) {
    public CnlQuery() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}
