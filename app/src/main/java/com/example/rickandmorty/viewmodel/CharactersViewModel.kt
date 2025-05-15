package com.example.rickandmorty.viewmodel
import RmCharacter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.paging.RMCharacterPagingSource
import com.example.rickandmorty.servicies.ApiInterface
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(private val api: ApiInterface) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<RmCharacter>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { RMCharacterPagingSource(api) }
    ).flow.cachedIn(viewModelScope)
}