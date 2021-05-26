package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.favorite.favoritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.databinding.FragmentMovieFavoriteBinding
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.detail.DetailViewModel
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.favorite.FavoriteAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MovieFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentMovieFavoriteBinding
    private val viewModel: DetailViewModel by sharedViewModel()
    private val adapterFavorite = FavoriteAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {


            with(binding.recView) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(false)
                this.adapter = adapterFavorite
            }
        }
        loadData()
    }

    private fun loadData() {
        viewModel.allLikedArts("film").observe(viewLifecycleOwner) { showtaimentit ->
            showtaimentit.let { adapterFavorite.setData(showtaimentit) }
            binding.progressBar.visibility = View.GONE
            if (showtaimentit.loadedCount == 0) {
                binding.recView.visibility = View.GONE
            } else {
                binding.recView.visibility = View.VISIBLE
            }
        }
    }


}