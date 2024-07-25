package utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import android.util.Base64
import android.widget.ImageView

class Converters {
    fun bitmapToBase64(bitmap: Bitmap?) : String?{
        if(bitmap != null){
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
        return ""
    }

    fun base64ToBitmap(base64: String): Bitmap? {
        return try {
            val imageBytes = Base64.decode(base64, Base64.DEFAULT)
            android.graphics.BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        } catch (e: IllegalArgumentException) {
            null // Retorna null si la cadena Base64 es inválida
        }
    }
}