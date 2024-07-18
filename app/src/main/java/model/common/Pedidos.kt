package model.common

class Pedidos {


    var id:Int = 0
        get(){
            return field
        }
        set(id){
            field= id
        }

    var numPedido:Int =0
        get(){
            return field
        }
        set(np){
            field= np
        }

    var estadoPedido:String = ""
        get(){
            return field
        }
        set(ep){
            field= ep
        }

    var   productos:String = ""
        get(){
            return field
        }
        set(p){
            field= p
        }

    var   subtotal:Float = 0.0f
        get(){
            return field
        }
        set(s){
            field= s
        }

    var   cantidad:Int = 0
        get(){
            return field
        }
        set(c){
            field= c
        }

    var   total:Float = 0.0f
        get(){
            return field
        }
        set(t){
            field= t
        }

    var   destino:String=""
        get(){
            return field
        }
        set(d){
            field= d
        }


}