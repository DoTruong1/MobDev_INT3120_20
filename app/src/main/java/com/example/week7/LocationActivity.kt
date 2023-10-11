package com.example.week7

import android.annotation.SuppressLint
//import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.net.wifi.ScanResult
import androidx.core.app.ActivityCompat

class LocationActivity : MenuActivity() {
    private lateinit var cellIdTextView: TextView
    private lateinit var gpsTextView: TextView
    private lateinit var wifiTextView: TextView

    private lateinit var locationManager: LocationManager
    private lateinit var wifiManager: WifiManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        cellIdTextView = findViewById(R.id.cellIdTextView)
        gpsTextView = findViewById(R.id.gpsTextView)
        wifiTextView = findViewById(R.id.wifiTextView)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        getLocation()
    }
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val cellId = getCellId()
        val gpsLocation = getLastKnownLocation(LocationManager.GPS_PROVIDER)
//        val wifiLocation = getWifiLocation()

        cellIdTextView.text = "Cell ID: $cellId"
        gpsTextView.text = "GPS Location: $gpsLocation"
//        wifiTextView.text = "Wi-Fi Location: $wifiLocation"

        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null)
    }

    private fun getCellId(): Int {
        // Lấy thông tin cell ID từ các API cung cấp
        return 12345
    }


    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_LOCATION
            )
        } else {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(provider: String): Location? {
        if (locationManager.isProviderEnabled(provider)) {
            return locationManager.getLastKnownLocation(provider)
        }
        return null
    }



    private val locationListener: LocationListener = LocationListener { location -> // Xử lý sự thay đổi vị trí GPS
        gpsTextView.text = "GPS Location: ${location.latitude}, ${location.longitude}"
    }

    companion object {
        private const val PERMISSIONS_REQUEST_LOCATION = 100
    }
}