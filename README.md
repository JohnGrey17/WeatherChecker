<p align="center">
  <img src="res/topGif.gif" alt="Logo" width="400"/>
</p>

# WeatherAPI.com Code Challenge (CLI)

A console utility written in **Java + Gradle** that prints **tomorrow’s forecast** to **STDOUT** for the cities: **Chisinau, Madrid, Kyiv, Amsterdam**.  
Uses **Retrofit** for HTTP and outputs: **Min/Max °C, Humidity %, Wind kph, Wind Direction**.

---

## Table of Contents
- [Requirements](#Requirements)
- [Upgrade Gradle Wrapper](#Upgrade-Gradle-Wrapper)
- [Clone the Repository](#Clone-the-Repository)
- [Configure Your WeatherAPI Key](#Configure-Your-WeatherAPI-Key)
- [Run](#Run)
- [Sample Output](#Sample-Output)
- [Build & Tests](#Build-ests)

---

## Requirements
- **JDK 24** (check with `java -version`)
- **Git** (check with `git --version`)

> You do **not** need a local Gradle installation — the project uses the **Gradle Wrapper** (`gradlew`, `gradlew.bat`).

**Quick Links**  
[![Download Git](https://img.shields.io/badge/Download-Git-F05032?logo=git&logoColor=white&style=for-the-badge)](https://git-scm.com/downloads)
[![Download JDK 24 (Windows)](https://img.shields.io/badge/Download-JDK%2024%20(Windows)-007396?logo=openjdk&logoColor=white&style=for-the-badge)](https://www.oracle.com/ua/java/technologies/downloads/#jdk24-windows)

---

## Upgrade Gradle Wrapper
If you need to upgrade the wrapper locally, run from the **project root** (where `gradlew` and `build.gradle` live):
```bash
./gradlew wrapper --gradle-version 8.14.3
# Windows:
.\gradlew.bat wrapper --gradle-version 8.14.3
```

---

## Clone the Repository
```bash
git clone https://github.com/JohnGrey17/WheaterChecker.git
cd <project-folder>
```
> **Important:** After cloning, change into the project directory with `cd <project-folder>`.  
> Run all **git** and **gradle** commands **from this project root directory**.

---

## Configure Your WeatherAPI Key
1. Create an account at WeatherAPI and get your **API key**.
2. Create a `.env` file in the project root **(do not commit it)**:
   ```dotenv
   WEATHER_API_KEY=YOUR_KEY
   ```
   A template is provided: **.env.example**.

> `.env` is already listed in `.gitignore`.

---

## Run

### Default run (uses the built-in city list)
- macOS/Linux:
  ```bash
  ./gradlew run
  ```
- Windows PowerShell / CMD:
  ```powershell
  .\gradlew.bat run
  ```

### With arguments (`--cities=...`)
- macOS/Linux:
  ```bash
  ./gradlew run --args="--cities=Kyiv,Madrid"
  ```
- Windows PowerShell / CMD:
  ```powershell
  .\gradlew.bat run --args="--cities=Kyiv,Madrid"
  ```

**Cities with spaces:** use underscores in the argument — the app converts them back, e.g.  
`--args="--cities=New_York,San_Francisco"` → becomes `New York`, `San Francisco`.

### If you don’t use `.env` — via environment variable or JVM property
- PowerShell:
  ```powershell
  $env:WEATHER_API_KEY="YOUR_KEY"
  .\gradlew.bat run
  ```
- macOS/Linux:
  ```bash
  WEATHER_API_KEY=YOUR_KEY ./gradlew run
  ```
- Or JVM property:
  ```bash
  ./gradlew run -Dweather.api.key=YOUR_KEY
  ```

---

## Sample Output
```
+-----------+---------------------------------------------------------+
| City      | 2025-09-02                                              |
+-----------+---------------------------------------------------------+
| Chisinau  | min 17.6°C | max 33.5°C | hum 44% | wind 21.6 kph N     |
| Madrid    | min 16.8°C | max 27.9°C | hum 31% | wind 18.7 kph WSW   |
| Kyiv      | min 17.2°C | max 26.5°C | hum 55% | wind 16.6 kph N     |
| Amsterdam | min 14.5°C | max 22.4°C | hum 67% | wind 21.6 kph SW    |
+-----------+---------------------------------------------------------+
```

---

## Build-Tests
Full cycle (clean → tests → build):
```bash
./gradlew clean build
# Windows:
.\gradlew.bat clean build
```
Run tests only:
```bash
./gradlew test
```
