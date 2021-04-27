package com.example.newsapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.widget.DatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.helpers.AppbarHelper
import com.example.newsapp.helpers.DatePickerFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var selectedDate: String
    lateinit var appbarHelper:AppbarHelper
    val dateLiveData = MutableLiveData<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        initAppbar()
        //init default listing date
        val defaultDateCalendar = Calendar.getInstance()
        defaultDateCalendar.add(Calendar.DAY_OF_MONTH, -10)
        setSelectedYear(defaultDateCalendar)
    }

    private fun initAppbar() {
        appbarHelper = AppbarHelper(binding.appbar)
        with(appbarHelper){
            backAction = {onSupportNavigateUp()}
            menuAction = {drawerLayout.openDrawer(GravityCompat.START)}
            filterAction = {
                val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    //TODO("refresh news list")
                    setSelectedYear(year, month, dayOfMonth)
                }
                val newFragment = DatePickerFragment(listener, selectedDate)
                newFragment.show(supportFragmentManager, "datePicker")
            }
        }


    }

    private fun setSelectedYear(year:Int, month:Int, day:Int){
        val monthString = if (month.toString().length == 1) "0${month+1}" else month.toString()
        val dayString = if (day.toString().length == 1) "0$day" else day.toString()
        selectedDate = "$year-$monthString-$dayString"
        dateLiveData.postValue(selectedDate)
    }

    private fun setSelectedYear(calendar:Calendar){
        setSelectedYear(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        super.onBackPressed()
    }
}