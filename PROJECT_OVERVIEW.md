# OpenKFZ – Projektübersicht

## Vision

OpenKFZ ist eine Open-Source-Android-App zur lokalen Digitalisierung von Fahrzeugpapieren (insbesondere Fahrzeugscheinen) in Unternehmen.

Die App soll vollständig lokal funktionieren, ohne Cloud-Zwang und ohne externe Dienste. Die Daten bleiben im eigenen Netzwerk und können jederzeit exportiert und weiterverarbeitet werden.

Ziel ist ein einfaches, schnelles und sicheres System:

* Mitarbeiter scannen Fahrzeugdokumente mit dem Smartphone
* OCR erkennt automatisch die Daten
* Dokumente werden lokal als PDF gespeichert
* Fahrzeugdaten werden strukturiert abgelegt
* Ein Master-System verwaltet und synchronisiert die Daten
* Alle Komponenten bleiben Open Source

---

# Grundprinzip

## Architektur

Das System besteht aus zwei Gerätetypen:

## 1. Client-Geräte (Mitarbeiter)

Normale Android-Smartphones mit installierter OpenKFZ APK.

Aufgabe:

* Fahrzeugbrief/Fahrzeugschein scannen
* OCR-Erkennung durchführen
* PDF erzeugen
* Daten lokal zwischenspeichern
* Synchronisation mit Master durchführen

Die Benutzeroberfläche ist bewusst minimal:

* Vollbild-Kamera
* grüner Scan-Rahmen
* automatische Dokumentenerkennung
* Scan abgeschlossen → Upload/Synchronisation

Der Mitarbeiter soll keine komplizierten Menüs sehen.

---

# 2. Master-Gerät

Das Master-Gerät ist die zentrale Verwaltungsinstanz im lokalen Netzwerk.

Aufgaben:

* Verwaltung aller Client-Geräte
* Speicherung aller Dokumente
* Verwaltung der Datenbank
* Exportmöglichkeiten
* Benutzerverwaltung
* Synchronisation

Das Master-Gerät besitzt eine erweiterte Oberfläche.

---

# Verbindung zwischen Geräten

Die Kommunikation soll modular aufgebaut werden.

Mögliche Technologien:

* WLAN im lokalen Netzwerk
* Peer-to-Peer Verbindung
* Bluetooth als Backup
* lokale API
* verschlüsselte Datenübertragung

Die Architektur soll später verschiedene Transportwege unterstützen.

---

# Datenverarbeitung

Ablauf eines Scans:

1. Kamera öffnet sich
2. Fahrzeugschein wird erkannt
3. Bild wird aufgenommen
4. OCR verarbeitet das Dokument
5. Daten werden extrahiert
6. PDF wird erzeugt
7. Metadaten werden gespeichert
8. Synchronisation mit Master

---

# OCR und Dokumentverarbeitung

Priorität:

* Open Source
* lokal ausführbar
* keine Cloud-Abhängigkeit

Mögliche Komponenten:

* Tesseract OCR
* ML Kit lokal
* OpenCV Bildverarbeitung
* lokale PDF-Erzeugung

---

# Speicherung

Alle Daten bleiben lokal.

Struktur:

```
OpenKFZ/
├── database/
│   └── openkfz.db
│
├── documents/
│   ├── fahrzeuge/
│   │   ├── fahrzeug_001.pdf
│   │   └── fahrzeug_002.pdf
│
├── exports/
│   ├── csv/
│   ├── json/
│   └── xml/
│
└── backups/
```

---

# Datenbank

Die Datenbank soll strukturierte Fahrzeugdaten speichern.

Beispiel:

```
Fahrzeug
|
├── Halter
├── Kennzeichen
├── Fahrzeugidentifikationsnummer
├── Hersteller
├── Modell
├── Baujahr
├── Dokumentpfad
└── Erstellungsdatum
```

---

# Export

Die Daten sollen weiterverwendbar sein.

Geplante Formate:

* PDF
* CSV
* JSON
* XML

Damit kann OpenKFZ mit anderen Programmen verbunden werden.

---

# Benutzerrollen

## Scanner

Minimalrechte:

* Dokument scannen
* eigene Uploads anzeigen

## Administrator / Master

Erweiterte Rechte:

* Geräte verwalten
* Daten durchsuchen
* Exporte erstellen
* Backups verwalten
* Daten löschen oder bearbeiten

---

# Android-App Struktur

Aktueller Stand:

* Kotlin
* Jetpack Compose
* Material 3
* Gradle Kotlin DSL

Package:

```
com.openkfz.app
```

---

# Aktuelle Entwicklungsphasen

## Phase 1 – Grundgerüst

Status:

✅ Android Projekt erstellt
✅ Compose Oberfläche läuft
✅ Emulator eingerichtet
✅ APK Installation funktioniert

---

## Phase 2 – UI Konzept

Als nächstes:

* Startbildschirm
* Rollen-Auswahl:

  * Client
  * Master

Client UI:

* Kameraansicht
* Scan Button
* Statusanzeige

Master UI:

* Dashboard
* Geräte
* Dokumente
* Datenbank
* Export

---

## Phase 3 – Scanner

Geplant:

* Kamera Integration
* Dokumentenerkennung
* Perspektivkorrektur
* OCR

---

## Phase 4 – Lokale Speicherung

Geplant:

* SQLite Datenbank
* PDF Speicherung
* Dateiverwaltung

---

## Phase 5 – Netzwerk

Geplant:

* Master Discovery
* Geräteverwaltung
* Synchronisation
* Verschlüsselung

---

# Entwicklungsprinzipien

OpenKFZ bleibt:

* Open Source
* Offline First
* Datenschutzfreundlich
* Keine Cloud-Pflicht
* Keine Abhängigkeit von Google Services
* Modular erweiterbar

---

# Langfristiges Ziel

Eine professionelle lokale Fahrzeugdokument-Verwaltung für kleine und mittlere Unternehmen.

Einfach:

Scannen → Erkennen → Speichern → Synchronisieren → Exportieren

