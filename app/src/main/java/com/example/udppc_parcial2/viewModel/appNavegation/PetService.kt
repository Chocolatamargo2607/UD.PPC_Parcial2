package com.example.udppc_parcial2.viewModel.appNavegation

import com.example.udppc_parcial2.dataManagement.PetDTO
import com.example.udppc_parcial2.network.PetsApi
import com.example.udppc_parcial2.network.RetrofitPet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PetService: PetsDTO{
    override fun save(pet: Pet) {
        GlobalScope.launch {
            val image= File(pet.image?.path?: "")
            if (image.exists()){
                val imagepart =  MultipartBody.Part.createFormData("image",image.name,image.asRequestBody("image/*".toMediaTypeOrNull()))
                val namepet= RequestBody.create("text/plain".toMediaTypeOrNull(),pet.name)
                val typepet= RequestBody.create("text/plain".toMediaTypeOrNull(),pet.type)
                val agepet= RequestBody.create("text/plain".toMediaTypeOrNull(),pet.age.toString())
                val breedpet= RequestBody.create("text/plain".toMediaTypeOrNull(),pet.breed)
                try {
                    val respuesta= RetrofitPet.petsApi.save(
                        imagepart,
                        namepet,
                        typepet,
                        agepet,
                        breedpet
                    )

                }catch (e: Exception){
                    println("Error en guarda"+e.message)
                }
            }else{
                println("Imagen no encontrada")
            }
        }
    }

    override fun getAll(): List<Pet> {
        TODO("Not yet implemented")
    }

    override fun getPet(name: String, breed: String): List<Pet> {
        TODO("Not yet implemented")
    }


}