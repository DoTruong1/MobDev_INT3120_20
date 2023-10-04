package com.example.week6

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wee6.ContentProviderTest
import com.example.wee6.DatabaseTest

class MainActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var numberEditText: EditText
    lateinit var nextButton: Button
    lateinit var prevButton: Button
    lateinit var clearButton: Button
    lateinit var updateButton: Button
    lateinit var insertButton: Button
    lateinit var deleteButton: Button

    private lateinit var musicService: MusicService
    private var isBound = false
    companion object {
        var currId : Int = -1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.name)
        numberEditText = findViewById(R.id.number)
        nextButton = findViewById(R.id.nextBtn)
        prevButton = findViewById(R.id.prevButn)
        clearButton = findViewById(R.id.clearBtn)
        insertButton = findViewById(R.id.insertBtn)
        updateButton = findViewById(R.id.updateBtn)
        deleteButton = findViewById(R.id.deleteBtn)
        val playButton = findViewById<Button>(R.id.playButton)
        val pauseButton = findViewById<Button>(R.id.pauseButton)
        val stopButton = findViewById<Button>(R.id.stopButton)

        //create db
        var databaseTest = DatabaseTest(applicationContext)
        var db = databaseTest.readableDatabase
//        var rs = db.rawQuery("SELECT * FROM ${DatabaseTest.TABLE_NAME}", null)
        var rs = contentResolver.query(
            ContentProviderTest.CONTENT_URI,
            arrayOf(ContentProviderTest.ID, ContentProviderTest.NAME, ContentProviderTest.NUMBER),
            null,
            null,
            null)

        insertButton.isClickable = currId == -1
        nextButton.setOnClickListener {
            if (rs?.moveToNext() == true) {
                currId = rs.getInt(0)
                nameEditText.setText(rs.getString(1))
                numberEditText.setText(rs.getString(2))
            }

        }


        prevButton.setOnClickListener {
            if (rs?.moveToPrevious() == true) {
                currId = rs.getInt(0)
                nameEditText.setText(rs.getString(1))
                numberEditText.setText(rs.getString(2))
            }
        }

        clearButton.setOnClickListener {
            nameEditText.setText("")
            numberEditText.setText("")
            nameEditText.requestFocus()
            currId = -1
            rs?.requery()
        }

        insertButton.setOnClickListener {
            var cv = ContentValues()
            cv.put(ContentProviderTest.NAME, nameEditText.text.toString())
            cv.put(ContentProviderTest.NUMBER, numberEditText.text.toString())
            val newUri = contentResolver.insert(ContentProviderTest.URI, cv)
            //check if inserted
            if (newUri != null) {
                val insertedId: String = newUri.toString()
                Toast.makeText(this, "Row inserted with ID: $insertedId", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to insert row", Toast.LENGTH_SHORT).show()
            }
            clearButton.performClick()
            rs?.requery()
        }

        updateButton.setOnClickListener {
            var contentValues = ContentValues()
            contentValues.put(ContentProviderTest.NAME, nameEditText.text.toString())
            contentValues.put(ContentProviderTest.NUMBER, numberEditText.text.toString())

            val rowsUpdated : Int = contentResolver.update(ContentProviderTest.URI, contentValues, "id = ?", arrayOf(currId.toString()))
            // Check the result of the update operation
            if (rowsUpdated > 0) {
                // Update successful
                Toast.makeText(this, "Content provider updated", Toast.LENGTH_SHORT).show()
            } else {
                // Update failed
                Toast.makeText(this, "Failed to update content provider", Toast.LENGTH_SHORT).show()
            }
            clearButton.performClick()

            rs?.requery()

        }

        deleteButton.setOnClickListener {
            val rowsDeteled = contentResolver.delete(ContentProviderTest.CONTENT_URI, "id = ?", arrayOf(currId.toString()))
            if (rowsDeteled > 0) {
                Toast.makeText(this, "${nameEditText.text.toString()} deleted", Toast.LENGTH_SHORT).show()
                clearButton.performClick()
            } else {
                Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show()

            }
            clearButton.performClick()

            rs?.requery()
        }
//        if (rs.moveToFirst()) {
//            Toast.makeText(applicationContext, rs.getString(1) + "\n" + rs.getString(2), Toast.LENGTH_LONG).show()
//        }
        playButton.setOnClickListener {
            startService(Intent(this, MusicService::class.java))
        }

        stopButton.setOnClickListener {
            stopService(Intent(this, MusicService::class.java))
        }


    }

//    override fun onStart() {
//        super.onStart()
//        var serviceIntent = Intent(this, MusicService::class.java)
//    }
}