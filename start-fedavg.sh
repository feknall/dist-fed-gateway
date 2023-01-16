#!/bin/bash

docker compose -f docker-compose-fedavg.yml up -d
docker compose -f docker-compose-trainer-fedavg.yml up -d