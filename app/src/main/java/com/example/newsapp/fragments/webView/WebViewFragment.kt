package com.example.newsapp.fragments.webView

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.activities.main.MainActivity
import com.example.newsapp.databinding.WebViewFragmentBinding
import com.example.newsapp.fragments.base.BaseFragment

class WebViewFragment : BaseFragment() {

    private lateinit var binding:WebViewFragmentBinding
    private var url:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WebViewFragmentBinding.inflate(inflater, container, false)
        arguments?.let { args ->
            if (args.containsKey("url")){
                url = args.getString("url")
            }
        }
        val appbarHelper = (requireActivity() as MainActivity).appbarHelper
        appbarHelper.setTitle("Wear Mask")
        appbarHelper.showBackButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url?.let {
            binding.webView.loadUrl(url)
        }
    }



}