package com.tmdbapp.mylearning.ui.popular

import androidx.lifecycle.*
import com.tmdbapp.mylearning.model.MoviesListResponse
import com.tmdbapp.mylearning.service.MoviesRepository
import com.tmdbapp.mylearning.service.ServiceBuilder
import com.tmdbapp.mylearning.utils.Resource
import kotlinx.coroutines.launch

class PopularMoviesViewModel : ViewModel() {

    private val _moviesListLiveData =
        MediatorLiveData<Resource<MoviesListResponse>>() // Baking Field Feature of Kotlin
    val moviesListLiveData: LiveData<Resource<MoviesListResponse>> =
        _moviesListLiveData  // Baking Field Feature of Kotlin

    fun fetchMoviesList(apiKey: String, pageNo: Int) {
        viewModelScope.launch {
            val response: LiveData<Resource<MoviesListResponse>> =
                MoviesRepository.getPopularMovies(apiKey, pageNo)
            _moviesListLiveData.addSource(response) {
                if (it is Resource.Success || it is Resource.Failure) {
                    _moviesListLiveData.removeSource(response)
                }
                _moviesListLiveData.value = it
            }
        }
    }
}