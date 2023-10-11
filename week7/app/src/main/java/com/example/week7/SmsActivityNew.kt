package com.example.week7

import android.os.Bundle
import android.provider.Telephony
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class SmsActivityNew : MenuActivity() {
    private lateinit var smsListView: ListView
    private lateinit var smsList: ArrayList<SmsItem>

    companion object {
        private const val SMS_PERMISSION_REQUEST_CODE = 123
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        smsListView = findViewById(R.id.sms_listview)
        smsList = ArrayList()

        // Request SMS permission if not granted
        // Request SMS permission if not granted

        displaySmsMessages()

        smsListView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedSms = smsList[position]
                Toast.makeText(
                    this,
                    "SMS from ${selectedSms.sender}: ${selectedSms.content}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun displaySmsMessages() {
        val cursor = contentResolver.query(
            Telephony.Sms.Inbox.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.let {
            while (it.moveToNext()) {
                val sender = it.getString(it.getColumnIndexOrThrow(Telephony.Sms.Inbox.ADDRESS))
                val content = it.getString(it.getColumnIndexOrThrow(Telephony.Sms.Inbox.BODY))
                smsList.add(SmsItem(sender, content))
            }
            it.close()
        }

        val adapter = ArrayAdapter(
            this,
            R.layout.item_sms,
            R.id.sms_sender_textview,
            smsList
        )
        smsListView.adapter = adapter
    }
}