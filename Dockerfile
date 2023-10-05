FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*.war
COPY ${JAR_FILE} /app.war
ENTRYPOINT ["java","-jar","/app.war"]
EXPOSE 8080