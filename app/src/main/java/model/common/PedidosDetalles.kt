package model.common

class PedidosDetalles {

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