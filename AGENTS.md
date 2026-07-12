# OpenKFZ Projekt Kontext

Projekt:
OpenKFZ Android App

Sprache:
Kotlin

Struktur:

com.openkfz
- app
- setup
- client
- camera
- modules
- ui

Aktueller Stand:
- UI Aufbau
- Startscreen Master/Client
- Client Einrichtung
- Kamera Screen
- Settings Button
- QR Verbindung später

Wichtig:
Keine Architekturänderung.
Keine Clean Architecture Migration.
Bestehende Struktur behalten.

Arbeitsweise:
Nur Fehler beheben und Features nach Plan ergänzen.

## Build Rules

Do not suggest dependency changes unless a real compiler error proves it.

Do not convert Activities to Compose automatically.

Do not introduce Clean Architecture.

Do not rename or reorganize existing packages.

Keep these packages:
- com.openkfz.app
- com.openkfz.setup
- com.openkfz.client
- com.openkfz.ui
- com.openkfz.modules

Only fix:
- compiler errors
- runtime crashes
- AndroidManifest errors
- broken imports
- Gradle build errors

Always analyze the existing code first.
Do not redesign the project architecture.
