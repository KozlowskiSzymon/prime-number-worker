#!/bin/bash
VERSION=0.0.1-SNAPSHOT
docker build -t szymon2kozlowski/prime-number-worker:$VERSION .
docker push szymon2kozlowski/prime-number-worker:$VERSION