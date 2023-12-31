package com.safi.starship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.safi.designsystem.extfun.PagingState
import com.safi.designsystem.extfun.execute
import com.safi.designsystem.extfun.navigate
import com.safi.designsystem.extfun.observeState
import com.safi.designsystem.extfun.setUpVerticalRecyclerView
import com.safi.navigation.R
import com.safi.starship.databinding.FragmentStarshipBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarshipFragment : Fragment() {

    private var _binding: FragmentStarshipBinding? = null
    private val binding get() = _binding!!

    private val viewModel by hiltNavGraphViewModels<StarshipViewModel>(R.id.nav_graph_main)
    private lateinit var adapter: StarshipAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStarshipBinding.inflate(inflater, container, false)

        adapter = StarshipAdapter{
            navigate(StarshipFragmentDirections.actionNavigateToStarshipDetailsFragment(it))
        }
        requireContext().setUpVerticalRecyclerView(binding.characterRV, adapter)

        observeUiState()
        clickEventListener()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickEventListener(){
        binding.errorLayoutInc.retryButton.setOnClickListener {
            binding.errorLayoutInc.root.isVisible = false
            adapter.retry()
        }
    }

    private fun observeUiState(){

        execute {
            adapter.loadStateFlow.observeState { pagingState ->

                when(pagingState) {
                    is PagingState.Prepending -> binding.progressIndicator.isVisible = pagingState.isPrepending
                    is PagingState.Appending -> binding.bottomProgressIndicator.isVisible = pagingState.isAppending
                    is PagingState.Error -> {
                        binding.errorLayoutInc.root.isVisible = true
                        setNetworkErrorUiData(pagingState.message)
                    }
                }
            }
        }

        execute {

            viewModel.uiState.collect{ uiState ->
                adapter.submitData(uiState)
            }
        }

    }

    private fun setNetworkErrorUiData(message : String){
        binding.errorLayoutInc.errorTitleTV.text = getString(com.safi.designsystem.R.string.message_connection_failed)
        binding.errorLayoutInc.errorMessageTV.text = message
    }

}