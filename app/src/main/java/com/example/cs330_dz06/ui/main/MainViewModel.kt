package com.example.cs330_dz06.ui.main

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.cs330_dz06.contentprovider.PredmetProvider
import com.example.cs330_dz06.data.model.Predmet
import dagger.hilt.android.internal.Contexts.getApplication


class MainViewModel :ViewModel() {



    fun getItemsList(context:Context): List<Predmet> {

        val predmeti = mutableListOf<Predmet>()
        val rs  = getApplication(context).contentResolver.query(PredmetProvider.URI_ITEM, arrayOf(
            PredmetProvider.KEY_ID,
            PredmetProvider.KEY_NAME,
            PredmetProvider.KEY_SIFRA,
            PredmetProvider.KEY_RAD
        ), null, null, null )



        while(rs!= null && rs.moveToNext()){
            val predmet = Predmet(
                rs.getInt(0),
                rs.getString(1),
                rs.getString(2),
                rs.getString(3))
            predmeti.add(predmet)
        }
        rs?.close()

        return predmeti


    }

    fun deletePredmet(predmet: Predmet, context: Context){

        getApplication(context).contentResolver.delete(
            PredmetProvider.URI_ITEM,
            "_id = ?",
            arrayOf(predmet.id.toString()))
    }

    fun updatePredmet(predmet: Predmet, context: Context): Int {

        return getApplication(context).contentResolver.update(
            PredmetProvider.URI_ITEM,
            createContentValuesPredmet(predmet),
            "_id = ?",
            arrayOf(predmet.id.toString())
        )
    }



    fun createContentValuesPredmet(predmet: Predmet): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(PredmetProvider.KEY_NAME, predmet.name)
        contentValues.put(PredmetProvider.KEY_SIFRA, predmet.sifra)
        contentValues.put(PredmetProvider.KEY_RAD, predmet.radSaStudentima)
        return contentValues
    }


}
