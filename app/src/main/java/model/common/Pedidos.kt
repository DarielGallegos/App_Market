package model.common

class Pedidos {

    var pedido_numero:Int =0
        get(){
            return field
        }
        set(np){
            field= np
        }

    var estado_pedido:String = ""
        get(){
            return field
        }
        set(ep){
            field= ep
        }

    var   total:Float = 0.0f
        get(){
            return field
        }
        set(t){
            field= t
        }

    var   usuario:String=""
        get(){
            return field
        }
        set(u){
            field= u
        }

    var   cliente:String=""
        get(){
            return field
        }
        set(c){
            field= c
        }


}