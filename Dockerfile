FROM openjdk:9-jdk-alpine
ADD build/libs/hospital-backend-0.0.1-SNAPSHOT.jar hospital.jar
ENTRYPOINT ["java", "-jar","hospital.jar"]
EXPOSE 8080
