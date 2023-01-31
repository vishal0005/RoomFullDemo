package com.vishal.roomfulldemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal.roomfulldemo.adapter.CateListAdapter
import com.vishal.roomfulldemo.databinding.CategoryListFragBinding
import com.vishal.roomfulldemo.interfc.onMyItemListener
import com.vishal.roomfulldemo.viewmodel.fragment.CategoryListViewModel

class CategoryListFrag : Fragment() {

    lateinit var binding: CategoryListFragBinding
    lateinit var vieweModel: CategoryListViewModel
    lateinit var adapter: CateListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryListFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vieweModel = CategoryListViewModel(requireActivity())
        binding.viewModel = vieweModel
        binding.lifecycleOwner = this

        vieweModel.allCategoryLive.observe(requireActivity()) { it ->
            vieweModel.allCategory.clear()
            vieweModel.allCategory.addAll(it)
            adapter.notifyDataSetChanged()
        }

        adapter =
            CateListAdapter(requireActivity(), vieweModel.allCategory, object : onMyItemListener {
                override fun onItemClick(pos: Int, objects: Any) {

                }
            })
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

    }

}