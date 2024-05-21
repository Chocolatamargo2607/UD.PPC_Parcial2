package com.example.udppc_parcial2.viewModel


import com.example.udppc_parcial2.network.PetsApi
import com.example.udppc_parcial2.network.RetrofitPet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PetService: PetsDTO {
    override fun save(pet: Pet) {
        GlobalScope.launch {
            val image = File(pet.image?.path ?: "")
            if (image.exists()) {
                val imagePart = MultipartBody.Part.createFormData(
                    "image",
                    image.name,
                    image.asRequestBody("image/*".toMediaTypeOrNull())
                )
                val namePet = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.name)
                val typePet = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.type)
                val agePet = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.age.toString())
                val breedPet = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.breed)

                try {
                    val respuesta = RetrofitPet.petsApi.save(
                        imagePart,
                        namePet,
                        typePet,
                        agePet,
                        breedPet
                    )
                    println("Guardado exitosamente: $respuesta")
                } catch (e: Exception) {
                    println("Error en guardar: ${e.message}")
                }
            } else {
                println("Imagen no encontrada")
            }
        }
    }

    override fun getAll(): List<Pet> {
        return runBlocking {
            try {
                val respuesta = RetrofitPet.petsApi.getAll()
                println("Mascotas obtenidas: $respuesta")
                respuesta
            } catch (e: Exception) {
                println("Error al obtener mascotas: ${e.message}")
                emptyList()
            }
        }
    }

    override fun getPet(name: String, breed: String): List<Pet> {
        // Implementar si es necesario
        return emptyList()
    }
}