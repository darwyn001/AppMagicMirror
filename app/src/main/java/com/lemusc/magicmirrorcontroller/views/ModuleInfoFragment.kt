package com.lemusc.magicmirrorcontroller.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.lemusc.magicmirrorcontroller.R
import com.lemusc.magicmirrorcontroller.poko.Module
import kotlinx.android.synthetic.main.fragment_module_info.view.*

class ModuleInfoFragment(val module: Module, val apiUrl:String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_module_info, container, false)

        view.textViewPositionInfo.text = module.index
        view.textViewIdInfo.text = module.id
        view.textViewNameInfo.text = module.nombre
        view.textViewPathInfo.text = module.ruta
        view.textViewFileInfo.text = module.archivo
        view.textViewClassInfo.text = module.clase
        view.switchShowInfo.isSelected = module.mostrar

        view.switchShowInfo.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
            val isShowed = if (module.mostrar) "show" else "hide"
            val k = apiUrl +"modules/"+ module.nombre + "/" + isShowed + "?apiKey=c59340c7de5a4b06ba88bc5960aa8244"

            val request = StringRequest(
                Request.Method.GET,
                k,
                {
                    Log.e("TAAAAA", "AAAAAAaaa")

                },
                {
                    Log.e("TAAAAA", "AAAAAAaaa")
                })


            val vol = Volley.newRequestQueue(requireContext())
            vol.add(request)
        }

        return view
    }

}