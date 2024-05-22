package com.example.udppc_parcial2.viewModel.appNavegation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udppc_parcial2.network.RetrofitPet
import com.example.udppc_parcial2.repository.GetPetRepository
import kotlinx.coroutines.launch

class ScreenMainViewModel : ViewModel() {
    private val petsApi = RetrofitPet.petsApi
    private val getPetRepository = GetPetRepository()

    fun searchPets(name: String, orderBy: String = "breed", onResult: (List<PetDTO>) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val pets = getPetRepository.searchPets(name)
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