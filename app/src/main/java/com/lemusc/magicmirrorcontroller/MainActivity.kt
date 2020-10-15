package com.lemusc.magicmirrorcontroller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val dialog: LoadingDialog = LoadingDialog.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonContinue.setOnClickListener {
            dialog.show(supportFragmentManager, "Loading")
            val ip = editTextIpDevice.text.toString()
            if (ip.isNotEmpty() && validateIp(ip)) {
                testIp(ip)
            } else {
                setError(applicationContext.resources.getString(R.string.lbl_invaid_ip))
            }
        }
    }

    private fun setError(error: String) {
        editTextIpDevice.error = error

    }

    private fun testIp(ip: String) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            "http://$ip:8080/api/test?apiKey=c59340c7de5a4b06ba88bc5960aa8244",
            null,
            {
                if (it.getBoolean("success"))
                    showMenu(ip)
                else {
                    Toast.makeText(
                        applicationContext,
                        "No se encontró un espejo en esta dirección",
                        Toast.LENGTH_SHORT
                    ).show()

                    setError(applicationContext.resources.getString(R.string.lbl_invaid_ip))
                }
                dialog.dismiss()
            },
            {
                setError("no se obtuvo la respuesta deseada")
                dialog.dismiss()
            })

        val vol = Volley.newRequestQueue(applicationContext)
        vol.add(request)
    }

    private fun validateIp(ip: String): Boolean {
        val regex = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"

        return (regex.toRegex()).matches(ip)
    }

    private fun showMenu(ip: String) {
        val intent = Intent(applicationContext, ControllerActivity::class.java)
        intent.putExtra("IP_DEVICE", ip)
        startActivity(intent)
    }
}