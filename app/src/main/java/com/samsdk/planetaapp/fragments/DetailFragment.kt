package com.samsdk.planetaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.samsdk.planetaapp.R
import com.samsdk.planetaapp.databinding.FragmentDetailBinding
import com.samsdk.planetaapp.model.Planet

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var planet: Planet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        planet = arguments?.getParcelable("planet")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        binding.apply {
            Glide.with(requireActivity())
                .load(planet.imgUrl)
                .into(imageView)

            textTitle.text = planet.title
            textDesc.text = planet.desc
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}