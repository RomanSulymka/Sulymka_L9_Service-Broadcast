package com.example.sulymka_l9_service_broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Double
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var br: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getDoubleExtra(RESULT, 0.0) ?: 0.0
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment, Answer.newInstance(result))
                    .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(br, IntentFilter(BROADCAST_ACTION))
    }

    override fun onStop() {
        super.onStop()
        try {
            unregisterReceiver(br)
        } catch (e: IllegalArgumentException) {
            Log.e("Broadcast", "Time tick Receiver not registered", e)
        }
    }

    private fun listener(){
        start_service_textV.setOnClickListener {
            val number1Str: String = first_number_edit.text.toString()
            val number2Str: String = second_number_edit.text.toString()

            if (number1Str.trim() == "" || number2Str.trim() == "") {
                Toast.makeText(this, "You must type number", Toast.LENGTH_SHORT).show()
            } else {
                val number1 = Double.valueOf(number1Str)
                val number2 = Double.valueOf(number2Str)
                Intent(this, Calculate::class.java).apply {
                    putExtra(FIRST_NUMBER, number1)
                    putExtra(SECOND_NUMBER, number2)
                    startService(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listener()
    }
}