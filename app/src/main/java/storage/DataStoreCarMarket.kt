package storage

import model.common.CarMarketProducto
import java.util.ArrayList

class DataStoreCarMarket{
    object CarMarket{
         lateinit var list : MutableList<CarMarketProducto>
        init{
            list = ArrayList<CarMarketProducto>()
        }

        fun addCarMarket(carMarketProducto: CarMarketProducto){
            list.add(carMarketProducto)
        }

        fun getCarMarket() : MutableList<CarMarketProducto>{
            return list
        }

        fun deleteCarMarket(carMarketProducto: CarMarketProducto){
            list.remove(carMarketProducto)
        }

        fun clearCarMarket(){
            list.clear()
        }

        fun updateCarMarket(carMarketProducto: CarMarketProducto){
            list.set(carMarketProducto.id, carMarketProducto)
        }

        fun imprimir()
        {
            list.forEach { elemeto ->
                println(elemeto.id.toString() + " " + (elemeto.producto)+" "+(elemeto.cantidad) +" "+ (elemeto.precio))
            }
        }
    }
}