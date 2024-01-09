package com.galal.yajhz.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.galal.yajhz.Pojo.Populars
import com.galal.yajhz.databinding.ItemPopularBinding


class popularAdapter():RecyclerView.Adapter<popularAdapter.PopularViewHolder>() {

    private var popularList = ArrayList<Populars>()

    fun setPopular(popularList: ArrayList<Populars>){
        this.popularList = popularList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(popularList[position].image)
            .into(holder.binding.imagePop)
        holder.binding.NameItemPop.text = popularList[position].name
        holder.binding.textRate.text = popularList[position].rate

    }

    class PopularViewHolder(var binding: ItemPopularBinding):RecyclerView.ViewHolder(binding.root) {

    }


}