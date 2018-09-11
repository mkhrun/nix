FROM openjdk:10.0.2
COPY eurekaserver/target/eurekaserver.jar ./
COPY services/one/target/one.jar ./
COPY services/two/target/two.jar ./
COPY start-apps.sh ./
WORKDIR ./
EXPOSE 9091 8080 8081
CMD ["bash", "start-apps.sh"]
