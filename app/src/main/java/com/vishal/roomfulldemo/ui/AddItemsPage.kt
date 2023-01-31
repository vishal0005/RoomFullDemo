package com.vishal.roomfulldemo.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vishal.roomfulldemo.Helper
import com.vishal.roomfulldemo.R
import com.vishal.roomfulldemo.databinding.AddItemsPageBinding
import com.vishal.roomfulldemo.utilitys.MyActions
import com.vishal.roomfulldemo.viewmodel.AddItemsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@BindingAdapter("itemImage")
fun loadImage(view: AppCompatImageView, path: String) {
    Log.e("Items", "loadImage: $path")
    Glide.with(view.context).load(path).addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            view.setImageResource(R.mipmap.ic_launcher)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

    }).into(view)
}


class AddItemsPage : AppCompatActivity() {

    lateinit var binding: AddItemsPageBinding
    lateinit var viewModel: AddItemsViewModel
    var cn: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddItemsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = AddItemsViewModel(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.itemAddListener.observe(this) { it ->
            if (it) {
                Helper.toastMsg(cn, getString(R.string.item_added_successfully))
                setResult(RESULT_OK)
                finish()
            } else {
                Helper.toastMsg(cn, getString(R.string.item_added_failed))
            }
        }

        viewModel.commonListener.observe(this) { it ->
            if (it == MyActions.PICK_IMAGE) {
                Helper.pickImage(imagePickLauncher)
            }
        }


        viewModel.itemAddListener.observe(this) { it ->
            if (it) {
                Helper.toastMsg(cn, getString(R.string.item_added_successfully))
                setResult(RESULT_OK)
                finish()
            } else {
                Helper.toastMsg(cn, getString(R.string.item_added_failed))
            }
        }
    }

    var imagePickLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == RESULT_OK && it.data != null) {
                var uri = it.data!!.data
                CoroutineScope(Dispatchers.IO).launch {
                    var saveFile = Helper.saveUriToFilePath(cn, uri)
                    viewModel.updateImageFile(saveFile)
                }
            }
        }

}