FROM openjdk:10.0.2
COPY *.jar ./
COPY start-apps.sh ./
WORKDIR ./
EXPOSE 9091 8080 8081
CMD ["bash", "start-apps.sh"]