#!/bin/bash

./gradlew clean build
docker image build -t demoauthapi .
docker network create authapi-net

docker run -d -p 8080:8080 --net authapi-net --name demoauthapi demoauthapi
docker run --net authapi-net -p 1080:1080 -p 1025:1025 --name maildev soulteary/maildev

echo "You're good to go :)"