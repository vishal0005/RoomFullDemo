package com.vishal.roomfulldemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vishal.roomfulldemo.BR
import com.vishal.roomfulldemo.database.Category
import com.vishal.roomfulldemo.databinding.AdCateListBinding
import com.vishal.roomfulldemo.interfc.onMyItemListener

class CateListAdapter : RecyclerView.Adapter<CateListAdapter.Holder> {

    var cn: Context
    var allCategory: ArrayList<Category>
    var listener: onMyItemListener

    constructor(
        cn: Context,
        allCategory: ArrayList<Category>,
        listener: onMyItemListener
    ) : super() {
        this.cn = cn
        this.allCategory = allCategory
        this.listener = listener
    }

    class Holder : ViewHolder {
        var binding: AdCateListBinding

        constructor(binding: AdCateListBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(obj: Any?) {
            binding.setVariable(BR.viewModel, obj)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var binding = AdCateListBinding.inflate(LayoutInflater.from(cn), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var model = allCategory[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return allCategory.size
    }

}