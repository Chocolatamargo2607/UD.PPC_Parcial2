package com.example.udppc_parcial2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udppc_parcial2.dataManagement.PetDTO
import com.example.udppc_parcial2.network.PetsApi
import com.example.udppc_parcial2.network.RetrofitPet
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {
    private val petsApi = RetrofitPet.petsApi
    fun fetchPets(onResult: (List<PetDTO>) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val pets = petsApi.listPets()
                onResult(pets)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}