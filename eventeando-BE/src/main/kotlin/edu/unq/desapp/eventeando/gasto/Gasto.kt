package edu.unq.desapp.eventeando.gasto

class Gasto {
    private var valor: Int = 0

    companion object {
        fun crear(unValor: Int): Gasto {
            val gasto = Gasto()
            gasto.valor = unValor
            return gasto
        }
    }

    fun valor(): Int = this.valor

}
