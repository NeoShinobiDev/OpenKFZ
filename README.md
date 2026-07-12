# OpenKFZ

OpenKFZ is an open-source vehicle management platform designed to manage vehicle data, documents and automotive information in a modern and extensible way.

The goal of OpenKFZ is to provide a free and transparent platform for organizing vehicle-related information with a focus on privacy, modularity and community development.

## Features

🚗 Vehicle Management

* Manage multiple vehicles
* Store vehicle information and specifications
* Organize automotive data

📄 Document Management

* Manage important vehicle documents
* Keep maintenance and vehicle history organized

🔧 Modern Android Application

* Built with Kotlin
* Uses Jetpack Compose
* Modern Android architecture

🔒 Open Source & Privacy Focused

* Licensed under GNU AGPLv3
* Community-driven development
* No proprietary lock-in

## Project Status

OpenKFZ is currently under active development.

The project is in an early development phase. Features, architecture and APIs may change as development continues.

## Requirements

To build OpenKFZ locally you need:

* Android Studio or compatible IDE
* JDK 17+
* Android SDK
* Gradle (included via Gradle Wrapper)

## Build

Clone the repository:

```bash
git clone https://github.com/NeoShinobiDev/OpenKFZ.git
cd OpenKFZ
```

Build the project:

```bash
./gradlew build
```

Create an Android APK:

```bash
./gradlew assemble
```

## Project Structure

```
OpenKFZ
├── app/              # Android application
├── docs/             # Project documentation
├── gradle/           # Gradle wrapper
├── build.gradle.kts  # Root Gradle configuration
└── settings.gradle.kts
```

## Documentation

Additional documentation can be found in:

```
docs/
```

Currently available:

* Architecture
* API
* Database
* Network
* OCR
* Parser
* Roadmap

## Roadmap

Planned improvements:

* Vehicle database system
* Document scanning and OCR
* Maintenance tracking
* User accounts
* Synchronization
* Extended vehicle information

## Contributing

Contributions are welcome.

Before contributing:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Create a pull request

Please ensure your changes are tested before submitting.

## License

This project is licensed under the GNU Affero General Public License v3.0.

See [LICENSE](LICENSE) for details.

