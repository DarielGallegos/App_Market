package model.common

import com.google.gson.annotations.SerializedName

class PedidosDetalles {

    @SerializedName("pedido_numero")
    var pedidoNumero: Int = 0

    @SerializedName("NombreProducto")
    var nombreProducto: String = ""

    @SerializedName("Precio")
    var precio: Float = 0.0f

    @SerializedName("Cantidad")
    var cantidad: Int = 0

    @SerializedName("Monto")
    var monto: Float = 0.0f

    @SerializedName("Imagen")
    var imagen: String = ""
}
