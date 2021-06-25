package com.leavingston.exchangerates.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leavingston.exchangerates.repository.roomRepository

class Factory(private val repository: roomRepository ) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DBViewModel(repository) as T
    }
}