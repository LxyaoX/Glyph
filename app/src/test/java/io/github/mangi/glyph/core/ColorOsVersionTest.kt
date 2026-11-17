package io.github.mangi.glyph.core

import org.junit.Assert.assertEquals
import org.junit.Test

class ColorOsVersionTest {

    @Test
    fun `major version is taken from the leading segment`() {
        assertEquals(17, parseMajorVersion("V17.0.1"))
        assertEquals(16, parseMajorVersion("16.1.0"))
        assertEquals(15, parseMajorVersion("V15"))
    }

    @Test
    fun `missing or non numeric version falls back to zero`() {
        assertEquals(0, parseMajorVersion(null))
        assertEquals(0, parseMajorVersion(""))
        assertEquals(0, parseMajorVersion("Unavailable"))
    }
}
