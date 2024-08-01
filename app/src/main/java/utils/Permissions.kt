package utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissions {
    val CAMERA_PERMISSION_CODE = 100
    val REQUEST_TAKE_PHOTO = 101
    val CALL_PERMISSION_CODE = 102

    fun checkCameraPermission(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            takePhoto(context)
        }
    }

    private fun takePhoto(context: Context){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(context.packageManager) != null){
            (context as Activity).startActivityForResult(intent, REQUEST_TAKE_PHOTO)
        }
    }

    fun checkCallPermission(context: Context, phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_CODE
            )
        } else {
            call(context, phoneNumber)
        }
    }

    private fun call(context: Context, e: String){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:+504${e}")
        context.startActivity(intent)
    }

}