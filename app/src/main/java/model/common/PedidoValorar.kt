package model.common

import android.os.Parcel
import android.os.Parcelable

class PedidoValorar (): Parcelable {
    var pedidoNumero: Int = 0
    var Cliente: String = ""
    var usuario: String = ""
    var total: Float = 0.0f
    var estado: String = ""

    constructor(parcel: Parcel) : this() {
        pedidoNumero= parcel.readInt()
        Cliente = parcel.readString() ?: ""
        usuario = parcel.readString() ?: ""
        total = parcel.readFloat()
        estado= parcel.readString() ?: ""

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pedidoNumero)
        parcel.writeString(Cliente)
        parcel.writeString(usuario)
        parcel.writeFloat(total)
        parcel.writeString(estado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PedidoValorar> {
        override fun createFromParcel(parcel: Parcel): PedidoValorar {
            return PedidoValorar(parcel)
        }

        override fun newArray(size: Int): Array<PedidoValorar?> {
            return arrayOfNulls(size)
        }
    }
}

