FROM openjdk:17
MAINTAINER com.booking.bus.pavan
COPY target/Bus-ms-Admin-0.0.1-SNAPSHOT.jar app.jar
RUN useradd -u 8877 audits
USER audits
EXPOSE  8086
ENTRYPOINT ["java","-jar","/app.jar"]