package com.geeks.hw_6_2.ui.fragments.cartoon

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.hw_6_2.utils.OnClick
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.geeks.hw_6_2.databinding.FragmentCharactersBinding
import com.geeks.hw_6_2.data.models.Character
import com.geeks.hw_6_2.ui.base.BaseFragment
import com.geeks.hw_6_2.ui.fragments.cartoon.adapter.CharacterAdapter


class CharactersFragment : BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate),
    OnClick {

    private val viewModel: CharacterViewModel by viewModel()
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
