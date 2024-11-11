package com.example.movie.ui.screens.movies.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.ui.stateHolders.StateHolder
import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.useCases.GetMoviesUseCase
import com.invia.domain.useCases.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(val useCase: MovieDetailsUseCase) : ViewModel() {

    private var _response = MutableStateFlow<StateHolder<Movie>>(StateHolder.Loading())
    val response: StateFlow<StateHolder<Movie>>
        get() = _response.asStateFlow()

    suspend fun getMovie(movieId: Int) {
        useCase.invoke(movieId).collectLatest {
            _response.value = when (it) {
                is Result.Loading -> StateHolder.Loading()
                is Result.Success<*> ->{
                    if (it.data is Movie)
                        StateHolder.Success(it.data!!)
                    else StateHolder.Error(
                        error = "No data found"
                    )
                }
                is Result.Error -> StateHolder.Error(error = it.message.toString())
            }
        }
    }
}