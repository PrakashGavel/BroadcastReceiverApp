package com.example.broadcastreceiverapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TestReceiver : BroadcastReceiver() {

    var onMessageReceived: ((String) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.example.broadcastreceiverapp.TEST_ACTION") {
            Log.d("ReceiverApp", "Broadcast received!")
            onMessageReceived?.invoke("Broadcast Received from Sender App")
        }
    }
}
