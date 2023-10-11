package com.example.week7

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : MenuActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var sensorList: ListView
    private lateinit var message: TextView
    private lateinit var wifiManager : WifiManager
    private var mLight: Sensor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        sensorList = findViewById(R.id.sensorList)
        message = findViewById(R.id.message)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
//        wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager

//        val currentWifi = wifiManager.connectionInfo



        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        var arrayAdaptor =
            ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, deviceSensors)
        sensorList.adapter = arrayAdaptor

    }


    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light1 = event.values[0]

            message.text = "Sensor: $light1\n${brightness(light1)}"
        }
    }

    override fun onResume() {
        super.onResume()
        mLight?.also {
            light -> sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)

        }
    }

    private fun brightness(brightness: Float): String {
        return when (brightness.toInt()) {
            0 -> "maybe dark"
            in 1..10 -> "bad eyes"
            in 11..50 -> "its grey"
            in 51 .. 5000 -> "normal"
            in 5001..250000 -> "LIGHT!"
            else -> "This light will kill your eyes"
        }
     }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }
}