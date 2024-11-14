package com.example.movie.ui.screens.details

import androidx.lifecycle.ViewModel
import com.example.movie.core.util.Result
import com.example.movie.core.util.UIStateHolder
import com.example.movie.domain.model.Movie
import com.example.movie.domain.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) : ViewModel() {

    private var _response = MutableStateFlow<UIStateHolder<Movie>>(UIStateHolder.Loading())
    val response: StateFlow<UIStateHolder<Movie>>
        get() = _response.asStateFlow()

    suspend fun getMovie(movieId: Int) {
        getMovieDetailsUseCase.invoke(movieId).collectLatest {
            _response.value = when (it) {
                is Result.Loading -> UIStateHolder.Loading()
                is Result.Success<*> ->{
                    if (it.data is Movie)
                        UIStateHolder.Success(it.data)
                    else UIStateHolder.Error(
                        error = "No data found"
                    )
                }
                is Result.Error -> UIStateHolder.Error(error = it.message.toString())
            }
        }
    }
}