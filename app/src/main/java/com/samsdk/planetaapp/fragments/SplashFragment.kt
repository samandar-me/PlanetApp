package com.samsdk.planetaapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.samsdk.planetaapp.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViews()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun initViews() {
        object : CountDownTimer(4000, 1000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                findNavController().navigate(R.id.action_splashFragment_to_planetFragment)
            }
        }.start()
    }
}