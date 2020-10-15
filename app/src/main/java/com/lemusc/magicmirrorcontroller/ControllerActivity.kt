package com.lemusc.magicmirrorcontroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lemusc.magicmirrorcontroller.adapters.PagedIndexAdapter
import com.lemusc.magicmirrorcontroller.views.MagicMirrorFragment
import com.lemusc.magicmirrorcontroller.views.ModulesFragment
import com.lemusc.magicmirrorcontroller.views.RaspberryFragment
import kotlinx.android.synthetic.main.activity_controller.*

class ControllerActivity : AppCompatActivity() {
    private lateinit var apiUrl: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controller)

        val viewPager: ViewPager = vp_patrimonio_pager
        val tabs: TabLayout = tbl_modules
        val ip = intent?.getStringExtra("IP_DEVICE")
        apiUrl = "http://$ip:8080/api/"

        tabs.setupWithViewPager(viewPager)
        setupViewPager(viewPager)
        tabs.tabMode = TabLayout.MODE_SCROLLABLE

        val tvUrlApi: TextView = textViewUrlApi
        tvUrlApi.text = "URL API : http://$ip:8080/api/"
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = PagedIndexAdapter(supportFragmentManager)

        adapter.addFragment(
            ModulesFragment.newInstance(apiUrl),
            applicationContext.resources.getString(R.string.lbl_modules)
        )
        adapter.addFragment(
            MagicMirrorFragment.newInstance(apiUrl),
            applicationContext.resources.getString(R.string.lbl_magic_mirror)
        )
        adapter.addFragment(
            RaspberryFragment.newInstance(apiUrl),
            applicationContext.resources.getString(R.string.lbl_raspberry)
        )

        viewPager.adapter = adapter
    }
}