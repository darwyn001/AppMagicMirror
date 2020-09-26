package com.lemusc.magicmirrorcontroller.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.lemusc.magicmirrorcontroller.R
import com.lemusc.magicmirrorcontroller.VolleySingleton
import kotlinx.android.synthetic.main.fragment_raspberry.view.*

private const val ARG_URL = "api_url"

class RaspberryFragment : Fragment() {

    private var apiUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            apiUrl = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_raspberry, container, false)

        view.buttonRestart.setOnClickListener {
            excecuteCommand("reboot")
        }
        view.buttonShutdown.setOnClickListener {
            excecuteCommand("shutdown")
        }

        return view
    }

    private fun excecuteCommand(action:String) {
        val request = StringRequest(
            Request.Method.GET,
            "$apiUrl$action?apiKey=c59340c7de5a4b06ba88bc5960aa8244",
            {
                Log.e("TAAAAA", "AAAAAAaaa")
            },
            {
                Log.e("TAAAAA", "AAAAAAaaa")
            })

        val vol = Volley.newRequestQueue(requireContext())
        vol.add(request)
    }

    companion object {

        @JvmStatic
        fun newInstance(apiUrl: String) = RaspberryFragment().apply {
            arguments = Bundle().apply {
                putString(
                    ARG_URL, apiUrl
                )
            }
        }
    }
}