package model.common

import android.os.Parcel
import android.os.Parcelable

class Producto() : Parcelable {
    var id: Int = 0
    var producto: String = ""
    var foto: String = ""
    var precio: Float = 0.0f
    var marca: String = ""
    var cantidad: Int = 0
    var subtotal: Double = 0.0
    var descripcion: String = ""
    var id_categoria: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        producto = parcel.readString() ?: ""
        foto = parcel.readString() ?: ""
        precio = parcel.readFloat()
        marca = parcel.readString() ?: ""
        cantidad = parcel.readInt()
        subtotal = parcel.readDouble()
        descripcion = parcel.readString() ?: ""
        id_categoria = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(producto)
        parcel.writeString(foto)
        parcel.writeFloat(precio)
        parcel.writeString(marca)
        parcel.writeInt(cantidad)
        parcel.writeDouble(subtotal)
        parcel.writeString(descripcion)
        parcel.writeInt(id_categoria)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}
