FROM openjdk:8-jdk-alpine
ADD build/libs/hospital-backend-0.0.1-SNAPSHOT-plain.jar hospital.jar
ENTRYPOINT ["java", "-jar","hospital.jar"]
EXPOSE 8080