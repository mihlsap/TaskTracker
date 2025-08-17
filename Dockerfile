#FROM amazoncorretto:24-alpine3.22-jdk
#ARG JAR_FILE=target/*.jar
#COPY ./target/TaskTracker-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#EXPOSE 8080

FROM amazoncorretto:24-alpine3.22-jdk as build

RUN apk update && apk add maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src /app/src

RUN mvn clean package -DskipTests

FROM amazoncorretto:24-alpine3.22-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
