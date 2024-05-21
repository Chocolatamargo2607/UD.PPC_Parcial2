package com.example.udppc_parcial2.viewModel.appNavegation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udppc_parcial2.network.RetrofitPet
import kotlinx.coroutines.launch


class PetViewModel : ViewModel() {
    private val petsApi = RetrofitPet.petsApi
    fun fetchPets(onResult: (List<Pet>) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val pets = petsApi.getAll()
                onResult(pets)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}