package storage

import model.common.Producto
import java.util.ArrayList
import java.util.List

class DataStoreCarMarket{
    object CarMarket{
         lateinit var list : ArrayList<Producto>
        init{
            list = ArrayList<Producto>()
        }

        fun addCarMarket(producto: Producto){
            list.add(producto)
        }

        fun getCarMarket() : ArrayList<Producto>{
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