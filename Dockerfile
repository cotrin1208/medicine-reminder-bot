FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:17-jdk-slim AS cds
COPY --from=build /home/gradle/src/build/libs/medicine-reminder-bot-all.jar /app.jar
RUN timeout -s INT 5 java -XX:ArchiveClassesAtExit=app-cds.jsa -jar app.jar

FROM openjdk:11-jdk-slim
EXPOSE 8080:8080COPY --from=build /home/gradle/src/build/libs/medicine-reminder-bot-all.jar /app.jar
COPY --from=cds app-cds.jsa app-cds.jsa
COPY src/main/resources/application.conf application.conf
ENTRYPOINT exec java -XX:+TieredCompilation -XX:TieredStopAtLevel=1 -Xshare:on -XX:SharedArchiveFile=app-cds.jsa -jar app.jar -config=application.conf
