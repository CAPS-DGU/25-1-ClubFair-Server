#!/bin/bash
./gradlew clean build -x test
docker buildx build --platform linux/amd64 --load --tag jeongheumchoi/clubfair-be:0.0.1 .
docker push jeongheumchoi/clubfair-be:0.0.1
