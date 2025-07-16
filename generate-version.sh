#!/bin/bash

# Get the latest annotated tag (e.g., v1.0.3)
VERSION=$(git describe --tags --abbrev=0 2>/dev/null)

# Fallback if no tag exists yet
if [ -z "$VERSION" ]; then
  VERSION="v0.0.0"
fi

# Output to resources
echo "version=$VERSION" > src/main/resources/version.properties
