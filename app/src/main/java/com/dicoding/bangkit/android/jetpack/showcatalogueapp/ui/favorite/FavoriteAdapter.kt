package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.favorite

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.databinding.ItemRowFilm2Binding
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.detail.DetailActivity
import com.topanlabs.filmtopan.db.ArtEntity


class FavoriteAdapter : PagedListAdapter<ArtEntity, FavoriteAdapter.FavoriteViewHolder>(
    DIFF_CALLBACK
) {
    private val list = ArrayList<ArtEntity>()

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ArtEntity> =
            object : DiffUtil.ItemCallback<ArtEntity>() {
                override fun areItemsTheSame(oldArt: ArtEntity, newArt: ArtEntity): Boolean {
                    return oldArt.id == newArt.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldArt: ArtEntity, newArt: ArtEntity): Boolean {
                    return oldArt == newArt
                }
            }
    }

    fun setData(list: List<ArtEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(private val binding: ItemRowFilm2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArtEntity) {
            with(binding) {
                tvYear.text = item.year
                Glide
                    .with(imgView.context)
                    .load(item.photo)
                    .into(imgView)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    if (item.type == "tv") {
                        intent.putExtra(DetailActivity.TYPE_TAG, DetailActivity.ID_TV)
                    } else {
                        intent.putExtra(DetailActivity.TYPE_TAG, DetailActivity.ID_FILM)
                    }
                    intent.putExtra(DetailActivity.ID_TAG, item.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val binding =
            ItemRowFilm2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val art = list[position]
        holder.bind(art)
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}