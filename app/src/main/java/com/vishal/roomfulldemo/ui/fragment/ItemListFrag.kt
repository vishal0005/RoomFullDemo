package com.vishal.roomfulldemo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal.roomfulldemo.adapter.ItemsListAdapter
import com.vishal.roomfulldemo.databinding.ItemListFragBinding
import com.vishal.roomfulldemo.interfc.onMyMultiItemListener
import com.vishal.roomfulldemo.ui.AddItemsPage
import com.vishal.roomfulldemo.utilitys.MyActions
import com.vishal.roomfulldemo.viewmodel.fragment.ItemListViewModel

class ItemListFrag : Fragment() {

    lateinit var binding: ItemListFragBinding
    lateinit var viewModel: ItemListViewModel
    lateinit var adapter: ItemsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemListFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ItemListViewModel(requireActivity())
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.allItemLiveData.observe(requireActivity()) { it ->
            viewModel.allItems.clear()
            viewModel.allItems.addAll(it)
            adapter.updateData(viewModel.allItems)
        }

        viewModel.commonListener.observe(requireActivity()) { it ->
            if (it == MyActions.JUMP_ADD_ITEMS) {
                addPageCall()
            }
        }

        adapter =
            ItemsListAdapter(requireActivity(), viewModel.allItems, object : onMyMultiItemListener {
                override fun onItem1Click(pos: Int, objects: Any) {
                }

                override fun onItem2Click(pos: Int, objects: Any) {
                }
            })
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    fun addPageCall() {
        addLauncher.launch(Intent(requireActivity(), AddItemsPage::class.java))
    }

    var addLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                viewModel.getAllItems()
            }
        }
}