package com.example.diffutil

import androidx.recyclerview.widget.DiffUtil

class OrderAdapterDiffCallback : DiffUtil.ItemCallback<FoodOrder>() {
    override fun areItemsTheSame(
        oldItem: FoodOrder,
        newItem: FoodOrder,
    ): Boolean {
        return oldItem.orderId == newItem.orderId
    }

    override fun areContentsTheSame(
        oldItem: FoodOrder,
        newItem: FoodOrder,
    ): Boolean {
        return oldItem == newItem
    }
}
