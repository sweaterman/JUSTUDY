FROM azul/zulu-openjdk:11

WORKDIR /server

COPY ./build/libs/justudy-0.0.1-SNAPSHOT.jar server.jar

ENTRYPOINT ["java", "-jar", "server.jar"]