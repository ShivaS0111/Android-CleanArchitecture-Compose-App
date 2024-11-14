package com.example.movie.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.common.Result
import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.domain.repository.MoviesRepository
import com.example.movie.ui.stateHolders.StateHolder
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
class MovieListViewModel @Inject constructor(val repository: MoviesRepository) : ViewModel() {

    private val _toastEventFlow = MutableSharedFlow<String>(replay = 1)
    val toastEventFlow: SharedFlow<String> = _toastEventFlow

    private fun showToastMessage(message: String) {
        _toastEventFlow.tryEmit(message)
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    private var _response = MutableStateFlow<StateHolder<List<Movie>>>(StateHolder.Loading())
    val response: StateFlow<StateHolder<List<Movie>>>
        get() = _response.asStateFlow()

    fun getAllTvShows() {
        viewModelScope.launch {
            if(!isRefreshing.value){
                _response.value = StateHolder.Loading()
                //delay(2000)
            }
            repository.getTvShows().collectLatest {
                println("===>result: receive $it")
                _response.value = when (it) {

                    is Result.Loading -> StateHolder.Loading()
                    is Result.Success<*> ->{
                        if (it.data is List && it.data.isNotEmpty())
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

    fun setRefreshing(isRefreshing: Boolean) {
        _isRefreshing.value = isRefreshing
    }

    fun onDeleteClick(movie: Movie) {
        viewModelScope.launch {
            println("===>${movie.id}, $movie")
            repository.deleteMovie(movie).collectLatest {
                when(it){
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val data =_response.value.data?.toMutableList() ?: mutableListOf()
                        data.remove(movie)
                        _response.value = StateHolder.Success(data)
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