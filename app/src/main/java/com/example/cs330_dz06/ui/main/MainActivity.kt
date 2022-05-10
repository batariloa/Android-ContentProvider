package com.example.cs330_dz06.ui.main

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs330_dz06.R
import com.example.cs330_dz06.contentprovider.PredmetProvider
import com.example.cs330_dz06.data.model.Predmet
import com.example.cs330_dz06.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val viewModel:MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        setupListofDataIntoRecyclerView()



        binding.btnAddExam.setOnClickListener {

            if (binding.etNameAdd.text.isNotEmpty() && binding.etSifraAdd.text.isNotEmpty()) {
                val predmet = Predmet(
                    1,
                    binding.etNameAdd.text.toString(),
                    binding.etSifraAdd.text.toString(),
                    binding.etRad.text.toString()
                )

             contentResolver.insert(PredmetProvider.URI_ITEM, viewModel.createContentValuesPredmet(predmet))
                setupListofDataIntoRecyclerView()
            }


        }


    }
     fun setupListofDataIntoRecyclerView() {

        val itemsList = binding.rvItems
        if (viewModel.getItemsList(applicationContext).isNotEmpty()) {

           itemsList.visibility = View.VISIBLE

            // Set the LayoutManager that this RecyclerView will use.
            itemsList.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = ItemAdapter(this, viewModel.getItemsList(applicationContext))
            // adapter instance is set to the recyclerview to inflate the items.
            itemsList.adapter = itemAdapter
        } else {

            itemsList.visibility = View.GONE

        }
    }
    fun updateRecordDialog(predmet: Predmet) {
        val updateDialog = Dialog(this)
        updateDialog.setCancelable(false)
        //Set the screen content from a layout resource.

        updateDialog.setContentView(R.layout.dialog_update)

        updateDialog.findViewById<EditText>(R.id.et_name).setText(predmet.name)
        updateDialog.findViewById<EditText>(R.id.edit_code).setText(predmet.sifra)
        updateDialog.findViewById<Button>(R.id.btn_accept_edit).setOnClickListener{

            val name = updateDialog.findViewById<EditText>(R.id.et_name).text.toString()
            val sifra = updateDialog.findViewById<EditText>(R.id.edit_code).text.toString()
            val rad = updateDialog.findViewById<EditText>(R.id.edit_rad).text.toString()



            if (name.isNotEmpty() && sifra.isNotEmpty() && rad.isNotEmpty()) {

                 if(viewModel.updatePredmet(Predmet(predmet.id,name, sifra, rad),applicationContext)>-1)

                    updateDialog.dismiss() // Dialog will be dismissed
                    setupListofDataIntoRecyclerView()

            } else {
                Toast.makeText(
                    applicationContext,
                    "Name or exam code cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        updateDialog.findViewById<Button>(R.id.btn_cancel_edit).setOnClickListener {
            updateDialog.dismiss()
        }
        //Start the dialog and display it on screen.
        updateDialog.show()
    }
    fun delete(predmet: Predmet){
        viewModel.deletePredmet(predmet,applicationContext)
        setupListofDataIntoRecyclerView()
    }


}