package com.example.newsapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.MainActivity
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.fragments.base.BaseFragment
import com.example.newsapp.helpers.AppbarHelper

class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var appbarHelper: AppbarHelper
    lateinit var startDateString: String
    private val newsListAdapter = NewsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        appbarHelper = (requireActivity() as MainActivity).appbarHelper
        appbarHelper.setTitle("Latest News")
        binding.rvNews.adapter = newsListAdapter
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        (requireActivity() as MainActivity).dateLiveData.observe(viewLifecycleOwner, Observer { dateString ->
            viewModel.getFootballNews(dateString)
            startDateString = dateString
        })
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                result.articles?.let {
                    newsListAdapter.articles.clear()
                    newsListAdapter.articles.addAll(it)
                    newsListAdapter.notifyDataSetChanged()
                }
            }
        })
        viewModel.apiFailLiveData.observe(viewLifecycleOwner, Observer { apiFail ->
            Toast.makeText(requireContext(), "${apiFail.code}: ${apiFail.message}" , Toast.LENGTH_SHORT).show()
        })
    }

}