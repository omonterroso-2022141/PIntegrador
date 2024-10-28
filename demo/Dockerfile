# Usa una imagen base con Java 17 para la construcción
FROM openjdk:17-jdk-slim AS build

# Define el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y el Maven Wrapper
COPY pom.xml ./
COPY mvnw ./
COPY .mvn/ .mvn/

# Asegúrate de que mvnw sea ejecutable
RUN chmod +x mvnw

# Descarga las dependencias de Maven sin compilar el código
RUN ./mvnw dependency:go-offline -B

# Copia el código fuente y construye el proyecto
COPY src ./src
RUN ./mvnw clean install -DskipTests

# Usa una imagen base para la ejecución
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR construido desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
