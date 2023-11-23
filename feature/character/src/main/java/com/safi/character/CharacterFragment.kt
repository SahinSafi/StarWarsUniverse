package com.safi.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.paging.LoadState
import com.safi.character.databinding.FragmentCharacterBinding
import com.safi.designsystem.extfun.execute
import com.safi.designsystem.extfun.setUpVerticalRecyclerView
import com.safi.navigation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by hiltNavGraphViewModels<CharacterViewModel>(R.id.nav_graph_main)
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterBinding.inflate(layoutInflater)

        adapter = CharacterAdapter()
        requireContext().setUpVerticalRecyclerView(binding.characterRV, adapter)

        observeUiState()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUiState(){

        execute {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressIndicator.isVisible = loadStates.refresh is LoadState.Loading || loadStates.prepend is LoadState.Loading
                binding.bottomProgressIndicator.isVisible = loadStates.append is LoadState.Loading

//                binding.progressIndicator.isVisible = loadState.refresh !is LoadState.Loading
//                binding.progressIndicator.isVisible = loadState.refresh is LoadState.Error
            }
        }

        execute {

            viewModel.uiState.collect{ uiState ->
                adapter.submitData(uiState)
            }

        }

    }

}