package com.example.sulymka_l9_service_broadcast

import android.app.Service
import android.content.Intent
import android.os.IBinder

class Calculate : Service() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val intentToBR = Intent()
        intentToBR.action = BROADCAST_ACTION
        intentToBR.putExtra(RESULT, multiplication(intent))
        sendBroadcast(intentToBR)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    private fun multiplication(intent: Intent): Double {
        val firstNum = intent.getDoubleExtra(FIRST_NUMBER, -2.0)
        val secondNum = intent.getDoubleExtra(SECOND_NUMBER, -1.0)
        return firstNum * secondNum
    }
}