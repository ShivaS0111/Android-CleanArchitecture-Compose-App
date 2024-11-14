package com.example.movie.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.core.util.Result
import com.example.movie.core.util.UIStateHolder
import com.example.movie.domain.model.Movie
import com.example.movie.domain.usecases.DeleteMovieUseCase
import com.example.movie.domain.usecases.GetMoviesUseCase
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
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _toastEventFlow = MutableSharedFlow<String>(replay = 1)
    val toastEventFlow: SharedFlow<String> = _toastEventFlow

    private fun showToastMessage(message: String) {
        _toastEventFlow.tryEmit(message)
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    private var _response = MutableStateFlow<UIStateHolder<List<Movie>>>(UIStateHolder.Loading())
    val response: StateFlow<UIStateHolder<List<Movie>>>
        get() = _response.asStateFlow()

    fun getAllTvShows() {
        viewModelScope.launch {
            if (!isRefreshing.value) {
                _response.value = UIStateHolder.Loading()
            }else{
                delay(1000)
            }
            getMoviesUseCase.invoke().collectLatest {
                println("===>result: receive $it")
                _response.value = when (it) {
                    is Result.Loading -> UIStateHolder.Loading()
                    is Result.Success<*> -> {
                        if (it.data is List && it.data.isNotEmpty())
                            UIStateHolder.Success(it.data)
                        else UIStateHolder.Error(
                            error = "No data found"
                        )
                    }

                    is Result.Error -> UIStateHolder.Error(error = it.message.toString())
                }
                _isRefreshing.value = false
            }
        }
    }

    fun setRefreshing(isRefreshing: Boolean) {
        _isRefreshing.value = isRefreshing
    }

    fun onDeleteClick(movie: Movie) {
        viewModelScope.launch {
            println("===>${movie.id}, $movie")
            deleteMovieUseCase.invoke(movie).collectLatest {
                when (it) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val data = _response.value.data?.toMutableList() ?: mutableListOf()
                        data.remove(movie)
                        _response.value = UIStateHolder.Success(data)
                        showToastMessage("${movie.name} deleted successfully")
                    }

                    is Result.Error -> {
                        showToastMessage(it.message ?: "Failed to delete")
                    }
                }
            }
        }
    }

}