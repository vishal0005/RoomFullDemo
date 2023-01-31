package com.vishal.roomfulldemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyDashboardAdapter : FragmentStateAdapter {
    var allFragmnet: ArrayList<Fragment>

    constructor(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
        allFragmnet: ArrayList<Fragment>
    ) : super(fragmentManager, lifecycle) {
        this.allFragmnet = allFragmnet
    }


    override fun getItemCount(): Int {
        return allFragmnet.size
    }

    override fun createFragment(position: Int): Fragment {
        return allFragmnet[position]
    }
}