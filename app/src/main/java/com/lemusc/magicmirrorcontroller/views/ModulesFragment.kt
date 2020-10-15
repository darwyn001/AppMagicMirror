package com.lemusc.magicmirrorcontroller.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.lemusc.magicmirrorcontroller.BaseDialog
import com.lemusc.magicmirrorcontroller.R
import com.lemusc.magicmirrorcontroller.adapters.ModulesAdapter
import com.lemusc.magicmirrorcontroller.interfaces.IDismiss
import com.lemusc.magicmirrorcontroller.poko.Module
import kotlinx.android.synthetic.main.fragment_modules.view.*

private const val ARG_URL = "api_url"

class ModulesFragment : Fragment() {

    private var apiUrl: String? = null
    private lateinit var rvModules: RecyclerView

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
        val view = inflater.inflate(R.layout.fragment_modules, container, false)

        rvModules = view.recyclerViewModules
        rvModules.setHasFixedSize(true)
        rvModules.layoutManager = LinearLayoutManager(requireContext())
        rvModules.adapter = createAdapter()

        getModuesList()
        return view
    }

    private fun getModuesList() {
        val request = JsonObjectRequest(
            Request.Method.GET,
            apiUrl + "modules?apiKey=c59340c7de5a4b06ba88bc5960aa8244",
            null,
            { response ->
                val d = response.getJSONArray("data")
                val l: MutableList<Module> = mutableListOf()
                for (i in 0 until d.length()) {
                    val x = d.getJSONObject(i)
                    val n = Module(
                        x.getString("index"),
                        x.getString("identifier"),
                        x.getString("name"),
                        x.getString("path"),
                        x.getString("file"),
                        x.getString("config"),
                        x.getString("classes"),
                        x.getBoolean("hidden")
                    )
                    l.add(n)
                }
                (rvModules.adapter as ModulesAdapter).addItems(l)
                Log.e("TAAAAA", "AAAAAAaaa")
            },
            {
                Log.e("TAAAAA", "AAAAAAaaa")
            })

        val vol = Volley.newRequestQueue(requireContext())
        vol.add(request)
    }

    private fun createAdapter(): ModulesAdapter {
        return ModulesAdapter {
            val moduleInfoFragment = ModuleInfoFragment(it, apiUrl!!)

            val postSupervisionInfo = BaseDialog(moduleInfoFragment, "Modulo")
            postSupervisionInfo.isClosable(true)
            postSupervisionInfo.isCancelable = false
            postSupervisionInfo.showNow(parentFragmentManager, "BASE_DIALOG_TAG")

            postSupervisionInfo.setIDissmis(object : IDismiss {
                override fun getDissmis(isDissmissed: Boolean) {
                    activity?.runOnUiThread {
                        (rvModules.adapter as ModulesAdapter).clearList()
                        getModuesList()
                    }
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(apiUrl: String) = ModulesFragment().apply {
            arguments = Bundle().apply {
                putString(
                    ARG_URL, apiUrl
                )
            }
        }
    }
}