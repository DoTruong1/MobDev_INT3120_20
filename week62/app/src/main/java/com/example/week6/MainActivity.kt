package com.example.week6

import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wee6.ContentProviderTest
import com.example.wee6.DatabaseTest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //create db
        var databaseTest = DatabaseTest(applicationContext)
        var db = databaseTest.readableDatabase
//        var rs = db.rawQuery("SELECT * FROM ${DatabaseTest.TABLE_NAME}", null)
        var rs = contentResolver.query(ContentProviderTest.CONTENT_URI, arrayOf())
//        if (rs.moveToFirst()) {
//            Toast.makeText(applicationContext, rs.getString(1) + "\n" + rs.getString(2), Toast.LENGTH_LONG).show()
//        }
    }
}