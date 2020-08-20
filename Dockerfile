FROM maven:3.6.3-jdk-11

EXPOSE 8080

WORKDIR /app

CMD ["/app/mvnw","compile", "-Duser.home=/var/maven", "quarkus:dev"]
