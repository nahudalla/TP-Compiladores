#!/bin/zsh

./generateParser.sh && \
mvn package -f ../../pom.xml
