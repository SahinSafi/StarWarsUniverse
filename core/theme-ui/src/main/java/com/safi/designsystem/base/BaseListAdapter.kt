package com.safi.designsystem.base

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * A generic RecyclerView ListAdapter that uses Data Binding & DiffUtil.
 *
 * @param <T> Type of the items in the list
 * @param <V> The type of the ViewDataBinding
 */
 abstract class BaseListAdapter<T,V : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
 ) : ListAdapter<T, BaseViewHolder<V>>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val binding = createBinding(parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {

        bind(holder.binding, getItem(position), position)
    }

    protected abstract fun createBinding(parent: ViewGroup): V
    protected abstract fun bind(binding: V, item: T, position: Int)
}

abstract class BasePagingAdapter<T : Any,V : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BaseViewHolder<V>>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val binding = createBinding(parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        getItem(position)?.let { bind(holder.binding, it, position) }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int, payloads: MutableList<Any>) {

        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else {
            val newItem = payloads[0] as T
            bind(holder.binding, newItem, position)
        }
    }

    protected abstract fun createBinding(parent: ViewGroup): V
    protected abstract fun bind(binding: V, item: T, position: Int)
}

/**
 * A generic ViewHolder that works with a [ViewBinding].
 * @param <T> The type of the ViewDataBinding.
 */
class BaseViewHolder<out T : ViewBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)