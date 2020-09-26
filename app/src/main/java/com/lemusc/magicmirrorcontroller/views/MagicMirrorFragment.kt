package com.lemusc.magicmirrorcontroller.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.lemusc.magicmirrorcontroller.R
import kotlinx.android.synthetic.main.fragment_magic_mirror.view.*

private const val ARG_URL = "api_url"

class MagicMirrorFragment : Fragment() {

    private var apiUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            apiUrl = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_magic_mirror, container, false)

        view.buttonMinimize.setOnClickListener { excecuteCommand("minimize") }
        view.buttonRefresh.setOnClickListener { excecuteCommand("refresh") }
        view.switchFullScreen.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
            excecuteCommand(
                "togglefullscreen"
            )
        }

        val seek: SeekBar = view.seekBarBrigtness
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress == 1)
                    excecuteCommand("brightness/10")
                else
                    excecuteCommand("brightness/" + (progress * 20))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        return view
    }

    private fun excecuteCommand(action: String) {
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
        fun newInstance(apiUrl: String) = MagicMirrorFragment().apply {
            arguments = Bundle().apply {
                putString(
                    ARG_URL, apiUrl
                )
            }
        }
    }
}