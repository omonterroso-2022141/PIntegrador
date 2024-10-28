# Usa una imagen de base con Java 17 (o la versión que uses)
FROM openjdk:17-jdk-slim AS build

# Define el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo de Maven Wrapper y el archivo de configuración de Maven
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Agrega permisos de ejecución a mvnw
RUN chmod +x mvnw

# Descarga dependencias sin construir el código (mejora tiempos de construcción en cambios de código)
RUN ./mvnw dependency:go-offline -B

# Copia el código fuente del proyecto al contenedor
COPY src ./src

# Construye el proyecto y genera el archivo JAR
RUN ./mvnw clean package -DskipTests

# Usa una nueva imagen de base solo con el JRE para reducir el tamaño de la imagen final
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
