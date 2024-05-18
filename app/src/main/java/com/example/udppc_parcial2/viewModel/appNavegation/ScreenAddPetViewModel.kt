package com.example.udppc_parcial2.viewModel.appNavegation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URI

class ScreenAddPetViewModel ():ViewModel(){
    private val _type = MutableLiveData<String>()
    val type: LiveData<String> get() = _type

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> get() = _age

    private val _breed = MutableLiveData<String>()
    val breed: LiveData<String> get() = _breed

    private val _image = MutableLiveData<Uri?>()
    val image: LiveData<Uri?> get() = _image

    fun setType(type: String) {
        _type.value = type
    }
    fun setName(name: String) {
        _name.value = name
    }
    fun setAge(age: Int) {
        _age.value = age
    }
    fun setBreed(breed: String) {
        _breed.value = breed
    }
    fun setImage(image: Uri?) {
        _image.value = image
    }

    fun imprimir(){
        println(_type.value)
        println(_name.value)
        println(_age.value)
        println(_breed.value)
        println(_image.value)
    }

}