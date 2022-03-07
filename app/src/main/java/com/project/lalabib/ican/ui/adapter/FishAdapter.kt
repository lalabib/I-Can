package com.project.lalabib.ican.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.lalabib.ican.data.local.FishEntity
import com.project.lalabib.ican.databinding.ItemListBinding

class FishAdapter :  RecyclerView.Adapter<FishAdapter.FishViewHolder>() {

    var onItemClickCallback: OnItemClickCallback? = null

    private val listFish = ArrayList<FishEntity>()

    fun setFish(fish: List<FishEntity>) {
        if (fish.isNullOrEmpty()) return
        this.listFish.clear()
        this.listFish.addAll(fish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishViewHolder {
        return FishViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FishViewHolder, position: Int) {
        val fish = listFish[position]
        holder.bind(fish) {
            onItemClickCallback?.onItemClicked(fish)
        }
    }

    override fun getItemCount(): Int = listFish.size

    class FishViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(fish: FishEntity, itemClicked :() -> Unit) {
            with(binding) {
                tvLocationTitle.text = fish.area_provinsi
                tvTitle.text = fish.komoditas
                tvWeight.text = fish.size
                tvPrice.text = fish.price

                itemView.setOnClickListener{ itemClicked.invoke() }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(fish: FishEntity)
    }
}