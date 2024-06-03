# Etapa de construcción
FROM amazoncorretto:21-alpine-jdk
#COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
#FROM openjdk:17-jdk-slim
COPY target/postulaciones-management-system-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Variables de entorno de la base de datos
#ENV SPRING_DATASOURCE_URL=jdbc:mysql://<host>:<port>/pms?serverTimezone=America/Argentina/Jujuy
#ENV SPRING_DATASOURCE_USERNAME=<username>
#ENV SPRING_DATASOURCE_PASSWORD=<password>

# Otras configuraciones
#ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect
#ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
#ENV SPRING_LOGGING_LEVEL_ORG_HIBERNATE_SQL=DEBUG
#ENV SPRING_LOGGING_LEVEL_ORG_HIBERNATE_TYPE=TRACE
#ENV SPRING_LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_SECURITY=TRACE
#ENV SPRING_SERVLET_MULTIPART_MAX_REQUEST_SIZE=6MB
#ENV SPRING_SERVLET_MULTIPART_MAX_FILE_SIZE=6MB
