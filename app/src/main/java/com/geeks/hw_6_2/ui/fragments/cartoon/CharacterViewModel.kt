package com.geeks.hw_6_2.ui.fragments.cartoon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.hw_6_2.data.repository.Repository
import com.geeks.hw_6_2.utils.Resource
import com.geeks.hw_6_2.data.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> get() = _characters

    init {
        fetchedCharacter()
    }

    private fun fetchedCharacter() {
        viewModelScope.launch {
            repository.getCharacters().observeForever {
                _characters.postValue(it)
            }
        }
    }
}