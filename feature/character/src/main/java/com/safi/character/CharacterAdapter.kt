package com.safi.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.safi.character.databinding.ItemCharacterBinding
import com.safi.designsystem.base.BasePagingAdapter
import com.safi.entity.CharacterListItemEntity
import com.safi.designsystem.R as Assets

class CharacterAdapter(private val onClickItem:(item : CharacterListItemEntity) -> Unit) :
    BasePagingAdapter<CharacterListItemEntity, ItemCharacterBinding>(

    diffCallback = object : DiffUtil.ItemCallback<CharacterListItemEntity>(){

        override fun areItemsTheSame(oldItem: CharacterListItemEntity, newItem: CharacterListItemEntity) = oldItem == newItem
        override fun areContentsTheSame(oldItem: CharacterListItemEntity, newItem: CharacterListItemEntity) = oldItem == newItem
    }

) {

    override fun createBinding(parent: ViewGroup) =
        ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)


    override fun bind(binding: ItemCharacterBinding, item: CharacterListItemEntity, position: Int) {
        binding.characterNameTV.text = binding.root.context.getString(Assets.string.placeholder1_name, item.name)
        binding.genderTV.text = binding.root.context.getString(Assets.string.placeholder1_gender, item.gender)

        binding.root.setOnClickListener {
            onClickItem.invoke(item)
        }
    }

}