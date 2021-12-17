FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD

WORKDIR /build/

COPY pom.xml mvnw mvnw.cmd /build/

RUN mvn -N io.takari:maven:wrapper

COPY src /build/src/

# Modified for OS
RUN mvn package -Dmaven.test.skip=true

FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

RUN mkdir -p /app/resources/i18n/ui

COPY --from=MAVEN_BUILD /build/target/verification.company-0.0.1-SNAPSHOT.jar /app/
COPY --from=MAVEN_BUILD /build/src/main/resources/i18n/ui/* /app/resources/i18n/ui/

# For OpenShift - nothing runs with ROOT 
#RUN chmod -R u=rwx,g=rw,o=rw /app && chmod -R u=rwx,g=rw,o=rw /app/*
RUN chgrp -R 0 /app && \
    chmod -R g=u /app && \
    chgrp -R 0 /app/* && \
    chmod -R g=u /app/*

EXPOSE 8080

ENTRYPOINT ["java", "-Dlog4j2.formatMsgNoLookups=true","-jar", "verification.company-0.0.1-SNAPSHOT.jar"]
