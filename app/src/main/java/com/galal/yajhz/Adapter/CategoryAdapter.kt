package com.galal.yajhz.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.galal.yajhz.Pojo.Categories
import com.galal.yajhz.databinding.ItemCategoryBinding

class CategoryAdapter():RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList = ArrayList<Categories>()

    fun setCategories(categoryList: ArrayList<Categories>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return  CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoryList[position].image)
            .into(holder.binding.imgCategoryItem)
        holder.binding.ItemName.text = categoryList[position].name

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class CategoryViewHolder(var binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root) {

    }

}


