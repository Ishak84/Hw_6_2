package com.geeks.hw_6_2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.hw_6_2.R
import com.geeks.hw_6_2.databinding.FragmentCharactersBinding
import com.geeks.hw_6_2.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }
    private var charactersAdapter: CharacterAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.getCharacters().observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Loading -> {
                    binding.pbCharacter.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbCharacter.visibility = View.GONE
                    charactersAdapter?.submitList(res.data)
                }
                is Resource.Error -> {
                    binding.pbCharacter.visibility = View.GONE
                    Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharacterAdapter()
        binding.rvCharacters.apply {
            setHasFixedSize(true)
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
