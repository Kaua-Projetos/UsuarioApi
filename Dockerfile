# Estágio de build
FROM maven:3.9-eclipse-temurin-21-alpine AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml
COPY pom.xml .

# Copie o Maven wrapper
COPY .mvn/ .mvn/
COPY mvnw mvnw.cmd ./

# Garanta que o script mvnw tenha permissão de execução
RUN chmod +x ./mvnw

# Copie o código fonte
COPY src ./src

# Execute o build com Maven, pulando os testes
RUN ./mvnw -B -DskipTests clean package

# Estágio de execução
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copie apenas o JAR gerado do estágio de build
COPY --from=build /app/target/Gerenciador-de-Tarefas-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta da aplicação
EXPOSE 8080

# Defina o comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]

