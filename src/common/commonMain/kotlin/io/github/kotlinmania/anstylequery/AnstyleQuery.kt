package io.github.kotlinmania.anstylequery

/**
 * Check [CLICOLOR] status.
 *
 * - When `true`, ANSI colors are supported and should be used when the program isn't piped,
 *   similar to [termSupportsColor].
 * - When `false`, don't output ANSI color escape codes, similar to [noColor].
 *
 * [CLICOLOR]: https://bixense.com/clicolors/
 */
public fun clicolor(): Boolean? = clicolor { name -> systemEnvVar(name) }

/**
 * Check [CLICOLOR_FORCE] status.
 *
 * ANSI colors should be enabled no matter what.
 *
 * [CLICOLOR_FORCE]: https://bixense.com/clicolors/
 */
public fun clicolorForce(): Boolean = clicolorForce { name -> systemEnvVar(name) }

/**
 * Check [NO_COLOR] status.
 *
 * When `true`, should prevent the addition of ANSI color.
 *
 * [NO_COLOR]: https://no-color.org/
 */
public fun noColor(): Boolean = noColor { name -> systemEnvVar(name) }

/**
 * Check `TERM` for color support.
 */
public fun termSupportsColor(): Boolean =
    termSupportsColor(envLookup = { name -> systemEnvVar(name) }, isWindows = isWindowsHost())

/**
 * Check `TERM` for ANSI color support.
 */
public fun termSupportsAnsiColor(): Boolean =
    termSupportsAnsiColor(envLookup = { name -> systemEnvVar(name) }, isWindows = isWindowsHost())

/**
 * Check [COLORTERM] for truecolor support.
 *
 * [COLORTERM]: https://github.com/termstandard/colors
 */
public fun truecolor(): Boolean = truecolor { name -> systemEnvVar(name) }

/**
 * Report whether this is running in CI.
 */
public fun isCi(): Boolean = isCi { name -> systemEnvVar(name) }

internal fun clicolor(envLookup: (String) -> String?): Boolean? {
    val value = envLookup("CLICOLOR") ?: return null
    return value != "0"
}

internal fun clicolorForce(envLookup: (String) -> String?): Boolean {
    val value = envLookup("CLICOLOR_FORCE") ?: "0"
    return value != "0"
}

internal fun noColor(envLookup: (String) -> String?): Boolean {
    val value = envLookup("NO_COLOR") ?: ""
    return value != ""
}

internal fun termSupportsColor(
    envLookup: (String) -> String?,
    isWindows: Boolean,
): Boolean {
    val value = envLookup("TERM")
    return if (isWindows) {
        value != "dumb"
    } else {
        value != null && value != "dumb"
    }
}

internal fun termSupportsAnsiColor(
    envLookup: (String) -> String?,
    isWindows: Boolean,
): Boolean =
    if (isWindows) {
        when (envLookup("TERM")) {
            null, "dumb", "cygwin" -> false
            else -> true
        }
    } else {
        termSupportsColor(envLookup = envLookup, isWindows = false)
    }

internal fun truecolor(envLookup: (String) -> String?): Boolean {
    val value = envLookup("COLORTERM") ?: ""
    return value == "truecolor" || value == "24bit"
}

internal fun isCi(envLookup: (String) -> String?): Boolean = envLookup("CI") != null
