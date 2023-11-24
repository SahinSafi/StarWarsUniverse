package com.safi.designsystem.extfun

import android.content.Context
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

fun<viewHolder,T:RecyclerView.Adapter<viewHolder>> Context.setUpGridRecyclerView(view: RecyclerView,viewAdapter:T,column:Int){
    view.setHasFixedSize(true)
    view.layoutManager = GridLayoutManager(this,column)
    view.adapter = viewAdapter
}

fun<viewHolder,T:RecyclerView.Adapter<viewHolder>> Context.setUpHorizontalReverseRecyclerView(view: RecyclerView,viewAdapter:T){
    view.setHasFixedSize(true)
    view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    view.adapter = viewAdapter
}

fun<viewHolder,T:RecyclerView.Adapter<viewHolder>> Context.setUpVerticalRecyclerView(view: RecyclerView,viewAdapter:T){
    view.layoutManager = LinearLayoutManager(this)
    view.adapter = viewAdapter
}

suspend fun Flow<CombinedLoadStates>.observeState(state: (pagingState : PagingState) -> Unit){

    this.collectLatest {loadStates ->

        // sending page refreshing and prepending loading state
        state(PagingState.Prepending(loadStates.refresh is LoadState.Loading || loadStates.prepend is LoadState.Loading))
        // sending page appending loading state
        state(PagingState.Appending(loadStates.append is LoadState.Loading))

        val errorState = when {
            loadStates.prepend is LoadState.Error -> loadStates.prepend as LoadState.Error
            loadStates.append is LoadState.Error -> loadStates.append as LoadState.Error
            loadStates.refresh is LoadState.Error -> loadStates.refresh as LoadState.Error
            else -> null
        }
        // checking if LoadState is error then send error message to state
        if (errorState is LoadState.Error) state(PagingState.Error(errorState.error.message?:""))
    }

}

sealed class PagingState {
    data class Prepending(val isPrepending: Boolean): PagingState()
    data class Appending(val isAppending: Boolean): PagingState()
    data class Error(val message : String): PagingState()
}
