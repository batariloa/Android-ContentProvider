package com.example.cs330_dz06.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.cs330_dz06.data.db.DatabaseHandler
import com.example.cs330_dz06.data.model.Predmet
import javax.inject.Inject


class PredmetProvider @Inject constructor()
    : ContentProvider() {
    companion object{
        val AUTHORITY = "com.example.cs330_dz06"
      val TABLE_NAME = Predmet::class.java.simpleName
       val URI_ITEM = Uri.parse("content://$AUTHORITY/$TABLE_NAME")



        private const val DATABASE_NAME = "Konsultacije"
        private const val DATABASE_VERSION = 4
        private const val TABLE_PREDMETI = "PredmetiTable"

      const val KEY_ID = "_id"
       const val KEY_NAME = "naziv_predmeta"
       const val KEY_SIFRA = "sifra"
        const val KEY_RAD = "rad_sa_studentima"
    }
    lateinit var db:SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = DatabaseHandler(context)
        db = helper.writableDatabase

    return true
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query(TABLE_PREDMETI, cols,condition, condition_val, null, null, order)
    }

    override fun getType(uri: Uri): String? {

        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun insert(uri: Uri, p1: ContentValues?): Uri {

        db.insert(TABLE_PREDMETI, null, p1)
        context?.contentResolver?.notifyChange(uri,null)
        return uri
        }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.delete(TABLE_PREDMETI, condition, condition_val)
        context?.contentResolver?.notifyChange(uri,null)
        return count

    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
       val count =  db.update(TABLE_PREDMETI, cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri,null)
        return count
    }




}