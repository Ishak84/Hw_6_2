package com.geeks.hw_6_2.ui.fragments.detail

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.navArgs
import coil.load
import com.geeks.hw_6_2.databinding.FragmentCharacterDetailBinding
import com.geeks.hw_6_2.ui.base.BaseFragment

class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {
    //private lateinit var viewModel: DetalViewModel
    private val viewModel: DetailViewModel by viewModel()
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