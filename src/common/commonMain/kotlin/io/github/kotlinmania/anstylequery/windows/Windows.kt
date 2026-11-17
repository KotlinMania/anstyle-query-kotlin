// port-lint: source windows.rs
package io.github.kotlinmania.anstylequery.windows

import io.github.kotlinmania.anstylequery.platformEnableAnsiColors

/**
 * Enable ANSI escape codes (`ENABLE_VIRTUAL_TERMINAL_PROCESSING`).
 *
 * For non-Windows systems, returns `null`.
 */
public fun enableAnsiColors(): Boolean? = platformEnableAnsiColors()
