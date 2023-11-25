package com.safi.planet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.safi.designsystem.R
import com.safi.planet.databinding.FragmentPlanetDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetDetailsFragment : Fragment() {

    private var _binding: FragmentPlanetDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<PlanetDetailsFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPlanetDetailsBinding.inflate(inflater, container, false)

        bindArgsWithUi()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindArgsWithUi(){

        binding.nameTV.text = getString(R.string.placeholder1_name, args.planetListItemEntity.name)
        binding.diameterTV.text = getString(R.string.placeholder1_diameter, args.planetListItemEntity.diameter)
        binding.climateTV.text = getString(R.string.placeholder1_climate, args.planetListItemEntity.climate)
        binding.gravityTV.text = getString(R.string.placeholder1_gravity, args.planetListItemEntity.gravity)
        binding.populationTV.text = getString(R.string.placeholder1_population, args.planetListItemEntity.population)
    }

}