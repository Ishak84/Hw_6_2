package com.geeks.hw_6_2.ui.fragments

import androidx.lifecycle.ViewModel
import com.geeks.hw_6_2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun getCharacters() = repository.getCharacters()

}