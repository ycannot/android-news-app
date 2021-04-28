package com.example.newsapp.activities.main

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.fragments.home.HomeViewModel
import com.example.newsapp.helpers.AppbarHelper
import com.example.newsapp.helpers.DatePickerFragment
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var selectedDate: String
    private lateinit var viewModel:MainViewModel
    lateinit var appbarHelper:AppbarHelper
    val dateLiveData = MutableLiveData<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        setSelectedYear(viewModel.getTenDaysBefore())
        initAppbar()
    }

    private fun initAppbar() {
        appbarHelper = AppbarHelper(binding.appbar)
        with(appbarHelper){
            backAction = {onSupportNavigateUp()}
            menuAction = {drawerLayout.openDrawer(GravityCompat.START)}
            filterAction = {
                val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    setSelectedYear(year, month, dayOfMonth)
                }
                val newFragment = DatePickerFragment(listener, selectedDate)
                newFragment.show(supportFragmentManager, "datePicker")
            }
        }
    }

    private fun setSelectedYear(year:Int, month:Int, day:Int){
        selectedDate = viewModel.getFormattedDateString(year, month, day)
        dateLiveData.postValue(selectedDate)
    }

    private fun setSelectedYear(calendar:Calendar){
        setSelectedYear(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        super.onBackPressed()
    }
}