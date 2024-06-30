package com.mesutemre.unittestwork

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before

class TestVergi {

    private lateinit var vergi: Vergi

    @Before
    fun setUp() {
        this.vergi = Vergi()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `testBrutGelir100AndVergiYuzde10BeklentiVergiSonuc10`() {
        val vergiSonuc = vergi.calculateVergi(100.0,0.1)
        assertThat(vergiSonuc).isEqualTo(10)
    }

    @Test
    fun `testBrutGelir100AndVergiYuzde10BeklentiNetGelirSonuc90`() {
        val gelirSonuc = vergi.calculateGelir(100.0,0.1)
        assertThat(gelirSonuc).isEqualTo(90)
    }
}