package com.example.cs330_dz06.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cs330_dz06.R
import com.example.cs330_dz06.data.model.Predmet

class ItemAdapter(val context:Context, val items:List<Predmet>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.items_row,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.row_name.text = item.name
        holder.row_code.text = item.sifra
        holder.row_rad.text = item.radSaStudentima

        switchColor(position,holder)

        setClickListeners(holder, item)


    }

    override fun getItemCount(): Int {
       return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val row_name: TextView = view.findViewById<TextView>(R.id.row_name)
        val row_code: TextView = view.findViewById<TextView>(R.id.row_code)
        val row_rad :TextView = view.findViewById<TextView>(R.id.row_rad)
        val row_delete: Button = view.findViewById<Button>(R.id.row_delete)
        val row_edit: Button = view.findViewById<Button>(R.id.row_edit)
        val row_wrapper: LinearLayout = view.findViewById<LinearLayout>(R.id.row_wrapper)

    }

    private fun switchColor(position: Int, holder: ViewHolder){

        if(position%2==0){
            holder.row_wrapper.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.light_purple
                )
            )
        }
        else{
            holder.row_wrapper.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
    }

    private fun setClickListeners(viewHolder: ViewHolder, item:Predmet){
        viewHolder.row_delete.setOnClickListener { view->
            if(context is MainActivity){
                context.delete(item)
                context.setupListofDataIntoRecyclerView()
            }
        }
        viewHolder.row_edit.setOnClickListener {

            if(context is MainActivity){

                context.updateRecordDialog(item)
            }
        }



    }
    }


