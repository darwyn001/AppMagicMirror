package com.lemusc.magicmirrorcontroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment


class LoadingDialog : DialogFragment() {


    override fun onStart() {
        super.onStart()

        if (dialog != null && dialog!!.window != null)
            dialog!!.window!!.setBackgroundDrawable(null)

        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading_dialog, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = LoadingDialog()
    }
}