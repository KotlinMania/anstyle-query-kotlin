@file:OptIn(kotlin.js.ExperimentalWasmJsInterop::class)

package io.github.kotlinmania.anstylequery

internal actual fun systemEnvVar(name: String): String? = jsGetEnv(name)

internal actual fun isWindowsHost(): Boolean = jsIsWindows()

internal actual fun platformEnableAnsiColors(): Boolean? = null

private fun jsGetEnv(name: String): String? =
    js(
        "(typeof process !== 'undefined' && process && process.env && typeof process.env[name] === 'string') ? process.env[name] : null",
    )

private fun jsIsWindows(): Boolean =
    js("(typeof process !== 'undefined' && process && process.platform === 'win32') ? true : false")
