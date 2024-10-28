# Usa una imagen base con Java
FROM openjdk:17-jdk-slim AS build

# Define el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml para descargar dependencias
COPY pom.xml .

# Copia los archivos de Maven Wrapper y asigna permisos
COPY mvnw .
COPY .mvn/ .mvn

# Asegúrate de que mvnw sea ejecutable
RUN chmod +x mvnw

# Ejecuta el comando dependency:go-offline para descargar dependencias sin compilar aún el código
RUN ./mvnw dependency:go-offline -B

# Copia el código fuente y construye el proyecto
COPY src ./src
RUN ./mvnw clean install -DskipTests
