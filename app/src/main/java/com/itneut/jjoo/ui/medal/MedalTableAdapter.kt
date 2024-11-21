package com.itneut.jjoo.ui.medal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.data.Country
import com.squareup.picasso.Picasso

class MedalTableAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<MedalTableAdapter.MedalViewHolder>() {
    class MedalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryFlag: ImageView = itemView.findViewById(R.id.ivFlag)
        val countryName: TextView = itemView.findViewById(R.id.tvCountryName)
        val tvMedals: TextView = itemView.findViewById(R.id.tvMedals)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medal_table, parent, false)
        return MedalViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedalViewHolder, position: Int) {
        val country = countries[position]
        holder.countryName.text = country.name
        holder.tvMedals.text = "🥇 Gold: ${country.goldMedals} 🥈 Silver: ${country.silverMedals} 🥉 Bronze: ${country.bronzeMedals}"

        Picasso.get()
            .load(country.flag)
            .into(holder.countryFlag)
    }

    override fun getItemCount(): Int = countries.size
}