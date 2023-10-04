package com.example.wee6

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class ContentProviderTest : ContentProvider() {
    lateinit var db : SQLiteDatabase
    companion object {
        val PROVIDER_NAME : String = "com.example.week6.provider/ContentProviderTest"
        val URL : String = "content://$PROVIDER_NAME/${DatabaseTest.TABLE_NAME}"
        val CONTENT_URI : Uri = Uri.parse(URL)
        val _ID : String = "id"
    }
    override fun onCreate(): Boolean {
        var helper : DatabaseTest? = context?.let { DatabaseTest(it) }
        db = helper?.writableDatabase!!
        return db != null
    }

    override fun query(
        uri: Uri,
        cols : Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query(DatabaseTest.TABLE_NAME, cols, condition, condition_val, null, null ,order)
    }

    override fun getType(p0: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert(DatabaseTest.TABLE_NAME, null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri

    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        var count = db.delete(DatabaseTest.TABLE_NAME, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count

    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        var count = db.update(DatabaseTest.TABLE_NAME, cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count

    }

}