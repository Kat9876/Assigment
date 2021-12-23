FROM openjdk:8
EXPOSE 8080
ADD target/assignment.jar assignment.jar
ENTRYPOINT ["java","-jar","/assignment.jar"]