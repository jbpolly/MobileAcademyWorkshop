package com.mysticraccoon.mobileacademyworkshop.ui.foodList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.databinding.ViewholderFoodFromCategoryItemBinding

class FoodListAdapter (private val onItemClicked: FoodItemClicked) :
    ListAdapter<FoodItem, FoodItemFromCategoryViewHolder>(
        FoodItemDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemFromCategoryViewHolder {
        val binding = ViewholderFoodFromCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodItemFromCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodItemFromCategoryViewHolder, position: Int) {
        val categoryItem = getItem(position)
        holder.bind(categoryItem, onItemClicked)
    }

}

class FoodItemFromCategoryViewHolder(private val foodCategoryItemBinding: ViewholderFoodFromCategoryItemBinding) :
    RecyclerView.ViewHolder(foodCategoryItemBinding.root) {

    fun bind(item: FoodItem, itemClick: FoodItemClicked) {

        foodCategoryItemBinding.itemTitle.text = item.name
       // foodCategoryItemBinding.thumbUrl = item.url
        itemView.setOnClickListener {
            itemClick.onClick(item)
        }
    }

}

class FoodItemClicked(val block: (FoodItem) -> Unit) {
    fun onClick(category: FoodItem) = block(category)
}

class FoodItemDiffCallback : DiffUtil.ItemCallback<FoodItem>() {

    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }
}