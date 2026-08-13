#!/usr/bin/env bash

echo "🔎 OpenKFZ Environment Check"

command -v git && echo "✓ Git"
command -v gh && echo "✓ GitHub CLI"

echo ""
echo "AI Dateien:"
ls .ai

echo ""
echo "System bereit."
