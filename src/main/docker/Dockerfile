# From the openjdk 8 alpine in dockerhub
FROM openjdk:8-jre-alpine

# Add our project specific scripts, configs and deployment files
ADD restapi-1.0.0.jar  /restapi-1.0.0.jar

MAINTAINER PriyaParvathaneni "krishna.parvathaneni@centurylink.com"

EXPOSE 8080

CMD ["java","-jar", "/restapi.jar"]
