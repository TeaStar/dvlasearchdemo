FROM openjdk
VOLUME /tmp
ADD dvlasearch.jar app.jar
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
