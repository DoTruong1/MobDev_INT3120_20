package com.example.week7

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class MenuActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item1 -> {
                // Handle Option 1 selection
                switch(Intent(this, MainActivity::class.java))
            }
            R.id.menu_item2 -> {
                // Handle Option 2 selection
                switch(Intent(this, WifiActivity::class.java))
            }
            R.id.menu_item3 -> {
                // Handle Option 3 selection
                switch(Intent(this, SmsActivityNew::class.java))

            }
            R.id.menu_item4 -> {
                // Handle Option 3 selection
                switch(Intent(this, LocationActivity::class.java))

            }
            R.id.menu_item5 -> {
                // Handle Option 3 selection
                switch(Intent(this, MapsActivity::class.java))

            }
            R.id.menu_item6 -> {
                // Handle Option 3 selection
                switch(Intent(this, WebviewActivity::class.java))

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switch(intent: Intent): Boolean {
//        val intent = Intent(this, AnotherActivity::class.java)
        startActivity(intent)
        return true
    }
}