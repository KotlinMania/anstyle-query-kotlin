package io.github.kotlinmania.anstylequery

import io.github.kotlinmania.anstylequery.windows.enableAnsiColors
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AnstyleQueryTest {
    @Test
    fun clicolorMatchesUpstreamSemantics() {
        assertNull(clicolor { null })
        assertEquals(false, clicolor { mapOf("CLICOLOR" to "0")[it] })
        assertEquals(true, clicolor { mapOf("CLICOLOR" to "1")[it] })
        assertEquals(true, clicolor { mapOf("CLICOLOR" to "")[it] })
        assertEquals(true, clicolor { mapOf("CLICOLOR" to "always")[it] })
    }

    @Test
    fun clicolorForceMatchesUpstreamSemantics() {
        assertFalse(clicolorForce { null })
        assertFalse(clicolorForce { mapOf("CLICOLOR_FORCE" to "0")[it] })
        assertTrue(clicolorForce { mapOf("CLICOLOR_FORCE" to "1")[it] })
        assertTrue(clicolorForce { mapOf("CLICOLOR_FORCE" to "")[it] })
        assertTrue(clicolorForce { mapOf("CLICOLOR_FORCE" to "yes")[it] })
    }

    @Test
    fun noColorMatchesUpstreamSemantics() {
        assertFalse(noColor { null })
        assertFalse(noColor { mapOf("NO_COLOR" to "")[it] })
        assertTrue(noColor { mapOf("NO_COLOR" to "1")[it] })
        assertTrue(noColor { mapOf("NO_COLOR" to "0")[it] })
        assertTrue(noColor { mapOf("NO_COLOR" to "true")[it] })
    }

    @Test
    fun termSupportsColorRespectsPlatformDifferences() {
        assertFalse(termSupportsColor({ null }, isWindows = false))
        assertFalse(termSupportsColor({ mapOf("TERM" to "dumb")[it] }, isWindows = false))
        assertTrue(termSupportsColor({ mapOf("TERM" to "cygwin")[it] }, isWindows = false))
        assertTrue(termSupportsColor({ mapOf("TERM" to "xterm-256color")[it] }, isWindows = false))

        assertTrue(termSupportsColor({ null }, isWindows = true))
        assertFalse(termSupportsColor({ mapOf("TERM" to "dumb")[it] }, isWindows = true))
        assertTrue(termSupportsColor({ mapOf("TERM" to "cygwin")[it] }, isWindows = true))
        assertTrue(termSupportsColor({ mapOf("TERM" to "xterm-256color")[it] }, isWindows = true))
    }

    @Test
    fun termSupportsAnsiColorRespectsPlatformDifferences() {
        assertFalse(termSupportsAnsiColor({ null }, isWindows = false))
        assertFalse(termSupportsAnsiColor({ mapOf("TERM" to "dumb")[it] }, isWindows = false))
        assertTrue(termSupportsAnsiColor({ mapOf("TERM" to "cygwin")[it] }, isWindows = false))
        assertTrue(termSupportsAnsiColor({ mapOf("TERM" to "xterm")[it] }, isWindows = false))

        assertFalse(termSupportsAnsiColor({ null }, isWindows = true))
        assertFalse(termSupportsAnsiColor({ mapOf("TERM" to "dumb")[it] }, isWindows = true))
        assertFalse(termSupportsAnsiColor({ mapOf("TERM" to "cygwin")[it] }, isWindows = true))
        assertTrue(termSupportsAnsiColor({ mapOf("TERM" to "xterm-256color")[it] }, isWindows = true))
    }

    @Test
    fun truecolorMatchesColortermValues() {
        assertFalse(truecolor { null })
        assertFalse(truecolor { mapOf("COLORTERM" to "")[it] })
        assertFalse(truecolor { mapOf("COLORTERM" to "yes")[it] })
        assertTrue(truecolor { mapOf("COLORTERM" to "truecolor")[it] })
        assertTrue(truecolor { mapOf("COLORTERM" to "24bit")[it] })
    }

    @Test
    fun isCiChecksPresenceOnly() {
        assertFalse(isCi { null })
        assertTrue(isCi { mapOf("CI" to "true")[it] })
        assertTrue(isCi { mapOf("CI" to "false")[it] })
        assertTrue(isCi { mapOf("CI" to "woodpecker")[it] })
        assertTrue(isCi { mapOf("CI" to "")[it] })
    }

    @Test
    fun liveHostQueriesExecuteWithoutErrors() {
        // Call all top-level environment queries against the active runtime target.
        clicolor()
        clicolorForce()
        noColor()
        termSupportsColor()
        termSupportsAnsiColor()
        truecolor()
        isCi()

        val ansiEnabled = enableAnsiColors()
        if (isWindowsHost()) {
            assertTrue(ansiEnabled != null)
        } else {
            assertNull(ansiEnabled)
        }
    }
}
