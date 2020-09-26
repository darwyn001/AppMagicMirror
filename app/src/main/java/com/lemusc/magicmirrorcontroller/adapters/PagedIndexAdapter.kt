package com.lemusc.magicmirrorcontroller.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagedIndexAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    val fragments = mutableListOf<Fragment>()
    val fragmentTitles = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitles[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    public fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitles.add(title)

    }
}