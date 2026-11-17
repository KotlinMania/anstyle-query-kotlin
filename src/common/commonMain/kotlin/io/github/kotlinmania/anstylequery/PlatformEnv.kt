package io.github.kotlinmania.anstylequery

internal expect fun systemEnvVar(name: String): String?

internal expect fun isWindowsHost(): Boolean

internal expect fun platformEnableAnsiColors(): Boolean?
