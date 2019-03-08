#!/usr/bin/env bash

SAVEIFS=$IFS
IFS=$'\n'
CHANGED_FILES=($(git diff --cached --name-only --diff-filter=ACMRT | grep '\.java$'))
IFS=$SAVEIFS
unset SAVEIFS

FAILED=0

for CHANGED_FILE in "${CHANGED_FILES[@]}"; do
	clang-format -style=file -output-replacements-xml "${CHANGED_FILE}" | grep '<replacement '>/dev/null
	if [ $? -ne 1 ]; then
		echo "${CHANGED_FILE}: Not clang-formatted"
		FAILED=1
	fi
done

exit $FAILED
