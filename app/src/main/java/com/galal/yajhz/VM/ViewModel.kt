package com.galal.yajhz.VM

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.galal.yajhz.API.RetrofitInstance
import com.galal.yajhz.Pojo.Categories
import com.galal.yajhz.Pojo.CategoryList
import com.galal.yajhz.Pojo.PopularList
import com.galal.yajhz.Pojo.Populars
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModel : ViewModel() {

    private var categoriesItemLiveData = MutableLiveData<List<Categories>>()
    private var popularItemLiveData = MutableLiveData<List<Populars>>()
    private var trendingItemLiveData = MutableLiveData<List<Populars>>()

    @SuppressLint("StaticFieldLeak")
   // private lateinit var context: Context
     fun getCategoriesItem(){
        RetrofitInstance.api.getCategoriesItem().enqueue(object :Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null){
                    categoriesItemLiveData.value = response.body()!!.data.data
                }
            }
            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
//                Toast.makeText(context,"Check your internet",Toast.LENGTH_SHORT).show()
            }
        })
    }

    //*************************************************************

    fun getPopularsItem(){
        RetrofitInstance.api.getPopularList().enqueue(object : Callback<PopularList> {
            override fun onResponse(call: Call<PopularList>, response: Response<PopularList>) {
                if (response.body() != null){
                    popularItemLiveData.value = response.body()?.data
                }
            }
            override fun onFailure(call: Call<PopularList>, t: Throwable) {
               // Toast.makeText(context,"Check your internet",Toast.LENGTH_SHORT).show()
            }
        })
    }


    //*************************************************************

    fun getTrendingItem(){
        RetrofitInstance.api.getTrendingList().enqueue(object : Callback<PopularList> {
            override fun onResponse(call: Call<PopularList>, response: Response<PopularList>) {
                if (response.body() != null){
                    trendingItemLiveData.value = response.body()!!.data
                }
            }
            override fun onFailure(call: Call<PopularList>, t: Throwable) {
              //  Toast.makeText(context,"Check your internet",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun observeCategoriesLivedata():LiveData<List<Categories>>{
        return categoriesItemLiveData
    }
    fun observePopularLivedata(): LiveData<List<Populars>> {
        return popularItemLiveData
    }
    fun observeTrendingLivedata(): LiveData<List<Populars>> {
        return trendingItemLiveData
    }



}