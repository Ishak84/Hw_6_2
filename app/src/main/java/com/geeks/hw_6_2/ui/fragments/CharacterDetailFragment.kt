package com.geeks.hw_6_2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.geeks.hw_6_2.R
import com.geeks.hw_6_2.databinding.FragmentCharacterDetailBinding
import com.geeks.hw_6_2.databinding.FragmentCharactersBinding
import com.geeks.hw_6_2.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {
    //private lateinit var viewModel: DetalViewModel
    private val viewModel: DetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var binding2: FragmentCharacterDetailBinding

    override fun setupViews(view: View) {
        binding2 = FragmentCharacterDetailBinding.bind(view)
        val id = args.id
        viewModel.getCharacterById(id)
    }

    override fun observeViewModel() {
        viewModel.characters.handleResource(
            isLoading = { visibility ->
                binding.pbDetail.isVisible = visibility
            },
            onSuccess = { character ->
                binding.pbDetail.apply {
                    character?.let {
                        binding.apply {
                            tvName.text = it.name
                            tvAlive.text = it.status
                            tvGender.text = it.gender
                            tvLastLocation.text = it.location.name
                            imgCharacter.load(it.image)
                        }
                    }
                }
            }
        )
    }
}