package com.lemusc.magicmirrorcontroller

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class VolleySingleton(private val context: Context) {
    private var instance: VolleySingleton? = null
    private var queue: RequestQueue? = null

    private fun VolleySingleton() {
        instance = this
        queue = Volley.newRequestQueue(context)
    }

    companion object {
       fun newIntance(context: Context) = VolleySingleton(context)
    }

    fun addQueue(queue: StringRequest) {
        this.queue?.add(queue)
    }
}