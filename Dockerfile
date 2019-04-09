FROM anapsix/alpine-java

WORKDIR /AppServer
ADD target/desafio-b2w.jar desafio-b2w.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /AppServer/desafio-b2w.jar"]