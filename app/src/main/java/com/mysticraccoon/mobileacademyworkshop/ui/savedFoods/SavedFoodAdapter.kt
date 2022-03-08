package com.mysticraccoon.mobileacademyworkshop.ui.savedFoods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.databinding.ViewholderSavedFoodItemBinding

class SavedFoodAdapter(private val onItemClicked: SavedFoodItemClicked): RecyclerView.Adapter<SavedFoodItemViewHolder>() {

    private var dataList = ArrayList<FoodItem>()

    override fun getItemCount(): Int {
        return dataList.count()
    }

    private fun getItem(position: Int): FoodItem? {
        return dataList.getOrNull(position)
    }

    fun getItemAtPosition(position: Int): FoodItem? {
        return dataList.getOrNull(position)
    }

    fun setList(list: List<FoodItem>) {
        dataList = ArrayList(list)
        notifyDataSetChanged()
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    fun addItem(item: FoodItem) {
        dataList.add(item)
        notifyItemInserted(dataList.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedFoodItemViewHolder {
        val binding = ViewholderSavedFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedFoodItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedFoodItemViewHolder, position: Int) {
        if(position in dataList.indices){
            getItem(position)?.let { item ->
                holder.bind(item, onItemClicked)
            }
        }
    }
}

class SavedFoodItemClicked(val block: (FoodItem) -> Unit, val deleteClick: (FoodItem) -> Unit){
    fun onClick(item: FoodItem) = block(item)
    fun onDelete(item: FoodItem) = deleteClick(item)
}

class SavedFoodItemViewHolder(private val binding: ViewholderSavedFoodItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(item: FoodItem, itemClick: SavedFoodItemClicked){
        binding.itemTitle.text = item.name
        //TODO add url to imageView

        binding.itemDelete.setOnClickListener {
            itemClick.deleteClick(item)
        }

        itemView.setOnClickListener {
            itemClick.onClick(item)
        }
    }
}

