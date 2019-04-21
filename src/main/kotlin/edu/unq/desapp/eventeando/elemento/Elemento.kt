package edu.unq.desapp.eventeando.elemento

class Elemento {
    var descripcion: String = ""
    var proporcionPorPersona: Double = 0.00

    companion object {
        fun crear(descripcion:String, proporcion: Double ): Elemento {
            val elemento = Elemento()
            elemento.descripcion=descripcion
            elemento.proporcionPorPersona=proporcion
            return elemento
        }
    }

    public fun proporcionPorPersona():Double{
        return proporcionPorPersona
    }

    public fun descripcion():String{
        return descripcion
    }

}