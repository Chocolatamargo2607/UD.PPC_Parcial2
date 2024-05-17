package com.example.udppc_parcial2.dataManagement

import android.annotation.SuppressLint
import android.content.ContentValues


class PetsService(private val dbHelper:Helper){

    @SuppressLint("Range")
    fun getAllPets():List<PetDTO>{
        val db = dbHelper.readableDatabase
        val cursor = db.query("Pets",null,null,null,null,null,null)
        val pets = mutableListOf<PetDTO>()

        if(cursor.moveToFirst()){
            do{
                val id= cursor.getInt(cursor.getColumnIndex("id"))
                val name= cursor.getString(cursor.getColumnIndex("name"))
                val type= cursor.getString(cursor.getColumnIndex("type"))
                val age= cursor.getString(cursor.getColumnIndex("age"))
                val breed= cursor.getString(cursor.getColumnIndex("breed"))
                val pet= PetDTO(id,name,type,age,breed)
                pets.add(pet)
            }   while (cursor.moveToNext())
        }
        cursor.close()
        return pets
    }

    fun save(pet: PetDTO):Long{
        val db= dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", pet.name)
            put("type", pet.type)
            put("age",pet.age)
            put("breed", pet.breed)
        }
        return db.insert("Pets",null,values)
    }

    fun delete(id: Int){
        val db = dbHelper.readableDatabase
       db.delete("Pets", "id=?", arrayOf(id.toString()))
    }
    fun findByName(name: String): PetDTO?{
        val db = dbHelper.readableDatabase
        val projection = arrayOf("id", "name", "type", "age", "breed")
        val selection = "name= ?"
        val selectionArgs = arrayOf(name)
        val sortOrder = "name DESC"

        val cursor = db.query(
            "Pets",
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        with(cursor){
            while (moveToNext()){
                val itemId= getLong(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                val type = getString(getColumnIndexOrThrow("type"))
                val age = getString(getColumnIndexOrThrow("age"))
                val breed = getString(getColumnIndexOrThrow("breed"))
                return PetDTO(itemId.toInt(),name,type,age,breed)
            }
        }
        return null
    }
}