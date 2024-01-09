package com.galal.yajhz.UI

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.galal.yajhz.Adapter.CategoryAdapter
import com.galal.yajhz.Adapter.popularAdapter
import com.galal.yajhz.Adapter.trendingAdapter
import com.galal.yajhz.VM.ViewModel
import com.galal.yajhz.Pojo.Categories
import com.galal.yajhz.Pojo.Populars
import com.galal.yajhz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import mumayank.com.airlocationlibrary.AirLocation

class MainActivity : AppCompatActivity(), AirLocation.Callback {
    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var categoryViewModel: ViewModel
    private lateinit var catAdapter: CategoryAdapter
    private lateinit var popularViewModel: ViewModel
    private lateinit var popAdapter: popularAdapter
    private lateinit var trendingViewModel: ViewModel
    private lateinit var trendingAdapter: trendingAdapter
    private lateinit var myLocation : AirLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        categoryViewModel = ViewModelProviders.of(this)[ViewModel::class.java]
        catAdapter = CategoryAdapter()
        prepareCategoryItemRecycleView()
        categoryViewModel.getCategoriesItem()
        observedCategoryLiveData()


        popularViewModel = ViewModelProviders.of(this)[ViewModel::class.java]
        popAdapter = popularAdapter()
        preparePopularItemRecycleView()
        popularViewModel.getPopularsItem()
        observedPopularLiveData()

        trendingViewModel = ViewModelProviders.of(this)[ViewModel::class.java]
        trendingAdapter = trendingAdapter()
        prepareTrendingItemRecycleView()
        trendingViewModel.getTrendingItem()
        observedTrendingLiveData()

        location()




    }

    private fun prepareCategoryItemRecycleView() {
        binding.recycleC.apply{
            layoutManager= LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter = catAdapter
        }
    }
    private fun observedCategoryLiveData() {
        categoryViewModel.observeCategoriesLivedata().observe(this
        ) { catList->
            catAdapter.setCategories(categoryList = catList as ArrayList<Categories>)
        }
    }

    private fun preparePopularItemRecycleView() {
        binding.recyclePopular.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter = popAdapter
        }
    }

    private fun observedPopularLiveData() {
        popularViewModel.observePopularLivedata().observe(this){
                popList ->
            popAdapter.setPopular(popularList = popList as ArrayList<Populars>)
        }
    }

    private fun prepareTrendingItemRecycleView() {
        binding.recycleTrending.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
        }
    }


    private fun observedTrendingLiveData() {
        trendingViewModel.observeTrendingLivedata().observe(this){
            trend ->
            trendingAdapter.setTrending(trendingList = trend as ArrayList<Populars>)
        }

    }

    private fun location() {
        myLocation = AirLocation(this, this,false,0,"")
        myLocation.start()
    }
    override fun onFailure(locationFailedEnum: AirLocation.LocationFailedEnum) {
        Snackbar.make(binding.textCat, "Check your permissions", Snackbar.LENGTH_SHORT).show()
    }

    override fun onSuccess(locations: ArrayList<Location>) {
        locations[0].accuracy
        val lat =locations[0].latitude
        val long = locations[0].longitude

        val gps = Geocoder(this)
        val address = gps.getFromLocation(lat,long,1)
        if (address != null) {
            if (address.isNotEmpty()){
                binding.location.text = address[0]?.locality
            }else {
                Toast.makeText(this,"Check your internet or location",Toast.LENGTH_SHORT).show()
            }
        }else {
            Toast.makeText(this,"Check your internet or location",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        myLocation.onActivityResult(requestCode, resultCode, data) // ADD THIS LINE INSIDE onActivityResult
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        myLocation.onRequestPermissionsResult(requestCode, permissions, grantResults) // ADD THIS LINE INSIDE onRequestPermissionResult
    }


}