#!/bin/bash

git diff --cached --name-only --diff-filter=ACMRT |
  grep "\.java$" | tr '\n' '\0' |
  xargs -0 -n1 clang-format -style=file -output-replacements-xml |
  grep "<replacement " >/dev/null
if [ $? -ne 1 ]; then 
    echo "Committed code is not clang-formatted"
    exit 1
fi
