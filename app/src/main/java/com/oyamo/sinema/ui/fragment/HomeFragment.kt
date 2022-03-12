package com.oyamo.sinema.ui.fragment

import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.oyamo.sinema.R
import com.oyamo.sinema.data.entity.MovieItem
import com.oyamo.sinema.databinding.FragmentHomeBinding
import com.oyamo.sinema.ui.fragment.adapter.TrendingAdapter
import com.oyamo.sinema.ui.fragment.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val adapter: TrendingAdapter = TrendingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        homeViewModel.fetchPopular()
    }

    private fun setupRecyclerView() {
        binding.rvTrending.adapter = adapter
    }

    private fun updateRecyclerView(movies: List<MovieItem>) {
        adapter.updateMovies(movies)
    }

    private fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }


    private fun setUp() {
        lifecycleScope.launchWhenStarted{
            homeViewModel.uiState.collect {
                when (it.popularMovies) {
                    null -> showError(it.fetchingError)
                    else -> updateRecyclerView(it.popularMovies.items)
                }
            }
        }
    }
}