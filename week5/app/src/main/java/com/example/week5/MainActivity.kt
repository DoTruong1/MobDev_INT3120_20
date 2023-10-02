package com.example.week5

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import com.example.week5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var editText: EditText
    private lateinit var startButton : Button
    private lateinit var stopButton : Button

    private lateinit var saveNameBtn : Button
    private lateinit var changeNameBtn : Button
    private lateinit var nameInput : EditText
    private lateinit var preferences: SharedPreferences

    private lateinit var broadcastReiceverStatic: BroadcastReiceverStatic


    companion object {
        private const val PREFERENCES_FILE = "preferences_file"
        private var isEditable = true
    }
    override fun onStop() {
        unregisterReceiver(broadcastReiceverStatic)
        super.onStop()

    }
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onStart() {
        super.onStart()
        broadcastReiceverStatic = BroadcastReiceverStatic()
        registerReceiver(broadcastReiceverStatic, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startButton = findViewById(R.id.buttonStart)
        stopButton = findViewById(R.id.buttonStop)
        editText = findViewById(R.id.input)


        nameInput = findViewById(R.id.nameInput)
        changeNameBtn = findViewById(R.id.changeName)
        saveNameBtn = findViewById(R.id.saveName)
        preferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)



        val name : String = preferences.getString("name", "").toString()
        if (name.isNotEmpty()) {
            isEditable = false;
            nameInput.isEnabled = isEditable
        }
        nameInput.setText(name)
        changeNameBtn.setOnClickListener {
            startSaving()
        }

        saveNameBtn.setOnClickListener {
            saveName()
        }


        startButton.setOnClickListener {
            startService()
        }
        stopButton.setOnClickListener {
            stopService()
        }
//        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    private fun saveName() {
        isEditable = false;
        val sharedPreferences : SharedPreferences.Editor =   preferences.edit()
        sharedPreferences.putString("name", nameInput.text.toString())
        nameInput.isEnabled = isEditable
        sharedPreferences.commit()
    }

    private fun startSaving() {
        isEditable = true;
        nameInput.setText(preferences.getString("name", "").toString())
        nameInput.isEnabled = isEditable
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
    private fun startService() {
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("input", editText.text.toString().trim())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Start service as foreground
            startForegroundService(intent)
        } else {
        // Start service normally
            startService(intent)
        }

    }
    private fun stopService() {
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }

    private fun isExist() : Boolean {
        if(preferences.contains("name") && preferences.getString("user_id", null) != null) {
            return true
        }
        return false
    }

}