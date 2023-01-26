FROM azul/zulu-openjdk:11

WORKDIR /server

COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar server.jar

ENTRYPOINT ["java", "-jar", "server.jar"]
