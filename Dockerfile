FROM openjdk:17
COPY ./out/production/Connect-four/ /app
WORKDIR /app
ENTRYPOINT ["java","Connect-four"]