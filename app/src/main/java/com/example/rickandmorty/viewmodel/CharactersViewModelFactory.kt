package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.servicies.ApiInterface

class CharactersViewModelFactory(private val api: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CharactersViewModel::class.java) ->
                CharactersViewModel(api) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
