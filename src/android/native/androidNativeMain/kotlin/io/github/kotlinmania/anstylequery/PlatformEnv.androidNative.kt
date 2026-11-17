package io.github.kotlinmania.anstylequery

import io.github.kotlinmania.libc.unix.getenv

internal actual fun systemEnvVar(name: String): String? = getenv(name)

internal actual fun isWindowsHost(): Boolean = false

internal actual fun platformEnableAnsiColors(): Boolean? = null
