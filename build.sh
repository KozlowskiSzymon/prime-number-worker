#!/bin/bash
VERSION=0.1.0
docker build -t szymon2kozlowski/prime-number-worker:$VERSION .
docker push szymon2kozlowski/prime-number-worker:$VERSION