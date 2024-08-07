package com.geeks.hw_6_2.ui.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.hw_6_2.data.models.Character
import com.geeks.hw_6_2.data.repository.Repository
import com.geeks.hw_6_2.utils.Resource
import kotlinx.coroutines.launch


class DetailViewModel (private val repository: Repository) : ViewModel() {

    private val _characters = MutableLiveData<Resource<Character>>()
    val characters: LiveData<Resource<Character>> get() = _characters

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            repository.getCharacterDetails(id).observeForever {
                _characters.postValue(it)
            }
        }
    }
}