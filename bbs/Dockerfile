FROM java:8
VOLUME /tmp
ADD /target/bbs-0.0.1-SNAPSHOT.jar bbs.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","bbs.jar"]