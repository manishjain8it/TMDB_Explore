package com.tmdbapp.mylearning.ui.top_rated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tmdbapp.mylearning.R
import com.tmdbapp.mylearning.adapter.MoviesListAdapter
import com.tmdbapp.mylearning.extensions.toMovieView
import com.tmdbapp.mylearning.mapper.MovieView
import com.tmdbapp.mylearning.model.MoviesListResponse
import com.tmdbapp.mylearning.utils.Constants
import com.tmdbapp.mylearning.utils.Resource
import kotlinx.android.synthetic.main.fragment_show_movies.*

class TopRatedMoviesFragment : Fragment() {

    private val topRatedMoviesViewModel: TopRatedMoviesViewModel by viewModels()
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeRecyclerView()
        topRatedMoviesViewModel.fetchMoviesList(Constants.api_key, 1)

        topRatedMoviesViewModel.moviesListLiveData.observe(
            viewLifecycleOwner,
            Observer { resource: Resource<MoviesListResponse> ->
                when (resource) {
                    is Resource.Loading -> {
                        pbView.visibility = View.VISIBLE
                        rvMoviesList.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        pbView.visibility = View.GONE
                        rvMoviesList.visibility = View.VISIBLE
                        setDataWithUI(resource.data)
                    }
                    is Resource.Failure -> {
                        pbView.visibility = View.GONE
                        rvMoviesList.visibility = View.GONE
                        Toast.makeText(context, resource.error, Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun initializeRecyclerView() {
        setupAdapter()
        rvMoviesList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesListAdapter
        }
    }

    private fun setupAdapter() {
        moviesListAdapter = MoviesListAdapter()
    }

    private fun setDataWithUI(moviesListResponse: MoviesListResponse) {
        moviesListResponse.results.let { moviesList ->

            val movieViewList: MutableList<MovieView> =
                moviesList.map { it.toMovieView() }.toMutableList()

            moviesListAdapter.addItems(movieViewList)
        }

    }

}