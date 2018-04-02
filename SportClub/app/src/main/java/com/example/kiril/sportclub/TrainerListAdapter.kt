package com.example.kiril.sportclub

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class TrainerListAdapter(private val trainers: List<String>) : RecyclerView.Adapter<TrainerListAdapter.TrainerListViewHolder>()
{
    class TrainerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trainerFullNameTV: TextView = view.findViewById<View>(R.id.trainer_full_name) as TextView
        val trainerTelephoneTV: TextView = view.findViewById<View>(R.id.trainer_telephone_text_view) as TextView
    }

    override fun getItemCount() = trainers.size

    override fun onBindViewHolder(holder: TrainerListViewHolder, position: Int) {
        holder.trainerFullNameTV.text = trainers[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TrainerListAdapter.TrainerListViewHolder{
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.trainer_info_recycler_view, parent, false)
        return TrainerListViewHolder(inflatedView)
    }
}