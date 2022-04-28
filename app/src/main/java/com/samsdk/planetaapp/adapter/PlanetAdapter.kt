package com.samsdk.planetaapp.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.samsdk.planetaapp.databinding.ItemLayoutBinding
import com.samsdk.planetaapp.model.Planet


class PlanetAdapter : ListAdapter<Planet, PlanetAdapter.PlanetViewHolder>(DiffCallBack()) {

    lateinit var onClickItem: (Planet) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        return PlanetViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallBack : DiffUtil.ItemCallback<Planet>() {
        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem.planetId == newItem.planetId
        }

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem == newItem
        }
    }

    inner class PlanetViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: Planet) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(planet.imgUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.isVisible = false
                            return false
                        }
                    })
                    .into(imageView)

                textView.text = planet.title

                itemView.setOnClickListener {
                    onClickItem.invoke(planet)
                }
            }
        }
    }
}