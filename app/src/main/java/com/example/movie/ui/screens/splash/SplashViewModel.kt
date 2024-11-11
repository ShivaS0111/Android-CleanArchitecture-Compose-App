package com.example.movie.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.ui.stateHolders.StateHolder
import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.useCases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(val useCase: GetMoviesUseCase) : ViewModel() {

    private val _isUserAuthenticated = MutableStateFlow<StateHolder<Boolean>>(StateHolder.Loading())
    val isUserAuthenticated = _isUserAuthenticated.asStateFlow()

    fun authenticateUser(){
        viewModelScope.launch {
            delay(5000L)
            _isUserAuthenticated.value = StateHolder.Success(true)
        }
    }

}