package com.tmdbapp.mylearning.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.tmdbapp.mylearning.model.MoviesListResponse
import com.tmdbapp.mylearning.utils.Constants
import com.tmdbapp.mylearning.utils.Resource
import retrofit2.Response
import java.net.UnknownHostException

object MoviesRepository {
    fun getPopularMovies(apiKey: String, pageNo: Int): LiveData<Resource<MoviesListResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response: Response<MoviesListResponse?> = ServiceBuilder.buildService().getPopularMovies(apiKey, pageNo)
            response.body()?.let { movies: MoviesListResponse ->
                when {
                    movies.results.isNotEmpty() -> emit(Resource.Success(movies))
                    else -> emit(Resource.Failure("No movies to show right now"))
                }
            } ?: kotlin.run {
                emit(Resource.Failure())
            }
        } catch (exception: Exception) {
            when (exception) {
                is UnknownHostException -> emit(Resource.Failure(Constants.NO_NETWORK))
                else -> exception.localizedMessage?.let {
                    emit(Resource.Failure(it))
                } ?: kotlin.run {
                    emit(Resource.Failure())
                }
            }
        }
    }

    fun getNowPlayingMovies(apiKey: String, pageNo: Int, region : String): LiveData<Resource<MoviesListResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response: Response<MoviesListResponse?> = ServiceBuilder.buildService().getNowPlaying(apiKey, pageNo, region)
            response.body()?.let { movies: MoviesListResponse ->
                when {
                    movies.results.isNotEmpty() -> emit(Resource.Success(movies))
                    else -> emit(Resource.Failure("No movies to show right now"))
                }
            } ?: kotlin.run {
                emit(Resource.Failure())
            }
        } catch (exception: Exception) {
            when (exception) {
                is UnknownHostException -> emit(Resource.Failure(Constants.NO_NETWORK))
                else -> exception.localizedMessage?.let {
                    emit(Resource.Failure(it))
                } ?: kotlin.run {
                    emit(Resource.Failure())
                }
            }
        }
    }

    fun getTopRatedMovies(apiKey: String, pageNo: Int): LiveData<Resource<MoviesListResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response: Response<MoviesListResponse?> = ServiceBuilder.buildService().getTopRatedMovies(apiKey, pageNo)
            response.body()?.let { movies: MoviesListResponse ->
                when {
                    movies.results.isNotEmpty() -> emit(Resource.Success(movies))
                    else -> emit(Resource.Failure("No movies to show right now"))
                }
            } ?: kotlin.run {
                emit(Resource.Failure())
            }
        } catch (exception: Exception) {
            when (exception) {
                is UnknownHostException -> emit(Resource.Failure(Constants.NO_NETWORK))
                else -> exception.localizedMessage?.let {
                    emit(Resource.Failure(it))
                } ?: kotlin.run {
                    emit(Resource.Failure())
                }
            }
        }
    }

    fun getUpcomingMovies(apiKey: String, pageNo: Int, region : String): LiveData<Resource<MoviesListResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response: Response<MoviesListResponse?> = ServiceBuilder.buildService().getUpcomingMovies(apiKey, pageNo, region)
            response.body()?.let { movies: MoviesListResponse ->
                when {
                    movies.results.isNotEmpty() -> emit(Resource.Success(movies))
                    else -> emit(Resource.Failure("No movies to show right now"))
                }
            } ?: kotlin.run {
                emit(Resource.Failure())
            }
        } catch (exception: Exception) {
            when (exception) {
                is UnknownHostException -> emit(Resource.Failure(Constants.NO_NETWORK))
                else -> exception.localizedMessage?.let {
                    emit(Resource.Failure(it))
                } ?: kotlin.run {
                    emit(Resource.Failure())
                }
            }
        }
    }

}