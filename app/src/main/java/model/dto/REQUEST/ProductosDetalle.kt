package model.dto.REQUEST

data class ProductosDetalle(
    val nombreProducto: String = "",
    val precio : Double = 0.0,
    val cantidad : Int = 0,
    val monto : Double = 0.0
)
