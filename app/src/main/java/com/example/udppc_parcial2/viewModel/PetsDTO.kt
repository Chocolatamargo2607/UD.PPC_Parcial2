package com.example.udppc_parcial2.viewModel

interface PetsDTO {
    fun save (pet: Pet)
    fun getAll(): List<Pet>
    fun getPet(name: String, breed: String): List<Pet>

}