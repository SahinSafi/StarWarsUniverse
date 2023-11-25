package com.safi.planet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.safi.designsystem.extfun.PagingState
import com.safi.designsystem.extfun.execute
import com.safi.designsystem.extfun.observeState
import com.safi.designsystem.extfun.setUpVerticalRecyclerView
import com.safi.navigation.R
import com.safi.planet.databinding.FragmentPlanetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetFragment : Fragment() {

    private var _binding: FragmentPlanetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by hiltNavGraphViewModels<PlanetViewModel>(R.id.nav_graph_main)
    private lateinit var adapter: PlanetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetBinding.inflate(inflater, container, false)

        adapter = PlanetAdapter()
        requireContext().setUpVerticalRecyclerView(binding.planetRV, adapter)

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