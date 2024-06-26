package com.example.diffutil

import androidx.recyclerview.widget.RecyclerView
import com.example.diffutil.databinding.FoodOrderItemBinding

class OrderHolder(
    private val foodOrderItemBinding: FoodOrderItemBinding,
    private val plusButtonClickListener: (FoodOrder) -> Unit,
    private val minusButtonClickListener: (FoodOrder) -> Unit
) : RecyclerView.ViewHolder(foodOrderItemBinding.root) {
    fun bind(foodOrder: FoodOrder) {
        foodOrderItemBinding.run {
            foodNameTextView.text = foodOrder.name
            orderCountTextview.text = foodOrder.count.toString()
            orderCountPlusButton.setOnClickListener {
                plusButtonClickListener(foodOrder)
            }
            orderCountMinusButton.setOnClickListener {
                minusButtonClickListener(foodOrder)
            }
        }
    }
}
