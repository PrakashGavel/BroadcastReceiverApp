package com.example.broadcastreceiverapp

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.broadcastreceiverapp.ui.theme.BroadcastReceiverAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var testReceiver: TestReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testReceiver = TestReceiver()
        val filter = IntentFilter("com.example.broadcastreceiverapp.TEST_ACTION")

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(testReceiver, filter, RECEIVER_EXPORTED)
        }

        setContent {
            BroadcastReceiverAppTheme {
                var message by remember { mutableStateOf("Waiting for broadcast...") }

                LaunchedEffect(Unit) {
                    testReceiver.onMessageReceived = { msg ->
                        message = msg
                    }
                }

                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    Text("Receiver App")
                    Text(message, modifier = Modifier.padding(top = 16.dp))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(testReceiver)
    }
}
