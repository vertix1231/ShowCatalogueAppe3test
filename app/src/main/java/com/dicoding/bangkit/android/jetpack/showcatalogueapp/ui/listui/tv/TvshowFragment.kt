package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.TvShowHead
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.TvshowResultResponses
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.databinding.FragmentTvshowBinding
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.ListHomeViewModel
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TvshowFragment : Fragment() {

    private lateinit var binding: FragmentTvshowBinding
    private val homeViewModel: ListHomeViewModel by sharedViewModel()

//    private lateinit var  adapter : TvAdapter
    var adapter = TvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            with(binding.recView) {
//                layoutManager = GridLayoutManager(context, 2)
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
        setObservers()
    }

    private fun setObservers() {
        homeViewModel.getTvku()
        homeViewModel.tvs.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        // recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { results ->
                            results as TvShowHead
                            updateData(results.results)
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