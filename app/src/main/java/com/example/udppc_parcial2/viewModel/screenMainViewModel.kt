package com.example.udppc_parcial2.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ScreenMainViewModel (private val context: Context, private val service: PetService): ViewModel(){
        private val _pets = MutableLiveData<List<Pet>>()
        val pets: LiveData<List<Pet>> get() = _pets
        init {
            fetchAllPets()
        }
        fun fetchAllPets() {
            viewModelScope.launch {
                try {
                    val petList = service.getAll()
                    _pets.value = petList
                    println("Se obtuvo lista!! ")
                } catch (e: Exception) {
                    println("Error al obtener la lista de mascotas: ${e.message}")
                    _pets.value = emptyList()
                }
            }
        }

        fun imprimir(petList: List<Pet>?){
            petList?.forEach{
                pet ->
                println("Name: ${pet.name}, Type: ${pet.type}, Age: ${pet.age}, Breed: ${pet.breed}, ${pet.image.toString()}")

            }
        }


    }
