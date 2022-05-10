package com.example.cs330_dz06.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context?) :
SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object {
        private const val DATABASE_NAME = "Konsultacije"
        private const val DATABASE_VERSION = 4
        private const val TABLE_PREDMETI = "PredmetiTable"

        private const val KEY_ID = "_id"
        private const val KEY_NAME = "naziv_predmeta"
        private const val KEY_SIFRA = "sifra"
        private const val KEY_RAD = "rad_sa_studentima"
    }

    override fun onCreate(db: SQLiteDatabase?) {
    //create table with fields
        Log.d("DB", "YES CREATED")

        db?.execSQL("CREATE TABLE $TABLE_PREDMETI  ($KEY_ID INTEGER PRIMARY KEY NOT NULL, $KEY_SIFRA TEXT, $KEY_NAME TEXT, $KEY_RAD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PREDMETI")
    }



}
