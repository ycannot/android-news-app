package com.example.newsapp.helpers

import android.view.View
import com.example.newsapp.databinding.CustomAppBarBinding

class AppbarHelper(appBarBinding: CustomAppBarBinding) {

    var backAction:(()->Unit)? = null
    var filterAction:(()->Unit)? = null
    var menuAction:(()->Unit)? = null
    var binding = appBarBinding


    init {
        binding.btnBack.setOnClickListener {
            backAction?.invoke()
        }
        binding.btnMenu.setOnClickListener {
            menuAction?.invoke()
        }
        binding.btnFilter.setOnClickListener {
            filterAction?.invoke()
        }
    }
    fun showBackButton(){
        binding.btnMenu.visibility = View.GONE
        binding.btnBack.visibility = View.VISIBLE
        binding.btnFilter.visibility = View.GONE
    }
    fun showMenuButton(){
        binding.btnMenu.visibility = View.VISIBLE
        binding.btnBack.visibility = View.GONE
        binding.btnFilter.visibility = View.VISIBLE
    }
    fun setTitle(title:String){
        binding.tvToolbarTitle.text = title
    }

}