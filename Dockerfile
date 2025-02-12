# Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

# Add Maintainer Info
MAINTAINER duncan-cadoret.com

# Copy the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# When the container launches, execute this command
ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]