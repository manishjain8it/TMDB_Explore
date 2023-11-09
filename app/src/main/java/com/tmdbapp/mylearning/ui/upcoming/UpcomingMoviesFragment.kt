package com.tmdbapp.mylearning.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tmdbapp.mylearning.adapter.MoviesListAdapter
import com.tmdbapp.mylearning.databinding.FragmentShowMoviesBinding
import com.tmdbapp.mylearning.extensions.toMovieView
import com.tmdbapp.mylearning.mapper.MovieView
import com.tmdbapp.mylearning.model.MoviesListResponse
import com.tmdbapp.mylearning.utils.Constants
import com.tmdbapp.mylearning.utils.Resource

class UpcomingMoviesFragment : Fragment() {

    private val upcomingMoviesViewModel: UpcomingMoviesViewModel by viewModels()
    private lateinit var binding: FragmentShowMoviesBinding
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeRecyclerView()
        upcomingMoviesViewModel.fetchMoviesList(Constants.api_key, 1, "IN")

        upcomingMoviesViewModel.moviesListLiveData.observe(
            viewLifecycleOwner,
            Observer { resource: Resource<MoviesListResponse> ->
                when (resource) {
                    is Resource.Loading -> {
                        binding.pbView.visibility = View.VISIBLE
                        binding.rvMoviesList.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.pbView.visibility = View.GONE
                        binding.rvMoviesList.visibility = View.VISIBLE
                        setDataWithUI(resource.data)
                    }
                    is Resource.Failure -> {
                        binding.pbView.visibility = View.GONE
                        binding.rvMoviesList.visibility = View.GONE
                        Toast.makeText(context, resource.error, Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun initializeRecyclerView() {
        setupAdapter()
        binding.rvMoviesList.apply {
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