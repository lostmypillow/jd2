package com.lostmypillow.jd2.constants;

public enum Type {
    // MenuStructure types
    CONTAINER,
    ACTION,
    LINK,

    // BasicAuthentication types
    FTP,
    HTTP;

    public enum Menu {
        CONTAINER,
        ACTION,
        LINK
    }

    public enum Auth {
        FTP,
        HTTP
    }
}
