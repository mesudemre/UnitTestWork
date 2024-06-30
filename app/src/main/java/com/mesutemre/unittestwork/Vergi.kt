package com.mesutemre.unittestwork

class Vergi {

    fun calculateVergi(brutGelir: Double, vergiYuzde: Double): Double = vergiYuzde * brutGelir

    fun calculateGelir(brutGelir: Double, vergiYuzde: Double): Double =
        brutGelir - (vergiYuzde * brutGelir)
}