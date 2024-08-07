package com.geeks.hw_6_2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.hw_6_2.OnClick
import com.geeks.hw_6_2.databinding.FragmentCharactersBinding
import com.geeks.hw_6_2.utils.Resource
import com.geeks.hw_6_2.utils.gone
import com.geeks.hw_6_2.data.models.Character
import com.geeks.hw_6_2.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate), OnClick {

    private val viewModel: CharacterViewModel by viewModels()
    private val adapter by lazy {
        CharacterAdapter(this)
    }

    override fun setupViews(view: View) {
        binding.rvCharacters.layoutManager = LinearLayoutManager(context)
        binding.rvCharacters.adapter = adapter
    }

    override fun observeViewModel() {
        viewModel.characters.handleResource(
            isLoading = { visibility ->
                binding.pbCharacter.isVisible = visibility
            },
            onSuccess = { data ->
                adapter.submitList(data)
            }
        )
    }

    override fun onClick(position: Character) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(position.id)
        findNavController().navigate(action)
    }
}
