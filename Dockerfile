
FROM eclipse-temurin:21-jre-alpine

RUN apk add --no-cache \
    tzdata \
    fontconfig \
    ttf-dejavu \
    wget \
    curl

WORKDIR /opt/jdownloader

# Download official JDownloader launcher
RUN wget -q https://installer.jdownloader.org/JDownloader.jar

# Configure headless operation and enable the deprecated local API
RUN mkdir -p cfg && \
    printf '%s\n' \
    '{' \
    '  "deprecatedapienabled": true,' \
    '  "deprecatedapilocalhostonly": false,' \
    '  "headlessmyjdownloadermandatory": false' \
    '}' \
    > cfg/org.jdownloader.api.RemoteAPIConfig.json

EXPOSE 3128

ENTRYPOINT ["java", \
    "-Djava.awt.headless=true", \
    "-Dcom.sun.management.jmxremote=false", \
    "-jar", "JDownloader.jar", \
    "-norestart"]
