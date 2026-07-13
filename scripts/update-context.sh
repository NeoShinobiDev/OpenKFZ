#!/usr/bin/env bash

DATE=$(date)

cat > .ai/CONTEXT.md <<EOT
# Projekt Kontext

Datum:
$DATE

## Branch

$(git branch --show-current)

## Letzte Commits

$(git log -5 --oneline)

## Status

$(git status --short)

EOT

echo "✓ AI Kontext aktualisiert"
