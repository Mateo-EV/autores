# Etapa 1: Build
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw -B -DskipTests clean install

# Etapa 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/autores-0.0.1.jar app.jar

# Crear directorio para imágenes persistentes
RUN mkdir -p /app/uploads/images

# Crear volumen para persistencia de imágenes
VOLUME ["/app/uploads"]

EXPOSE 8080
CMD ["java", "-Xms128m", "-Xmx256m", "-XX:MaxMetaspaceSize=128m", "-Xss256k", "-XX:+UseSerialGC", "-jar", "app.jar"]