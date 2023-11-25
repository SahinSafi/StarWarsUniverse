package com.safi.planet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.safi.designsystem.base.BasePagingAdapter
import com.safi.entity.PlanetListItemEntity
import com.safi.planet.databinding.ItemPlanetBinding
import com.safi.designsystem.R as Assets

class PlanetAdapter(private val onClickItem:(item : PlanetListItemEntity) -> Unit) : BasePagingAdapter<PlanetListItemEntity, ItemPlanetBinding>(

    diffCallback = object : DiffUtil.ItemCallback<PlanetListItemEntity>(){

        override fun areItemsTheSame(oldItem: PlanetListItemEntity, newItem: PlanetListItemEntity) = oldItem == newItem
        override fun areContentsTheSame(oldItem: PlanetListItemEntity, newItem: PlanetListItemEntity) = oldItem == newItem
    }

) {

    override fun createBinding(parent: ViewGroup) =
        ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false)


    override fun bind(binding: ItemPlanetBinding, item: PlanetListItemEntity, position: Int) {
        binding.characterNameTV.text = binding.root.context.getString(Assets.string.placeholder1_name, item.name)
        binding.gravityTV.text = binding.root.context.getString(Assets.string.placeholder1_gravity, item.gravity)

        binding.root.setOnClickListener {
            onClickItem.invoke(item)
        }
    }

}