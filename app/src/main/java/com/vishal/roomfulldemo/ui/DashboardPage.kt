package com.vishal.roomfulldemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vishal.roomfulldemo.R
import com.vishal.roomfulldemo.adapter.MyDashboardAdapter
import com.vishal.roomfulldemo.databinding.DashboardPageBinding
import com.vishal.roomfulldemo.ui.fragment.CategoryListFrag
import com.vishal.roomfulldemo.ui.fragment.ItemListFrag
import com.vishal.roomfulldemo.utilitys.MyActions
import com.vishal.roomfulldemo.viewmodel.DashboardViewModel

class DashboardPage : AppCompatActivity() {
    lateinit var binding: DashboardPageBinding
    lateinit var viewModel: DashboardViewModel

    lateinit var adapter: MyDashboardAdapter
    var allFragment = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DashboardPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = DashboardViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.commonListener.observe(this) { it ->
            if (it == MyActions.CATEGORY_CLICK) {
                binding.viewPager.currentItem = 0
            } else if (it == MyActions.ITEMS_CLICK) {
                binding.viewPager.currentItem = 1
            }
        }

        allFragment.add(CategoryListFrag())
        allFragment.add(ItemListFrag())
        adapter = MyDashboardAdapter(supportFragmentManager, lifecycle, allFragment)
        binding.viewPager.adapter = adapter
    }
}