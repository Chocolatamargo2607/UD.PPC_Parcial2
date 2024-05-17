package com.example.udppc_parcial2.dataManagement

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Helper(context: Context):

    SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION= 1
        private const val DATABASE_NAME= "Pets_Database"
        private const val TABLE_NAME= "Pets"
        private const val KEY_ID= "id"
        private const val KEY_NAME= "name"
        private const val KEY_TYPE= "type"
        private const val KEY_AGE= "age"
        private const val KEY_BREED= "breed"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE" + TABLE_NAME + "("
                + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + "TEXT, "
                + KEY_TYPE + "TEXT, "
                + KEY_AGE + "TEXT, "
                + KEY_BREED + "TEXT " + ")")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}