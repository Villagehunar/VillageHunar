FROM openjdk:17.0.1-jdk-slim
COPY . .

RUN mvn clean package -DskipTests


COPY --from=build /target/seeker-0.0.1.jar app.java

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]