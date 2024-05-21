package com.example.udppc_parcial2.network

import android.media.Image
import com.example.udppc_parcial2.viewModel.Pet
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PetsApi{
    @GET("pets")
    suspend fun getAll(): List<Pet>
    @Multipart
    @POST("pets")
    suspend fun save(
        @Part image: MultipartBody.Part,
        @Part ("name") name: RequestBody,
        @Part ("type") type: RequestBody,
        @Part ("age") age: RequestBody,
        @Part ("breed") breed: RequestBody
    )
    @GET("pets_1")
    suspend fun getPet(name: String, breed: String):List<Pet>
}