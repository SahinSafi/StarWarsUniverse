package com.safi.starship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.safi.designsystem.R
import com.safi.starship.databinding.FragmentStarshipDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarshipDetailsFragment : Fragment() {

    private var _binding: FragmentStarshipDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<StarshipDetailsFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStarshipDetailsBinding.inflate(inflater, container, false)

        bindArgsWithUi()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindArgsWithUi(){

        binding.nameTV.text = getString(R.string.placeholder1_name, args.starshipListItemEntity.name)
        binding.modelTV.text = getString(R.string.placeholder1_diameter, args.starshipListItemEntity.model)
        binding.cargoCapacityTV.text = getString(R.string.placeholder1_climate, args.starshipListItemEntity.cargo_capacity)
        binding.crewTV.text = getString(R.string.placeholder1_gravity, args.starshipListItemEntity.crew)
        binding.lengthTV.text = getString(R.string.placeholder1_population, args.starshipListItemEntity.length)
    }

}