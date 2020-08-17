FROM maven:3.6.3-jdk-11

EXPOSE 8080

WORKDIR /app

CMD ["mvn","compile","quarkus:dev"]
