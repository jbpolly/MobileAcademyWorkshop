package com.mysticraccoon.mobileacademyworkshop.ui.foodCategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mysticraccoon.mobileacademyworkshop.R
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory
import com.mysticraccoon.mobileacademyworkshop.databinding.ViewholderFoodCategoryListItemBinding

class FoodCategoryAdapter(private val onItemClicked: FoodCategoryClicked): ListAdapter<FoodCategory, FoodCategoryViewHolder>(FoodCategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        val binding = ViewholderFoodCategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

}


class FoodCategoryClicked(val block: (FoodCategory) -> Unit) {
    fun onClick(category: FoodCategory) = block(category)
}

class FoodCategoryViewHolder(private val binding: ViewholderFoodCategoryListItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(item: FoodCategory, itemClick: FoodCategoryClicked){

        binding.itemTitle.text = item.title
        Glide.with(itemView)
            .load(item.url)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_no_food)
            .into(binding.itemThumb)
        itemView.setOnClickListener {
            itemClick.onClick(item)
        }

    }

}

class FoodCategoryDiffCallback : DiffUtil.ItemCallback<FoodCategory>() {

    override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
        return oldItem == newItem
    }
}