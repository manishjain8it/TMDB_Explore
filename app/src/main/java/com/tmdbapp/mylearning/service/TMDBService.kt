package com.tmdbapp.mylearning.service

import com.tmdbapp.mylearning.model.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") pageIndex: Int
    ): Response<MoviesListResponse?>

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") pageIndex: Int
    ): Response<MoviesListResponse?>

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") pageIndex: Int,
        @Query("region") region: String?
    ): Response<MoviesListResponse?>

    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String?,
        @Query("page") pageIndex: Int,
        @Query("region") region: String?
    ): Response<MoviesListResponse?>

}