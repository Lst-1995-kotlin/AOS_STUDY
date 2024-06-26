package com.example.diffutil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: OrderAdapter
    private val orderAdapterDiffCallback = OrderAdapterDiffCallback()

    private var orders =
        listOf(FoodOrder(0, "스파게티", 0), FoodOrder(1, "치킨", 0), FoodOrder(2, "피자", 0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plusListener: (FoodOrder) -> Unit = { foodOrder ->
            val newList = mutableListOf<FoodOrder>()
            adapter.currentList.forEach {
                if (it == foodOrder) {
                    newList.add(
                        it.copy(
                            count = it.count + 1,
                        ),
                    )
                } else {
                    newList.add(it)
                }
            }
            adapter.submitList(newList)
        }
        val minusListener: (FoodOrder) -> Unit = { foodOrder ->
            val newList = mutableListOf<FoodOrder>()
            adapter.currentList.forEach {
                if (it == foodOrder && it.count > 0) {
                    newList.add(
                        it.copy(
                            count = it.count - 1,
                        ),
                    )
                } else {
                    newList.add(it)
                }
            }
            adapter.submitList(newList)
        }

        adapter = OrderAdapter(orderAdapterDiffCallback, plusListener, minusListener)
        adapter.submitList(orders)

        binding.run {
            foodOrdersRecyclerview.adapter = adapter
            foodOrdersRecyclerview.layoutManager = LinearLayoutManager(applicationContext)

            addButton.setOnClickListener {
                val newList = adapter.currentList.toMutableList()
                newList.add(
                    FoodOrder(newList.last().orderId + 1, "추가된 음식${newList.last().orderId + 1}", 0),
                )
                adapter.submitList(newList)
            }

            removeButton.setOnClickListener {
                val newList =
                    adapter.currentList.toMutableList().subList(0, adapter.currentList.size - 1)
                adapter.submitList(newList)
            }
        }
    }
}
