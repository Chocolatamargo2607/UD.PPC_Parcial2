package com.example.udppc_parcial2.repository

import com.example.udppc_parcial2.viewModel.appNavegation.Pet

interface PostPetsRepository {
    fun save (pet: Pet)
}