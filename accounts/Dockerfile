#Start with a base image "java" containing Java runtime
# image name openjdk and tag name is 21
FROM openjdk:21-jdk-slim

# MAINTAINER instruction is deprecated in favor of using label
MAINTAINER easybank.com
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="easybank.com"

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-Dspring.h2.console.enabled=true", "-Dspring.h2.console.settings.web-allow-others=true", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]