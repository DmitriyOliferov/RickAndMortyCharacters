package com.oliferov.rickandmortyapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.security.Provider

class ViewModelFactory(
private val viewModelProvider:@JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return
    }
}