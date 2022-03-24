#!/bin/bash

docker kill demoauthapi
docker kill maildev
docker network rm authapi-net

echo "All systems down. Traces Terminated :)"
