FROM adoptopenjdk:11-jre-hotspot
MAINTAINER pichincha.com
RUN mkdir -p /app/
ADD build/libs/transactions-0.0.1-SNAPSHOT.jar /app/transactions-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/transactions-0.0.1-SNAPSHOT.jar"]