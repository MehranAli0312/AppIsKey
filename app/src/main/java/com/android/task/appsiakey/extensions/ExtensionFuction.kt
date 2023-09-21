package com.android.task.appsiakey.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.task.appsiakey.R
import com.bumptech.glide.Glide
import java.util.*


fun Context.checkInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    // if the android version is equal to M
    // or greater we need to use the
    // NetworkCapabilities to check what type of
    // network has the internet connection
    // Returns a Network object corresponding to
    // the currently active default data network.
    val network = connectivityManager.activeNetwork ?: return false

    // Representation of the capabilities of an active network.
    val activeNetwork =
        connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        // Indicates this network uses a Wi-Fi transport,
        // or WiFi has network connectivity
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

        // Indicates this network uses a Cellular transport. or
        // Cellular has network connectivity
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

        // else return false
        else -> false
    }
}


inline fun <reified T> ImageView.load(value: T) {
    Glide.with(this.context).load(value)
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_launcher_foreground))
        .into(this)
}

fun Context.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.wordCounts(): Map<String, Int> {
    val wordCounts = mutableMapOf<String, Int>()

    // Split by any whitespace, remove punctuation, and convert to lowercase
    val words = this.split(Regex("\\s+"))
        .map { it.replace(Regex("[^a-zA-Z0-9]"), "").lowercase(Locale.ROOT) }
        .filter { it.isNotEmpty() }

    // Count occurrences of each word
    for (word in words) {
        wordCounts[word] = wordCounts.getOrDefault(word, 0) + 1
    }

    return wordCounts
}

