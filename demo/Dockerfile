# Usa una imagen base con Java
RUN chmod +x ./mvnw

FROM openjdk:17-jdk-slim AS build

# Define el directorio de trabajo
WORKDIR /app

# Copia los archivos de Maven Wrapper y asigna permisos
COPY mvnw .
COPY .mvn/ .mvn


# Copia el archivo pom.xml para descargar dependencias
COPY pom.xml .

# Ejecuta el comando dependency:go-offline para descargar dependencias sin compilar aún el código
RUN ./mvnw dependency:go-offline -B

# Copia el código fuente y construye el proyecto
COPY src ./src
RUN ./mvnw clean install -DskipTests
