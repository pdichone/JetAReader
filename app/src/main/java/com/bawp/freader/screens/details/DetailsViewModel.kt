package com.bawp.freader.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bawp.freader.data.Resource
import com.bawp.freader.model.Item
import com.bawp.freader.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository)

    : ViewModel(){
        suspend fun getBookInfo(bookId: String): Resource<Item> {
            return repository.getBookInfo(bookId)
        }
    }