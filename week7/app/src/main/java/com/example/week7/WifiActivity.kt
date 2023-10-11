package com.example.week7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class WifiActivity : MenuActivity() {
    lateinit var wmgr : WifiManager
    private lateinit var wifiManager: WifiManager
    private lateinit var signalStrengthTextView: TextView
    private lateinit var ssidTextView: TextView
    private lateinit var bssidTextView: TextView
    private lateinit var linkSpeedTextView: TextView
    private lateinit var signalStrengthProgressBar: ProgressBar

    private val wifiReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val wifiInfo: WifiInfo? = wifiManager.connectionInfo
            val signalStrength = wifiInfo?.rssi ?: 0
            val ssid = wifiInfo?.ssid ?: ""
            val bssid = wifiInfo?.bssid ?: ""
            val linkSpeed = wifiInfo?.linkSpeed ?: 0

            signalStrengthTextView.text = "Signal Strength: $signalStrength dBm"
            ssidTextView.text = "SSID: $ssid"
            bssidTextView.text = "BSSID: $bssid"
            linkSpeedTextView.text = "Link Speed: $linkSpeed Mbps"

            signalStrengthProgressBar.progress = convertSignalStrengthToProgressBarValue(signalStrength)
        }
    }

    private fun convertSignalStrengthToProgressBarValue(signalStrength: Int): Int {
        val progressBarRange = 100
        val signalStrengthRange = -20 - (-100)
        val normalizedSignalStrength = signalStrength + 100

        return (normalizedSignalStrength * progressBarRange) / signalStrengthRange
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiReceiver)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(wifiReceiver)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        signalStrengthTextView = findViewById(R.id.signal_strength_textview)
        ssidTextView = findViewById(R.id.ssid_textview)
        bssidTextView = findViewById(R.id.bssid_textview)
        linkSpeedTextView = findViewById(R.id.link_speed_textview)
        signalStrengthProgressBar = findViewById(R.id.signal_strength_progressbar)
    }

    private fun handleWifiStateChange(wifiState: Int) {
        when (wifiState) {
            WifiManager.WIFI_STATE_ENABLED -> showToast("Wi-Fi turned on")
            WifiManager.WIFI_STATE_DISABLED -> showToast("Wi-Fi turned off")
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(wifiReceiver, IntentFilter(WifiManager.RSSI_CHANGED_ACTION))
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}