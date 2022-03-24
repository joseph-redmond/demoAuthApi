#!/bin/bash

docker kill demoauthapi
docker rm demoauthapi
docker kill maildev
docker rm maildev
docker network rm authapi-net

echo "All systems down. Traces Terminated :)"
