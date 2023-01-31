package com.vishal.roomfulldemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vishal.roomfulldemo.BR
import com.vishal.roomfulldemo.database.Items
import com.vishal.roomfulldemo.databinding.AdItemsListBinding
import com.vishal.roomfulldemo.interfc.onMyItemListener
import com.vishal.roomfulldemo.interfc.onMyMultiItemListener
import java.util.Objects

class ItemsListAdapter : RecyclerView.Adapter<ItemsListAdapter.Holder> {

    var cn: Context
    var allItem: ArrayList<Items> = ArrayList()
    var listener: onMyMultiItemListener

    constructor(cn: Context, allItem: ArrayList<Items>, listener: onMyMultiItemListener) : super() {
        this.cn = cn
        this.allItem.clear()
        this.allItem.addAll(allItem)
        this.listener = listener
    }

    class Holder : ViewHolder {
        var binding: AdItemsListBinding

        constructor(binding: AdItemsListBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(objects: Any) {
            binding.setVariable(BR.viewModel, objects)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var binding = AdItemsListBinding.inflate(LayoutInflater.from(cn), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return allItem.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var model = allItem[position]
        holder.bind(model)
    }

    fun updateData(newData: ArrayList<Items>) {
        val diffCallback = UpdateCallBack(allItem, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        allItem.clear()
        allItem.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    class UpdateCallBack(
        var oldList: ArrayList<Items>,
        var newList: ArrayList<Items>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }

    }

}