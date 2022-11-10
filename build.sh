#!/bin/bash

./gradlew build
docker build -t dist-fed-gateway . -f Dockerfile
docker tag dist-fed-gateway hmaid/hyperledger:dist-fed-gateway
docker push hmaid/hyperledger:dist-fed-gateway