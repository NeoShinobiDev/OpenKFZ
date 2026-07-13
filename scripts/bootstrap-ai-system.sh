#!/usr/bin/env bash
set -e

echo "🚀 OpenKFZ AI Development Bootstrap"

mkdir -p \
.ai \
.devcontainer \
.github/workflows \
docs \
scripts

echo "🤖 Erstelle KI-Kontext..."

cat > .ai/AGENTS.md <<'EOF'
# AI Agent Rules

Projekt: OpenKFZ

## Regeln

- Erst analysieren, dann ändern.
- Keine großen Umbauten ohne Erklärung.
- Bestehende Architektur respektieren.
- Kleine, nachvollziehbare Änderungen machen.
- Nach Änderungen Dokumentation aktualisieren.

## Workflow

1. PROJECT.md lesen
2. CONTEXT.md lesen
3. TASKS.md prüfen
4. Änderungen durchführen
5. Tests ausführen
6. Änderungen dokumentieren
EOF


cat > .ai/PROJECT.md <<'EOF'
# OpenKFZ Projekt

## Beschreibung

OpenKFZ ist ein Projekt zur Verwaltung und Verarbeitung von Fahrzeugdaten.

## Technologie

Hier eintragen:

- Sprache:
- Framework:
- Datenbank:
- APIs:

## Ziel

Eine moderne, wartbare Fahrzeuganwendung entwickeln.
EOF


cat > .ai/CONTEXT.md <<'EOF'
# Projekt Kontext

Datum:

## Aktueller Stand

-

## Letzte Änderungen

-

## Probleme

-

## Nächste Schritte

-
EOF


cat > .ai/TASKS.md <<'EOF'
# Aufgaben

## Priorität Hoch

- [ ]

## Priorität Mittel

- [ ]

## Ideen

-
EOF


cat > .ai/STYLE.md <<'EOF'
# Coding Style

- Sauberer Code
- Verständliche Namen
- Keine unnötigen Abhängigkeiten
- Dokumentation aktuell halten
- Tests bevorzugen
EOF


echo "🐳 Dev Container..."

cat > .devcontainer/devcontainer.json <<'EOF'
{
    "name": "OpenKFZ Development",

    "image": "mcr.microsoft.com/devcontainers/base:ubuntu",

    "features": {
        "ghcr.io/devcontainers/features/github-cli:1": {},
        "ghcr.io/devcontainers/features/node:1": {}
    },

    "postCreateCommand": "bash scripts/doctor.sh"
}
EOF


echo "⚙️ Scripts..."

cat > scripts/doctor.sh <<'EOF'
#!/usr/bin/env bash

echo "🔎 OpenKFZ Environment Check"

command -v git && echo "✓ Git"
command -v gh && echo "✓ GitHub CLI"

echo ""
echo "AI Dateien:"
ls .ai

echo ""
echo "System bereit."
EOF


cat > scripts/update-context.sh <<'EOF'
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
EOF


chmod +x scripts/*.sh


echo "🔧 GitHub Actions..."

cat > .github/workflows/build.yml <<'EOF'
name: OpenKFZ Build

on:
  push:
    branches:
      - main
      - develop

jobs:

  build:
    runs-on: ubuntu-latest

    steps:

      - name: Checkout
        uses: actions/checkout@v4

      - name: Info
        run: |
          echo "OpenKFZ Build"
          ls
EOF


cat > .github/workflows/ai-context.yml <<'EOF'
name: Update AI Context

on:
  push:
    branches:
      - main
      - develop

permissions:
  contents: write

jobs:

  update:

    runs-on: ubuntu-latest

    steps:

      - uses: actions/checkout@v4

      - name: Update Context
        run: |
          bash scripts/update-context.sh

      - name: Commit
        run: |
          git config user.name github-actions
          git config user.email github-actions@github.com
          git add .ai/CONTEXT.md
          git commit -m "Update AI context" || exit 0
          git push
EOF


cat > docs/architecture.md <<'EOF'
# Architektur

## Komponenten

-

## Datenfluss

-

## Entscheidungen

-
EOF


echo ""
echo "✅ Fertig!"

echo ""
echo "Jetzt:"
echo "git add ."
echo "git commit -m 'Add AI development system'"
echo "git push"
