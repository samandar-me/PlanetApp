package com.samsdk.planetaapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsdk.planetaapp.model.Planet
import com.samsdk.planetaapp.repository.FirebaseRepository

class MainViewModel : ViewModel() {

    private val firebaseRepository = FirebaseRepository()
    private val planetsList: MutableLiveData<List<Planet>> by lazy {
        MutableLiveData<List<Planet>>().also {
            loadPlanetsData()
        }
    }

    fun getPlanetsList(): LiveData<List<Planet>> {
        return planetsList
    }

    private fun loadPlanetsData() {
        firebaseRepository.queryPlanets().addOnCompleteListener {
            if (it.isSuccessful) {
                val result = it.result
                if (!result.isEmpty) {
                    if (planetsList.value == null) {
                        planetsList.value = result.toObjects(Planet::class.java)
                    } else {
                        planetsList.value =
                            planetsList.value!!.plus(result.toObjects(Planet::class.java))
                    }
                }
            } else {
                Log.d("MainViewModel", "${it.exception!!.message}")
            }
        }
    }
}