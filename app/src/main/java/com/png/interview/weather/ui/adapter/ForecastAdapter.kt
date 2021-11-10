package com.png.interview.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.ItemForecastBinding
import com.png.interview.weather.ui.model.ForecastViewData

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.MyViewHolder>() {

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<ForecastViewData>() {
        override fun areItemsTheSame(oldItem: ForecastViewData, newItem: ForecastViewData): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: ForecastViewData, newItem: ForecastViewData): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        with(holder.viewBinding) {
            item = currentItem
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateList(updatedList: List<ForecastViewData>) {
        if (updatedList.isEmpty()) {
            differ.submitList(emptyList())
            return
        }
        differ.submitList(updatedList)
    }

    inner class MyViewHolder(val viewBinding: ItemForecastBinding) : RecyclerView.ViewHolder(viewBinding.root)

}