FROM java:alpine
VOLUME /tmp
EXPOSE 8080
ADD gateway-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
