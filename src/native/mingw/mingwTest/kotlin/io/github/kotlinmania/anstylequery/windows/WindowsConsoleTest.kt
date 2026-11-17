package io.github.kotlinmania.anstylequery.windows

import io.github.kotlinmania.anstylequery.isWindowsHost
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class WindowsConsoleTest {
    @Test
    fun windowsHostReportsTrueAndEnableAnsiColorsIsNonNull() {
        assertTrue(isWindowsHost())
        val enabled = enableAnsiColors()
        assertNotNull(enabled)
        assertEquals(enableVirtualTerminalProcessing(), enabled)
    }
}
