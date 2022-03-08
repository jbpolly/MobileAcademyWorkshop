package com.mysticraccoon.mobileacademyworkshop.ui.savedFoods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.databinding.ViewholderSavedFoodItemBinding

class SavedFoodDiffAdapter(private val onItemClicked: SavedFoodItemClicked): ListAdapter<FoodItem, SavedFoodItemViewHolder>(FoodItemDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedFoodItemViewHolder {
        val binding = ViewholderSavedFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedFoodItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedFoodItemViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }
}


class FoodItemDiffCallback: DiffUtil.ItemCallback<FoodItem>(){

    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }
}