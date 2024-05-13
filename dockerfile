FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/ClusteredData-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "ClusteredData-0.0.1-SNAPSHOT.jar"]
