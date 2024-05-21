package com.example.udppc_parcial2.viewModel.appNavegation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udppc_parcial2.dataManagement.PetDTO
import com.example.udppc_parcial2.network.PetsApi
import com.example.udppc_parcial2.network.RetrofitPet
import com.example.udppc_parcial2.repository.PetRepository
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {
    private val petsApi = RetrofitPet.petsApi
    private val petRepository = PetRepository()

    fun searchPets(name: String, orderBy: String = "breed", onResult: (List<PetDTO>) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val pets = petRepository.searchPets(name)
                onResult(pets)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
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