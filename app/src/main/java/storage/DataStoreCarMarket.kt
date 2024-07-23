package storage

import model.common.Producto
import java.util.ArrayList
import java.util.List

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
    }
}