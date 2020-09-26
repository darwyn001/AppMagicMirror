package com.lemusc.magicmirrorcontroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_base_dialog.view.*

class BaseDialog(content: Fragment, title: String) : DialogFragment() {

    var contentSent: Fragment? = null
    private var title: String? = null
    private var closable: Boolean? = null
    private var buttons: MutableList<Button> = mutableListOf()

    lateinit var bottomBar: LinearLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_dialog, container, false)

        bottomBar = view.LinearLayoutBottomBaseDialog

        view.textViewTitleBaseDialog.text = title
        view.buttonCloseBase.setOnClickListener { this.dismiss() }

        replaceContent(contentSent!!)

        if (!closable!!)
            view.buttonCloseBase.visibility = View.INVISIBLE

        buttons.let {
            for (button: Button in it) {
                view.LinearLayoutBottomBaseDialog.addView(button)
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun replaceContent(newContent: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout3, newContent)
        transaction.commit()
    }

    init {
        this.contentSent = content
        this.title = title
    }

    fun isClosable(closable: Boolean) {
        this.closable = closable
    }

    fun updateButtons(buttons: MutableList<Button>) {
        removeButtons()
        setButtons(buttons)
        showButtons()
    }

    fun updateButton(button: Button) {
        removeButtons()
        setButton(button)
        showButtons()
    }

    fun removeButtons() {
        for (v: Button in buttons) {
            bottomBar.removeView(v)
        }
        bottomBar.visibility = View.VISIBLE
    }

    fun setButtons(buttons: MutableList<Button>) {
        if (buttons.isNotEmpty())
            this.buttons = buttons.toMutableList()
    }

    fun setButton(button: Button) {
        this.buttons.add(button)
    }

    private fun showButtons() {
        buttons.let {
            for (button: Button in it) {
                bottomBar.addView(button)
            }
        }
    }

}