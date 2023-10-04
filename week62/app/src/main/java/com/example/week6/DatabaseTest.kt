package com.example.wee6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseTest(context: Context) : SQLiteOpenHelper(context, "User", null, 1) {
    companion object {
        val TABLE_NAME : String = "Users"
        val NAME_COl : String = "name"
        val NUMBER_COL : String = "number"
        val ID_COL : String = "id"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COl + " TEXT," +
                NUMBER_COL + " TEXT" + ")")

        db?.execSQL(query)
        db?.execSQL("INSERT INTO $TABLE_NAME (name, number) values ('do', '12345')")
        db?.execSQL("INSERT INTO $TABLE_NAME (name, number) values ('do2', '123457')")


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}