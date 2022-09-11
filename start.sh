#!/bin/bash

echo "Trainer1..."
nohup ./gradlew bootRun --args='--server.port=8080 --fl.user=Trainer1' &>trainer1.log &

echo "Trainer2..."
nohup ./gradlew bootRun --args='--server.port=8081 --fl.user=Trainer2' &>trainer2.log &

echo "LeadAggregator1..."
nohup ./gradlew bootRun --args='--server.port=8082 --fl.user=LeadAggregator1' &>leadAggregator1.log &

echo "flAdmin1..."
nohup ./gradlew bootRun --args='--server.port=8083 --fl.user=flAdmin1' &>flAdmin1.log &

echo "Org1Aggregator1..."
nohup ./gradlew bootRun --args='--server.port=8091 --fl.user=Aggregator1 --fl.aggregator.organization=org1' &>org1aggregator1.log &

echo "Org2Aggregator1..."
nohup ./gradlew bootRun --args='--server.port=8092 --fl.user=Aggregator1 --fl.aggregator.organization=org2' &>org2aggregator1.log &