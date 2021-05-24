package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.databinding.FragmentMovieBinding
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.ListViewModel
import com.topanlabs.filmtopan.data.MovieResultResponses
import com.topanlabs.filmtopan.data.MovieHead
import com.topanlabs.filmtopan.utils.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    val viewModel: ListViewModel by sharedViewModel()
    var adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {


            with(binding.recView) {

                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(false)
                this.adapter = adapter
            }
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.getFilmku()
        viewModel.films.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        // recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { results ->
                            results as MovieHead
                            updateData(results.movieResultResponses)
                        }
                    }
                    Status.ERROR -> {
                        // recyclerView.visibility = View.VISIBLE
                        // progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        //  progressBar.visibility = View.VISIBLE
                        // recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun updateData(movieResultResponses: List<MovieResultResponses>) {
        binding.recView.adapter = adapter
        adapter.setData(movieResultResponses)
    }
}