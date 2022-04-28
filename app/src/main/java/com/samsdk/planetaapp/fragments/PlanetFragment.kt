package com.samsdk.planetaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsdk.planetaapp.R
import com.samsdk.planetaapp.adapter.PlanetAdapter
import com.samsdk.planetaapp.databinding.FragmentPlanetBinding
import com.samsdk.planetaapp.viewmodel.MainViewModel

class PlanetFragment : Fragment() {

    private lateinit var planetAdapter: PlanetAdapter
    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: FragmentPlanetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        planetAdapter = PlanetAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = planetAdapter
        }
        mainViewModel.getPlanetsList().observe(viewLifecycleOwner) {
            planetAdapter.submitList(it)
            binding.progressBar.isVisible = false
        }
        planetAdapter.onClickItem = {
            val navController =
                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
            val bundle = Bundle()
            bundle.putParcelable("planet", it)
            navController.navigate(R.id.action_planetFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}