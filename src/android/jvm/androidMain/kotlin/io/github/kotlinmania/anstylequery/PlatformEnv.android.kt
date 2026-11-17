package io.github.kotlinmania.anstylequery

internal actual fun systemEnvVar(name: String): String? = System.getenv(name)

internal actual fun isWindowsHost(): Boolean = false

internal actual fun platformEnableAnsiColors(): Boolean? = null
