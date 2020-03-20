FROM java:8
VOLUME /tmp
ADD /target/manager-0.0.1-SNAPSHOT.jar manager.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","manager.jar"]