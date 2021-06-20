FROM java:alpine
VOLUME /tmp
EXPOSE 8080
ADD user-server-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
