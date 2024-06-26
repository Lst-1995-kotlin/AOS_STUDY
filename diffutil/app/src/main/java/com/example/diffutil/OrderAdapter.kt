package com.example.diffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutil.databinding.FoodOrderItemBinding

class OrderAdapter(
    private val diffCallback: OrderAdapterDiffCallback,
    private val plusButtonClickListener: (FoodOrder) -> Unit,
    private val minusButtonClickListener: (FoodOrder) -> Unit
) : ListAdapter<FoodOrder, RecyclerView.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodOrderItemBinding.inflate(inflater, parent, false)
        return OrderHolder(binding, plusButtonClickListener, minusButtonClickListener)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is OrderHolder -> holder.bind(currentList[position])
        }
    }
}
