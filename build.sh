#!/bin/bash
VERSION=0.2.1
docker build -t szymon2kozlowski/prime-number-worker:$VERSION .
docker push szymon2kozlowski/prime-number-worker:$VERSION