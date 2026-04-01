#!/bin/sh
# Maven Wrapper script
# Detects if mvn is installed; if not, downloads it
if command -v mvn &> /dev/null; then
    exec mvn "$@"
else
    echo "Maven is not installed. Please install Maven or use: mvn clean package"
    exit 1
fi
