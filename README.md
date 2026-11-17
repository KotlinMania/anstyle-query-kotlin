# anstyle-query-kotlin in Kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Fanstyle--query--kotlin-blue.svg)](https://github.com/KotlinMania/anstyle-query-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/anstyle-query-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/anstyle-query-kotlin)
[![Build status](https://img.shields.io/github/actions/workflow/status/KotlinMania/anstyle-query-kotlin/ci.yml?branch=main)](https://github.com/KotlinMania/anstyle-query-kotlin/actions)

This is a Kotlin Multiplatform line-by-line port of [`rust-cli/anstyle` (`anstyle-query`)](https://github.com/rust-cli/anstyle/tree/main/crates/anstyle-query).

## Installation

```kotlin
dependencies {
    implementation("io.github.kotlinmania:anstyle-query-kotlin:0.1.0")
}
```

## API

Package `io.github.kotlinmania.anstylequery`:

- `clicolor(): Boolean?` — checks `CLICOLOR`
- `clicolorForce(): Boolean` — checks `CLICOLOR_FORCE`
- `noColor(): Boolean` — checks `NO_COLOR`
- `termSupportsColor(): Boolean` — checks `TERM` for color support
- `termSupportsAnsiColor(): Boolean` — checks `TERM` for ANSI color support
- `truecolor(): Boolean` — checks `COLORTERM` (`"truecolor"` or `"24bit"`)
- `isCi(): Boolean` — checks `CI` presence

Package `io.github.kotlinmania.anstylequery.windows`:

- `enableAnsiColors(): Boolean?` — enables `ENABLE_VIRTUAL_TERMINAL_PROCESSING` on Windows (`null` on non-Windows)
- `enableVirtualTerminalProcessing(): Boolean` — raw Windows console VT enablement (`mingwMain`)

## Building

```bash
./gradlew build
./gradlew test
```
