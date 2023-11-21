package com.safi.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.safi.character.databinding.FragmentCharacterBinding
import com.safi.designsystem.extfun.execute
import com.safi.designsystem.extfun.setUpVerticalRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import com.safi.navigation.R

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
            viewModel.uiState.collect {uiState ->
                when(uiState) {
                    is UiState.Loading -> binding.progressIndicator.isVisible = uiState.isLoading
                    is UiState.CharacterList -> adapter.submitList(uiState.data)
                    is UiState.ApiError ->{}
                }
            }
        }

    }

}