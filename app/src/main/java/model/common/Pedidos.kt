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

    var NombreProducto: String = ""
        get() {
            return field
        }
        set(np) {
            field = np
        }

    var Precio: Float = 0.0f
        get() {
            return field
        }
        set(p) {
            field = p
        }

    var Cantidad: Int = 0
        get() {
            return field
        }
        set(c) {
            field = c
        }

    var Monto: Float = 0.0f
        get() {
            return field
        }
        set(m) {
            field = m
        }

    var Imagen: String = ""
        get() {
            return field
        }
        set(i) {
            field = i
        }



}