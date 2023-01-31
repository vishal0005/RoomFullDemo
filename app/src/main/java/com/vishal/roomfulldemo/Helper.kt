package com.vishal.roomfulldemo

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


object Helper {

    private val TAG: String = Helper.javaClass.name

    fun toastMsg(cn: Context, msg: String) {
        Toast.makeText(cn, msg, Toast.LENGTH_SHORT).show()
    }

    fun pickImage(imagePickLauncher: ActivityResultLauncher<Intent>) {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        imagePickLauncher.launch(intent)
    }

    suspend fun saveUriToFilePath(cn: Context, uri: Uri?): File? {
        var file = cn.filesDir
        file = File(file, "items")
        file.mkdir()
        file = File(file, "images_" + System.currentTimeMillis() + ".png")
        var out = FileOutputStream(file)
        var bitmap = getBitmapFromURI(cn, uri)
        return if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.flush()
            out.close()
            file
        } else {
            null
        }
    }

    suspend fun getBitmapFromURI(context: Context, uri: Uri?): Bitmap? {
        try {
            val input = context.contentResolver.openInputStream(uri!!) ?: return null
            return BitmapFactory.decodeStream(input)
        } catch (e: FileNotFoundException) {
            Log.e(TAG, "getBitmapFromURI: error ${e.message}")
        }
        return null
    }


}