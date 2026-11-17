package io.github.kotlinmania.anstylequery.windows

import io.github.kotlinmania.windowssys.windows.win32.system.console.ENABLE_VIRTUAL_TERMINAL_PROCESSING
import io.github.kotlinmania.windowssys.windows.win32.system.console.GetConsoleMode
import io.github.kotlinmania.windowssys.windows.win32.system.console.GetStdHandle
import io.github.kotlinmania.windowssys.windows.win32.system.console.STD_ERROR_HANDLE
import io.github.kotlinmania.windowssys.windows.win32.system.console.STD_OUTPUT_HANDLE
import io.github.kotlinmania.windowssys.windows.win32.system.console.SetConsoleMode

private const val INVALID_HANDLE_VALUE: Long = -1L

private fun enableVt(handle: Long): Boolean {
    if (handle == 0L || handle == INVALID_HANDLE_VALUE) {
        return false
    }
    val mode = UIntArray(1)
    if (GetConsoleMode(handle, mode) == 0) {
        return false
    }
    val updated = mode[0] or ENABLE_VIRTUAL_TERMINAL_PROCESSING
    return SetConsoleMode(handle, updated) != 0
}

/**
 * Enables `ENABLE_VIRTUAL_TERMINAL_PROCESSING` on `stdout` and `stderr` console handles on Windows.
 */
public fun enableVirtualTerminalProcessing(): Boolean {
    val stdoutHandle = GetStdHandle(STD_OUTPUT_HANDLE)
    val stderrHandle = GetStdHandle(STD_ERROR_HANDLE)
    if (!enableVt(stdoutHandle)) {
        return false
    }
    if (stdoutHandle != stderrHandle && !enableVt(stderrHandle)) {
        return false
    }
    return true
}
