#!/bin/zsh

./generateParser.sh && \
mvn clean package -f ../../pom.xml
