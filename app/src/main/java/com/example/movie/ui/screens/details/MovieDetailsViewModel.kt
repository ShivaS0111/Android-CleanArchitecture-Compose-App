package com.example.movie.ui.screens.details

import androidx.lifecycle.ViewModel
import com.example.movie.domain.common.Result
import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.domain.repository.MoviesRepository
import com.example.movie.ui.stateHolders.StateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(val repository: MoviesRepository) : ViewModel() {

    private var _response = MutableStateFlow<StateHolder<Movie>>(StateHolder.Loading())
    val response: StateFlow<StateHolder<Movie>>
        get() = _response.asStateFlow()

    suspend fun getMovie(movieId: Int) {
        repository.getMovieDetails(movieId).collectLatest {
            _response.value = when (it) {
                is Result.Loading -> StateHolder.Loading()
                is Result.Success<*> ->{
                    if (it.data is Movie)
                        StateHolder.Success(it.data)
                    else StateHolder.Error(
                        error = "No data found"
                    )
                }
                is Result.Error -> StateHolder.Error(error = it.message.toString())
            }
        }
    }
}