#!/bin/bash

docker compose -f docker-compose-fedavg.yml up -d
docker compose -f docker-compose-trainer-fedavg-8.yml up -d