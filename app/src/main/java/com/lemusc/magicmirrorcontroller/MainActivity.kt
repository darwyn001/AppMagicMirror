package com.lemusc.magicmirrorcontroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonContinue.setOnClickListener {
            val ip = editTextIpDevice.text.toString()
            if (ip.isNotEmpty() && validateIp(ip)) {
                val intent = Intent(applicationContext, ControllerActivity::class.java)
                intent.putExtra("IP_DEVICE", ip)
                startActivity(intent)
            } else {
                editTextIpDevice.error =
                    applicationContext.resources.getString(R.string.lbl_invaid_ip)
            }
        }
    }

    private fun validateIp(ip: String): Boolean {
        val regex = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"

        return (regex.toRegex()).matches(ip)
    }
}