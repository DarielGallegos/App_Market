package storage

import model.common.CarMarketProducto
import model.common.Producto
import java.util.ArrayList

class DataStoreCarMarket{
    object CarMarket{
         lateinit var list : MutableList<Producto>
        init{
            list = ArrayList<Producto>()
        }


        fun addCarMarket(producto: Producto){
            list.add(producto)
        }

        fun getCarMarket() : MutableList<Producto>{
            return list
        }

        fun deleteCarMarket(producto: Producto){
            list.remove(producto)
        }

        fun clearCarMarket(){
            list.clear()
        }

        fun updateCarMarket(producto: Producto){
            list.set(producto.id, producto)
        }

        fun imprimir()
        {
            list.forEach { elemeto ->
                println(elemeto.id.toString() + " " + (elemeto.producto)+" "+(elemeto.cantidad) +" "+ (elemeto.precio))
            }
        }
    }
}