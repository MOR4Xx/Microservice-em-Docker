
# Estágio 1: Build (Compilação e criação do Jar)
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Imagem final leve apenas para rodar)
FROM openjdk:22-jdk
WORKDIR /app
# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

