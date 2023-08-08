FROM openjdk:17-jdk
LABEL maintainer="minhyung0237@gmail.com"
ARG JAR_FILE=build/libs/basicbe-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} docker-springboot.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-springboot.jar"]