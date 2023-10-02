package com.example.week5

import android.app.NotificationManager
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import java.io.IOException
import java.net.URL

class BroadcastReiceverStatic : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.i("BroadcastReiceverStatic", "test")

        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            if (isConnected(context)) {
                Toast.makeText(context, "Internet connected!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Internet is not connected!!", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

}