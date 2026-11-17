package io.github.kotlinmania.anstylequery

internal actual fun systemEnvVar(name: String): String? {
    val raw: dynamic = jsGetEnv(name)
    return if (raw == null || raw == jsUndefined()) null else raw.unsafeCast<String>()
}

internal actual fun isWindowsHost(): Boolean = jsIsWindows()

internal actual fun platformEnableAnsiColors(): Boolean? = null

private fun jsGetEnv(name: String): dynamic =
    js(
        "(typeof process !== 'undefined' && process && process.env && typeof process.env[name] === 'string') ? process.env[name] : undefined",
    )

private fun jsIsWindows(): Boolean =
    js("(typeof process !== 'undefined' && process && process.platform === 'win32') ? true : false")
        .unsafeCast<Boolean>()

private fun jsUndefined(): dynamic = js("undefined")
