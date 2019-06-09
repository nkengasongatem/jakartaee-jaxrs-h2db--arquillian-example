#!/bin/sh
mvn clean package && docker build -t com.creatixx.users/users .
docker rm -f users || true && docker run -d -p 8080:8080 -p 4848:4848 --name users com.creatixx.users/users 
