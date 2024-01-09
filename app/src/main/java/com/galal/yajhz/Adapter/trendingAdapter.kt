package com.galal.yajhz.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.galal.yajhz.Pojo.Populars
import com.galal.yajhz.databinding.ItemTrendingBinding

class trendingAdapter(): RecyclerView.Adapter<trendingAdapter.trendingViewHolder>() {

    private var trendingList = ArrayList<Populars>()

    fun setTrending(trendingList: ArrayList<Populars>){
        this.trendingList = trendingList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): trendingViewHolder {
        return  trendingViewHolder(
            ItemTrendingBinding.inflate(
                LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun onBindViewHolder(holder: trendingViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(trendingList[position].image)
            .into(holder.binding.imageTrending)

    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    class trendingViewHolder(var binding: ItemTrendingBinding): RecyclerView.ViewHolder(binding.root) {

    }

}

