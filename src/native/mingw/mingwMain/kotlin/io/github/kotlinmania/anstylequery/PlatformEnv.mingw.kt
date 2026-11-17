package io.github.kotlinmania.anstylequery

import io.github.kotlinmania.anstylequery.windows.enableVirtualTerminalProcessing
import io.github.kotlinmania.libc.windows.getenv

internal actual fun systemEnvVar(name: String): String? = getenv(name)

internal actual fun isWindowsHost(): Boolean = true

internal actual fun platformEnableAnsiColors(): Boolean? = enableVirtualTerminalProcessing()
