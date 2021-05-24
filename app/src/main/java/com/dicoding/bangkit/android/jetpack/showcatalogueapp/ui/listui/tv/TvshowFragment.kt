package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.databinding.FragmentTvshowBinding
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.ListViewModel
import com.topanlabs.filmtopan.data.TvshowResultResponses
import com.topanlabs.filmtopan.data.TvShowHead
import com.topanlabs.filmtopan.utils.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TvshowFragment : Fragment() {

    private lateinit var binding: FragmentTvshowBinding
    val viewModel: ListViewModel by sharedViewModel()
    val adapter = TvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            with(binding.recView) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.getTvku()
        viewModel.tvs.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        // recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { results ->
                            results as TvShowHead
                            updateData(results.tvshowResults)
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

    private fun updateData(tvshowResults: List<TvshowResultResponses>) {
        binding.recView.adapter = adapter
        adapter.setData(tvshowResults)
    }
}