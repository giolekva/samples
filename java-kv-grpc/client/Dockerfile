FROM kv:latest

WORKDIR /kv/client

ENV SERVER_ADDRESS "localhost:8081"

CMD java -jar target/client-0.0.1-SNAPSHOT-jar-with-dependencies.jar ${SERVER_ADDRESS}