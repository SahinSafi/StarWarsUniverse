package com.safi.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.safi.character.databinding.FragmentCharacterDetailsBinding
import com.safi.designsystem.R

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        bindArgsWithUi()

        return binding.root
    }

    private fun bindArgsWithUi(){

        binding.nameTV.text = getString(R.string.placeholder1_name, args.characterListItemEntity.name)
        binding.genderTV.text = getString(R.string.placeholder1_gender, args.characterListItemEntity.gender)
        binding.hairColorTV.text = getString(R.string.placeholder1_hair_color, args.characterListItemEntity.hair_color)
        binding.skinColorTV.text = getString(R.string.placeholder1_skin_color, args.characterListItemEntity.skin_color)
        binding.birthYearTV.text = getString(R.string.placeholder1_birth_year, args.characterListItemEntity.birth_year)
    }

}