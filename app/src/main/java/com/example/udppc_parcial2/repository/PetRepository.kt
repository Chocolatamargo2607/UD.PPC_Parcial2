package com.example.udppc_parcial2.repository

import com.example.udppc_parcial2.dataManagement.PetDTO
import com.example.udppc_parcial2.network.PetsApi
import com.example.udppc_parcial2.network.RetrofitPet

class PetRepository {
    private val petsApi: PetsApi = RetrofitPet.petsApi

    private suspend fun listPets(name: String, orderBy: String = "breed"): List <PetDTO> {
        return petsApi.listPets()
    }

    suspend fun searchPets(name: String): List<PetDTO> {
        return listPets(name).filter { it.name.contains(name, ignoreCase = true) }
    }
}