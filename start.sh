#!/bin/bash

docker compose -f docker-compose-fedshare.yml up -d
docker compose -f docker-compose-trainer-fedshare.yml up -d