package com.rq.orderbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SecondBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "received in SecondBroadcastReceiver", Toast.LENGTH_LONG).show()
    }
}