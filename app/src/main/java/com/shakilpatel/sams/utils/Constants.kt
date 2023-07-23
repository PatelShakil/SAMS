package com.shakilpatel.sams.utils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.widget.Toast
import com.shakilpatel.sams.utils.notification.ApiUtils
import com.shakilpatel.sams.utils.notification.PushNotification
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class Constants {
    companion object{
        fun encodeImage(bitmap: Bitmap):String{
            var pwidth = bitmap.width
            var pheight = bitmap.height
            var pbitmap = Bitmap.createScaledBitmap(bitmap,pwidth,pheight,false)
            var byteArrayOutputStream = ByteArrayOutputStream()
            pbitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream)
            var bytes=byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(bytes, Base64.DEFAULT)
        }
        fun decodeImage(postimg:String):Bitmap{
            var bytes = Base64.decode(postimg,Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        }
        fun createBitmapFromUri(context: Context, uri: Uri): Bitmap? {
            var bitmap: Bitmap? = null
            var inputStream: InputStream? = null

            try {
                val contentResolver: ContentResolver = context.contentResolver
                inputStream = contentResolver.openInputStream(uri)
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
            }

            return bitmap
        }
        fun generateRandomValue(): String {
            val characterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            val random = Random(System.currentTimeMillis())
            val randomValue = StringBuilder()

            repeat(9) {
                val randomIndex = random.nextInt(characterSet.length)
                randomValue.append(characterSet[randomIndex])
            }

            return randomValue.toString()
        }
        fun convertLongToDate(timestamp: Long, format: String): String {
            val date = Date(timestamp)
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            return sdf.format(date)
        }
    }
}