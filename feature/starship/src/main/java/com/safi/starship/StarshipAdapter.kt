package com.safi.starship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.safi.designsystem.base.BasePagingAdapter
import com.safi.entity.StarshipListItemEntity
import com.safi.starship.databinding.ItemStarshipBinding
import com.safi.designsystem.R as Assets

class StarshipAdapter : BasePagingAdapter<StarshipListItemEntity, ItemStarshipBinding>(

    diffCallback = object : DiffUtil.ItemCallback<StarshipListItemEntity>(){

        override fun areItemsTheSame(oldItem: StarshipListItemEntity, newItem: StarshipListItemEntity) = oldItem == newItem
        override fun areContentsTheSame(oldItem: StarshipListItemEntity, newItem: StarshipListItemEntity) = oldItem == newItem
    }

) {

    override fun createBinding(parent: ViewGroup) =
        ItemStarshipBinding.inflate(LayoutInflater.from(parent.context), parent, false)


    override fun bind(binding: ItemStarshipBinding, item: StarshipListItemEntity, position: Int) {
        binding.characterNameTV.text = binding.root.context.getString(Assets.string.placeholder1_name, item.name)
        binding.modelTV.text = binding.root.context.getString(Assets.string.placeholder1_model, item.model)
    }

}