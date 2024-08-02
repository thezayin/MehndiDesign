package com.thezayin.framework.extension.functions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URI
import java.net.URISyntaxException
import java.net.URL
import java.util.Random


fun loadBitmap(url: String?): Bitmap? {
    var url = url
    var bitmap: Bitmap? = null
    var `in`: InputStream? = null
    var out: BufferedOutputStream? = null
    val IO_BUFFER_SIZE = 4 * 1024
    try {
        val uri = URI(url)
        url = uri.toASCIIString()
        `in` = BufferedInputStream(
            URL(url).openStream(), IO_BUFFER_SIZE
        )
        val dataStream = ByteArrayOutputStream()
        out = BufferedOutputStream(dataStream, IO_BUFFER_SIZE)
        var bytesRead: Int
        val buffer = ByteArray(IO_BUFFER_SIZE)
        while ((`in`.read(buffer).also { bytesRead = it }) != -1) {
            out.write(buffer, 0, bytesRead)
        }
        out.flush()
        val data = dataStream.toByteArray()
        val options = BitmapFactory.Options()
        bitmap = BitmapFactory.decodeByteArray(
            data, 0, data.size, options
        )
    } catch (e: IOException) {
        return null
    } catch (e: URISyntaxException) {
        e.printStackTrace()
    } finally {
        try {
            `in`!!.close()
            out?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return bitmap
}

fun saveToInternalStorage(url: String): String {
    val finalBitmap = loadBitmap(url)
    val root = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES
    ).toString()
    val myDir = File("$root/Mehndi Images")
    myDir.mkdirs()
    val generator = Random()

    var n = 10000
    n = generator.nextInt(n)
    val fname = "Image-$n.jpg"
    val file = File(myDir, fname)
    if (file.exists()) file.delete()
    try {
        val out = FileOutputStream(file)
        finalBitmap?.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
        out.close()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return file.absolutePath
}


tailrec fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.checkForInternet(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}



fun Context.sendMail() {
    val i = Intent(Intent.ACTION_SEND)
    i.setType("message/rfc822")
    i.putExtra(Intent.EXTRA_EMAIL, arrayOf("gasandsafety@gmail.com"))
    i.putExtra(Intent.EXTRA_SUBJECT, "Gas And Safety")
    i.putExtra(Intent.EXTRA_TEXT, "body of email")
    try {
        startActivity(Intent.createChooser(i, "Send mail..."))
    } catch (ex: ActivityNotFoundException) {
        Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
    }
}

fun Context.openLink(link: String) {
    val intent = Intent(
        Intent.ACTION_VIEW, Uri.parse(link)
    )
    this.startActivity(intent)
}

