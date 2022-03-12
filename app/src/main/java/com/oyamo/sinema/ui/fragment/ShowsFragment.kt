package com.oyamo.sinema.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.oyamo.sinema.data.entity.MovieItem
import com.oyamo.sinema.databinding.FragmentHomeBinding
import com.oyamo.sinema.databinding.FragmentShowsBinding
import com.oyamo.sinema.ui.fragment.adapter.ShowsAdapter
import com.oyamo.sinema.ui.fragment.adapter.TrendingAdapter
import com.oyamo.sinema.ui.fragment.viewmodel.ShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

@AndroidEntryPoint
class ShowsFragment : Fragment() {

    private val showViewModel: ShowViewModel by viewModels()
    private lateinit var binding: FragmentShowsBinding
    private val adapter: ShowsAdapter = ShowsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShowsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        showViewModel.fetchPopular()
    }

    private fun setupRecyclerView() {
        binding.rvMovies.adapter = adapter
    }

    private fun updateRecyclerView(movies: List<MovieItem>) {
        adapter.updateMovies(movies)
    }

    private fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }


    private fun setUp() {
        lifecycleScope.launchWhenStarted{
            showViewModel.uiState.collect {
                when (it.popularMovies) {
                    null -> showError(it.fetchingError)
                    else -> updateRecyclerView(it.popularMovies.items)
                }
            }
        }
    }
}